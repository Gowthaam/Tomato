package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="hotels")
public class Hotel {

	@Id
	@Column(name="hname")
	private String hname;
	@Column(name="location")
	private String location;
	
	public Hotel()
	{// class for getting all the hotels available in a location
	}
	
	
	
	public String getHname()
	{
		return hname;
	}
	public void setHname(Object hname)
	{
		this.hname=(String)hname;
	}

	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(Object location)
	{
		this.location=(String)location;
	}

	
}
