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
    abstract Long createComputer(Computer c) throws DAOException;

    abstract void updateComputer(Computer c) throws DAOException;

    abstract void deleteComputer(Computer c) throws DAOException;

    abstract Computer showDetails(Computer c) throws DAOException;

    abstract List<Computer> getListComputers(int pageNumber, int eltNumber)
            throws DAOException;

    int getPageCountComputers(int eltNumber) throws DAOException;

    int getCountComputers() throws DAOException;

    void deleteMultipleComputers(List<Long> listComputerIds)
            throws DAOException;

    List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search) throws DAOException;
}
