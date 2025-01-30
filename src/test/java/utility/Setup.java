package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

        try (Connection conn = DatabaseConnector.getConnection()) {
            conn.setAutoCommit(false);

            // First, execute DROP statements separately
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DROP TABLE IF EXISTS moons CASCADE");
                stmt.executeUpdate("DROP TABLE IF EXISTS planets CASCADE");
                stmt.executeUpdate("DROP TABLE IF EXISTS users CASCADE");
            }

            try (Stream<String> lines = Files.lines(sql)) {
                lines.forEach(sqlBuilder::append);
            }

            String sqlString = sqlBuilder.toString();
            String[] sqlStatements = sqlString.split(";");

            int imageCount = 1;

            for (String sqlStatement : sqlStatements) {
                sqlStatement = sqlStatement.trim();
                if (sqlStatement.isEmpty()) continue;

                if (sqlStatement.contains("?")) {  // Image loading logic
                    String type = sqlStatement.contains("moons") ? "moon" : "planet";
                    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
                        byte[] imageData = convertImgToByteArray(
                                String.format("src/test/resources/Celestial-Images/%s-%d.jpg", type, imageCount)
                        );
                        ps.setBytes(1, imageData);
                        ps.executeUpdate();
                        imageCount = imageCount == 2 ? 1 : 2;
                    }
                } else {  // Regular SQL execution
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

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}