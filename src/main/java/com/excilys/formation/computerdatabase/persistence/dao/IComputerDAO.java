/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author excilys
 */
public interface IComputerDAO {
    abstract void createComputer(Computer c);

    abstract void updateComputer(Computer c);

    abstract void deleteComputer(Computer c);

    abstract Computer showDetails(Computer c);

    abstract List<Computer> getListComputers(int pageNumber, int eltNumber);

    int getPageCountComputers(int eltNumber);
}
