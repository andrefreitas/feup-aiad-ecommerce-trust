package ecommerce;

import java.util.ArrayList;

public class Buyer extends User {
	protected ArrayList<Order> orders;
	protected ArrayList<FeedbackToBuyer> feedbacks;

	public Buyer(String name, String country) {
		super(name, country);
	}
	
	public int getTrust(){
		return 0;
	}
	
	

}
