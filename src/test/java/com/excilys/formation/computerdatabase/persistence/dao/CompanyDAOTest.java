/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;

/**
 * @author excilys
 */
public class CompanyDAOTest {

	private CompanyDAO newDAO = CompanyDAO.INSTANCE;

	@Test
	public void testGetListCompanies() {
		List<Company> listCompanies = new ArrayList<>();
		listCompanies = newDAO.getListCompanies();
		assertNotNull(listCompanies);
	}

}
