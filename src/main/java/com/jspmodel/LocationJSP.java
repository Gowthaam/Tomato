package com.jspmodel;

/**
 * This class is used to fetch the location selected by the user in the JSP page.
 * @author gowtham
 *
 */
public class LocationJSP
{
String name;
public LocationJSP()
{}

public LocationJSP(String name)
{
super();
this.name=name;
}

public String getName()
{
return this.name;	
}

public void setName(String name)
{
this.name=name;	
}


}