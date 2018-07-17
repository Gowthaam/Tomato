package com.jspmodel;

/**
 * This is a reader class that is used to get the data of the hotel selected 
 *by the user from the JSP page.
 * @author gowtham
 *
 */
public class HotelJSP {

	String id ;
	String name; 
	String location ;
	
	public HotelJSP()
	{}
	
	public HotelJSP(String id,String name,String location)
	{
		this.id=id;
		this.name=name;
		this.location=location;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location=location;
	}
	
}

