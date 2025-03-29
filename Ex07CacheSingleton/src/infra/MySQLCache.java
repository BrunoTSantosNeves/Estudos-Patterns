package infra;

import core.CacheRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;


public class MySQLCache implements CacheRepository {
    private static final String CREATE_TABLE_SQL = """
        CREATE TABLE IF NOT EXISTS cache (
            cache_key VARCHAR(255) PRIMARY KEY,
            cache_value TEXT NOT NULL,
            created_at DATETIME DEFAULT CURRENT_TIMESTAMP
        )
    """;

    private static final String SELECT_SQL = "SELECT cache_value, created_at FROM cache WHERE cache_key = ?";
    private static final String INSERT_UPDATE_SQL = """
        INSERT INTO cache (cache_key, cache_value, created_at)
        VALUES (?, ?, NOW()) 
        ON DUPLICATE KEY UPDATE cache_value = VALUES(cache_value), created_at = NOW()
    """;
    private static final String DELETE_EXPIRED_SQL = "DELETE FROM cache WHERE TIMESTAMPDIFF(SECOND, created_at, NOW()) > ?";
    
    private final DataSource dataSource;
    private final long ttl;

    public MySQLCache(DataSource dataSource, long ttl) {
        this.dataSource = dataSource;
        this.ttl = ttl;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_SQL)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela de cache", e);
        }
    }

    @Override
    public String get(String key) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_SQL)) {
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                if (isExpired(createdAt)) {
                    removeExpiredEntries(connection);
                    return null;
                }
                return rs.getString("cache_value");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar no cache", e);
        }
        return null;
    }

    @Override
    public void set(String key, String value) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_UPDATE_SQL)) {
            stmt.setString(1, key);
            stmt.setString(2, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir no cache", e);
        }
    }

    private void removeExpiredEntries(Connection connection) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_EXPIRED_SQL)) {
            stmt.setLong(1, ttl);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar entradas expiradas", e);
        }
    }

    private boolean isExpired(LocalDateTime createdAt) {
        return createdAt.plusSeconds(ttl).isBefore(LocalDateTime.now());
    }
}
