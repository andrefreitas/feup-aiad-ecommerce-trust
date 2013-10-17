package ecommerce;

import java.util.ArrayList;

public class User {
	protected String name;
	protected String country;
	protected ArrayList<Order> orders;
	protected ArrayList<FeedbackToBuyer> feedbacksBuyer;
	protected ArrayList<Product> products;
	protected ArrayList<FeedbackToSeller> feedbacksSeller;
	
	public User (String name, String country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	
	public int getTrust() {
		return 0;
	}
}
