package com.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UsersRepository;

import errorhandling.CustomException;

import java.util.*;
@Component
public class CustomAuth implements AuthenticationProvider{
public static String username = new String();
	
@Autowired
UsersRepository usersrepository;

	@Override
	public Authentication authenticate(Authentication auth) throws  AuthenticationException {
		// TODO Auto-generated method stub
		 username = auth.getName();
		String pass = auth.getCredentials().toString();
		String password=new String();
		
		if(usersrepository.findByUname(username).isEmpty()==false)
		{
			List<User> list = usersrepository.findByUname(username);	
		for(User u:list)
		 password = u.getPassword(); 
		if(password.equals(pass))
			return new UsernamePasswordAuthenticationToken(username,pass,Collections.EMPTY_LIST);
		else
			throw new BadCredentialsException("authentication failed");
		
		}
		else
			throw new BadCredentialsException("authentication failed");
	}

	@Override
	public boolean supports(Class<?> auth) {
		// TODO Auto-generated method stub
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
