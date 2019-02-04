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
		boolean run = true;
		List<String> toyCart = new ArrayList<String>();
		List<Double> toyCartPrice = new ArrayList<Double>();
		List<Double> toyCartNum = new ArrayList<Double>();

// check for file existence for it is important
		ProductUtil.checkFile(filePath);
// methods that prints inventory

		List<Toy> toyList = ProductUtil.readFile();
		while (run == true) {
			ProductUtil.getPrompt();
// begin user input for adding items to SHOPPING CART
			Scanner scnr = new Scanner(System.in);
			int userChoice = ProductUtil.getProductNum(scnr, "Enter a product number: ", 1, toyList.size());
			System.out.println("Do you want to add " + toyList.get(userChoice - 1).getCategory() + ", "
					+ toyList.get(userChoice - 1).getName() + " to your cart?");

// conditional logic to validate user input
			String userCont = ProductUtil.getUserCont(scnr, "(y/n): ", "[yYnN]");

			if (userCont.equalsIgnoreCase("y")) {

				double userQuantity = ProductUtil.getQuantityDouble(scnr,
						"How many of " + toyList.get(userChoice - 1).getName() + " would you like to add?:", 0, 500);
				System.out.println((int) userQuantity + " of " + toyList.get(userChoice - 1).getName()
						+ " have been added to the cart.");
// Adding userQuantity times the price to the subTotal
				subTotal += userQuantity * toyList.get(userChoice - 1).getPrice();

// We are storing the user input inside of para lol arrays. Like a boss.
				toyCart.add(toyList.get(userChoice - 1).getName());
				toyCartPrice.add(toyList.get(userChoice - 1).getPrice());
				toyCartNum.add(userQuantity);

// give user an option to check out after item is added
				System.out.println("Would you like to continue shopping?");
				userCont = ProductUtil.getUserCont(scnr, "(y/n): ", "[yYnN]");

				if (userCont.equalsIgnoreCase("n")) {
					break;
				}

			} else if (userCont.equalsIgnoreCase("n")) {
				System.out.println(
						ProductUtil.getUserCont(scnr, "Would you like to look for another toy? (y/n): ", "[yYnN]"));

				if (userCont.equalsIgnoreCase("n")) {
					break;
				}
				if (userCont.equalsIgnoreCase("y")) {
					continue;
				}

			}

		}

		System.out.println("Thanks for shopping with us at Toys 'R' Us!");
		System.out.println("\n");
//This is where we ask for payment type.
		System.out.println("How would you like to pay? ");
		
		System.out.print("Your grand total is: $");
		System.out.printf("%-9.2f", (grandTotal += subTotal * salesTax));
		System.out.println();

		
		
// This loop is for an invoice. It prints out the items ordered and their price.
		for (int i = 0; i < toyCart.size(); i++) {
			System.out.println(toyCartNum.get(i) + " " + toyCart.get(i) + " " + toyCartPrice.get(i));
		}

		System.out.println("\nYour grand total is: " + (grandTotal += subTotal * salesTax));

	}
// END MAIN METHOD
}