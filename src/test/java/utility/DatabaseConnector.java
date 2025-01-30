package utility;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DATABASE_URL");
        return DriverManager.getConnection(url);
    }
}
