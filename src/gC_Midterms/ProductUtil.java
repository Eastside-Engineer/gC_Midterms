package gC_Midterms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductUtil {

	// get the file path so we can read it in the getPrompt method
	private static Path filePath = Paths.get("inventory.txt");

	// private or protected????
	static List<String> getPrompt() throws IOException {

		List<String> books = Files.readAllLines(filePath);

		return books;
	}

	private static void checkForFile(Path filePath) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}
}
//Change Log:
//13:54 PM 2/2/2019 - Removed ProductUtil "extends Product"
//This file just contains methods we will call in the main. This
//is not an extension of the product class.
