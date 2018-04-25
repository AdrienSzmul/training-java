/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;
import com.excilys.formation.computerdatabase.persistence.dao.ICompanyDAO;
import com.excilys.formation.computerdatabase.persistence.dao.IComputerDAO;

/**
 * @author excilys
 */
@Service
public class CompanyService {
    private ICompanyDAO companyDAO;
    private IComputerDAO computerDAO;

    public CompanyService(ICompanyDAO companyDAO, IComputerDAO computerDAO) {
        this.companyDAO = companyDAO;
        this.computerDAO = computerDAO;
    }

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

    @Transactional
    public void deleteCompany(Company company) throws ServiceException {
        try {
            computerDAO.deleteMultipleComputersFromCompany(company);
            companyDAO.deleteCompany(company);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
