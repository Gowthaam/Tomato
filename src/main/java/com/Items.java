package com;

public class Items {

	String item;
	int price;
	
	public Items()
	{}
	
	public Items(String item,int price)
	{
		this.item=item;
		this.price=price;
	}
	
	public String getItem()
	{
		return item;
	}
	
	public void setName(String item)
	{
		this.item=item;
	}
	
	public int getprice()
	{
		return price;
	}
	
	public void setPrice(int price)
	{
		this.price=price;
	}
}
