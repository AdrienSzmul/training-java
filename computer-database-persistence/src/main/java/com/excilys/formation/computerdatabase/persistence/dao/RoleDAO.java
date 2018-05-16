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

import com.excilys.formation.computerdatabase.model.Role;
import com.excilys.formation.computerdatabase.model.Role_;

@Repository
public class RoleDAO implements IRoleDAO {
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        logger.info("getRoleByName");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> getRole = builder.createQuery(Role.class);
        Root<Role> root = getRole.from(Role.class);
        getRole.select(root);
        getRole.where(builder.equal(root.get(Role_.name), name));
        Role role = entityManager.createQuery(getRole).getSingleResult();
        return role;
    }

    @Override
    public void delete(Role role) {
        logger.info("delete Role");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Role> deleteRole = builder
                .createCriteriaDelete(Role.class);
        Root<Role> root = deleteRole.from(Role.class);
        deleteRole.where(builder.equal(root.get(Role_.id), role.getId()));
        entityManager.createQuery(deleteRole).executeUpdate();
    }
}
