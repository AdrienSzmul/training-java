package com.excilys.formation.computerdatabase.persistence.dao;

import com.excilys.formation.computerdatabase.model.User;

public interface IUserDAO {
    User getUserByName(String name);

    void delete(User user);
}
