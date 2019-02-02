package gC_Midterms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class POSApp {

	private static Path filePath = Paths.get("inventory.txt");

// possibly need to remove throws IO because not proper practice

	public static void main(String[] args) throws IOException {

// check for file existence for it is important
		checkFile(filePath);
// methods that prints inventory
		getPrompt();

	}
// END MAIN METHOD

// might move this to product util class later

	protected static void getPrompt() throws IOException {

		System.out.println("Welcome to the Hotel California\n");
// cycle through products and print them on lines
		List<String> books = Files.readAllLines(filePath);

		for (String thisBook : books) {
			System.out.println(thisBook);

		}
	}

// creating a list of product objects called "products" and setting it equal to an array list separated b ycom

	public static List<Product> readFile() throws IOException {
		List<String> books = Files.readAllLines(filePath);
		List<Product> products = new ArrayList<>();

		for (String thisBook : books) {
			String[] parts = books.split(",");
			Product p = new Product(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3]);
		}

		System.out.println("\nPlease type the item you wud like to purchase");

	}

	public static void checkFile(Path filePath) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}
}
