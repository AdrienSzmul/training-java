package com.excilys.formation.computerdatabase.persistence.dao;

import com.excilys.formation.computerdatabase.model.Users;

public interface IUserDAO {
    Users getUserByName(String name);
}
