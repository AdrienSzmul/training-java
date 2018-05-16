package com.excilys.formation.computerdatabase.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.Privilege;
import com.excilys.formation.computerdatabase.model.Privilege_;

@Repository
public class PrivilegeDAO implements IPrivilegeDAO {
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Privilege getPrivilegeByName(String name) {
        logger.info("getPrivilegeByName");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Privilege> getPrivilege = builder
                .createQuery(Privilege.class);
        Root<Privilege> root = getPrivilege.from(Privilege.class);
        getPrivilege.select(root);
        getPrivilege.where(builder.equal(root.get(Privilege_.name), name));
        Privilege privilege = entityManager.createQuery(getPrivilege)
                .getSingleResult();
        return privilege;
    }

    @Override
    public void delete(Privilege privilege) {
        logger.info("delete Privilege");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Privilege> deletePrivilege = builder
                .createCriteriaDelete(Privilege.class);
        Root<Privilege> root = deletePrivilege.from(Privilege.class);
        deletePrivilege.where(
                builder.equal(root.get(Privilege_.id), privilege.getId()));
        entityManager.createQuery(deletePrivilege).executeUpdate();
    }
}
