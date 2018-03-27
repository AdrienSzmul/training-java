/**
 *
 */
package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author excilys
 */
public enum HSQLDBConnection {
    INSTANCE;
    /**
     *
     */
    private Connection conn;
    private static final String PROPERTIES_FILE = "properties/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    private static final String PROPERTY_PASSWORD = "password";

    public Connection getConnection() throws SQLException, IOException {
        /* Chargement du driver JDBC pour MySQL */
        final Properties properties = new Properties();
        properties.load(getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE));
        final String url = properties.getProperty(PROPERTY_URL);
        final String utilisateur = properties
                .getProperty(PROPERTY_NOM_UTILISATEUR);
        final String password = properties.getProperty(PROPERTY_PASSWORD);
        final String driver = properties.getProperty(PROPERTY_DRIVER);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, utilisateur, password);
        return conn;
    }
}
