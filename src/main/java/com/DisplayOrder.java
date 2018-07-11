package com;

import java.util.*;

import com.model.Order;

public class DisplayOrder {

	int orderid;
	String hname;
	int total;
	List<Order> items;
	
	public DisplayOrder()
	{}
	
	public DisplayOrder(int orderid,List<Order> items)
	{
		this.orderid=orderid;
		this.items=items;
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
	
	public List<Order> getItems()
	{
		return items;
	}
	
	public void setItems(List<Order> items)
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
