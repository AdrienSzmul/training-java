package com.excilys.formation.computerdatabase.persistence.dao;

import com.excilys.formation.computerdatabase.model.Privilege;

public interface IPrivilegeDAO {
    Privilege getPrivilegeByName(String name);

    void delete(Privilege privilege);
}
