package com.vehiclejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private Connection connection;

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public void addUser(User user) throws SQLException {
		String query = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole());
			statement.executeUpdate();
		}
	}

	public User getUserByEmail(String email) throws SQLException {
		String query = "SELECT * FROM users WHERE email = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new User(resultSet.getString("email"), resultSet.getString("password"),
							resultSet.getString("role"));
				}
			}
		}
		return null;
	}

	public List<User> getAllUsers() throws SQLException {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				users.add(new User(resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("role")));
			}
		}
		return users;
	}
}
