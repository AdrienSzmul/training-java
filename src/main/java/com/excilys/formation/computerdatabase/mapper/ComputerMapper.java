/**
 *
 */
package com.excilys.formation.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

/**
 * @author excilys
 */
public enum ComputerMapper {
    INSTANCE;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    /**
     * @throws SQLException
     */
    public Computer createComputer(final ResultSet rs) throws SQLException {
        final ComputerBuilder b = new Computer.ComputerBuilder();
        b.withId(rs.getLong("cu_id"));
        if (rs.getString("cu_name") != null) {
            b.withName(rs.getString("cu_name"));
        }
        if (rs.getDate("cu_introduced") != null) {
            b.withIntroduced(rs.getDate("cu_introduced").toLocalDate());
        }
        if (rs.getDate("cu_discontinued") != null) {
            b.withDiscontinued(rs.getDate("cu_discontinued").toLocalDate());
        }
        b.withCompany(companyMapper.createCompany(rs));
        return b.build();
    }
}
