package com.excilys.formation.computerdatabase.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.model.Users;
import com.excilys.formation.computerdatabase.model.Users_;

@Repository
public class UserDAO implements IUserDAO {
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Users getUserByName(String name) {
        logger.info("getUserByName");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> getUser = builder.createQuery(Users.class);
        Root<Users> root = getUser.from(Users.class);
        getUser.select(root);
        getUser.where(builder.equal(root.get(Users_.username), name));
        Users user = entityManager.createQuery(getUser).getSingleResult();
        return user;
    }
}
