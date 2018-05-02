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

import com.excilys.formation.computerdatabase.model.User;
import com.excilys.formation.computerdatabase.model.User_;

@Repository
public class UserDAO implements IUserDAO {
    private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        logger.info("getUserByName");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> getUser = builder.createQuery(User.class);
        Root<User> root = getUser.from(User.class);
        getUser.select(root);
        getUser.where(builder.equal(root.get(User_.username), name));
        User user = entityManager.createQuery(getUser).getSingleResult();
        return user;
    }

    @Override
    public void delete(User user) {
        logger.info("delete User");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> deleteUser = builder
                .createCriteriaDelete(User.class);
        Root<User> root = deleteUser.from(User.class);
        deleteUser.where(builder.equal(root.get(User_.id), user.getId()));
        entityManager.createQuery(deleteUser).executeUpdate();
    }
}
