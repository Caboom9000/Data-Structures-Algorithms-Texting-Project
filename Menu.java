package messageappgp;

import java.util.Scanner;

public class Menu {
	
	private final Scanner scanner;

	public Menu(Scanner scanner) {
		this.scanner = scanner;
	}
	
	//method to show main menu
	public int showMenu() {
		int choice = 0;
		while (choice < 1 || choice > 4) {
			
			System.out.println("       Menu");
			System.out.println("\n1. Add contact");
			System.out.println("2. Remove contact");
			System.out.println("3.View contacts");
			System.out.println("4. Search contact");
			System.out.println("5. Exit");
			System.out.println("\nEnter you choice (1-5):");
		
			if (scanner.hasNextInt()){
				choice = scanner.nextInt();
						scanner.nextLine();
			}
			else {
				System.out.println("Enter a number between 1-4");
				scanner.nextLine();
			}
		}
		return choice;
	}
	//method for search menu
		public void search() {
			int choice = 0;
			while (choice < 1 || choice > 4) {
			System.out.println("\n Search contacts");
			System.out.println("1. Search by name");
			System.out.println("2. Search by ID");
			System.out.println("3. Search by Phone number");
			System.out.println("4. Return to menu");
			System.out.println("Choose an option (1-4)");
			}
		}
}
