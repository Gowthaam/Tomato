package com;

public class OrderDetails {

	 String item;
	 int quantity;
	 int price;
	 
	 public OrderDetails()
	 {}
	 
	 public OrderDetails(String item,int quantity,int price)
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