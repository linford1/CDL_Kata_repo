package com.cdlkata.Model;
 
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Inventory {
	private Map<String,Item> InventoryItems = new LinkedHashMap<>();
	
	 public void add(Item item)
	 {
		 this.InventoryItems.put(item.getId(),item);
	 }
	
	 public void remove(Item item)
	 {
		 this.InventoryItems.remove(item.getId());
	 }
	 
	 public  Item get(String id)
	 {
		return InventoryItems.get(id); 
	 }
	
	 public String getLastID()
	 {
		 int count = 1;
		 
	        for (Map.Entry<String, Item> it :  this.InventoryItems.entrySet()) {
	           
	            if (count == this.InventoryItems.size()) {
	                 
	                return it.getValue().getId();
	            }
	            count++;
	        }
	        
		return  "0";
	 }
	 
		public Map<String,Item> listItemsInInventory() {
			// TODO Auto-generated method stub
			return this.InventoryItems; 
		}
}
