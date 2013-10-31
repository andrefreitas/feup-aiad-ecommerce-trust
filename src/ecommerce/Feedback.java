package ecommerce;

public class Feedback {	
	private Product product;
	private int score;
	private int timeTick; 		
	private User buyer;
	
	public Feedback(Product product, int score, int timeTick, User buyer){
		this.setProduct(product);
		this.setScore(score);
		this.setTimeTick(timeTick);
		this.setBuyer(buyer);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTimeTick() {
		return timeTick;
	}

	public void setTimeTick(int timeTick) {
		this.timeTick = timeTick;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	
}
