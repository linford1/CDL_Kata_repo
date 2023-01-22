package com.cdlkata.Model;

import java.util.Objects;

public class LineItem {
	private String itemID;
	private int quantity;
	private double price;
	private String itemName;
	
	public String getItemName() {
		return itemName;
	}

	public LineItem(String itemID) { 
		this(itemID,1);
	}
	 
	public LineItem(String itemID, int quantity) { 
		this.itemID = itemID;
		this.quantity = quantity;
	}
	
	public String getItemID() {
		return itemID;
	} 
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	public void setPrice(double price) {
		this.price = price;
	}
 
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		return Objects.equals(itemID, other.itemID);
	}

	@Override
	public String toString() {
		// This can be improved
		return super.toString();
	}
	
	public void reduceQuantityBy(int quantityReducedBy) {
		// TODO Auto-generated method stub
		this.quantity -= quantityReducedBy;
	}
	
	public void increaseQuanitity(int quantityIncreasedBy) {
		// TODO Auto-generated method stub
		this.quantity += quantityIncreasedBy;
	}
	
	public double totalPrice()
	{
		return price * quantity;
	}
	
}
