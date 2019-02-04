package gC_Midterms;
//Java Bean holds only Data for the project 

public class Toy {
	
	// fields accessible to every class in the package 

	private String toyName;
	private double toyPrice;
	private String toyCategory;
	private String toyDescription;

	// no argument constructor
	public Toy() {

	}

	// quad argument constructor
	public Toy(String productName, double productPrice, String productCategory, String productDescription) {
		this.toyName = productName;
		this.toyPrice = productPrice;
		this.toyCategory = productCategory;
		this.toyDescription = productDescription;
	}
	
/// getter for product name

	public String getName() {
		return toyName;
	}
	
// setter for product name 

	public void setName(String productName) {
		this.toyName = productName;
	}
	
// getter for product price 

	public double getPrice() {
		return toyPrice;
	}
	
// setter for product price

	public void setPrice(double productPrice) {
		this.toyPrice = productPrice;
	}
	
//getter for category

	public String getCategory() {
		return toyCategory;
	}
// setter for category
	public void setCategory(String productCategory) {
		this.toyCategory = productCategory;
	}
//setter for product description 

	public String getDescription() {
		return toyDescription;
	}
// setter for description 
	public void setDescription(String productDescription) {
		this.toyDescription = productDescription;
	}
	
// override for toString method to format output nicely 
	
	@Override
	public String toString() {
		return "Product:" + toyName + "," + toyPrice +","+ toyCategory + "," + toyDescription;
	}
}
