package ecommerce;

import java.util.ArrayList;

public class Seller extends User{
	protected ArrayList<Product> products;
	protected ArrayList<FeedbackToSeller> feedbacks;
	
	public Seller(String name, String country) {
		super(name, country);
	}
	
	public boolean addProduct(Product item) {
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).equals(item))
				return false;
		}
		products.add(item);
		return true;
	}
	
	public boolean removeProduct(Product item) {
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).equals(item)) {
				products.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int getTrust(){
		return 0;
	}

}
