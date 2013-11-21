package ecommerce;

import static org.junit.Assert.*;

import org.junit.Test;

public class testTrust {

    /**
    * This tests the Linear Thrust algorith by facing an user that wants a 
    * "Mac Pro" computer with two sellers with similar feedback but with diferent
    * Categories sold.
    * 
    * Both users have 5/5 feedbacks, feedback times are similar and the product
    * the user wants was never sold by any of the sellers.
    * 
    * User 1 has sold more computers than User 2
    * 
    * It is expected a higher trust value for the User 1.
    * 
    */
    @Test
    public void testTrustBasedOnCategory() {
        User user1 = new User("Gilberta", "Portugal");
        User user2 = new User("Carlos", "Portugal");
        User user3 = new User("Josefina", "Portugal");

        // <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
        // User 1 sells more computers
        user1.addFeedback("Macbook Pro", "computadores", 5, 100, user2);
        user1.addFeedback("Macbook Pro", "computadores", 5, 100, user3);
        user1.addFeedback("Samsung Galaxy S 4", "telemoveis", 5, 100, user2);
        user1.addFeedback("Samsung R540", "computadores", 5, 100, user3);

        // User 2 sells computers and watches
        user2.addFeedback("Swatch", "relogios", 5, 100, user1);
        user2.addFeedback("Casio", "relogios", 5, 100, user3);
        user2.addFeedback("Asus", "computadores", 5, 100, user3);
        user2.addFeedback("Toshiba", "computadores", 5, 100, user1);

        // User 3 sells perfums
        user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);

        // User 3 wants to buy a computer
        double linearTrust1 = user3.computeLinearTrust(user1, "Macbook Air 13", "computadores", 10000);
        double linearTrust2 = user3.computeLinearTrust(user2, "Macbook Air 13", "computadores", 10000);

        // The trust from
        assertTrue(linearTrust1 > linearTrust2);

    }

    /**
    * This tests the Linear Thrust algorith by facing an user that wants a 
    * "Mac Pro" computer with two sellers with similar feedback but with diferent
    * products sold.
    * 
    * Both users have 5/5 feedbacks and feedback times are similar.
    * 
    * User 1 sold various diferent computers.
    * USer 2 only sold "Mac Pro".
    * 
    * It is expected a higher trust value for the User 2.
    * 
    */
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

        System.out.println(">> Test Based on Product -----------------");
        System.out.println("Trust1: " + linearTrust1);
        System.out.println("Trust2: " + linearTrust2);
        System.out.println(">>----------------------------------------");
        // The trust from
        assertTrue(linearTrust1 < linearTrust2);

    }
    
    /**
    * This tests the Linear Thrust algorith by facing an user that wants a 
    * "Mac Pro" computer with two sellers with similar feedback but at diferent
    * times.
    * 
    * Both users have 5/5 feedbacks and sold similar products
    * 
    * User 1 feedbacks are more recent, 200 ticks into the simulation.
    * USer 2 feedbacks are older, 100 ticks since the start.
    * 
    * It is expected a higher trust value for the User 1.
    * 
    */ 
    @Test
    public void testTrustBasedOnTime() {
        User user1 = new User("Gilberta", "Portugal");
        User user2 = new User("Carlos", "Portugal");
        User user3 = new User("Josefina", "Portugal");

		// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
        // User 1 sells more computers
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user2);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user3);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user2);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user3);

        // User 2 sells computers and watches
        user2.addFeedback("Mac Pro", "computadores", 5, 100, user1);
        user2.addFeedback("Mac Pro", "computadores", 5, 100, user3);
        user2.addFeedback("Mac Pro", "computadores", 5, 100, user3);
        user2.addFeedback("Mac Pro", "computadores", 5, 100, user1);

        // User 3 sells perfums
        user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);

        // User 3 wants to buy a computer
        double linearTrust1 = user3.computeLinearTrust(user1, "Mac Pro", "computadores", 200);
        double linearTrust2 = user3.computeLinearTrust(user2, "Mac Pro", "computadores", 200);

        System.out.println(">> Test Based on Time --------------------");
        System.out.println("Trust1: " + linearTrust1);
        System.out.println("Trust2: " + linearTrust2);
        System.out.println(">>----------------------------------------");
        // The trust from
        assertTrue(linearTrust2 < linearTrust1);

    }
    
   /**
    * This tests the Linear Thrust algorith by facing an user that wants a 
    * "Zenith Pilot" watch with three sellers with diferent profiles.
    * 
    * User 1 only sold computers, all of the feedbacks have 5 out of 5 score
    * and are from the same time instant as the trust computation.
    * 
    * User 2 sold a mixure of watches and computers but despite all of his feedbacks
    * having 5/5 score they are relatively old.
    * 
    * User 3 only sold watches but despite his recent feedbacks and have sold a watch
    * similar to the one User 4 is searching for, all of his feedbacks are 3/5.
    * 
    * It is expected a higher trust value for the User 2...
    * 
    */ 
     @Test
    public void testTrustBasedOnTimeProductsAndCategories() {
         
         
        User user1 = new User("Gilberta", "Portugal");
        User user2 = new User("Carlos", "Portugal");
        User user3 = new User("Zé", "Portugal");
        User user4 = new User("Josefina", "Portugal");

	// <Seller>.addFeedback(<Product>, <Category>, <Score (0-5)>, <TimeTick>, <Score>, <Buyer>)
        
        // User 1 only sells computers
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user2);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user3);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user2);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user3);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user4);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user3);
        user1.addFeedback("Mac Pro", "computadores", 5, 200, user4);

        // User 2 sells computers and watches
        user2.addFeedback("Rolex Daytona",     "relogio",      5, 100, user1);
        user2.addFeedback("Mac Pro",           "computadores", 5, 50, user3);
        user2.addFeedback("Mac Pro",           "computadores", 5, 50, user4);
        user2.addFeedback("Swatch",            "relogio",      5, 50, user3);
        user2.addFeedback("Mac Pro",           "computadores", 5, 100, user4);
        user2.addFeedback("Mac Pro",           "computadores", 5, 100, user3);
        user2.addFeedback("Zenith El Primero", "relogio",      5, 50, user1);
        
        // User 3 only sells watches
        user3.addFeedback("Zenith El Primero", "relogio", 3, 150, user1);
        user3.addFeedback("Rolex Daytona",     "relogio", 3, 100, user2);
        user3.addFeedback("IWC Portuguese",    "relogio", 3, 150, user4);
        user3.addFeedback("Rolex Datejust",    "relogio", 3, 100, user2);
        user3.addFeedback("Zenith Pilot",      "relogio", 3, 150, user4);
        user3.addFeedback("Rolex Explorer",    "relogio", 3, 100, user2);
        user3.addFeedback("Rolex Submariner",  "relogio", 3, 150, user1);

        // User 4 sells perfums
        user3.addFeedback("Carolina Herrera", "perfumes", 5, 100, user1);

        // User 4 wants to buy a watch
        double linearTrust1 = user4.computeLinearTrust(user1, "Zenith Pilot", "relogio", 200);
        double linearTrust2 = user4.computeLinearTrust(user2, "Zenith Pilot", "relogio", 200);
        double linearTrust3 = user4.computeLinearTrust(user3, "Zenith Pilot", "relogio", 200);

        System.out.println(">> Test Based on Time 2 ------------------");
        System.out.println("Trust1: " + linearTrust1);
        System.out.println("Trust2: " + linearTrust2);
        System.out.println("Trust3: " + linearTrust3);
        System.out.println(">>----------------------------------------");
        // The trust from
        assertTrue(linearTrust2 > linearTrust1 && linearTrust2 > linearTrust3);

    }

}
