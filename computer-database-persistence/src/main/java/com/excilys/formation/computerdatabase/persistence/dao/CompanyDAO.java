/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company_;

/**
 * @author excilys
 */
@Repository
public class CompanyDAO implements ICompanyDAO {
    /**
     *
     */
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> getListCompanies(final int pageNumber,
            final int taille) throws DAOException {
        logger.info("get List Companies");
        List<Company> listCompanies = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
        Root<Company> companyRoot = criteria.from(Company.class);
        CriteriaQuery<Company> select = criteria.select(companyRoot);
        select.orderBy(builder.asc(companyRoot.get(Company_.id)));
        TypedQuery<Company> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(pageNumber * taille);
        typedQuery.setMaxResults(taille);
        listCompanies = typedQuery.getResultList();
        if (!listCompanies.isEmpty()) {
            return listCompanies;
        } else {
            return null;
        }
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> count = builder.createQuery(Long.class);
        count.select(builder.count(count.from(Company.class)));
        Long nombreRes = entityManager.createQuery(count).getSingleResult();
        return nombreRes.intValue();
    }

    @Override
    public Company getCompanyById(Long id)
            throws DAOException, NoResultException {
        logger.info("get one Company");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> getCompany = builder.createQuery(Company.class);
        Root<Company> root = getCompany.from(Company.class);
        getCompany.select(root);
        getCompany.where(builder.equal(root.get(Company_.id), id));
        Company company = entityManager.createQuery(getCompany)
                .getSingleResult();
        return company;
    }

    public final Logger getLogger() {
        return logger;
    }

    @Override
    public void deleteCompany(Company company)
            throws DAOException, SQLException {
        logger.info("company deletion");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Company> deleteCompany = builder
                .createCriteriaDelete(Company.class);
        Root<Company> root = deleteCompany.from(Company.class);
        deleteCompany
                .where(builder.equal(root.get(Company_.id), company.getId()));
        entityManager.createQuery(deleteCompany).executeUpdate();
    }
}
