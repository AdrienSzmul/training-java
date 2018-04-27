package com.excilys.formation.computerdatabase.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class HSQLDataBase {
    @Autowired
    private DataSource dataSource;
    private static final String DROP_TABLE_COMPUTER = "DROP TABLE computer IF EXISTS;";
    private static final String DROP_TABLE_COMPANY = "DROP TABLE company IF EXISTS;";
    private static final String DROP_USER = "DROP USER admincdb;";

    public void destroy() throws SQLException, IOException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
                Statement statement = connection.createStatement();) {
            statement.executeUpdate(DROP_TABLE_COMPUTER);
            statement.executeUpdate(DROP_TABLE_COMPANY);
            statement.executeUpdate(DROP_USER);
        }
    }

    public void init() throws SQLException, IOException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
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
