package com.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UsersRepository;


import java.util.*;
@Component
public class CustomAuth implements AuthenticationProvider{
  String username ;
	
@Autowired
UsersRepository usersrepository;

	@Override
	public Authentication authenticate(Authentication auth)  {
		 username = auth.getName();
		String pass = auth.getCredentials().toString();
		
		
		if(!usersrepository.findByUname(username).isEmpty())
		{
			List<User> list = usersrepository.findByUname(username);	
			String password=null;
			for(User u:list)
		 password = u.getPassword(); 
		if(password!=null&&password.equals(pass))
			return new UsernamePasswordAuthenticationToken(username,pass,Collections.emptyList());
		else
			throw new BadCredentialsException("authentication failed");
		
		}
		else
			throw new BadCredentialsException("authentication failed");
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
