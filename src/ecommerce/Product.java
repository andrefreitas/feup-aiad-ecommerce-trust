package ecommerce;

public class Product {
	String name;
	double price;
	int date;
	
	public Product(String name, double price, int date) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	
}
