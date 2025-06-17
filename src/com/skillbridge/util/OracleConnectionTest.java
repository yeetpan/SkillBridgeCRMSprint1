package com.skillbridge.util;

import java.sql.*;

public class OracleConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//192.168.1.41:1521/xepdb1";
        String username = "system"; // your Oracle username
        String password = "tanishq100"; // your Oracle password

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn.isValid(10)) { // 10 second timeout
                System.out.println("Connection successful!");

                // Test query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT SYSDATE FROM DUAL");
                rs.next();
                System.out.println("Current Oracle time: " + rs.getTimestamp(1));

                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

