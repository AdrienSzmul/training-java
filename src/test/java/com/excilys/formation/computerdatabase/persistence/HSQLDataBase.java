package com.excilys.formation.computerdatabase.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

public abstract class HSQLDataBase {
    private static final DBConnection HSQLdb = DBConnection.INSTANCE;
    private static final String DROP_TABLE_COMPUTER = "DROP TABLE computer IF EXISTS;";
    private static final String DROP_TABLE_COMPANY = "DROP TABLE company IF EXISTS;";
    private static final String DROP_USER = "DROP USER admincdb;";

    public static void destroy() throws SQLException, IOException {
        try (Connection connection = HSQLdb.getConnection();
                Statement statement = connection.createStatement();) {
            statement.executeUpdate(DROP_TABLE_COMPUTER);
            statement.executeUpdate(DROP_TABLE_COMPANY);
            statement.executeUpdate(DROP_USER);
        }
    }

    public static void init() throws SQLException, IOException {
        try (Connection connection = HSQLdb.getConnection();
                InputStream inputStream = HSQLDataBase.class
                        .getResourceAsStream("/db/hsqlDB_script.sql");) {
            SqlFile sqlFile = new SqlFile(new InputStreamReader(inputStream),
                    "init", System.out, "UTF-8", false, new File("."));
            sqlFile.setConnection(connection);
            try {
                sqlFile.execute();
            } catch (SqlToolError e) {
                e.printStackTrace();
            }
        }
    }
}
