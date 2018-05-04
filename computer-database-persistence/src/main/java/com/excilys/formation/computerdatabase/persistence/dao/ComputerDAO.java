/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company_;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer_;

/**
 * @author excilys
 */
@Repository
public class ComputerDAO implements IComputerDAO {
    private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long createComputer(final Computer c) throws DAOException {
        logger.info("create Computer");
        c.setId(null);
        entityManager.persist(c);
        entityManager.flush();
        return c.getId();
    }

    @Override
    public void deleteComputer(final Computer c) throws DAOException {
        logger.info("delete Computer");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Computer> deleteComputer = builder
                .createCriteriaDelete(Computer.class);
        Root<Computer> root = deleteComputer.from(Computer.class);
        deleteComputer.where(builder.equal(root.get(Computer_.id), c.getId()));
        entityManager.createQuery(deleteComputer).executeUpdate();
    }

    @Override
    public void deleteMultipleComputers(List<Long> listComputerIds)
            throws DAOException {
        logger.info("delete multiple computers");
        for (Long id : listComputerIds) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Computer> deleteComputer = builder
                    .createCriteriaDelete(Computer.class);
            Root<Computer> root = deleteComputer.from(Computer.class);
            deleteComputer.where(builder.equal(root.get(Computer_.id), id));
            entityManager.createQuery(deleteComputer).executeUpdate();
        }
    }

    @Override
    public void deleteMultipleComputersFromCompany(Company company)
            throws DAOException, SQLException {
        logger.info("delete computers with company id");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Computer> deleteComputer = builder
                .createCriteriaDelete(Computer.class);
        Root<Computer> root = deleteComputer.from(Computer.class);
        deleteComputer.where(
                builder.equal(root.get(Computer_.company), company.getId()));
        entityManager.createQuery(deleteComputer).executeUpdate();
    }

    @Override
    public void updateComputer(final Computer c) throws DAOException {
        logger.info("update Computer");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Computer> updateComputer = builder
                .createCriteriaUpdate(Computer.class);
        Root<Computer> root = updateComputer.from(Computer.class);
        updateComputer.where(builder.equal(root.get(Computer_.id), c.getId()));
        updateComputer.set("name", c.getId());
        updateComputer.set("introduced", c.getIntroduced());
        updateComputer.set("discontinued", c.getDiscontinued());
        updateComputer.set("company", c.getCompany());
        entityManager.createQuery(updateComputer).executeUpdate();
    }

    @Override
    public List<Computer> getListComputersSorted(int pageNumber, int eltNumber,
            String orderby, boolean ascdesc) throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class);
        Root<Computer> computerRoot = criteria.from(Computer.class);
        CriteriaQuery<Computer> select = criteria.select(computerRoot);
        setOrderBy(orderby, ascdesc, builder, computerRoot, select);
        TypedQuery<Computer> typedQuery = setOffset(eltNumber, offset, select);
        listComputers = typedQuery.getResultList();
        if (!listComputers.isEmpty()) {
            return listComputers;
        } else {
            return null;
        }
    }

    @Override
    public List<Computer> getListComputersSearchSorted(int pageNumber,
            int eltNumber, String search, String orderby, boolean ascdesc)
            throws DAOException {
        final int offset = pageNumber * eltNumber;
        String searchPC = "%" + search + "%";
        List<Computer> listComputers = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class);
        Root<Computer> computerRoot = criteria.from(Computer.class);
        CriteriaQuery<Computer> select = setSearchAndCompanyJoin(searchPC,
                builder, criteria, computerRoot);
        setOrderBy(orderby, ascdesc, builder, computerRoot, select);
        TypedQuery<Computer> typedQuery = setOffset(eltNumber, offset, select);
        listComputers = typedQuery.getResultList();
        if (!listComputers.isEmpty()) {
            return listComputers;
        } else {
            return null;
        }
    }

    @Override
    public List<Computer> getListComputers(int pageNumber, int eltNumber)
            throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class);
        Root<Computer> companyRoot = criteria.from(Computer.class);
        CriteriaQuery<Computer> select = criteria.select(companyRoot);
        select.orderBy(builder.asc(companyRoot.get(Computer_.id)));
        TypedQuery<Computer> typedQuery = setOffset(eltNumber, offset, select);
        listComputers = typedQuery.getResultList();
        if (!listComputers.isEmpty()) {
            return listComputers;
        } else {
            return null;
        }
    }

    @Override
    public List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search) throws DAOException {
        final int offset = pageNumber * eltNumber;
        String searchPC = "%" + search + "%";
        List<Computer> listComputers = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class);
        Root<Computer> computerRoot = criteria.from(Computer.class);
        CriteriaQuery<Computer> select = setSearchAndCompanyJoin(searchPC,
                builder, criteria, computerRoot);
        select.orderBy(builder.asc(computerRoot.get(Computer_.id)));
        TypedQuery<Computer> typedQuery = setOffset(eltNumber, offset, select);
        listComputers = typedQuery.getResultList();
        if (!listComputers.isEmpty()) {
            return listComputers;
        } else {
            return null;
        }
    }

    private CriteriaQuery<Computer> setSearchAndCompanyJoin(String searchPC,
            CriteriaBuilder builder, CriteriaQuery<Computer> criteria,
            Root<Computer> computerRoot) {
        Join<Computer, Company> companyJoin = computerRoot
                .join(Computer_.company, JoinType.LEFT);
        Predicate searchP = builder.or(
                builder.like(computerRoot.get(Computer_.name), searchPC),
                builder.like(companyJoin.get(Company_.name), searchPC));
        CriteriaQuery<Computer> select = criteria.select(computerRoot);
        select.where(searchP);
        return select;
    }

    private void setOrderBy(String orderby, boolean ascdesc,
            CriteriaBuilder builder, Root<Computer> computerRoot,
            CriteriaQuery<Computer> select) {
        if (!ascdesc) {
            if (orderby.matches("company")) {
                Join<Computer, Company> companyJoin = computerRoot
                        .join(Computer_.company, JoinType.LEFT);
                orderby = "name";
                select.orderBy(builder.desc(companyJoin.get(orderby)));
            } else {
                select.orderBy(builder.desc(computerRoot.get(orderby)));
            }
        } else {
            if (orderby.matches("company")) {
                Join<Computer, Company> companyJoin = computerRoot
                        .join(Computer_.company, JoinType.LEFT);
                orderby = "name";
                select.orderBy(builder.asc(companyJoin.get(orderby)));
            } else {
                select.orderBy(builder.asc(computerRoot.get(orderby)));
            }
        }
    }

    private TypedQuery<Computer> setOffset(int eltNumber, final int offset,
            CriteriaQuery<Computer> select) {
        TypedQuery<Computer> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(eltNumber);
        return typedQuery;
    }

    @Override
    public Computer getComputerById(Long id) throws DAOException {
        logger.info("get one Computer");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> getComputer = builder
                .createQuery(Computer.class);
        Root<Computer> root = getComputer.from(Computer.class);
        getComputer.select(root);
        getComputer.where(builder.equal(root.get(Computer_.id), id));
        Computer computer = entityManager.createQuery(getComputer)
                .getSingleResult();
        return computer;
    }

    @Override
    public int getPageCountComputers(final int eltNumber) throws DAOException {
        logger.info("count Computer Pages with size");
        int compNumber = getCountComputers();
        return compNumber % eltNumber == 0 ? compNumber / eltNumber
                : compNumber / eltNumber + 1;
    }

    @Override
    public int getPageCountComputersSearch(final int eltNumber, String search)
            throws DAOException {
        logger.info("count Computer Pages with size and search");
        int compNumber = getCountComputersSearch(search);
        return compNumber % eltNumber == 0 ? compNumber / eltNumber
                : compNumber / eltNumber + 1;
    }

    @Override
    public int getCountComputers() throws DAOException {
        logger.info("count Computers");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> count = builder.createQuery(Long.class);
        count.select(builder.count(count.from(Computer.class)));
        Long nombreRes = entityManager.createQuery(count).getSingleResult();
        return nombreRes.intValue();
    }

    @Override
    public int getCountComputersSearch(String search) throws DAOException {
        String tmpSearch = "%" + search + "%";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Computer> computerRoot = criteria.from(Computer.class);
        criteria.select(builder.count(computerRoot));
        Join<Computer, Company> companyJoin = computerRoot
                .join(Computer_.company, JoinType.LEFT);
        Predicate searchP = builder.or(
                builder.like(computerRoot.get(Computer_.name), tmpSearch),
                builder.like(companyJoin.get(Company_.name), tmpSearch));
        criteria.select(builder.count(computerRoot));
        criteria.where(searchP);
        Long nombreRes = entityManager.createQuery(criteria).getSingleResult();
        return nombreRes.intValue();
    }

    public final Logger getLogger() {
        return logger;
    }
}
