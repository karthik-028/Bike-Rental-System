package com.vehiclejdbc;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RentalSystem rentalSystem = new RentalSystem();
		AdminMenu adminMenu = new AdminMenu(rentalSystem, scanner);
		RenterMenu renterMenu = new RenterMenu(rentalSystem, scanner);

		while (true) {
			System.out.println("\nMain Menu:");
			System.out.println("1. Sign In");
			System.out.println("2. Sign Up");
			System.out.println("3. Admin Menu");
			System.out.println("4. Renter Menu");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				signIn(rentalSystem, scanner);
				break;
			case 2:
				signUp(rentalSystem, scanner);
				break;
			case 3:
				adminMenu.displayAdminMenu();
				break;
			case 4:
				renterMenu.displayRenterMenu();
				break;
			case 5:
				System.out.println("Exiting program. Goodbye!");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void signIn(RentalSystem rentalSystem, Scanner scanner) {
		System.out.print("Enter your email: ");
		String email = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		rentalSystem.signIn(email, password);
	}

	private static void signUp(RentalSystem rentalSystem, Scanner scanner) {
		System.out.print("Enter your email: ");
		String email = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		System.out.print("Enter your role (Admin/Renter): ");
		String role = scanner.nextLine();
		rentalSystem.signUp(email, password, role);
	}

}
