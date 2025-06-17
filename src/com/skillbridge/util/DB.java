package com.skillbridge.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String url = "jdbc:oracle:thin:@//192.168.1.41:1521/xepdb1";
    private static final String username = "system";
    private static final String password = "tanishq100";
    static Connection connection = null;

    public static Connection connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found: " + e.getMessage());
        }
        return connection;
    }
}
