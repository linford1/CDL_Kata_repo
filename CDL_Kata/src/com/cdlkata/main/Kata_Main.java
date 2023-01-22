package com.cdlkata.main;

import java.text.NumberFormat;
import java.util.Scanner;

import com.cdlkata.Model.Inventory;
import com.cdlkata.Model.Item;
import com.cdlkata.Model.LineItem;
import com.cdlkata.services.ShoppingCartService;

public class Kata_Main {

	public static void main(String[] args) {

		 Scanner reader = new Scanner(System.in);  
		NumberFormat formatter = NumberFormat.getCurrencyInstance();	
		 Item A = new Item("1","Apples",.50,1.30,3,true);
		 Item B = new Item("2","Banana",.30,.45,2,true);
		 Item C = new Item("3","Carrot",.20);
		 Item D = new Item("4","Date",.15);
		 
		Inventory inventory = new Inventory();
		 inventory.add(A);
		 inventory.add(B);
		 inventory.add(C);
		 inventory.add(D);
		 ShoppingCartService cart = new ShoppingCartService(inventory); 
		 
		 LineItem li1 = new LineItem("1");
		 LineItem li2= new LineItem("2"); 
		 
		 cart.addItem(li1);
		 cart.addItem(li2);
 
		 
		 while (true)
		 {
			 System.out.println("Checkout System");
			 System.out.println("1: Handle Inventory, 2: List Items Store Inventory, 3: Shopping Cart");
			 int InitialLogicOp = reader.nextInt(); 
			 
			 if(InitialLogicOp == 1)
			 {
				 System.out.println("1: AddItem, 2: UpdateItem, 3: RemoveItem");
				 int HandleInventoryOp = reader.nextInt(); 
				 
				 if(HandleInventoryOp == 1)
				 {
					 System.out.println("--Adding Item--"); 
					 
					 int enterID = Integer.parseInt(inventory.getLastID()) + 1; 
					 System.out.println(enterID); 
					 System.out.println("Item Name: "); 
			
					 String itemName = reader.next();
					 
					 System.out.println("Item UnitPrice (double): "); 
					 double itemUnitPrice = reader.nextDouble();
					 
					 System.out.println("Item has special salePrice: "); 
					 boolean itemHasSale = reader.nextBoolean();
					 
					 if(itemHasSale)
					 {
						System.out.println("Quantity for special price: ");  
						int Quantity_For_Special = reader.nextInt();
						
						System.out.println("Item Special price (double): "); 
						double specialSalePrice = reader.nextDouble();
						  
						 Item nItem = new Item(Integer.toString(enterID),itemName,itemUnitPrice,specialSalePrice,Quantity_For_Special,itemHasSale);
						 inventory.add(nItem);
						 System.out.println("Item Added with special price");
					 }
					 else
					 {
						 Item nItem = new Item(Integer.toString(enterID),itemName,itemUnitPrice);
						 inventory.add(nItem);
						 System.out.println("Item Added without special");
					 }
				 }
				 else if(HandleInventoryOp == 2)
				 {

					 System.out.println("--Updating Item--"); 
					 
					 System.out.println("Input Item ID:");
					 int UpdateItemID = reader.nextInt();  
					 
					 Item ic = inventory.get(Integer.toString(UpdateItemID));
					 if(ic != null)
					 {
						 System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()) + " (Quantity for discount: "+ ic.getQuantity_For_Special() + ") (discounted price: " +formatter.format(ic.getSpecial_Price()) +")" );  
						   
					 } 
					 
					 
					 
					 
					 
				 }
					 else if(HandleInventoryOp == 3)
					 {

						 System.out.println("--Removing Item--"); 
						 
						 System.out.println("Input Item ID:");
						 int RemoveItemID = reader.nextInt();  
						 
						 Item itemToRemove = inventory.get(Integer.toString(RemoveItemID));
						 if(itemToRemove != null)
						 {
							 System.out.println(itemToRemove.getId()+ ": " + itemToRemove.getName() +" : " + formatter.format(itemToRemove.getUnit_Price()) + " (Quantity for discount: "+ itemToRemove.getQuantity_For_Special() + ") (discoutned price: " +formatter.format(itemToRemove.getSpecial_Price()) +")" );  
							 System.out.println("!!Confirm item removal!!"); 
							 boolean remove = reader.nextBoolean();
							 
							 if(remove)
							 {
								 inventory.remove(itemToRemove); 
								 System.out.println("Item remove"); 
								 System.out.println(" "); 
							 }
						 }
						 
					 
					 }
				  
			 }
			 else if(InitialLogicOp == 2)
			 {
				 inventory.listItemsInInventory().entrySet().forEach(entry -> { 
					 Item ic = entry.getValue();
					 
					 if(ic.isHasSale())
					    {
						  System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()) + " (Quantity for discount: "+ ic.getQuantity_For_Special() + ") (discoutned price: " +formatter.format(ic.getSpecial_Price()) +")" );  
					    }
					 else
					 {
					      System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()));  
					 }
				 });
			 }
			 else if(InitialLogicOp == 3)
			 {

				 System.out.println("--- Items in basket ---"); 
				 
				 cart.listItemsInCart().entrySet().forEach(entry -> { 
					 LineItem lineItem = entry.getValue();
					 
					 System.out.println(lineItem.getItemName()+ ": " + lineItem.getQuantity() +" Unit Price: " + inventory.get(lineItem.getItemID()).getUnit_Price());  
				 }); 
				 
				 System.out.println(" ");  
				 System.out.println("Number of items: "+ cart.totalNumberOfItems());
				 System.out.println("Total Price: "+  formatter.format(cart.getTotalPrice())); 
				 
				 
				 System.out.println("1: Add Item to Cart, 2: Remove item / reduce quantity");
				 int basketOperationLogicNumber = reader.nextInt(); 
				 
				 
				 if(basketOperationLogicNumber == 1)
				 {
					 System.out.println(""); 
					 System.out.println("--Adding Item(s) to cart--");
					 System.out.println("----All items in inventory----"); 
					 inventory.listItemsInInventory().entrySet().forEach(entry -> { 
						 Item ic = entry.getValue();
						 
						 if(ic.isHasSale())
						    {
							  System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()) + " (Quantity for discount: "+ ic.getQuantity_For_Special() + ") (discoutned price: " +formatter.format(ic.getSpecial_Price()) +")" );  
						    }
						 else
						 {
						      System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()));  
						 }
					 });

					 System.out.println(" ");  

					 System.out.println("ID of Item to add");
					 
					 Item itemInventoryCheck = null;
					 int itemQuantityToAdd = 0;
					 
					 try {
					 String itemID = reader.next();
					 itemInventoryCheck = inventory.get(itemID); 
					 }
					 catch (java.util.InputMismatchException e) {return;}
					 {
						 reader.nextLine();
					 } 
					 
					 if(itemInventoryCheck != null)
					 {
						 System.out.println("Quantity to add"); 
						 itemQuantityToAdd = reader.nextInt();
						 
						 LineItem lineItemToAdd = new LineItem(itemInventoryCheck.getId(),itemQuantityToAdd);
						 
						 cart.addItem(lineItemToAdd);
						 
					 }
					 else
					 {
						 return;
					 }
				 }
				 else if(basketOperationLogicNumber == 2)
				 {
					 System.out.println(""); 
					 System.out.println("--Removing Item(s) from cart--");
					 System.out.println("----All items in inventory----"); 
					 inventory.listItemsInInventory().entrySet().forEach(entry -> { 
						 Item ic = entry.getValue();
						 
						 if(ic.isHasSale())
						    {
							  System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()) + " (Quantity for discount: "+ ic.getQuantity_For_Special() + ") (discoutned price: " +formatter.format(ic.getSpecial_Price()) +")" );  
						    }
						 else
						 {
						      System.out.println(ic.getId()+ ": " + ic.getName() +" : " + formatter.format(ic.getUnit_Price()));  
						 }
					 });

					 System.out.println(" ");  

					 System.out.println("ID of Item to remove");
					 
					 boolean itemCheck = false;
					 int itemQuantityToremove = 0;
					 String itemID  = null;
					 
					 try {
					 itemID = reader.next();
					 itemCheck = cart.checkLineItemExists(itemID); 
					 }
					 catch (java.util.InputMismatchException e) {return;}
					 {
						 reader.nextLine();
					 } 
					 
					 if(itemCheck)
					 {
						 System.out.println("Quantity to remove"); 
						 itemQuantityToremove = reader.nextInt();
						  
						 LineItem litem = new LineItem(cart.getLineItem(itemID).getItemID(),itemQuantityToremove); 
						 
						 cart.removeItem(litem);
						 
					 }
					 else
					 {
						 return;
					 }
				 }
			 }
		 }
	// reader.close();
	  
	} 
}