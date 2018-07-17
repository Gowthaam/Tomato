package com.jspmodel;

/**
 * It's used to retrieve the order details from the JSP page.
 * @author gowtham
 *
 */
public class OrderDetailsJSP {

	public String item;
	public int quantity;
	public int price;
	 
	 public OrderDetailsJSP()
	 {}
	 
	 public OrderDetailsJSP(String item,int quantity,int price)
	 {
		 this.item=item;
		 this.quantity=quantity;
		 this.price=price;
	 }
	
	 public String getItem()
	 {
		 return item;
	 }
	 
	 public void setItem(String item)
	 {
		 this.item=item;
	 }
	 
	 public int getQuantity()
	 {
		 return quantity;
	 }
	 
	 public void setQuantity(int quantity)
	 {
		 this.quantity=quantity;
	 }
	 
	 public int getPrice()
		{
			return price;
		}
		
		public void setPrice(int price)
		{
			this.price=price;
		}
}