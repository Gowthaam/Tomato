package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity class is model for the items table that has list of all the food
 * items available in the application,their restaurant, and price.
 * @author gowtham
 *
 */

@Entity
@Table(name="items")
public class Item {

	@Id
	@Column(name="id")
	private int id;
	@Column(name="hname")
	private String hname;
	@Column(name="item")
	private String item;
	@Column(name="price")
	private int price;
	
	
	public Item()
	{
		//For getting the menu of a hotel
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(Object id)
	{
		this.id=(int)id;
	}
	
	public String getHname()
	{
		return hname;
	}
	public void setHname(Object hname)
	{
		this.hname=(String)hname;
	}

	public String getItem()
	{
		return item;
	}
	
	public void setItem(Object item)
	{
		this.item=(String)item;
	}

	public int getPrice()
	{
		return price;
	}
	public void setPrice(Object price)
	{
		this.price=(int)price;
	}
	
}
