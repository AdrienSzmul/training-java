/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;

/**
 * @author excilys
 */
@Repository
public class ComputerDAO implements IComputerDAO {
    private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    private final ComputerMapper computerMapper = ComputerMapper.INSTANCE;
    private final String SELECT_LIST_COMPUTERS = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id ORDER BY %s %s LIMIT ? OFFSET ?;";
    private final String SELECT_LIST_COMPUTERS_SEARCH = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_name LIKE ? OR ca_name LIKE ? ORDER BY %s %s LIMIT ? OFFSET ?;";
    private final String COUNT_COMPUTERS = "SELECT count(cu_id) FROM computer;";
    private final String COUNT_COMPUTERS_SEARCH = "SELECT count(cu_id) FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_name LIKE ? OR ca_name LIKE ?";
    private final String SELECT_ONE_COMPUTER = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_id = ?;";
    private final String CREATE_COMPUTER = "INSERT INTO computer (cu_name, cu_introduced, cu_discontinued, cu_ca_id) VALUES (?, ?, ?, ?)";
    private final String DELETE_COMPUTER = "DELETE FROM computer WHERE cu_id = ?";
    private final String DELETE_COMPUTERS_COMPANY = "DELETE FROM computer WHERE cu_ca_id = ?";
    private final String UPDATE_COMPUTER = "UPDATE computer SET cu_name = ?, cu_introduced = ?, cu_discontinued = ?, cu_ca_id = ? WHERE cu_id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createComputer(final Computer c) throws DAOException {
        logger.info("create Computer");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER,
                    Statement.RETURN_GENERATED_KEYS);
            setStatementsSQL(c, ps);
            return ps;
        }, keyHolder);
        Number createdIdNbr = keyHolder.getKey();
        return createdIdNbr.longValue();
    }

    @Override
    public void deleteComputer(final Computer c) throws DAOException {
        logger.info("delete Computer");
        jdbcTemplate.update(DELETE_COMPUTER, new Object[] { c.getId() });
    }

    @Override
    // Transaction model implemented here
    public void deleteMultipleComputers(List<Long> listComputerIds)
            throws DAOException {
        logger.info("delete multiple computers");
        for (Long id : listComputerIds) {
            jdbcTemplate.update(DELETE_COMPUTER, new Object[] { id });
        }
    }

    @Override
    public void deleteMultipleComputersFromCompany(Company company)
            throws DAOException, SQLException {
        logger.info("delete computers with company id");
        jdbcTemplate.update(DELETE_COMPUTERS_COMPANY,
                new Object[] { company.getId() });
    }

    @Override
    public List<Computer> getListComputersSorted(int pageNumber, int eltNumber,
            String orderby, boolean ascdesc) throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        String newRequest;
        if (!ascdesc) {
            newRequest = String.format(SELECT_LIST_COMPUTERS, orderby, "ASC");
        } else {
            newRequest = String.format(SELECT_LIST_COMPUTERS, orderby, "DESC");
        }
        listComputers = jdbcTemplate.query(newRequest,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        ps.setInt(1, eltNumber);
                        ps.setInt(2, offset);
                    }
                }, (ResultSet st, int arg1) -> {
                    return computerMapper.createComputer(st);
                });
        return listComputers;
    }

    @Override
    public List<Computer> getListComputersSearchSorted(int pageNumber,
            int eltNumber, String search, String orderby, boolean ascdesc)
            throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        String newRequest;
        if (!ascdesc) {
            newRequest = String.format(SELECT_LIST_COMPUTERS_SEARCH, orderby,
                    "ASC");
        } else {
            newRequest = String.format(SELECT_LIST_COMPUTERS_SEARCH, orderby,
                    "DESC");
        }
        listComputers = jdbcTemplate.query(newRequest,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        String tmpSearch = "%" + search + "%";
                        ps.setString(1, tmpSearch);
                        ps.setString(2, tmpSearch);
                        ps.setInt(3, eltNumber);
                        ps.setInt(4, offset);
                    }
                }, (ResultSet st, int arg1) -> {
                    return computerMapper.createComputer(st);
                });
        return listComputers;
    }

    @Override
    public List<Computer> getListComputers(int pageNumber, int eltNumber)
            throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        String newRequest = String.format(SELECT_LIST_COMPUTERS, "cu_name",
                "ASC");
        listComputers = jdbcTemplate.query(newRequest,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        ps.setInt(1, eltNumber);
                        ps.setInt(2, offset);
                    }
                }, (ResultSet st, int arg1) -> {
                    return computerMapper.createComputer(st);
                });
        return listComputers;
    }

    @Override
    public List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search) throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        listComputers = jdbcTemplate.query(SELECT_LIST_COMPUTERS_SEARCH,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps)
                            throws SQLException {
                        String tmpSearch = "%" + search + "%";
                        ps.setString(1, tmpSearch);
                        ps.setString(2, tmpSearch);
                        ps.setInt(3, eltNumber);
                        ps.setInt(4, offset);
                    }
                }, (ResultSet st, int arg1) -> {
                    return computerMapper.createComputer(st);
                });
        return listComputers;
    }

    @Override
    public Computer getComputerById(Long id) throws DAOException {
        logger.info("get one Computer");
        Computer computer;
        try {
            computer = jdbcTemplate.queryForObject(SELECT_ONE_COMPUTER,
                    new Object[] { id }, (ResultSet st, int arg1) -> {
                        return computerMapper.createComputer(st);
                    });
            return computer;
        } catch (EmptyResultDataAccessException e) {
            logger.debug("Id doesn't match anything");
            return null;
        }
    }

    @Override
    public void updateComputer(final Computer c) throws DAOException {
        logger.info("update Computer");
        jdbcTemplate.update(UPDATE_COMPUTER, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                setStatementsSQL(c, ps);
                ps.setLong(5, c.getId());
            }
        });
    }

    @Override
    public int getPageCountComputers(final int eltNumber) throws DAOException {
        logger.info("count Computer Pages with size");
        int compNumber = getCountComputers();
        return compNumber / eltNumber;
    }

    @Override
    public int getPageCountComputersSearch(final int eltNumber, String search)
            throws DAOException {
        logger.info("count Computer Pages with size and search");
        int compNumber = getCountComputersSearch(search);
        return compNumber / eltNumber;
    }

    @Override
    public int getCountComputers() throws DAOException {
        logger.info("count Computers");
        int tailleListComputers = 0;
        tailleListComputers = jdbcTemplate.queryForObject(COUNT_COMPUTERS,
                Integer.class);
        return tailleListComputers;
    }

    @Override
    public int getCountComputersSearch(String search) throws DAOException {
        int nbrComputersResult = 0;
        nbrComputersResult = jdbcTemplate.queryForObject(COUNT_COMPUTERS_SEARCH,
                Integer.class, new Object[] { search, search });
        return nbrComputersResult;
    }

    public final Logger getLogger() {
        return logger;
    }

    // Ici on oblige la vérification nulle pour éviter d'avoir un crash peu
    // parlant
    // si on rentre des valeurs nulles (même si on fait la vérif avec un
    // validator
    // avant)
    private void setStatementsSQL(final Computer c,
            final PreparedStatement stat) throws SQLException {
        logger.info("setting values in sql requests as {}",
                stat.getParameterMetaData().toString());
        stat.setString(1, c.getName());
        if (c.getIntroduced() != null) {
            stat.setDate(2, Date.valueOf(c.getIntroduced()));
        } else {
            stat.setNull(2, java.sql.Types.DATE);
        }
        if (c.getDiscontinued() != null) {
            stat.setDate(3, Date.valueOf(c.getDiscontinued()));
        } else {
            stat.setNull(3, java.sql.Types.DATE);
        }
        if (c.getCompany() != null) {
            stat.setLong(4, c.getCompany().getId());
        } else {
            stat.setNull(4, java.sql.Types.BIGINT);
        }
    }
}
