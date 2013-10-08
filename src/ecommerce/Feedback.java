package ecommerce;

public class Feedback {	
	private int date; 		/* date in ticks */
	private Rating rating;	/* overall satisfaction rating */
	private int score;
	
	public void setDate(int date) {
		this.date = date;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDate() {
		return date;
	}
	
	
}
