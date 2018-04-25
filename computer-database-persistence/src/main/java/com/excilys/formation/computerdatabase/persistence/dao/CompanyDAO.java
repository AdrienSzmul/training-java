/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.mapper.CompanyMapper;
import com.excilys.formation.computerdatabase.model.Company;

/**
 * @author excilys
 */
@Repository
public class CompanyDAO implements ICompanyDAO {
    /**
     *
     */
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    private CompanyMapper companyMapper;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private EntityManagerFactory entityManagerFactory;
    private final String SELECT_LIST_COMPANIES = "SELECT ca_id, ca_name FROM company ORDER BY ca_id LIMIT ? OFFSET ?;";
    private final String COUNT_COMPANIES = "SELECT count(ca_id) FROM company;";
    private final String SELECT_ONE_COMPANY = "SELECT ca_id, ca_name FROM company WHERE ca_id = ?;";
    private final String DELETE_ONE_COMPANY = "DELETE FROM company WHERE ca_id = ?";

    public CompanyDAO(CompanyMapper companyMapper, DataSource dataSource,
            EntityManagerFactory entityManagerFactory) {
        this.companyMapper = companyMapper;
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Company> getListCompanies(final int pageNumber,
            final int taille) throws DAOException {
        logger.info("get List Companies");
        List<Company> listCompanies = new ArrayList<>();
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
        Root<Company> companyRoot = criteria.from(Company.class);
        criteria.select(companyRoot);
        listCompanies = entityManagerFactory.createEntityManager()
                .createQuery(criteria).getResultList();
        return listCompanies;
    }

    @Override
    public int getPageCountCompanies(final int taille) throws DAOException {
        logger.info("count Company Pages");
        int companyCount = 0;
        companyCount = getCountCompanies();
        return companyCount / taille;
    }

    @Override
    public int getCountCompanies() throws DAOException {
        logger.info("count Companies");
        int nombreRes = 0;
        nombreRes = jdbcTemplate.queryForObject(COUNT_COMPANIES, Integer.class);
        return nombreRes;
    }

    @Override
    public Company getCompanyById(Long id) throws DAOException {
        logger.info("get one Company");
        Company company = jdbcTemplate.queryForObject(SELECT_ONE_COMPANY,
                new Object[] { id }, (ResultSet st, int arg1) -> {
                    return companyMapper.createCompany(st);
                });
        return company;
    }

    public final Logger getLogger() {
        return logger;
    }

    @Override
    public void deleteCompany(Company company)
            throws DAOException, SQLException {
        logger.info("company deletion");
        jdbcTemplate.update(DELETE_ONE_COMPANY,
                new Object[] { company.getId() });
    }
}
