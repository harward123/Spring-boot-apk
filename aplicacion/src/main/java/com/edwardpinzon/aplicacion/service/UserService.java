package com.edwardpinzon.aplicacion.service;

import javax.validation.Valid;

import com.edwardpinzon.aplicacion.Exception.UserNameOrldNotFound;
import com.edwardpinzon.aplicacion.dto.ChangePasswordForm;
import com.edwardpinzon.aplicacion.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();

	public User createUser(User formUser) throws Exception;

	public User getUserById(long id) throws Exception;

	public User updateUser(User user) throws Exception;

	public void deleteUser(Long id) throws UserNameOrldNotFound;

	public User changePassword(ChangePasswordForm form) throws Exception;
}
