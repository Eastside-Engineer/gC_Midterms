package gC_Midterms;
//Java Bean holds only Data for the project 
public class Product {

	private String productName;
	private double productPrice;
	private String productCategory;
	private String productDescription;

	// no argument constructor
	public Product() {

	}

	// quad argument constructor
	public Product(String productName, double productPrice, String productCategory, String productDescription) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
	}

	public String getName() {
		return productName;
	}

	public void setName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return productPrice;
	}

	public void setPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getCategory() {
		return productCategory;
	}

	public void setCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getDescription() {
		return productDescription;
	}

	public void setDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Override
	public String toString() {
		return "Product:" + productName + "," + productPrice +","+ productCategory + "," + productDescription;
	}

}
