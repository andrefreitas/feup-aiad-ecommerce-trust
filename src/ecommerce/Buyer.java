package ecommerce;

import java.util.ArrayList;

public class Buyer extends User {
	protected ArrayList<Order> orders;

	public Buyer(String name, String country) {
		super(name, country);
	}
	
	

}
