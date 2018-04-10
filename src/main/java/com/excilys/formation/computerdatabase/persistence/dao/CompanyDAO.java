/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.mapper.CompanyMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.DBConnection;

/**
 * @author excilys
 */
public enum CompanyDAO implements ICompanyDAO {
    INSTANCE;
    /**
     *
     */
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    private final DBConnection dbConnection = DBConnection.INSTANCE;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;
    private final ComputerDAO computerDAO = ComputerDAO.INSTANCE;
    private final String SELECT_LIST_COMPANIES = "SELECT ca_id, ca_name FROM company ORDER BY ca_id LIMIT ? OFFSET ?;";
    private final String COUNT_COMPANIES = "SELECT count(ca_id) FROM company;";
    private final String SELECT_ONE_COMPANY = "SELECT ca_id, ca_name FROM company WHERE ca_id = ?;";
    private final String DELETE_ONE_COMPANY = "DELETE FROM company WHERE ca_id = ?";
    private final String DEBUG_STRING = "%s : %s";
    private final String EXCEPTION_DAO = "Un problème d'accès à la BDD a eu lieu";

    @Override
    public List<Company> getListCompanies(final int pageNumber,
            final int taille) throws DAOException {
        logger.info("get List Companies");
        final List<Company> listCompanies = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(SELECT_LIST_COMPANIES)) {
            stat.setInt(1, taille);
            stat.setInt(2, pageNumber * taille);
            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    listCompanies.add(companyMapper.createCompany(rs));
                }
            }
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, SELECT_LIST_COMPANIES,
                    e.getMessage()));
            throw new DAOException(EXCEPTION_DAO);
        }
        return listCompanies;
    }

    @Override
    public int getPageCountCompanies(final int taille) throws DAOException {
        logger.info("count Company Pages");
        int pageNumber = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn.prepareStatement(COUNT_COMPANIES);
                ResultSet rs = stat.executeQuery()) {
            rs.next();
            final int tailleListCompanies = rs.getInt(1);
            pageNumber = tailleListCompanies / taille;
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, COUNT_COMPANIES,
                    e.getMessage()));
            throw new DAOException(EXCEPTION_DAO);
        }
        return pageNumber;
    }

    @Override
    public int getCountCompanies() throws DAOException {
        logger.info("count Companies");
        int nombreRes = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn.prepareStatement(COUNT_COMPANIES);
                ResultSet rs = stat.executeQuery()) {
            rs.next();
            nombreRes = rs.getInt(1);
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, COUNT_COMPANIES,
                    e.getMessage()));
            throw new DAOException(EXCEPTION_DAO);
        }
        return nombreRes;
    }

    @Override
    // deprecative
    public Company showDetails(final Company c) throws DAOException {
        logger.info("show Details Company");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(SELECT_ONE_COMPANY)) {
            stat.setLong(1, c.getId());
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    companyMapper.createCompany(rs);
                }
            }
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, SELECT_ONE_COMPANY,
                    e.getMessage()));
            throw new DAOException(EXCEPTION_DAO);
        }
        return c;
    }

    @Override
    public Company getCompanyById(Long id) throws DAOException {
        logger.info("get one Company");
        Company company = null;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(SELECT_ONE_COMPANY)) {
            stat.setLong(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    company = companyMapper.createCompany(rs);
                }
            }
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, SELECT_ONE_COMPANY,
                    e.getMessage()));
            throw new DAOException(EXCEPTION_DAO);
        }
        return company;
    }

    public final Logger getLogger() {
        return logger;
    }

    @Override
    public void deleteCompany(Company company) throws DAOException {
        logger.info("company deletion");
        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stat = conn
                    .prepareStatement(DELETE_ONE_COMPANY)) {
                stat.setLong(1, company.getId());
                computerDAO.deleteMultipleComputersFromCompany(company, conn);
                stat.executeUpdate();
            } catch (SQLException e) {
                conn.rollback();
                throw new DAOException("Company delete went wrong");
            }
            conn.commit();
        } catch (SQLException e) {
            logger.debug(String.format(DEBUG_STRING, DELETE_ONE_COMPANY,
                    e.getMessage()));
        }
    }
}
