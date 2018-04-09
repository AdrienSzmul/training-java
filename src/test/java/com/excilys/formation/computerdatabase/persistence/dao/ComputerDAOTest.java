package com.excilys.formation.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.persistence.HSQLDataBase;

public class ComputerDAOTest {
    private static final ComputerDAO computerDAO = ComputerDAO.INSTANCE;
    private static final CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    @Before
    public void setUp() throws SQLException, IOException {
        HSQLDataBase.init();
    }

    @After
    public void tearUp() throws SQLException, IOException {
        HSQLDataBase.destroy();
    }

    @Test
    public void testCreateComputer() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        assertEquals(computerDAO.showDetails(computer).getName(), "José");
        assertEquals(computerDAO.showDetails(computer).getIntroduced(),
                LocalDate.parse("2012-12-23"));
        assertNull(computerDAO.showDetails(computer).getDiscontinued());
        computerDAO.showDetails(computer).getCompany().getName();
        assertEquals(computerDAO.showDetails(computer).getCompany().getName(),
                "Apple Inc.");
    }

    @Test
    public void testDeleteComputer() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        computerDAO.deleteComputer(computer);
        assertNull(computerDAO.showDetails(computer));
    }

    @Test
    public void testGetListComputers() throws DAOException {
        List<Computer> listComputers = computerDAO.getListComputers(0, 3,
                "cu_id", true);
        assertNotNull(listComputers);
        assertEquals(listComputers.size(), 3);
    }

    @Test
    public void testShowDetails() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        assertNotNull(computerDAO.showDetails(computer));
    }

    @Test
    public void testUpdateComputer() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        Computer computerNew = new ComputerBuilder().withName("Josée")
                .withId(computer.getId()).build();
        computerDAO.updateComputer(computerNew);
        assertEquals(computerDAO.showDetails(computer).getName(), "Josée");
    }
}
