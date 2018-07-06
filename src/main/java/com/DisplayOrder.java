package com;

import java.util.*;

public class DisplayOrder {

	int orderid;
	String hname;
	int total;
	ArrayList<OrderClone> items;
	
	public DisplayOrder()
	{}
	
	public DisplayOrder(int orderid,ArrayList<OrderClone> items)
	{
		orderid=this.orderid;
		items=this.items;
	}
	
	public int getOrderid()
	{
		return orderid;
	}
	
	public void setOrderid(int orderid)
	{
		this.orderid=orderid;
	}
	
	
	public String getHname()
	{
		return hname;
	}
	
	public void setHname(String hname)
	{
		this.hname=hname;
	}
	
	public ArrayList<OrderClone> getItems()
	{
		return items;
	}
	
	public void setItems(ArrayList<OrderClone> items)
	{
		this.items=items;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total=total;
	}
	
	
}
