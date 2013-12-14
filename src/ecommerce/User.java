package ecommerce;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {

    protected String name;
    protected String country;
    protected String behaviour;
    protected ArrayList<String> categories;
    protected ArrayList<Feedback> feedbacks;
    protected ArrayList<Product> products;
    protected Hashtable<String, Double> trust; //matchs category to trust
    protected static final double CATEGORY_BONUS = 0.1;
    protected static final double PRODUCT_BONUS = 0.3;
    protected static final double NEW_FEEDBACK_VALUE = 2;
    protected double global_trust = 0;

    public User(String name, String country) {
        this.name = name;
        this.country = country;
        this.feedbacks = new ArrayList<>();
        this.trust = new Hashtable<>();
        this.categories = new ArrayList();
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
    
    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }
    
    public void setCategories(ArrayList<String> categories){
        this.categories = categories;
    }
    
    public double getGlobalTrust() {
    	return global_trust;
    }
    
    public String getBehaviour() {
        return behaviour;
    }
    
    public void addCategory(String category){
        categories.add(category);
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    
    public void setGlobalTrust(double global_trust) {
    	this.global_trust = global_trust;
    }

    public void addFeedback(String productName, String productCategory, int rating, int ticks,
            User buyer) {
        // Linear Trust
        Product product = new Product(productName, productCategory);
        Feedback feedback = new Feedback(product, rating, ticks, buyer);
        feedbacks.add(feedback);
    }

    public ArrayList<Feedback> getFeedbacks() {
        return this.feedbacks;
    }

    public double computeLinearTrust(User seller, String productName, String productCategory, int timeTick) {
        ArrayList<Feedback> feedbacks = seller.getFeedbacks();
        double sum = 0;
        double feedbacksNumber = 0;
        int diff;
        int sameCategory;
        int sameProduct;
        double timeImportance;
        
        if(feedbacks.size() == 0 ) return 0;

        for (Feedback feedback : feedbacks) {
            sameCategory = (feedback.getProduct().getCategory().equals(productCategory)) ? 1 : 0;
            sameProduct = (feedback.getProduct().getName().equals(productName)) ? 1 : 0;
            diff = timeTick - feedback.getTimeTick();
            timeImportance = 1.0 / (0.01 * diff + 1);
            //para diff = 0 --> timeIm = 1  , diff = 100 --> timeIm = 0.5  ; tende para 0

            // Category and Product relevance
            double score = feedback.getScore()
                    + CATEGORY_BONUS * sameCategory * feedback.getScore()
                    + PRODUCT_BONUS * sameProduct * feedback.getScore();

            feedbacksNumber += CATEGORY_BONUS * sameCategory
                    + PRODUCT_BONUS * sameProduct;

            /* ----- CUIDADO COM PESOS NEGATIVOS! ------
             * Ler pagina do wikipedia em medias pesadas
             * http://en.wikipedia.org/wiki/Weighted_arithmetic_mean#Mathematical_definition
             */
            /*if (sameCategory == 0) {
                score = score * (1 - CATEGORY_BONUS);
            } else if (sameProduct == 0) {
                score = score * (1 - PRODUCT_BONUS);
            }*/

            // Time relevance
            if (sameCategory == 1){
                score += feedback.getScore() * timeImportance;
                feedbacksNumber += timeImportance;
            }
            
            sum += score;
            feedbacksNumber += 1;

        }

        double average = sum / feedbacksNumber;
        return average;
    }
    
    @Override
    public String toString(){
        String out =  "Name: " + name + ", Country: " + country + ", Categories: ";
        for(String category: categories){
            out += category + " ";
        }
        return out;
    }

}
