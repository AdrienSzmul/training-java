/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;

/**
 * @author excilys
 */
public enum CompanyService {
    INSTANCE;
    private final CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    public List<Company> getListCompanies(final int pageNumber,
            final int taille) {
        return companyDAO.getListCompanies(pageNumber, taille);
    }

    public int getPageCountCompanies(final int taille) {
        return companyDAO.getPageCountCompanies(taille);
    }
}
