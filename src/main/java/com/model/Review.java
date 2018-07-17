package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity class is a model for the reviews table that consists of all the reviews
 * given by the users to various restaurants.
 * @author gowtham
 *
 */

@Entity
@Table(name="reviews")
public class Review {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	 int id;
	@Column(name="uname")
	 String uname;
	@Column(name="hname")
	 String hname;
	@Column(name="rating")
	 int rating;
	@Column(name="review")
	 String review;
	
	
	public Review()
	{
		//for storing the ratings and reviews
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(Object id)
	{
		this.id=(int)id;
	}
	
	
	public String getUname()
	{
		return uname;
	}
	public void setUname(Object uname)
	{
		this.uname=(String)uname;
	}

	
	public String getHname()
	{
		return hname;
	}
	public void setHname(Object hname)
	{
		this.hname=(String)hname;
	}
	
	public String getReview()
	{
		return review;
	}
	
	public void setReview(Object review)
	{
		this.review=(String)review;
	}

	public int getRating()
	{
		return rating;
	}
	public void setRating(Object rating)
	{
		this.rating=(int)rating;
	}
	
	
}
