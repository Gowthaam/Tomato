package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="orderid")
	private int orderid;
	@Column(name="uname")
	private String uname;
	@Column(name="hname")
	private String hname;
	@Column(name="item")
	private String item;
	@Column(name="price")
	private int price;
	@Column(name="quantity")
	private int quantity;
	@Column(name="total")
	private int total;
	
	
	public Order()
	{
		//for Storing the orders
	}
	
	public Order(int orderid,String uname,String hname,String item,int price,int quantity,int total)
	{
		this.orderid=orderid;
		this.uname=uname;
		this.hname=hname;
		this.item=item;
		this.price=price;
		this.quantity=quantity;
		this.total=total;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(Object id)
	{
		this.id=(int)id;
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
		this.quantity=(int)quantity;
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
