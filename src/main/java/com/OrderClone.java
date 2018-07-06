package com;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class OrderClone {

	 int orderid;
	 String uname;
	 String hname;
	String item;
	 int price;
	 int quantity;
	 int total;
	
	
	public OrderClone()
	{}

	public OrderClone(int orderid,String uname,String hname,String item,int price,int quantity,int total)
	{
		this.orderid=orderid;
		this.uname=uname;
		this.hname=hname;
		this.item=item;
		this.price=price;
		this.quantity=quantity;
		this.total=total;
	}
	
	public int getOrderid()
	{
		return orderid;
	}
	
	public void setOrderid(Object orderid)
	{
		this.orderid=(int)orderid;
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
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(Object quantity)
	{
		this.total=(int)quantity;
	}
	
	public int getTotal()
	{
		return total;
	}
	public void setTotal(Object total)
	{
		this.total=(int)total;
	}
	
}
