package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

            // First, collect all SQL statements
            lines.forEach(sqlBuilder::append);
            String fullSql = sqlBuilder.toString();

            // Split statements more carefully to handle parameterized queries
            List<String> statements = splitSqlStatements(fullSql);

            int imageCount = 1;
            for (String sqlStatement : statements) {
                sqlStatement = sqlStatement.trim();
                if (sqlStatement.isEmpty()) {
                    continue;
                }

                if (sqlStatement.contains("$1")) {
                    String type = sqlStatement.contains("moons") ? "moon" : "planet";
                    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
                        byte[] imageData = convertImgToByteArray(
                                String.format("src/test/resources/Celestial-Images/%s-%d.jpg", type, imageCount)
                        );
                        ps.setBytes(1, imageData);
                        ps.executeUpdate();
                        imageCount = imageCount == 2 ? 1 : 2;
                    }
                } else {
                    try (Statement stmt = conn.createStatement()) {
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

    private static List<String> splitSqlStatements(String sql) {
        List<String> statements = new ArrayList<>();
        StringBuilder currentStatement = new StringBuilder();
        boolean inString = false;
        char[] chars = sql.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            currentStatement.append(c);

            if (c == '\'') {
                // Handle string literals
                if (i == 0 || chars[i-1] != '\\') {
                    inString = !inString;
                }
            } else if (c == ';' && !inString) {
                // Only split on semicolons that are not inside string literals
                String stmt = currentStatement.toString().trim();
                if (!stmt.isEmpty()) {
                    statements.add(stmt);
                }
                currentStatement = new StringBuilder();
            }
        }

        // Add the last statement if it doesn't end with a semicolon
        String lastStmt = currentStatement.toString().trim();
        if (!lastStmt.isEmpty()) {
            statements.add(lastStmt);
        }

        return statements;
    }

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}