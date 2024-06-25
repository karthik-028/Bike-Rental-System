package com.vehiclejdbc;

import java.util.Scanner;

public class AdminMenu {
	private RentalSystem rentalSystem;
	private Scanner scanner;

	public AdminMenu(RentalSystem rentalSystem, Scanner scanner) {
		this.rentalSystem = rentalSystem;
		this.scanner = scanner;
	}

	public void displayAdminMenu() {
		while (true) {
			System.out.println("\nAdmin Menu:");
			System.out.println("1. Add a Vehicle");
			System.out.println("2. Modify Vehicle Specifications");
			System.out.println("3. Delete a Vehicle");
			System.out.println("4. View List of Vehicles");
			System.out.println("5. Search for a Vehicle");
			System.out.println("6. Change Security Deposit Amount");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addVehicle();
				break;
			case 2:
				modifyVehicleSpecifications();
				break;
			case 3:
				deleteVehicle();
				break;
			case 4:
				rentalSystem.displayAvailableVehicles();
				break;
			case 5:
				searchForVehicle();
				break;
			case 6:
				changeSecurityDepositAmount();
				break;
			case 7:
				System.out.println("Exiting Admin Menu.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void addVehicle() {
		System.out.print("Enter the make of the vehicle: ");
		String make = scanner.nextLine();
		System.out.print("Enter the model of the vehicle: ");
		String model = scanner.nextLine();
		System.out.print("Enter the type of the vehicle (Car/Bike): ");
		String type = scanner.nextLine();
		System.out.print("Enter the rental price per day: ");
		double rentalPrice = scanner.nextDouble();
		System.out.print("Enter the security deposit amount: ");
		double securityDeposit = scanner.nextDouble();

		Vehicle newVehicle = new Vehicle(make, model, type, rentalPrice, securityDeposit);
		rentalSystem.addVehicle(newVehicle);
		System.out.println("Vehicle added successfully.");
	}

	private void modifyVehicleSpecifications() {
		System.out.print("Enter the make of the vehicle you want to modify: ");
		String make = scanner.nextLine();
		System.out.print("Enter the model of the vehicle you want to modify: ");
		String model = scanner.nextLine();

		Vehicle vehicle = rentalSystem.getVehicles().stream()
				.filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)).findFirst()
				.orElse(null);

		if (vehicle != null) {
			System.out.println("Vehicle found. Enter new specifications:");
			System.out.print("Enter the new rental price per day: ");
			double newRentalPrice = scanner.nextDouble();
			System.out.print("Enter the new security deposit amount: ");
			double newSecurityDeposit = scanner.nextDouble();

			vehicle.setRentalPrice(newRentalPrice);
			vehicle.setSecurityDeposit(newSecurityDeposit);
			rentalSystem.updateVehicle(vehicle);
			System.out.println("Vehicle specifications modified successfully.");
		} else {
			System.out.println("Vehicle not found. Please check the vehicle details and try again.");
		}
	}

	private void deleteVehicle() {
		System.out.print("Enter the make of the vehicle you want to delete: ");
		String make = scanner.nextLine();
		System.out.print("Enter the model of the vehicle you want to delete: ");
		String model = scanner.nextLine();

		rentalSystem.deleteVehicle(make, model);
	}

	private void searchForVehicle() {
		System.out.print("Enter the make of the vehicle you want to search: ");
		String make = scanner.nextLine();
		System.out.print("Enter the model of the vehicle you want to search: ");
		String model = scanner.nextLine();

		Vehicle vehicle = rentalSystem.getVehicles().stream()
				.filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)).findFirst()
				.orElse(null);

		if (vehicle != null) {
			System.out.println("Vehicle found:");
			System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getType() + ")");
		} else {
			System.out.println("Vehicle not found. Please check the vehicle details and try again.");
		}
	}

	private void changeSecurityDepositAmount() {
		System.out.print("Enter the new security deposit amount: ");
		double newSecurityDeposit = scanner.nextDouble();
		for (Vehicle vehicle : rentalSystem.getVehicles()) {
			vehicle.setSecurityDeposit(newSecurityDeposit);
			rentalSystem.updateVehicle(vehicle);
		}
		System.out.println("Security deposit amount changed successfully.");
	}
}
