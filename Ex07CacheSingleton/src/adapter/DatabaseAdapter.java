package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAdapter {
    private final DatabaseConnection databaseConnection;

    public DatabaseAdapter(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void saveData(String key, String value) {
        String sql = """
            INSERT INTO cache (cache_key, cache_value, created_at)
            VALUES (?, ?, NOW()) 
            ON DUPLICATE KEY UPDATE cache_value = VALUES(cache_value), created_at = NOW()
        """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, key);
            stmt.setString(2, value);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar dados no banco", e);
        }
    }

    public String getData(String key) {
        String sql = "SELECT cache_value FROM cache WHERE cache_key = ?";
        
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("cache_value");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dados no banco", e);
        }

        return null;
    }
}
