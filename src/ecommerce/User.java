package ecommerce;

import java.util.ArrayList;

public class User {
	protected String name;
	protected String country;
	protected ArrayList<Feedback> feedbacks;
	protected ArrayList<Product> products;
	protected static final double CATEGORY_BONUS = 0.1;
	protected static final double PRODUCT_BONUS = 0.3;
	
	
	public User (String name, String country) {
		this.name = name;
		this.country = country;
		this.feedbacks = new ArrayList<Feedback>();
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

	public void addFeedback(String productName, String productCategory, int rating, int ticks,
			User buyer) {
		Product product = new Product(productName, productCategory);
		Feedback feedback = new Feedback(product, rating, ticks, buyer);
		this.feedbacks.add(feedback);
	}
	
	public ArrayList<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public double computeTrust(User seller, String productName, String productCategory, int timeTick) {
		ArrayList<Feedback> feedbacks = seller.getFeedbacks();
		double sum = 0 ;
		int feedbacksNumber = feedbacks.size();
		
		for(Feedback feedback: feedbacks){
			int sameCategory = (feedback.getProduct().getCategory() == productCategory)? 1 : 0;
			int sameProduct = (feedback.getProduct().getName() == productName)? 1 : 0;
			double score = feedback.getScore()
				         + CATEGORY_BONUS * sameCategory * feedback.getScore()
				         + PRODUCT_BONUS * sameProduct * feedback.getScore();
			sum += score;
			
		}
		
		double average = sum / feedbacksNumber;
		return average;
	}
	
}
