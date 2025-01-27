package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {

        String url = System.getenv("DATABASE_URL");
        return DriverManager.getConnection(url);
    }

}
