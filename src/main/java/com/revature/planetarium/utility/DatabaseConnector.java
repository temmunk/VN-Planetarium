package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DatabaseConnector {
    private static final String url = System.getenv("DATABASE_URL");
    private static final String username = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASSWORD");


    static {
        try {
            Class.forName("org.postgresql.Driver"); // Explicitly load PostgreSQL driver
            testDatabaseConnection();
        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL JDBC Driver not found. Make sure it's in your dependencies.");
            e.printStackTrace();
            System.exit(1); // Exit if driver is missing
        }
    }

    public static Connection getConnection() throws SQLException {


        return DriverManager.getConnection(url, username, password);
    }

    private static void testDatabaseConnection() {
        System.out.println("Checking database connectivity...");
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection established successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // Stop the application if database is unreachable
        }
    }

}
