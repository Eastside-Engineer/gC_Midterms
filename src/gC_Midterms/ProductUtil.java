package gC_Midterms;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductUtil {

	// get the file path so we can read it in the getPrompt method
	private static Path filePath = Paths.get("inventory.txt");

	// private or protected????
	static List<String> getPrompt() throws IOException {

		List<String> books = Files.readAllLines(filePath);

		return books;
	}

	public static int getProductNum(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getProductNum(scnr, prompt);
		}
	}

	public static int getProductNum(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getProductNum(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

}
//Change Log:
//13:54 PM 2/2/2019 - Removed ProductUtil "extends Product"
//This file just contains methods we will call in the main. This
//is not an extension of the product class.
