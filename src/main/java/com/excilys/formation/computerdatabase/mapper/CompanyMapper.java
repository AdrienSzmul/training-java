package com.excilys.formation.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;

/**
 * @author excilys
 */
public enum CompanyMapper {
    INSTANCE;
    /**
     * @return Company object
     * @param rs
     * @throws SQLException
     */
    public Company createCompany(final ResultSet rs) throws SQLException {
        final CompanyBuilder b = new Company.CompanyBuilder()
                .withId(rs.getLong("ca_id"));
        if (rs.getString("ca_name") != null) {
            b.withName(rs.getString("ca_name"));
        }
        return b.build();
    }
}
