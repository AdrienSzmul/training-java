package com.excilys.formation.computerdatabase.persistence.dao;

import com.excilys.formation.computerdatabase.model.Role;

public interface IRoleDAO {
    Role getRoleByName(String name);

    void delete(Role role);
}
