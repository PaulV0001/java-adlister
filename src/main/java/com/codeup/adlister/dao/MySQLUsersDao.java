package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<User> all() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        // Write the SQL
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            // Create a prepared statement using the SQL
            PreparedStatement statement = connection.prepareStatement(sql);
            // Bind the parameters values
            // ' " % `
            statement.setString(1, username);
            // Execute the select & Retrieve the ResultSet
            ResultSet resultSet = statement.executeQuery();
            // If there's at least one row, build a User
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // otherwise return null
        return null;
    }

    @Override
    public Long insert(User user) {
        // Write the SQL for the insert
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try {
            // Create a prepared statement with the insert, request the generated keys
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            // Bind the parameters with their values
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            // Execute the query (copy/paste)
            statement.executeUpdate();
            // Return the generated key value
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
