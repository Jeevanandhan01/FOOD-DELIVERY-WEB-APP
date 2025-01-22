package com.jeeva.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection provideConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:mysql://localhost:3306/food_delivery_db";
        try {
            conn = DriverManager.getConnection(url,"root","james@5,7373");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exception occurred while connecting to database");
        }
        return conn;
    }
}
