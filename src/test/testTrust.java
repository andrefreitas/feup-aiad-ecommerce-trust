package test;
import ecommerce.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class testTrust {

	@Test
	public void testTrustOnProductAndCategory() {
		User user1 = new User("Gilberta", "Portugal");
		User user2 = new User("Gervásio", "Portugal");
		User user3 = new User("Josefina", "Portugal");
		
		
		// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
		// User 1 sells more computers
		user1.addFeedback("Macbook Air 13", "computadores", 5, 100, user2);
		user1.addFeedback("Macbook Air 13", "computadores", 4, 100, user3);
		user1.addFeedback("Samsung Galaxy S3", "telemoveis", 5, 100, user2);
		user1.addFeedback("Samsung R540", "computadores", 1, 100, user3);
		
		// User 2 sells computers and watches
		user2.addFeedback("Swatch", "relogios", 3, 100, user1);
		user2.addFeedback("Casio", "relogios", 1, 100, user3);
		user2.addFeedback("Asus", "computadores", 5, 100, user3);
		user2.addFeedback("Toshiba", "computadores", 3, 100, user1);
		
		// User 3 sells perfums
		user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);
		
		// User 3 wants to buy a computer
		double linearTrust1 = user3.computeLinearTrust(user1, "Macbook Air 13", "computadores", 10000);
		double linearTrust2 = user3.computeLinearTrust(user2, "Macbook Air 13", "computadores", 10000);
		
		double trust1 = user3.computeTrust(user1, "computadores", "Macbook Air 13");
		double trust2 = user3.computeTrust(user2, "computadores", "Macbook Air 13");
		
		// The trust from
		assertTrue(linearTrust1 > linearTrust2);
		//System.out.println("trust1: " + trust1);
		//System.out.println("trust2: " + trust2);
		//assertTrue(trust1 > trust2);
	
	}

}
