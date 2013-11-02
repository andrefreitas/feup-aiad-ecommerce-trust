package ecommerce;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {
	protected String name;
	protected String country;
	protected ArrayList<Feedback> feedbacks;
	protected ArrayList<Product> products;
	protected Hashtable<String, Double> trust;
	protected static final double CATEGORY_BONUS = 0.1;
	protected static final double PRODUCT_BONUS = 0.3;
	protected static final double NEW_FEEDBACK_VALUE = 2;
	
	
	public User (String name, String country) {
		this.name = name;
		this.country = country;
		this.feedbacks = new ArrayList<Feedback>();
		this.trust = new Hashtable<String, Double> ();
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
		// Linear Trust
		Product product = new Product(productName, productCategory);
		Feedback feedback = new Feedback(product, rating, ticks, buyer);
		feedbacks.add(feedback);
		
		// Non Linear Trust
		int numFeedbacks = feedbacks.size();
		String key = productCategory + "-" + productName;
		try {
			Double trust = this.trust.get(key);
			trust = (trust  +  rating * NEW_FEEDBACK_VALUE) / (numFeedbacks + 1);
			this.trust.put(key, trust);
			System.out.println(key);
		}
		catch (Exception e){
			this.trust.put(key, (double) rating);
		}
		
	}
	
    public double computeTrust(User seller, String productCategory, String productName){
    	
    	try {
    		String key = productCategory + "-" + productName;
    		double value = trust.get(key);
    		return value;
    	}
    	catch (Exception e){
			return 0;
		}
    	
    }
	
	public ArrayList<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public double computeLinearTrust(User seller, String productName, String productCategory, int timeTick) {
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
