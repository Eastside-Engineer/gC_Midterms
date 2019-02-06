package gC_Midterms;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class POSApp {

	private static Path filePath = Paths.get("inventory.txt");

// possibly need to remove throws IO because not proper practice use try/catch instead 

	public static void main(String[] args) throws IOException {

//Creating variables to hold items selected for cart
		double subTotal = 0;
		double grandTotal = 0;
		final double salesTaxMultiplier = 1.06;
		final double salesTax = 0.06;
		double theSalesTax = 0;
		boolean run = true;
		boolean fullLoop = true;
		double tendered = 0.00;
		String checkNumber = "0";
		String creditCardNumber = "0";
		String creditCardCVV = "0";
		Date creditCardExp = null;
		List<String> toyCart = new ArrayList<String>();
		List<Double> toyCartPrice = new ArrayList<Double>();
		List<Double> toyCartNum = new ArrayList<Double>();
		Scanner scnr = new Scanner(System.in);

// check for file existence for it is important
		ProductUtil.checkFile(filePath);
// methods that prints inventory
		do {
			List<Toy> toyList = ProductUtil.readFile();
			while (run == true) {

				ProductUtil.printGiraffe();
				ProductUtil.printLogo();

				System.out.println();

				ProductUtil.getPrompt();
// begin user input for adding items to SHOPPING CART

				int userChoice = ProductUtil.getProductNum(scnr, "Enter a product number: ", 1, toyList.size());
				System.out.println("\nDo you want to add " + toyList.get(userChoice - 1).getCategory() + ", "
						+ toyList.get(userChoice - 1).getName() + " to your cart?");

// conditional logic to validate user input
				String userCont = ProductUtil.getUserCont(scnr, "(y/n): ", "[yYnN]");

				if (userCont.equalsIgnoreCase("y")) {

					double userQuantity = ProductUtil.getQuantityDouble(scnr,
							"\nHow many of " + toyList.get(userChoice - 1).getName() + " would you like to add?:", 0,
							500);
					System.out.println((int) userQuantity + " of " + toyList.get(userChoice - 1).getName()
							+ " have been added to the cart.");
// Adding userQuantity times the price to the subTotal
					subTotal += userQuantity * toyList.get(userChoice - 1).getPrice();

// We are storing the user input inside of para lol arrays. Like a boss.
					toyCart.add(toyList.get(userChoice - 1).getName());
					toyCartPrice.add(toyList.get(userChoice - 1).getPrice());
					toyCartNum.add(userQuantity);

// give user an option to check out after item is added
					System.out.println("\nWould you like to continue shopping?");
					userCont = ProductUtil.getUserCont(scnr, "(y/n): ", "[yYnN]");

					if (userCont.equalsIgnoreCase("n")) {
						break;
					}

				} else if (userCont.equalsIgnoreCase("n")) {
					System.out.println(ProductUtil.getUserCont(scnr,
							"\nWould you like to look for another toy? (y/n): ", "[yYnN]"));

					if (userCont.equalsIgnoreCase("n")) {
						break;
					}
					if (userCont.equalsIgnoreCase("y")) {
						continue;
					}

				}

			}
//This should only display when there are no items in the cart and user exits.
			System.out.println("\nThanks for shopping with us at Toys 'R' Us!");

			System.out.println("\n");
//This is where we ask for payment type.
			if (toyCart.size() > 0) {
				System.out.println("How would you like to pay? ");
				theSalesTax = subTotal * salesTax;
				grandTotal = subTotal * salesTaxMultiplier;
//This is where totals are printed out for the receipt.
				System.out.print("Subtotal: ");
				System.out.printf("$%-9.2f", subTotal);
				System.out.print("\nSales Tax: ");
				System.out.printf("$%-9.2f", theSalesTax);
				System.out.print("\nGrand Total: ");
				System.out.printf("$%-9.2f", grandTotal);
				System.out.println();

				int paymentChoice = ProductUtil.getProductNum(scnr, "\n1 for Cash \n2 for Check \n3 for Credit Card: ",
						1, 3);
				if (paymentChoice == 1) {

					tendered = ProductUtil.getQuantityDouble(scnr, "\nCash tendered amount: ", grandTotal,
							(grandTotal + 100));

				} else if (paymentChoice == 2) {
					checkNumber = ProductUtil.getCheck(scnr, "\nPlease enter your Check number: ", 1, 9999);
					System.out.println("\nThank you.");
					System.out.println(checkNumber);

				} else if (paymentChoice == 3) {
//We wanted to make sure we could validate all major credit cards.
					creditCardNumber = ProductUtil.getCreditCard(scnr,
							"\nPlease enter your credit card number without dashes or spaces: ",
							"^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
									+ "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13}))$");

					creditCardExp = ProductUtil.getCreditCardEXP(scnr,
							"\nPlease enter an expiration date in the format (MM/YYYY): ");

					creditCardCVV = ProductUtil.getCreditCard(scnr,
							"\nPlease enter 3-digit security code on the back of your card: ", "(^[0-9]{3,4})$");

				}
				ProductUtil.getInvoiceHeader();

				if (paymentChoice == 1) {

					for (int i = 0; i < toyCart.size(); i++) {
						System.out.printf("%-12s", toyCartNum.get(i).intValue());
						System.out.printf("%-20s", " $" + (toyCartPrice.get(i)));
						System.out.printf("%-8s", toyCart.get(i));
						System.out.println("\n");

					}
					System.out.println();
					DecimalFormat decim = new DecimalFormat("0.00");
					System.out.println("Cash tendered: $" + decim.format(tendered));
					System.out.print("\nChange Given: ");
					System.out.printf("$%-9.2f", ProductUtil.getChange(tendered, grandTotal));
					System.out.println();

				}
				if (paymentChoice == 2) {
					for (int i = 0; i < toyCart.size(); i++) {
						System.out.printf("%-12s", toyCartNum.get(i).intValue());
						System.out.printf("%-20s", " $" + (toyCartPrice.get(i)));
						System.out.printf("%-8s", toyCart.get(i)); 
						System.out.println("\n");
				
					}
					System.out.println("\nCheck Number " + checkNumber + " has been recorded.");
				}
				if (paymentChoice == 3) {
					for (int i = 0; i < toyCart.size(); i++) {
						System.out.printf("%-12s", toyCartNum.get(i).intValue());
						System.out.printf("%-20s", " $" + (toyCartPrice.get(i)));
						System.out.printf("%-8s", toyCart.get(i));
						System.out.println("\n");
					}
					System.out.println(
							"\nCredit card ending in  -" + creditCardNumber.substring(12) + " has been approved. ");
				}

			} else {
				run = true;
			}

			String newOrder = ProductUtil.getUserCont(scnr, "Would you like to place a new order? (y/n)", "[yYnN]");
			if (newOrder.equalsIgnoreCase("n")) {
				fullLoop = false;
				System.out.println("Have a day full of fun and return to Toys 'R' Us!");
				break;
			} else {
				fullLoop = true;

			}

		} while (fullLoop = true);
// END MAIN METHOD
	}
}
