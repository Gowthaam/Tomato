package com;

import java.util.List;


import com.model.User;
import com.repository.UsersRepository;

/**
 * This class consists of the Implementations of some methods that have been refactored
 * for the purpose of Unit testing.
 * @author gowtham
 *
 */
public class RefactoredImplementation {

/**
 * It checks if the entered userName exists and generates response accordingly.	
 * @param inp, it takes user details in the form of User object.
 * @param usersrepository, It access the users table in database.
 * @return , resets password if user exists else asks user to register.
 */
public String forgetPasswordReplyImplementation(User inp,UsersRepository usersrepository)
{ 
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
	
/**
 * Its consists of the business logic of adding a new user to the database
 * @param inp, the user details are passed using the User object.
 * @param usersrepository, Its used to add the new user to the users table in database.
 * @return, it returns login if userName is not already registered else returns register page.
 */
public String addUserImplementation(User inp, UsersRepository usersrepository)
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
