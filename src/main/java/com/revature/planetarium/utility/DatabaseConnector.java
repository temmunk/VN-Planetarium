package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    static {
        testDatabaseConnection(); // Test connection on startup
    }

    public static Connection getConnection() throws SQLException {

        String url = System.getenv("DATABASE_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
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
