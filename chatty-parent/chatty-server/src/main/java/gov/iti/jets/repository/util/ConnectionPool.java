package gov.iti.jets.repository.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool INSTANCE = new ConnectionPool();
    
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;
    
    private ConnectionPool() {
        configure();
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private void configure() {
        Properties properties = loadProperties();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        config.setMaximumPoolSize(10);
        config.addDataSourceProperty("cachePrepStmts", properties.getProperty("cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", properties.getProperty("prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", properties.getProperty("prepStmtCacheSqlLimit"));
        ds = new HikariDataSource(config);
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try {
            properties.load(getClass().getResourceAsStream( "/datasource.properties" ));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties;
    }

    public void cleanup(){
        ds.close();
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
