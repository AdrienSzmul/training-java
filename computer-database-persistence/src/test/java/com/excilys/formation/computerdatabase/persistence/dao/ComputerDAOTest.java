package com.excilys.formation.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.config.PersistenceJPAConfig;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.HSQLDataBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class ComputerDAOTest {
    @Autowired
    private IComputerDAO computerDAO;
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
    public void testGetListComputers() throws DAOException {
        List<Computer> listComputers = computerDAO.getListComputers(0, 100);
        assertNotNull(listComputers);
        assertEquals(listComputers.size(), 12);
    }

    @Test
    public void testGetListComputersSearch() throws DAOException {
        List<Computer> listComputers = computerDAO.getListComputersSearch(0,
                100, "CM");
        assertNotNull(listComputers);
        assertEquals(listComputers.size(), 4);
    }

    @Test
    public void testGetListComputersSorted() throws DAOException {
        List<Computer> listComputers = computerDAO.getListComputersSorted(0,
                100, "name", true);
        assertNotNull(listComputers);
        assertEquals(listComputers.size(), 12);
        assertTrue(listComputers.get(0).getName().startsWith("Apple "));
    }

    @Test
    public void testGetCountComputers() throws DAOException {
        int nombreRes = computerDAO.getCountComputers();
        assertNotNull(nombreRes);
        assertTrue(nombreRes == 12);
    }

    @Test
    public void testGetCountComputersSearch() throws DAOException {
        int nombreRes = computerDAO.getCountComputersSearch("CM");
        assertNotNull(nombreRes);
        assertTrue(nombreRes == 4);
    }

    @Test
    public void testGetComputerById() throws DAOException {
        Computer computer = computerDAO.getComputerById(1L);
        assertNotNull(computer);
        assertTrue(computer.getId() == 1L);
    }
}
