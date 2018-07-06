package com;
import java.util.*;

public class Invoice {

	int id;
	String hotelname;
	int total;
	ArrayList<OrderDetails> items;
	
	public Invoice()
	{}
	
	public Invoice(int id,String hotelname,int total,ArrayList<OrderDetails> items)
	{
		this.id=id;
		this.hotelname=hotelname;
		this.total=total;
		this.items=items;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getHotelname()
	{
		return hotelname;
	}
	
	public void setHotelname(String hotelname)
	{
		this.hotelname=hotelname;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total=total;
	}
	
	public ArrayList<OrderDetails> getItems()
	{
		return items;
	}
	
	public void setItems(ArrayList<OrderDetails> items)
	{
		this.items=items;
	}
	
	
}
