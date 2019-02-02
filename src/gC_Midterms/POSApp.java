package gC_Midterms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

// might move this to product util class later

	protected static void getPrompt() throws IOException {

		System.out.println("Welcome to the Hotel California\n");

		List<String> books = Files.readAllLines(filePath);
// cycle through products and print them on lines

		for (String thisBook : books) {
			System.out.println(thisBook);

		}
	
		System.out.println("\nPlease type the item you wud like to purchase");

	}

	public static void checkFile(Path filePath) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}
}
