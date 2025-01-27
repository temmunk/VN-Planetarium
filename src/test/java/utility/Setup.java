package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class Setup {
    public static void main(String[] args) {
        resetTestDatabase();
    }

    public static void resetTestDatabase() {
        Path sql = Path.of("src/test/resources/setup-reset.sql");
        StringBuilder sqlBuilder = new StringBuilder();

        try (Connection conn = DatabaseConnector.getConnection();
             Stream<String> lines = Files.lines(sql)) {

            conn.setAutoCommit(false);
            lines.forEach(sqlBuilder::append);
            String sqlString = sqlBuilder.toString();

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