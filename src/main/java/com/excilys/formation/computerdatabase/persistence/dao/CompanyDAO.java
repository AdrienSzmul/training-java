/**
 * 
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.mapper.CompanyMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.DBConnection;

/**
 * @author excilys
 *
 */
public enum CompanyDAO implements ICompanyDAO {

	INSTANCE;

	/**
	 * 
	 */
	private DBConnection dbConnection = DBConnection.INSTANCE;
	private CompanyMapper companyMapper = CompanyMapper.INSTANCE;
	
	private String SELECT_LIST_COMPANIES = "SELECT * FROM company ORDER BY ca_id LIMIT ? OFFSET ?;";
	private String COUNT_COMPANIES = "SELECT count(*) FROM company;";
	private String SELECT_ONE_COMPANY = "SELECT * FROM company WHERE ca_id = ?;";

	@Override
	public List<Company> getListCompanies(int pageNumber, int eltNumber) {
		int offset = pageNumber * eltNumber;
		List<Company> listCompanies = new ArrayList<>();
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn
						.prepareStatement(SELECT_LIST_COMPANIES);) {
			stat.setInt(1, eltNumber);
			stat.setInt(2, offset);
			rs = stat.executeQuery();

			while (rs.next()) {
				listCompanies.add(companyMapper.createCompany(rs));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return listCompanies;
	}

	@Override
	public int getPageCountCompanies(int eltNumber) {
		int pageNumber = 0;
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(COUNT_COMPANIES);) {
			rs = stat.executeQuery();
			rs.next();
			int tailleListCompanies = rs.getInt(1);
			pageNumber = tailleListCompanies / eltNumber;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return pageNumber;
	}

	@Override
	public Company showDetails(Company c) {
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(SELECT_ONE_COMPANY);) {
			rs = stat.executeQuery();
			stat.setLong(1, c.getId());
			while (rs.next()) {
				companyMapper.createCompany(rs);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return c;
	}

	private void closeConnection(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
