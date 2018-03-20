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
    abstract List<Company> getListCompanies(int pageNumber, int eltNumber);

    abstract Company showDetails(Company c);

    abstract int getPageCountCompanies(int eltNumber);
}
