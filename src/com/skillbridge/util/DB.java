package com.skillbridge.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public final class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/skillbridge";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    // Private constructor to prevent instantiation
    private DB() {}

    /**
     * Establishes and returns a connection to the database.
     *
     * @return Connection object or null if connection fails
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Closes the provided database connection.
     *
     * @param conn the Connection object to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
