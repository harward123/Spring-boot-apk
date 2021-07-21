package com.edwardpinzon.aplicacion.service;


import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edwardpinzon.aplicacion.entity.Role;
import com.edwardpinzon.aplicacion.repository.UserRepository;


@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	
	
	@Autowired
    UserRepository   userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
			com.edwardpinzon.aplicacion.entity.User appUser = userRepository.findByUsername(username).orElseThrow(()-> new 
					UsernameNotFoundException("login usernam invalido."));
			
			Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
			for (Role role: appUser.getRoles()) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
	            grantList.add(grantedAuthority);
			}
			UserDetails user = (UserDetails) new User(username,appUser.getPassword(),grantList);

			return user;
		}


}
