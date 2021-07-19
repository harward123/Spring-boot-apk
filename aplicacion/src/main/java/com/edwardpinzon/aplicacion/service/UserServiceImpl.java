package com.edwardpinzon.aplicacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwardpinzon.aplicacion.entity.User;
import com.edwardpinzon.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
    @Autowired
	UserRepository repository;
	
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
		if(user.getConfirmPassword()==null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("confirm password es obligatorio");
		}
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}


	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(long id) throws Exception {
		
		return repository.findById(id).orElseThrow(() -> new Exception("Elsusuario para editar no existe."));
	}

	@Override
	public User updateUser(User user) throws Exception {
		User toUser = getUserById(user.getId());
		mapUser(user, toUser);
		return repository.save(toUser);
	}
	/**
	 * Ponemos todo menos el passworxd.
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

	}
	
