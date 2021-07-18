package com.edwardpinzon.aplicacion.service;

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


	}
	
