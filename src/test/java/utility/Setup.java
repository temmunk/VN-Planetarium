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
        try (Connection conn = DatabaseConnector.getConnection(); Stream<String> lines = Files.lines(sql)) {
            conn.setAutoCommit(false);
            lines.forEach(sqlBuilder::append);
            String sqlString = sqlBuilder.toString();
            String[] sqlStatements = sqlString.split(";");

            for (String sqlStatement : sqlStatements) {
                if (sqlStatement.trim().isEmpty()) continue;

                if (sqlStatement.toLowerCase().contains("insert into planets") || sqlStatement.toLowerCase().contains("insert into moons")) {
                    handleInsertWithImages(sqlStatement.trim(), conn);
                } else {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.executeUpdate(sqlStatement.trim());
                    }
                }
            }
            conn.commit();
            System.out.println("Database setup complete");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void handleInsertWithImages(String sqlStatement, Connection conn) throws SQLException {
        String type = sqlStatement.toLowerCase().contains("moons") ? "moon" : "planet";

        // Replace placeholders with positional parameters for PostgreSQL
        String preparedSql = sqlStatement.replace("?", "$1");

        int imageCount = 1;
        for (int i = 0; i < 2; i++) {
            String imagePath = String.format("src/test/resources/Celestial-Images/%s-%d.jpg", type, imageCount);
            try (PreparedStatement ps = conn.prepareStatement(preparedSql)) {
                byte[] imageData = convertImgToByteArray(imagePath);
                ps.setBytes(1, imageData);
                ps.executeUpdate();
                imageCount = imageCount == 2 ? 1 : 2;
            } catch (IOException e) {
                System.err.println("Failed to load image: " + imagePath);
                throw new SQLException("Image conversion failed", e);
            }
        }
    }

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}
