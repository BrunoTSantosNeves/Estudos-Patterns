package infra;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConnection {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/CacheMenager");
        config.setUsername("root");
        config.setPassword("BrUn@1304");
        config.setMaximumPoolSize(10); 
        config.setMinimumIdle(2); 
        config.setIdleTimeout(30000); 
        config.setMaxLifetime(1800000); 

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}