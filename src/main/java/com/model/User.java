package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity class is a model for users table that consists of all the users and
 * their passwords.
 * @author gowtham
 *
 */
@Entity
@Table(name="users")
public class User {
	
	
	@Id
	@Column(name="uname")
	private String uname;
	@Column(name="password")
	private String password;
	
	public User()
	{
		//for registring the users.
	}
	
	
	public String getUname()
	{
		return uname;
	}
	public void setUname(Object uname)
	{
		this.uname=(String)uname;
	}

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(Object password)
	{
		this.password=(String)password;
	}
}
