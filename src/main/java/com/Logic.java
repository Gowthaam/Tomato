package com;

import java.util.List;


import com.model.User;
import com.repository.UsersRepository;

public class Logic {

	
public String forgetReply(User inp,UsersRepository usersrepository)
{
	System.out.println(inp.getUname()); 
	List<User> l= usersrepository.findByUname(inp.getUname());
	System.out.println(l.size());
	if(usersrepository.findByUname(inp.getUname()).isEmpty())
	{
			return "Username not found ! Please Register";
	}
else
	{
	return "Hi"+inp.getUname().toUpperCase()+"! Password has been sent to your registered Mail.";
	}	
	
}
	

public String addUser(User inp, UsersRepository usersrepository)
{
	
	User user = new User();
	user.setUname(inp.getUname());
	user.setPassword(inp.getPassword());
	if(usersrepository.findByUname(inp.getUname()).isEmpty())
	{
		
		usersrepository.save(user);		
		return "login";
	}
	else
	{
			return "register";
	}
}
	



}
