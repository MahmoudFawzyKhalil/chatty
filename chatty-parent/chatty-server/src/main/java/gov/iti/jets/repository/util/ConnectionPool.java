package gov.iti.jets.repository.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        configure();
    }
    private ConnectionPool() {
    }

    private static void configure() {
        Properties properties = loadProperties();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", properties.getProperty("cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", properties.getProperty("prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", properties.getProperty("prepStmtCacheSqlLimit"));
        ds = new HikariDataSource(config);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("datasource.properties")) {
            properties.load(fis);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
