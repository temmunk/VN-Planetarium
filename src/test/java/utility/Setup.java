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
        Path sqlPath = Path.of("src/test/resources/setup-reset.sql");
        StringBuilder sqlBuilder = new StringBuilder();

        try (Connection conn = DatabaseConnector.getConnection(); Stream<String> lines = Files.lines(sqlPath)) {
            conn.setAutoCommit(false);

            // Read SQL file and combine all lines
            lines.forEach(sqlBuilder::append);

            // Split SQL commands by semicolon to handle multiple statements
            String[] sqlStatements = sqlBuilder.toString().split(";");

            try (Statement stmt = conn.createStatement()) {
                for (String sqlStatement : sqlStatements) {
                    // Skip empty or whitespace-only statements
                    if (sqlStatement.trim().isEmpty()) continue;

                    // Execute the SQL statement
                    stmt.executeUpdate(sqlStatement.trim());
                }
            }

            conn.commit();
            System.out.println("Database setup complete");
        } catch (IOException e) {
            System.err.println("Failed to read SQL file: " + sqlPath);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to execute SQL statements");
            e.printStackTrace();
        }
    }
}
