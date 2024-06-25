package com.vehiclejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
	private Connection connection;

	public VehicleDao(Connection connection) {
		this.connection = connection;
	}

	public void addVehicle(Vehicle vehicle) throws SQLException {
		String query = "INSERT INTO vehicles (make, model, type, rentalPrice, securityDeposit, rented) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, vehicle.getMake());
			statement.setString(2, vehicle.getModel());
			statement.setString(3, vehicle.getType());
			statement.setDouble(4, vehicle.getRentalPrice());
			statement.setDouble(5, vehicle.getSecurityDeposit());
			statement.setBoolean(6, vehicle.isRented());
			statement.executeUpdate();
		}
	}

	public Vehicle getVehicle(String make, String model) throws SQLException {
		String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, make);
			statement.setString(2, model);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new Vehicle(resultSet.getString("make"), resultSet.getString("model"),
							resultSet.getString("type"), resultSet.getDouble("rentalPrice"),
							resultSet.getDouble("securityDeposit"));
				}
			}
		}
		return null;
	}

	public List<Vehicle> getAllVehicles() throws SQLException {
		String query = "SELECT * FROM vehicles";
		List<Vehicle> vehicles = new ArrayList<>();
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				vehicles.add(new Vehicle(resultSet.getString("make"), resultSet.getString("model"),
						resultSet.getString("type"), resultSet.getDouble("rentalPrice"),
						resultSet.getDouble("securityDeposit")));
			}
		}
		return vehicles;
	}

	public void updateVehicle(Vehicle vehicle) throws SQLException {
		String query = "UPDATE vehicles SET rentalPrice = ?, securityDeposit = ?, rented = ? WHERE make = ? AND model = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, vehicle.getRentalPrice());
			statement.setDouble(2, vehicle.getSecurityDeposit());
			statement.setBoolean(3, vehicle.isRented());
			statement.setString(4, vehicle.getMake());
			statement.setString(5, vehicle.getModel());
			statement.executeUpdate();
		}
	}

	public void deleteVehicle(String make, String model) throws SQLException {
		String query = "DELETE FROM vehicles WHERE make = ? AND model = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, make);
			statement.setString(2, model);
			statement.executeUpdate();
		}
	}
}
