/**
 * 
 */
package com.excilys.formation.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.Builder;

/**
 * @author excilys
 *
 */
public enum CompanyMapper {

	INSTANCE;

	/**
	 * 
	 */

	public Company createCompany(ResultSet rs) throws SQLException {
		Builder b = new Company.Builder().withId(rs.getLong("ca_id"));
		if (rs.getString("ca_name") != null) {
			b.withName(rs.getString("ca_name"));
		}
		return b.build();
	}

}
