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
		double tendered = 0.00;
		String checkNumber = "0";
		String creditCardNumber = "0";
		String creditCardCVV = "0";
		String creditCardExp = "0";
		List<String> toyCart = new ArrayList<String>();
		List<Double> toyCartPrice = new ArrayList<Double>();
		List<Double> toyCartNum = new ArrayList<Double>();
		Scanner scnr = new Scanner(System.in);

// check for file existence for it is important
		ProductUtil.checkFile(filePath);
// methods that prints inventory

		List<Toy> toyList = ProductUtil.readFile();
		while (run == true) {
			ProductUtil.getPrompt();
// begin user input for adding items to SHOPPING CART

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
//This should only display when there are no items in the cart and user exits.
		System.out.println("Thanks for shopping with us at Toys 'R' Us!");

		System.out.println("\n");
//This is where we ask for payment type.
		System.out.println("How would you like to pay? ");
		System.out.print("Your grand total is: $");
		System.out.printf("%-9.2f", (grandTotal = subTotal * salesTax));

		int paymentChoice = ProductUtil.getProductNum(scnr, "Enter 1 for Cash, 2 for Check, 3 for Credit Card: ", 1, 3);
		if (paymentChoice == 1) {

			tendered = ProductUtil.getQuantityDouble(scnr, "Cash tendered amount: ", grandTotal, (grandTotal + 100));

		} else if (paymentChoice == 2) {
			checkNumber = ProductUtil.getCheck(scnr, "Please enter your Check number: ", 1, 9999);
			System.out.println("Thank you.");
			System.out.println(checkNumber);

		} else if (paymentChoice == 3) {
//We wanted to make sure we could validate all major credit cards.
			creditCardNumber = ProductUtil.getCreditCard(scnr, "Please enter your credit card number without dashes or spaces: ",
					"^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
							+ "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13}))$");
			creditCardCVV = ProductUtil.getCreditCard(scnr, "Please enter 3-digit security code on the back of your card: ", "(^[0-9]{3,4})$");
			creditCardExp = ProductUtil.getCreditCardExp(scnr, "Please enter your expiration date: (mm/yyyy) ", "([1-9]|0[1-9]|1[012])/([0-9][0-9])");
			
			//System.out.println(creditCardNumber);
		}
		System.out.println("Here's your itemized receipt: ");
		if (paymentChoice == 1) {
			for (int i = 0; i < toyCart.size(); i++) {
				System.out.println(toyCartNum.get(i) + " " + toyCart.get(i) + " " + toyCartPrice.get(i));
			}
			System.out.println("Your change is: $" + ProductUtil.getChange(tendered, grandTotal));
		}
		if (paymentChoice == 2) {
			for (int i = 0; i < toyCart.size(); i++) {
				System.out.println(toyCartNum.get(i) + " " + toyCart.get(i) + " " + toyCartPrice.get(i));
			}
			System.out.println("Thank you for your check number: #" + checkNumber);
		}
		if (paymentChoice == 3) {
			for (int i = 0; i < toyCart.size(); i++) {
				System.out.println(toyCartNum.get(i) + " " + toyCart.get(i) + " " + toyCartPrice.get(i));
			}
			System.out.println("Thank you for your credit card payment ending in: " + creditCardNumber.substring(12));
		}


		System.out.println("\nYour grand total is: " + (grandTotal = subTotal * salesTax));

	}
// END MAIN METHOD
}
