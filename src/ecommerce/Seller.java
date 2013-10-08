package ecommerce;

import java.util.ArrayList;

public class Seller extends User{
	protected ArrayList<Product> products;
	
	public Seller(String name, String country) {
		super(name, country);
	}

}
