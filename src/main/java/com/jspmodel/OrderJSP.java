package com.jspmodel;

import java.util.*;

import com.model.Order;

/**
 * This is a formatter class that can be used to display all the orders placed by a
 * user in the readable format.
 * @author gowtham
 *
 */
public class OrderJSP {

	public int orderid;
	public String hname;
	public int total;
	public List<Order> items;
	
	public OrderJSP()
	{}
	
	public OrderJSP(int orderid,List<Order> items)
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
