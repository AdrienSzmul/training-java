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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.persistence.HSQLDataBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
public class ComputerDAOTest {
    @Autowired
    private ComputerDAO computerDAO;
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
    public void testCreateComputer() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        assertEquals(computerDAO.getComputerById(computer.getId()).getName(),
                "José");
        assertEquals(
                computerDAO.getComputerById(computer.getId()).getIntroduced(),
                LocalDate.parse("2012-12-23"));
        assertNull(computerDAO.getComputerById(computer.getId())
                .getDiscontinued());
        computerDAO.getComputerById(computer.getId()).getCompany().getName();
        assertEquals(computerDAO.getComputerById(computer.getId()).getCompany()
                .getName(), "Apple Inc.");
    }

    @Test
    public void testDeleteComputer() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        computerDAO.deleteComputer(computer);
        assertNull(computerDAO.getComputerById(computer.getId()));
    }

    @Test
    public void testGetListComputers() throws DAOException {
        List<Computer> listComputers = computerDAO.getListComputers(0, 3);
        assertNotNull(listComputers);
        assertEquals(listComputers.size(), 3);
    }

    @Test
    public void testGetComputerById() throws DAOException {
        Company company = new CompanyBuilder().withId(1l).build();
        Computer computer = new ComputerBuilder().withName("José")
                .withIntroduced(LocalDate.parse("2012-12-23"))
                .withDiscontinued(null).withCompany(company).build();
        computer.setId(computerDAO.createComputer(computer));
        assertNotNull(computerDAO.getComputerById(computer.getId()));
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
        assertEquals(computerDAO.getComputerById(computer.getId()).getName(),
                "Josée");
    }
}
