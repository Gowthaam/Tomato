package com.jspmodel;

/**
 * This page is used to get the rating and review entered by the user from the
 * JSP page.
 * @author gowtham
 *
 */
public class RatingJSP {

	String username;
	int rating;
	String review;
	
	public RatingJSP()
	{}
	
	public RatingJSP(String username,int rating,String review)
	{
		this.username=username;
		this.rating=rating;
		this.review=review;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	
	public int getRating()
	{
		return rating;
	}
	
	public void setRating(int rating)
	{
		this.rating=rating;
	}
	
	public String getReview()
	{
		return review;
	}
	
	public void setReview(String review)
	{
		this.review=review;
	}
	
	
}