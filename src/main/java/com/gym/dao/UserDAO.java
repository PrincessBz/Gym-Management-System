package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.database.DatabaseConnection;
import com.gym.user.User;



public class UserDAO {

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean  registerUser(User user) {
        String query = "INSERT INTO users (username, password ,email, phonenumber, address, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhonenumber());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getRole());
            
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error registering user: " + e.getMessage());
                
            }
            return false;
        }
    
    @SuppressWarnings("CallToPrintStackTrace")
        public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving user by email: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return users;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";    

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting user: " + e.getMessage());
        }
        return false;
    }
      
      
        
    }
	
