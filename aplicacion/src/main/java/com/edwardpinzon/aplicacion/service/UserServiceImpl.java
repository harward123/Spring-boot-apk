package com.edwardpinzon.aplicacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edwardpinzon.aplicacion.Exception.UserNameOrldNotFound;
import com.edwardpinzon.aplicacion.dto.ChangePasswordForm;
import com.edwardpinzon.aplicacion.entity.User;
import com.edwardpinzon.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("confirm password es obligatorio");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(long id) throws UserNameOrldNotFound {

		return repository.findById(id).orElseThrow(() -> new UserNameOrldNotFound("El id de usuario  no existe."));
	}

	@Override
	public User updateUser(User user) throws Exception {
		User toUser = getUserById(user.getId());
		mapUser(user, toUser);
		return repository.save(toUser);
	}

	/**
	 * Ponemos todo menos el passworxd.
	 * 
	 * @param from
	 * @param to
	 */

	private void mapUser(User from, User to) {
		// TODO Auto-generated method stub
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}

	@Override
	public void deleteUser(Long id) throws UserNameOrldNotFound {

		User user = getUserById(id);
		repository.delete(user);

	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user = getUserById(form.getId());
		
		
		if ( !isLoggedUserADMIN() && !user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception ("Current Password invalido.");
		}

		if (user.getPassword().equals(form.getNewPassword())) {
			throw new Exception("Nuevo debe ser diferente al password actual.");
		}
		if (!form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception("nuevo passwo0rd y current password no coinciden.");
		}

		String encodePasswork = bCryptPasswordEncoder.encode(form.getNewPassword());

		user.setPassword(encodePasswork);
		return repository.save(user);
	}



	public boolean isLoggedUserADMIN() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		Object roles = null; 
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		
			roles = loggedUser.getAuthorities().stream()
					.filter(x -> "ROLE_ADMIN".equals(x.getAuthority())).findFirst()
					.orElse(null);
		}
		return roles != null ?true :false;
	}
}
