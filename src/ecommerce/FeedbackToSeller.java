package ecommerce;

public class FeedbackToSeller extends Feedback {

	private int comunicationScore;
	private int descriptionScore;
	private int postageScore;
	private int timetoShipScore;
	
	public FeedbackToSeller(int comunicationScore, int descriptionScore, int postageScore, int timetoShipScore) {
		this.comunicationScore = comunicationScore;
		this.descriptionScore = descriptionScore;
		this.postageScore = postageScore;
		this.timetoShipScore = timetoShipScore;
	}
	
	public int getComunicationScore() {
		return comunicationScore;
	}
	
	public int getDescriptionScore() {
		return descriptionScore;
	}
	
	public int getPostageScore() {
		return postageScore;
	}
	
	public int getTimetoShipScore() {
		return timetoShipScore;
	}

}
