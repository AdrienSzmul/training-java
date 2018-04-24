/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;

/**
 * @author excilys
 */
public interface ICompanyDAO {
    abstract List<Company> getListCompanies(int pageNumber, int eltNumber)
            throws DAOException;

    abstract int getPageCountCompanies(int eltNumber) throws DAOException;

    Company getCompanyById(Long id) throws DAOException;

    int getCountCompanies() throws DAOException;

    void deleteCompany(Company company) throws DAOException, SQLException;
}
