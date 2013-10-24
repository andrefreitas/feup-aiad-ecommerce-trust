package ecommerce;

import java.util.ArrayList;

public class User {
	protected String name;
	protected String country;
	protected ArrayList<Feedback> feedbacks;
	protected ArrayList<Product> products;
	
	
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

	public void addFeedback(String product, String category, int rating, int ticks,
			User user) {
		
		// TODO Auto-generated method stub
		
	}
	
}
