/**
 *
 */
package com.excilys.formation.computerdatabase.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author excilys
 */
public enum DBConnection {
    INSTANCE;
    /**
     *
     */
    private Connection conn;
    private static final String PROPERTIES_FILE = "resources/properties/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    private static final String PROPERTY_PASSWORD = "password";

    public Connection getConnection() throws SQLException, IOException {
        /* Chargement du driver JDBC pour MySQL */
        final Properties properties = new Properties();
        final InputStream fichierProperties = new FileInputStream(
                PROPERTIES_FILE);
        properties.load(fichierProperties);
        final String url = properties.getProperty(PROPERTY_URL);
        final String utilisateur = properties
                .getProperty(PROPERTY_NOM_UTILISATEUR);
        final String password = properties.getProperty(PROPERTY_PASSWORD);
        try {
            Class.forName(PROPERTY_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, utilisateur, password);
        return conn;
    }
}
