package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
	{}
	
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
