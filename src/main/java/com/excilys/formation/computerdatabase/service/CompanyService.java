/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;

/**
 * @author excilys
 */
public enum CompanyService {
    INSTANCE;
    private final CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    public List<Company> getListCompanies(final int pageNumber,
            final int taille) throws ServiceException {
        List<Company> listCompanies = null;
        try {
            listCompanies = companyDAO.getListCompanies(pageNumber, taille);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return listCompanies;
    }

    public int getPageCountCompanies(final int taille) throws ServiceException {
        int nbrPageCompany = 0;
        try {
            nbrPageCompany = companyDAO.getPageCountCompanies(taille);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return nbrPageCompany;
    }

    public int getCountCompanies() throws ServiceException {
        int nombreRes = 0;
        try {
            nombreRes = companyDAO.getCountCompanies();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return nombreRes;
    }

    public Company getCompanyById(Long id) throws ServiceException {
        try {
            return companyDAO.getCompanyById(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteCompany(Company company) throws ServiceException {
        try {
            companyDAO.deleteCompany(company);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
