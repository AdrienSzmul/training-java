/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;

/**
 * @author excilys
 */
public interface ICompanyDAO {
    abstract List<Company> getListCompanies(int pageNumber, int eltNumber)
            throws DAOException;

    abstract Company showDetails(Company c) throws DAOException;

    abstract int getPageCountCompanies(int eltNumber) throws DAOException;

    Company getCompanyById(Long id) throws DAOException;

    int getCountCompanies() throws DAOException;

    void deleteCompany(Company company) throws DAOException;
}
