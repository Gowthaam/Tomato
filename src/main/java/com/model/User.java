package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
