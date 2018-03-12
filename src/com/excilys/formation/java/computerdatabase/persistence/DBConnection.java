/**
 * 
 */
package com.excilys.formation.java.computerdatabase.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author excilys
 *
 */
public class DBConnection {

	/**
	 * 
	 */


	private Connection conn = null;
	private static DBConnection instance;
	private static final String PROPERTIES_FILE = "WebContent/WEB-INF/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
	private static final String PROPERTY_PASSWORD = "password";		


	private DBConnection()  throws ClassNotFoundException, SQLException, IOException {
		String url;
		String driver;
		String utilisateur;
		String password;
		/* Chargement du driver JDBC pour MySQL */
		Properties properties = new Properties();
		InputStream fichierProperties = new FileInputStream(PROPERTIES_FILE);

		properties.load(fichierProperties);
		url = properties.getProperty(PROPERTY_URL);
		driver = properties.getProperty(PROPERTY_DRIVER);
		utilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
		password = properties.getProperty(PROPERTY_PASSWORD);


		Class.forName( driver );
		conn = DriverManager.getConnection(url, utilisateur, password);
		if (conn != null) {
			conn.close();
		}

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance.conn;
	}
}
