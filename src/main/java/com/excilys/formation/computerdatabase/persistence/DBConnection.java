/**
 *
 */
package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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

    DBConnection() {
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, IOException {
        /* Chargement du driver JDBC pour MySQL */
        conn = hikariDS.getConnection();
        return conn;
    }
}
