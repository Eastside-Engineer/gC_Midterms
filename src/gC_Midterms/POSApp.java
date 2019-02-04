package gC_Midterms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class POSApp {

	private static Path filePath = Paths.get("inventory.txt");

// possibly need to remove throws IO because not proper practice use try/catch instead 

	public static void main(String[] args) throws IOException {

// check for file existence for it is important
		checkFile(filePath);
// methods that prints inventory
		// getPrompt();

		for (Toy p : readFile()) {
			System.out.println(p);
		}
		// test printing 1 product

	}
// END MAIN METHOD

// might move this to product util class later

	protected static void getPrompt() throws IOException {

		System.out.println("Welcome to the Hotel California\n");
// cycle through products and print them on lines
		List<String> toyInventory = Files.readAllLines(filePath);

		for (String thisToy : toyInventory) {
			System.out.println(thisToy);

		}
	}

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