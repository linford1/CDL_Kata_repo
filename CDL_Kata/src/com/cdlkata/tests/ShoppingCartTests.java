package com.cdlkata.tests;
   
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.cdlkata.Model.Inventory;
import com.cdlkata.Model.Item;
import com.cdlkata.Model.LineItem;
import com.cdlkata.services.ShoppingCartService; 

public class ShoppingCartTests {
	private Inventory inventory;
	private ShoppingCartService cart;
	
	
	@Before
	public void setUp() throws Exception
	{
		 Item A = new Item("1","Apples",.50,1.30,3,true);
		 Item B = new Item("2","Banana",.30,.45,2,true);
		 Item C = new Item("3","Carrot",.20);
		 Item D = new Item("4","Date",.15);
		 
		 inventory = new Inventory();
		 inventory.add(A);
		 inventory.add(B);
		 cart = new ShoppingCartService(inventory);
		 
		 }
	
	@Test
	public void should_add_an_item_to_a_cart() {  
		cart.addItem(new LineItem("1")); 
		int cartItemLength = cart.totalNumberOfItems(); 
		assertEquals(cartItemLength,1); 
	}

	@Test
	public void should_add_multiple_items_to_a_cart() { 
		   
		cart.addItem(new LineItem("1"));
		cart.addItem(new LineItem("2"));
		
		
		int cartItemLength = cart.totalNumberOfItems(); 
		assertEquals(cartItemLength,2); 
	}
	
	@Test
	public void should_add_multiple_of_the_same_item_to_a_cart() { 
		
		cart.addItem(new LineItem("1",2));
		 
		int cartItemLength = cart.totalNumberOfItems();
		
		assertEquals(cartItemLength,2); 
	}
	
	
	@Test
	public void should_remove_an_item_in_a_cart() {
		
		cart.addItem(new LineItem("1"));
		cart.addItem(new LineItem("2"));
		
		cart.removeItem(new LineItem("2",2));
		
		int cartItemLength = cart.totalNumberOfItems();
		
		assertEquals(cartItemLength,1); 
	}
	
	@Test
	public void should_remove_specific_quantity_of_an_item_from_cart() {
		
		cart.addItem(new LineItem("1",2));
		cart.addItem(new LineItem("2",3));
		
		cart.removeItem(new LineItem("1",2));
		cart.removeItem(new LineItem("2",1));
		
		 
		
		int cartItemLength = cart.totalNumberOfItems();
		
		assertEquals(cartItemLength,2); 
	}
	
	@Test
	public void should_remove_item_from_cart_when_quantity_removed() {
		
		cart.addItem(new LineItem("1",2)); 
		
		cart.removeItem(new LineItem("1",2)); 
		
		 
		
		int cartItemLength = cart.totalNumberOfItems();
		
		assertEquals(cartItemLength,0); 
	}
	
	@Test
	public void should_increase_specific_quantity_of_an_item_in_cart() {
		
		cart.addItem(new LineItem("1",2));
		cart.addItem(new LineItem("2",3));
		 
		cart.addItem(new LineItem("2",2));
		
		 
		
		int cartItemLength = cart.totalNumberOfItems();
		
		assertEquals(cartItemLength,7); 
	}
	

	@Test
	public void should_show_list_of_items_in_cart() { 
		cart.addItem(new LineItem("1",2));
		cart.addItem(new LineItem("2",3));
		
		
		Map<String,LineItem> lineItems = cart.listItemsInCart();
		
		assertEquals(lineItems.get("1").totalPrice() ,1); 
		assertEquals(lineItems.get("2").totalPrice() ,.9,0.01f);   
	}
	
	  @Test
	public void should_increase_quantity_when_added_same_item_seperate_time_cart() { 
		cart.addItem(new LineItem("1",2));
		cart.addItem(new LineItem("1",1)); 
		Map<String,LineItem> lineItems = cart.listItemsInCart();
		
		assertEquals(lineItems.get("1").totalPrice() ,1.50);  
	} 
	  
	  
	  @Test
		public void return_total_price_when_special_discount_added_to_Item() { 
			cart.addItem(new LineItem("1",2));
			cart.addItem(new LineItem("1",1));  
			double totalprice = cart.getTotalPrice();
			
			assertEquals(totalprice ,1.30);  
		} 
	  
	 
}
