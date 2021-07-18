package com.edwardpinzon.aplicacion.service;



import javax.validation.Valid;

import com.edwardpinzon.aplicacion.entity.User;

public interface UserService {
	
	
	public Iterable<User> getAllUsers();

	public User createUser(User  formUser) throws Exception;
	

}
