package com.vehiclejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class RentalSystem {
	private UserDao userDao;
	private VehicleDao vehicleDao;
	private User currentUser;

	public RentalSystem() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Education", "root", "");
			this.userDao = new UserDao(connection);
			this.vehicleDao = new VehicleDao(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.currentUser = null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void signIn(String email, String password) {
		try {
			User user = userDao.getUserByEmail(email);
			if (user != null && user.getPassword().equals(password)) {
				currentUser = user;
				System.out.println("Sign-in successful. Welcome, " + currentUser.getRole() + "!");
			} else {
				System.out.println("Invalid email or password. Please try again.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void signUp(String email, String password, String role) {
		try {
			User newUser = new User(email, password, role);
			userDao.addUser(newUser);
			System.out.println("Sign-up successful. Welcome, " + newUser.getRole() + "!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void displayAvailableVehicles() {
		try {
			List<Vehicle> vehicles = vehicleDao.getAllVehicles();
			System.out.println("Available Vehicles:");
			for (Vehicle vehicle : vehicles) {
				if (!vehicle.isRented()) {
					System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getType() + ")");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addVehicle(Vehicle vehicle) {
		try {
			vehicleDao.addVehicle(vehicle);
			System.out.println("Vehicle added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateVehicle(Vehicle vehicle) {
		try {
			vehicleDao.updateVehicle(vehicle);
			System.out.println("Vehicle updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteVehicle(String make, String model) {
		try {
			vehicleDao.deleteVehicle(make, model);
			System.out.println("Vehicle deleted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rentVehicle(String make, String model) {
		try {
			Vehicle vehicle = vehicleDao.getVehicle(make, model);
			if (vehicle != null && !vehicle.isRented()) {
				vehicle.setRented(true);
				vehicleDao.updateVehicle(vehicle);
				System.out.println("Vehicle rented successfully.");
			} else {
				System.out.println("Vehicle not available for rent. Please check the vehicle details and try again.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Vehicle> getVehicles() {
		try {
			return vehicleDao.getAllVehicles();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
