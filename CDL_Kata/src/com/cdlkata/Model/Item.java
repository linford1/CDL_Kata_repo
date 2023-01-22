package com.cdlkata.Model;

 

public class Item {


	private String id;
	private String itemName;
	private double unit_Price;
	private double special_Price;
	private int Quantity_For_Special; 
	private boolean hasSale;
	
	 
	
	public Item(String id,String name, double unit_Price, double special_Price, int quantity_For_Special,boolean hasSale) { 
		this.id = id;
		this.itemName = name; 
		this.unit_Price = unit_Price;
		this.special_Price = special_Price;
		this.Quantity_For_Special = quantity_For_Special;
		this.hasSale = hasSale; 
	}
	
	public Item(String id,String name, double unit_Price) { 
		this.id = id;
		this.itemName = name; 
		this.unit_Price = unit_Price;  
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 

	public String getName() {
		return itemName;
	}
	public void setName(String name) {
		this.itemName = name;
	} 
	public double getUnit_Price() {
		return unit_Price;
	}
	public void setUnit_Price(double unit_Price) {
		this.unit_Price = unit_Price;
	}
	public double getSpecial_Price() {
		return special_Price;
	}
	public void setSpecial_Price(int special_Price) {
		this.special_Price = special_Price;
	}
	public int getQuantity_For_Special() {
		return Quantity_For_Special;
	}
	public void setQuantity_For_Special(int quantity_For_Special) {
		Quantity_For_Special = quantity_For_Special;
	}

	public boolean isHasSale() {
		return hasSale;
	}

	public void setHasSale(boolean hasSale) {
		this.hasSale = hasSale;
	}

	public void setSpecial_Price(double special_Price) {
		this.special_Price = special_Price;
	}

	
}
