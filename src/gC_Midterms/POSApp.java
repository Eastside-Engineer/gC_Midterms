package gC_Midterms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POSApp {

	private static Path filePath = Paths.get("inventory.txt");

// possibly need to remove throws IO because not proper practice use try/catch instead 

	public static void main(String[] args) throws IOException {

//Creating variables to hold items selected for cart
		double subTotal = 0;
		double grandTotal = 0;
		final double salesTax = 1.06;
		List<String> toyCart = new ArrayList<String>();
		List<Double> toyCartPrice = new ArrayList<Double>();
		List<Double> toyCartNum = new ArrayList<Double>();

// check for file existence for it is important
		checkFile(filePath);
// methods that prints inventory

		List<Toy> toyList = readFile();

		getPrompt();
// begin user input for adding items to SHOPPING CART
		Scanner scnr = new Scanner(System.in);
		int userChoice = ProductUtil.getProductNum(scnr, "Enter a product number: ", 1, toyList.size());
		System.out.println("Do you want to add " + toyList.get(userChoice - 1).getCategory() + ", "
				+ toyList.get(userChoice - 1).getName() + " to your cart?");
//		System.out.println("(y/n); ");
		// conditional logic to validate user input
		String userCont = Validator.getStringMatchingRegex(scnr, "(y/n): ", "[yYnN]");
		if (userCont.equalsIgnoreCase("y")) {
			double userQuantity = Validator.getDouble(scnr,
					"How many of " + toyList.get(userChoice - 1).getName() + " would you like to add?:", 0, 500);
			System.out.println((int) userQuantity + " of " + toyList.get(userChoice - 1).getName()
					+ " have been added to the cart.");
			// Adding userQuantity times the price to the subTotal
			subTotal += userQuantity * toyList.get(userChoice - 1).getPrice();
			System.out.println("Your Subtotal is:");
			System.out.printf("$%-9.2f", subTotal);
			
			//We are storing the user input inside of para lol arrays. Like a boss.
			toyCart.add(toyList.get(userChoice-1).getName());
			toyCartPrice.add(toyList.get(userChoice-1).getPrice());
			toyCartNum.add(userQuantity);
//			 grandTotal += subTotal*salesTax;

		} else if (userCont.equalsIgnoreCase("n")) {
			System.out.println(Validator.getStringMatchingRegex(scnr, "Would you like to look for another toy? (y/n): ",
					"[yYnN]"));

		} else {
//			System.out.println(Validator.getStringMatchingRegex(scnr, "Display menu, Press 'm'", "[mM]"));
//			System.out.println();

		}
		// int userInput = scnr.nextInt();
		System.out.println(toyCartNum.toString() + ", "+toyCart.toString()+ ", for a total of $"+toyCartPrice.toString());
	} // END MAIN METHOD

// might move this to product util class later

	protected static void getPrompt() throws IOException {

		System.out.println("Welcome to Toys R' Us. The most profitable, long lasting Toy store in the world.\n");
// cycle through products and print them on lines
		List<String> toyInventory = Files.readAllLines(filePath);
		List<Toy> toyList = readFile();
//		for (String thisToy : toyInventory) {
//			System.out.println(thisToy);
			for (int i = 0; i < toyList.size(); i++) {
				// Trying to print the index of the menu.
				// System.out.printf("%-9s", "\n" + toyList.);

				System.out.print(i + 1 + ". "); // using the incrementor +1 to denote index AKA product nmber for user input
				System.out.println(toyList.get(i).getName());
				System.out.printf("%-10s", "$" + toyList.get(i).getPrice());
				System.out.printf("%-10s \t", toyList.get(i).getCategory());
				System.out.printf("%-9s", "\n" + toyList.get(i).getDescription());
				System.out.println("\n");

			}
		}
//	}

// creating a list of product objects called "products" and setting it equal to an array list separated b ycom

	public static List<Toy> readFile() throws IOException {

		// pass the product class

		List<String> toyInventory = Files.readAllLines(filePath);

		List<Toy> toys = new ArrayList<>();

		for (String thisToy : toyInventory) {
			String[] parts = thisToy.split(",");
			Toy p = new Toy(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3]);

			toys.add(p);
		}

		return toys;

	}

	public static void checkFile(Path filePath) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}
}