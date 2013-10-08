package ecommerce;

public class FeedbackToBuyer extends Feedback {
	
	public int timetoPayScore;

	public FeedbackToBuyer(int timetoPayScore) {
		this.timetoPayScore = timetoPayScore;
	}
	
	public int getTimetoPayScore() {
		return timetoPayScore;
	}

	public void setTimetoPayScore(int timetoPayScore) {
		this.timetoPayScore = timetoPayScore;
	}
}
