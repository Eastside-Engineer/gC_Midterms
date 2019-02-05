package gC_Midterms;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductUtil {

	// get the file path so we can read it in the getPrompt method
	private static Path filePath = Paths.get("inventory.txt");

	// private or protected????
	protected static void getPrompt() throws IOException {

		System.out.println("\t\t   Welcome to Toys R' Us. The most profitable, long lasting Toy store in the world.\n");
// cycle through products and print them on lines
		List<String> toyInventory = Files.readAllLines(filePath);
		List<Toy> toyList = readFile();
		System.out.println(
				"# === Category === Price ============ Name ================ Description =============================================== ");
		for (int i = 0; i < toyList.size(); i++) {
// header for inventory print out 

			System.out.printf("%-9s", i + 1 + ". "); // using the incrementor +1 to denote index AKA product number for
														// user
			System.out.printf("%-8s", toyList.get(i).getCategory());
			System.out.printf("%-12s", " $" + toyList.get(i).getPrice());// input
			System.out.printf("%-30s ", toyList.get(i).getName());
			System.out.printf("%-50s", toyList.get(i).getDescription());
			System.out.println("\n");

		}
	}

// creating a list of toys objects called "toyInventory" and setting it equal to an array list separated by commas.
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

	public static int getProductNum(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.err.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getProductNum(scnr, prompt);
		}
	}

	public static double getQuantityDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = Validator.getDouble(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.err.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.err.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	public static int getProductNum(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getProductNum(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.err.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.err.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	public static String getUserCont(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = Validator.getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.err.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	public static Double getChange(double tendered, double grandTotal) {

		// subtracts cash tendered (SCNR) from grand TOtal

		double change = tendered - grandTotal;

		return change;
	}

	public static String getCheck(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int checkNumber;
		do {
			checkNumber = getProductNum(scnr, prompt);

			if (checkNumber < min) {
				isValid = false;
				System.err.println("The check number must be at least " + min);
			} else if (checkNumber > max) {
				isValid = false;
				System.err.println("The check number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		if (checkNumber < 10) {
			return "000" + checkNumber;
		}
		if (checkNumber > 10 && checkNumber < 100) {
			return "00" + checkNumber;
		}
		if (100 < checkNumber && checkNumber < 1000) {
			return "0" + checkNumber;
		}
		return "" + checkNumber;
	}

	public static String getCreditCard(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = Validator.getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.err.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	public static Date getCreditCardEXP(Scanner scnr, String prompt) {
		SimpleDateFormat format = new SimpleDateFormat("MM/YYYY");
		format.setLenient(false); // <-- date format must match
		boolean isValid = false;
		Date date = null;
		String input;
		do {
			// Step 1: get the raw string
			input = Validator.getString(scnr, prompt);
			// Step 2: convert it to a date
			try {
				// format.parse throws a ParseException, which is a checked exception and MUST
				// be caught.
				date = format.parse(input);
				// If exception doesn't occur, it's valid.
				isValid = true;
			} catch (ParseException ex) {
				// If exception occurs, it's invalid.
				isValid = false;
				System.out.println("Enter a valid date in format MM/YYYY.");
			}

		} while (!isValid);
		return date;
	}

	public static String getCreditCardCVV(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = Validator.getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.err.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	public static String getDate(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = Validator.getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.err.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	



	public static void printLogo() {

System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(((@@@@@@@@@@@@((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
System.out.println("@@@@@@@@%((((((@@@@@%,,,,,,,&@@@@*,,@@@@@@@,,@@@@&(((((((((@@@@(((@(@&((((((((@@(@((@@@@@,,,,,@@@@,,,,@@@@(((((((((@@@@");
System.out.println("(((((((((((((((@@@,,,,,,,,,,,,@@,,,,,,@@@,,,,,@@(((((((@#(#@@@@@@@@((((((((((((((@@@@@@@,,,,,,@@@@,,,,@@(((((((##((@@@@");
System.out.println("((((((((((((((@@,,,,,,,,,,,,,,,@@,,,,,,,,,,,,,@@(((((((@@@@@@@@@@@@((((#@@@@(((((@@@@@@,,,,,,@@@@@,,,,@@(((((((@@@@@@@@");
System.out.println("@@@@(((((((@@@@,,,,,@@@,,,,,,,,@@@*,,,,,,,,,,@@@((((((((((@@@@@@@@@((((((((((((((@@@@@@,,,,,@@@@@,,,,@@@#(((((((((%@@@@");
System.out.println("@@@@@@(((((@@@@,,,,,@@@@@,,,,,@@@@@@,,,,,,,@@@@@@@((((((((((@@@@@@@@@(((((((((((@@@@@@@,,,,,@@@@,,,,,@@@@@@(((((((((@@@");
System.out.println("@@@@@@(((((@@@@,,,,,&@@@@,,,,,@@@@@,,,,,,,@@@@@(@@@@@((((((((@@@@@@@((((((((((((@@@@@@@,,,,,,,,,,,,,@@@@&@@@@@(((((((@@");
System.out.println("@@@@@@@((((#@@@@,,,,,,,,,,,,@@@@@,,,,,,,@@@@@@@(((((((((((((@@@@@@(((((@@@(((((@@@@@@@,,,,,,,,,,,,@@@@((((((((((((((@@@");
System.out.println("@@@@@@@(((((@@@@@,,,,,,,,,@@@@@@@*,,,,@@@@@@@@@@@((((((((((@@@@@@@((((@@@@(((((@@@@@@@@@@,,,,,,,@@@@@@@@@((((((((((@@@@");
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(((@@@@@@((((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
		
	}

	public static void printGiraffe () {
		
		
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/////#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(//(@@@@@@@@@@(/////(&@@@@@@@%//////(&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##@////(%@@@@@@(//#@@@@@@@@@(////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(%@@@@@@@#//%@@@@&%%#@@@@@@@@@#////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/&@@@@@@@@&//&@@@@@@@@@@@@@@@#//////(#@@@@@@%#%&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/&@@@@@@@@@@@@@@@@@@@@@@@@@(/////(&@@@@@@@(////////(#&@@@(#@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(%@@@@@@@@&(&@@@@&%@@@@@#(///////%@@@@@@@(////##(////////////%@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&/(&@@@@(@@@#%/(/#@@@#////////(@@@@@@@///#@@@@@@@@@&%%@%///%@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%//(%@&//#&((%&&%###@@(/////(&@@@@@@@&///(@@@@@@@@@@@@@@#//(@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@//(///&@@@@(//#(///(@@@@@@@@@(///(@@@@@@@@@@@@@@#///&@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(  %////((   .%%//////(&@@@@@/////#@@@@@@@@@@@@@#///&@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   /(//(%      #(////////##(////////(@@@@@@@@@@@@(///%@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@& ,/.(//%,  .@@( #/////////////////////#@@@@@@@@%///(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@#////////#&@@@@&*@@#///#  ,@@@@((////////////(%&@@(/////////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@#///////////(@@@@/@@&///(  *@@@@#////////////&@@@@@@@@@(///////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@&///////////////%@/&@(////. .#@@@/////////////#@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@%////////////////(#/#(////((/////////////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@&//////////((//////////////////////////////&@@@%(///#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@&/////////(///%(///////////(%(//////////((&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@%(/////(%%////////////(%(////////(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@(#%///////////////(///////(///(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(((//((#&@&(////////&@@@&////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#(////#%(////#@@@@@#//(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%&@@@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(#((/////(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%///////(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&////////&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(////////#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(/////(%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(//////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(/////////#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&//////////#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%///////////%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
	}
	
	public static void getInvoiceHeader() {
		double rand = Math.random() * 1000 + 1;
		
		System.out.println("@@@@@@@@@@@@@@@((@@@@@@@@@@@@@@@@@@@@@@														");
		System.out.println("@@@@@@@//#&@@@%//%@@@@////@@@@@@@@@@@@@														");
		System.out.println("@@@@@@&&@@@@/@@@@@@@@@(///@@@@@@@@@@@@@														");
		System.out.println("@@@@@@&&@@@@@@@@@@@&///#@@@%//////#(/&@  ██╗███╗   ██╗██╗   ██╗ ██████╗ ██╗ ██████╗███████╗	");
		System.out.println("@@@@@@@((@@(@#&%(@(///#@@@#/@@@@@@@%/&@  ██║████╗  ██║██║   ██║██╔═══██╗██║██╔════╝██╔════╝	");
		System.out.println("@@@@@@@@@@%///%#&///(@@@@//#@@@@@@@/(@@  ██║██╔██╗ ██║██║   ██║██║   ██║██║██║     █████╗  	");
		System.out.println("@@@@@@@@@  (// * #//////////@@@@@(/#@@@  ██║██║╚██╗██║╚██╗ ██╔╝██║   ██║██║██║     ██╔══╝  	");
		System.out.println("@/////(@@/@// *@@//////#@@@@//////@@@@@  ██║██║ ╚████║ ╚████╔╝ ╚██████╔╝██║╚██████╗███████╗ ");    
		System.out.println("%///////(%&//,##(///////(/#@@@@@@@@@@@@  ╚═╝╚═╝  ╚═══╝  ╚═══╝   ╚═════╝ ╚═╝ ╚═════╝╚══════╝	");
		System.out.println("@/////#(/////////////(@@&%@@@@@@@@@@@@@														");
		System.out.println("@@((////////(%/////@@@@@@@@@@@@@@@@@@@@		   Toys R' Us INVOICE: \"" + (int) rand+"       ");
		System.out.println("@@@@@@@&@@#/(//(@@(/@@@@@@@@@@@@@@@@@@@														");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@		    	STORE # 3945 Detroit,MICHIGAN"+"        ");
		System.out.println("@@@@@@@@@@@@@@@@@@@/////@@@@@@@@@@@@@@@														");
		System.out.println("@@@@@@@@@@@@@@@@@@@@////@@@@@@@@@@@@@@@														");
		System.out.println("@@@@@@@@@@@@@@@@@@@(///%&@@@@@@@@@@@@@@														");
		System.out.println("@@@@@@@@@@@@@@@@/////@@@@@@@@@@@@@@@@@@														");
		System.out.println("");
		System.out.println("Quantity ==== Unit Price ============ Name =================================================");
  	}

}

//Change Log:
//13:54 PM 2/2/2019 - Removed ProductUtil "extends Product"
//This file just contains methods we will call in the main. This
//is not an extension of the product class.
