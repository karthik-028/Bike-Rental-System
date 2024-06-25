package com.vehiclejdbc;

import java.util.Scanner;

public class RenterMenu {
	private RentalSystem rentalSystem;
	private Scanner scanner;

	public RenterMenu(RentalSystem rentalSystem, Scanner scanner) {
		this.rentalSystem = rentalSystem;
		this.scanner = scanner;
	}

	public void displayRenterMenu() {
		while (true) {
			System.out.println("\nRenter Menu:");
			System.out.println("1. View Available Vehicles");
			System.out.println("2. Rent a Vehicle");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				rentalSystem.displayAvailableVehicles();
				break;
			case 2:
				rentVehicle();
				break;
			case 3:
				System.out.println("Exiting Renter Menu.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void rentVehicle() {
		System.out.print("Enter the make of the vehicle you want to rent: ");
		String make = scanner.nextLine();
		System.out.print("Enter the model of the vehicle you want to rent: ");
		String model = scanner.nextLine();

		rentalSystem.rentVehicle(make, model);
	}

}
