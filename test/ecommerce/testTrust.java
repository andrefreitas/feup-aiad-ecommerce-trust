package ecommerce;
import ecommerce.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class testTrust {

	@Test
	public void testTrustBasedOnCategory() {
		User user1 = new User("Gilberta", "Portugal");
		User user2 = new User("Carlos", "Portugal");
		User user3 = new User("Josefina", "Portugal");
		
		
		// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
		// User 1 sells more computers
		user1.addFeedback("Macbook Pro", "computadores", 5, 100, user2);
		user1.addFeedback("Macbook Pro", "computadores", 4, 100, user3);
		user1.addFeedback("Samsung Galaxy S 4", "telemoveis", 5, 100, user2);
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
		
		// The trust from
		assertTrue(linearTrust1 > linearTrust2);
                  
	}
        
        @Test
	public void testTrustBasedOnProduct() {
		User user1 = new User("Gilberta", "Portugal");
		User user2 = new User("Carlos", "Portugal");
		User user3 = new User("Josefina", "Portugal");
                
		// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
		// User 1 sells more computers
		user1.addFeedback("Mac Pro", "computadores", 5, 100, user2);
		user1.addFeedback("iMac 22", "computadores", 5, 100, user3);
		user1.addFeedback("Mac Mini", "computadores", 5, 100, user2);
		user1.addFeedback("Macbook Pro", "computadores", 5, 100, user3);
		
		// User 2 sells computers and watches
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user1);
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user3);
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user3);
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user1);
		
		// User 3 sells perfums
		user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);
		
		// User 3 wants to buy a computer
		double linearTrust1 = user3.computeLinearTrust(user1, "Mac Pro", "computadores", 10000);
		double linearTrust2 = user3.computeLinearTrust(user2, "Mac Pro", "computadores", 10000);
		
                System.out.println("Trust1: " + linearTrust1);
                System.out.println("Trust2: " + linearTrust2);
		// The trust from
		assertTrue(linearTrust1 < linearTrust2);
                  
	}
        
     	@Test
	public void testTrustBasedOnTime() {
		User user1 = new User("Gilberta", "Portugal");
		User user2 = new User("Carlos", "Portugal");
		User user3 = new User("Josefina", "Portugal");
                
		// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
		// User 1 sells more computers
		user1.addFeedback("Mac Pro", "computadores", 5, 100, user2);
		user1.addFeedback("Mac Pro", "computadores", 5, 100, user3);
		user1.addFeedback("Mac Pro", "computadores", 4, 200, user2);
		user1.addFeedback("Mac Pro", "computadores", 4, 200, user3);
		
		// User 2 sells computers and watches
		user2.addFeedback("Mac Pro", "computadores", 4, 100, user1);
		user2.addFeedback("Mac Pro", "computadores", 4, 100, user3);
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user3);
		user2.addFeedback("Mac Pro", "computadores", 5, 100, user1);
		
		// User 3 sells perfums
		user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);
		
		// User 3 wants to buy a computer
		double linearTrust1 = user3.computeLinearTrust(user1, "Mac Pro", "computadores", 200);
		double linearTrust2 = user3.computeLinearTrust(user2, "Mac Pro", "computadores", 200);
		
                System.out.println(">> Test Based on Time"); 
                System.out.println("Trust1: " + linearTrust1);
                System.out.println("Trust2: " + linearTrust2);
		// The trust from
		assertTrue(linearTrust1 < linearTrust2);
                  
	}
        

}
