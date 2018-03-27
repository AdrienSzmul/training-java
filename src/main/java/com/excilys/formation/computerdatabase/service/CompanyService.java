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
}
