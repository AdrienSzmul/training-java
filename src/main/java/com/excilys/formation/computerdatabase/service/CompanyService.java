/**
 * 
 */
package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;

/**
 * @author excilys
 *
 */
public enum CompanyService {

	INSTANCE;

	private CompanyDAO companyDAO = CompanyDAO.INSTANCE;

	public List<Company> getListCompanies(int pageNumber, int taille) {
		return companyDAO.getListCompanies(pageNumber, taille);
	}

	public int getPageCountCompanies(int taille) {
		return companyDAO.getPageCountCompanies(taille);
	}

}
