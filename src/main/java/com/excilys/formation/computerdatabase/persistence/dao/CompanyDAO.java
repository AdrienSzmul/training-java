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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author excilys
 *
 */
public enum CompanyDAO implements ICompanyDAO {

	INSTANCE;

	/**
	 * 
	 */
	
	final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	private DBConnection dbConnection = DBConnection.INSTANCE;
	private CompanyMapper companyMapper = CompanyMapper.INSTANCE;
	
	private String SELECT_LIST_COMPANIES = "SELECT ca_id, ca_name FROM company ORDER BY ca_id LIMIT ? OFFSET ?;";
	private String COUNT_COMPANIES = "SELECT count(ca_id) FROM company;";
	private String SELECT_ONE_COMPANY = "SELECT ca_id, ca_name FROM company WHERE ca_id = ?;";

	@Override
	public List<Company> getListCompanies(int offset, int taille) {
		logger.info("get List Companies");
		List<Company> listCompanies = new ArrayList<>();
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn
						.prepareStatement(SELECT_LIST_COMPANIES);) {
			stat.setInt(1, taille);
			stat.setInt(2, offset);
			rs = stat.executeQuery();

			while (rs.next()) {
				listCompanies.add(companyMapper.createCompany(rs));
			}

		} catch (SQLException e) {
			logger.debug(SELECT_LIST_COMPANIES, e.getMessage());
		} catch (IOException e) {
			logger.debug(SELECT_LIST_COMPANIES, e.getMessage());
		}
		closeConnection(rs);
		return listCompanies;
	}

	@Override
	public int getPageCountCompanies(int taille) {
		logger.info("count Companies");
		int pageNumber = 0;
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(COUNT_COMPANIES);) {
			rs = stat.executeQuery();
			rs.next();
			int tailleListCompanies = rs.getInt(1);
			pageNumber = tailleListCompanies / taille;
		} catch (SQLException e) {
			logger.debug(COUNT_COMPANIES, e.getMessage());
		} catch (IOException e) {
			logger.debug(COUNT_COMPANIES, e.getMessage());
		}
		closeConnection(rs);
		return pageNumber;
	}

	@Override
	public Company showDetails(Company c) {
		logger.info("show Details Company");
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(SELECT_ONE_COMPANY);) {
			rs = stat.executeQuery();
			stat.setLong(1, c.getId());
			while (rs.next()) {
				companyMapper.createCompany(rs);
			}

		} catch (SQLException e) {
			logger.debug(SELECT_ONE_COMPANY, e.getMessage());
		} catch (IOException e) {
			logger.debug(SELECT_ONE_COMPANY, e.getMessage());
		}
		closeConnection(rs);
		return c;
	}

	private void closeConnection(ResultSet rs) {
		logger.info("Closing ocnenction");
		try {
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

}
