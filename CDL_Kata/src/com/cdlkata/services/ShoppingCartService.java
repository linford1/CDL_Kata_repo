package com.cdlkata.services;
 
import java.util.HashMap; 
import java.util.Map;
import java.util.Objects;

import com.cdlkata.Model.Inventory;
import com.cdlkata.Model.Item;
import com.cdlkata.Model.LineItem;

public class ShoppingCartService {
	
	private Map<String,LineItem> itemsInCart = new HashMap<>();
	
	private double totalPrice;

	private final Inventory inventory;
	
	 
	public ShoppingCartService(Inventory inventory) {
		 this.inventory = inventory;
	}
	
	
	
	public double calculateTotalPrice()
	{
		totalPrice = 0; 
		
		itemsInCart.entrySet().forEach(entry -> { 
		    
		    if(inventory.get(entry.getValue().getItemID()).isHasSale())
		    { 
		    	Item itemCur = inventory.get(entry.getValue().getItemID());
		    	
		    	int quotient = entry.getValue().getQuantity() / itemCur.getQuantity_For_Special();
		    	int remain = entry.getValue().getQuantity() % itemCur.getQuantity_For_Special(); 
				
		    	totalPrice += quotient * itemCur.getSpecial_Price();
		    	totalPrice += remain * itemCur.getUnit_Price();
 	
		    }
		    else
		    { 
		    	totalPrice += entry.getValue().getQuantity() * inventory.get(entry.getValue().getItemID()).getUnit_Price(); 
		    }
		     
		}); 
		
		return totalPrice;
	}
	
	public void addItem(LineItem lineItemToAdd)
	{
		if(lineItemToAdd.getQuantity() < 1) 
		{
			return;
		}
		 
		if(this.itemsInCart.containsKey(lineItemToAdd.getItemID()))
		{
			LineItem LineItemInCart = this.itemsInCart.get(lineItemToAdd.getItemID());
			LineItemInCart.increaseQuanitity(lineItemToAdd.getQuantity()); 
		}
		else
		{ 
			Item item = inventory.get(lineItemToAdd.getItemID());
			lineItemToAdd.setItemName(item.getName());
			lineItemToAdd.setPrice(item.getUnit_Price());
			this.itemsInCart.put(lineItemToAdd.getItemID(),lineItemToAdd);
		}
	}

	public void removeItem(LineItem itemToRemove)
	{ 
		if(itemToRemove.getQuantity() < 1) 
		{
			return;
		}
		
		boolean DeleteLineItem = false;
		
		for(LineItem itemInCart: itemsInCart.values())
		{
			if( Objects.equals(itemInCart.getItemID(),itemToRemove.getItemID()))
			{
				if (itemInCart.getQuantity() <= itemToRemove.getQuantity())
				{  
					DeleteLineItem = true; 
				}
				else
				{
					itemInCart.reduceQuantityBy(itemToRemove.getQuantity());
				}
			}
		} 
		
		if(DeleteLineItem)
		{ 
			this.itemsInCart.remove(itemToRemove.getItemID());
		}
		
	}
 
	public Map<String,LineItem> getCart() {
		return this.itemsInCart;
	}

	public boolean checkLineItemExists(String itemID) {
		if(this.itemsInCart.get(itemID) !=  null)
		{
			return true; 
		}
		return false;
	}
	
	public LineItem getLineItem(String itemID) { 
		return this.itemsInCart.get(itemID);
	}
	
	public void setCart(Map<String,LineItem> cart) {
		this.itemsInCart = cart;
	}

	public double getTotalPrice() {
		calculateTotalPrice();
		return totalPrice;
	}
 
	public int totalNumberOfItems()
	{
		int totalNumberOfItems = 0;
		
		for(LineItem li: itemsInCart.values())
		{
			totalNumberOfItems += li.getQuantity();
		}
		
		
		return totalNumberOfItems;
	}



	public Map<String,LineItem> listItemsInCart() {
		// TODO Auto-generated method stub
		return this.itemsInCart;
		
	}
	
	
}
 
