package ecommerce;

import java.util.ArrayList;

public class Order {
	ArrayList<Product> products;
	
	public boolean addProduct(Product p){
		return products.add(p);		
	}
	
	public Product getProdAtIndex(int i){
		try {
			return products.get(i);	
		} catch (IndexOutOfBoundsException e) {
			return null; //TODO tratar desta excepcao
		}
	}
	
	public boolean remProdAtIndex(int i){
		try {
			 Product p = products.remove(i);
			 if(p != null)
				 return true;
			 else
				 return false;
			 
		} catch (IndexOutOfBoundsException e) {
			return false; //TODO tratar desta excepcao
		}
	}
}
