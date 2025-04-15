package com.gym.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   
    private static final String URL = "jdbc:postgresql://localhost:5432/gym";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1964";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws SQLException {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


} 