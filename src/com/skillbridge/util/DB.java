package com.skillbridge.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String url="jdbc:mysql://localhost:3306/skilldb";
    private static final String username="root";
    private static final String password="password";
    static Connection connection=null;
public static Connection connect(){
    try{
         connection=DriverManager.getConnection(url,username,password);

    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    return connection;
}
}
