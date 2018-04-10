/**
 *
 */
package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author excilys
 */
public enum DBConnection {
    INSTANCE;
    /**
     *
     */
    private Connection conn;
    private HikariDataSource hikariDS;
    private static final String PROPERTIES_FILE = "properties/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    private static final String PROPERTY_PASSWORD = "password";
    private Logger logger = LoggerFactory.getLogger(DBConnection.class);

    DBConnection() {
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        final String url = properties.getProperty(PROPERTY_URL);
        final String utilisateur = properties
                .getProperty(PROPERTY_NOM_UTILISATEUR);
        final String password = properties.getProperty(PROPERTY_PASSWORD);
        final String driver = properties.getProperty(PROPERTY_DRIVER);
        hikariDS = new HikariDataSource();
        hikariDS.setJdbcUrl(url);
        hikariDS.setUsername(utilisateur);
        hikariDS.setPassword(password);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        /* Chargement du driver JDBC pour MySQL */
        conn = hikariDS.getConnection();
        return conn;
    }
}
