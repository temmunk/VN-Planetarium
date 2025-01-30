package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {
    public static void main(String[] args) {
        resetTestDatabase();
    }

    public static void resetTestDatabase() {
        Path sql = Path.of("src/test/resources/setup-reset.sql");

        try (Connection conn = DatabaseConnector.getConnection()) {
            conn.setAutoCommit(false);

            // First, execute DROP statements separately
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DROP TABLE IF EXISTS moons CASCADE");
                stmt.executeUpdate("DROP TABLE IF EXISTS planets CASCADE");
                stmt.executeUpdate("DROP TABLE IF EXISTS users CASCADE");
            }

            // Then read and execute the rest of the script
            String sqlString = Files.readString(sql);


            try (Statement stmt = conn.createStatement()) {
                for (String sqlStatement : sqlString.split(";")) {
                    sqlStatement = sqlStatement.trim();
                    if (!sqlStatement.isEmpty()) {
                        stmt.executeUpdate(sqlStatement);
                    }
                }
            }

            conn.commit();
            System.out.println("Database setup complete");

        } catch (IOException | SQLException e) {
            System.err.println("Error during database setup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}