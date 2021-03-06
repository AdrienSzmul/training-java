/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author excilys
 */
public interface IComputerDAO {
    abstract Long createComputer(Computer c) throws DAOException;

    abstract void updateComputer(Computer c) throws DAOException;

    abstract void deleteComputer(Computer c) throws DAOException;

    abstract List<Computer> getListComputers(int pageNumber, int eltNumber)
            throws DAOException;

    List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search) throws DAOException;

    List<Computer> getListComputersSorted(int pageNumber, int eltNumber,
            String orderby, boolean ascdesc) throws DAOException;

    List<Computer> getListComputersSearchSorted(int pageNumber, int eltNumber,
            String search, String orderby, boolean ascdesc) throws DAOException;

    int getPageCountComputers(int eltNumber) throws DAOException;

    int getCountComputers() throws DAOException;

    void deleteMultipleComputers(List<Long> listComputerIds)
            throws DAOException;

    int getCountComputersSearch(String search) throws DAOException;

    Computer getComputerById(Long id) throws DAOException;

    int getPageCountComputersSearch(int eltNumber, String search)
            throws DAOException;

    void deleteMultipleComputersFromCompany(Company company)
            throws DAOException, SQLException;
}
