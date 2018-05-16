package com.excilys.formation.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.config.PersistenceJPAConfig;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.HSQLDataBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class CompanyDAOTest {
    @Autowired
    private ICompanyDAO companyDAO;
    @Autowired
    private HSQLDataBase HSQLdb;

    @Before
    public void setUp() throws SQLException, IOException {
        HSQLdb.init();
    }

    @After
    public void tearUp() throws SQLException, IOException {
        HSQLdb.destroy();
    }

    @Test
    public void testGetListCompanies() throws DAOException {
        List<Company> listCompanies = new ArrayList<Company>();
        listCompanies = companyDAO.getListCompanies(0, 5);
        assertNotNull(listCompanies);
        assertTrue(listCompanies.get(1).getId() < listCompanies.get(2).getId());
    }

    @Test
    public void testGetCountCompanies() throws DAOException {
        int nombreRes = companyDAO.getCountCompanies();
        assertNotNull(nombreRes);
        assertTrue(nombreRes == 5);
    }

    @Test
    public void testGetCompanyById() throws DAOException {
        Company company = companyDAO.getCompanyById(2L);
        assertTrue(company.getId() == 2L);
        assertEquals(company.getName(), "Thinking Machines");
    }
}
