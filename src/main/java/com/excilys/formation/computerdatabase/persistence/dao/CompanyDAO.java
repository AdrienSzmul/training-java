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
    private final String selectListCompanies = "SELECT ca_id, ca_name FROM company ORDER BY ca_id LIMIT ? OFFSET ?;";
    private final String countCompanies = "SELECT count(ca_id) FROM company;";
    private final String selectOneCompany = "SELECT ca_id, ca_name FROM company WHERE ca_id = ?;";

    @Override
    public List<Company> getListCompanies(final int pageNumber,
            final int taille) throws DAOException {
        logger.info("get List Companies");
        final List<Company> listCompanies = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(selectListCompanies);) {
            stat.setInt(1, taille);
            stat.setInt(2, pageNumber * taille);
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    listCompanies.add(companyMapper.createCompany(rs));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", selectListCompanies, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return listCompanies;
    }

    @Override
    public int getPageCountCompanies(final int taille) throws DAOException {
        logger.info("count Companies");
        int pageNumber = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(countCompanies);) {
            try (ResultSet rs = stat.executeQuery();) {
                rs.next();
                final int tailleListCompanies = rs.getInt(1);
                pageNumber = tailleListCompanies / taille;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", countCompanies, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return pageNumber;
    }

    @Override
    public Company showDetails(final Company c) throws DAOException {
        logger.info("show Details Company");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(selectOneCompany);) {
            try (ResultSet rs = stat.executeQuery();) {
                stat.setLong(1, c.getId());
                while (rs.next()) {
                    companyMapper.createCompany(rs);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", selectOneCompany, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return c;
    }

    public final Logger getLogger() {
        return logger;
    }
}
