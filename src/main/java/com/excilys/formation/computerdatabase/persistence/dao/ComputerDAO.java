/**
 *
 */
package com.excilys.formation.computerdatabase.persistence.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.DBConnection;

/**
 * @author excilys
 */
public enum ComputerDAO implements IComputerDAO {
    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    private final DBConnection dbConnection = DBConnection.INSTANCE;
    private final ComputerMapper computerMapper = ComputerMapper.INSTANCE;
    private final String SELECT_LIST_COMPUTERS = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id ORDER BY %s %s LIMIT ? OFFSET ?;";
    private final String SELECT_LIST_COMPUTERS_SEARCH = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_name LIKE ? OR ca_name LIKE ? ORDER BY %s %s LIMIT ? OFFSET ?;";
    private final String COUNT_COMPUTERS = "SELECT count(cu_id) FROM computer;";
    private final String COUNT_COMPUTERS_SEARCH = "SELECT count(cu_id) FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_name LIKE ? OR ca_name LIKE ?";
    private final String SELECT_ONE_COMPUTER = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_id = ?;";
    private final String CREATE_COMPUTER = "INSERT INTO computer (cu_name, cu_introduced, cu_discontinued, cu_ca_id) VALUES (?, ?, ?, ?)";
    private final String DELETE_COMPUTER = "DELETE FROM computer WHERE cu_id = ?";
    private final String UPDATE_COMPUTER = "UPDATE computer SET cu_name = ?, cu_introduced = ?, cu_discontinued = ?, cu_ca_id = ? WHERE cu_id = ?";

    @Override
    public Long createComputer(final Computer c) throws DAOException {
        logger.info("create Computer");
        Long createdId = null;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn.prepareStatement(CREATE_COMPUTER,
                        Statement.RETURN_GENERATED_KEYS)) {
            setStatementsSQL(c, stat);
            stat.executeUpdate();
            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    createdId = rs.getLong(1);
                }
            }
            logger.debug("{}", createdId);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", CREATE_COMPUTER, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return createdId;
    }

    @Override
    public void deleteComputer(final Computer c) throws DAOException {
        logger.info("delete Computer");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(DELETE_COMPUTER)) {
            stat.setLong(1, c.getId());
            stat.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", DELETE_COMPUTER, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
    }

    @Override
    // Transaction model implemented here
    public void deleteMultipleComputers(List<Long> listComputerIds)
            throws DAOException {
        logger.info("delete multiple computers");
        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);
            for (Long id : listComputerIds) {
                try (PreparedStatement stat = conn
                        .prepareStatement(DELETE_COMPUTER)) {
                    stat.setLong(1, id);
                    stat.executeUpdate();
                } catch (SQLException e) {
                    conn.rollback();
                    throw new DAOException("Multi-delete went wrong");
                }
            }
            conn.commit();
        } catch (SQLException | IOException e) {
            logger.debug("{} : {}", DELETE_COMPUTER, e.getMessage());
        }
    }

    @Override
    public List<Computer> getListComputers(int pageNumber, int eltNumber,
            String orderby) throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        String newRequest = String.format(SELECT_LIST_COMPUTERS, orderby,
                "ASC");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn.prepareStatement(newRequest)) {
            stat.setInt(1, eltNumber);
            stat.setInt(2, offset);
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    listComputers.add(computerMapper.createComputer(rs));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", SELECT_LIST_COMPUTERS, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return listComputers;
    }

    @Override
    public List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search, String orderby) throws DAOException {
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        String newRequest = String.format(SELECT_LIST_COMPUTERS_SEARCH, orderby,
                "ASC");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn.prepareStatement(newRequest)) {
            String tmpSearch = "%" + search + "%";
            stat.setString(1, tmpSearch);
            stat.setString(2, tmpSearch);
            stat.setInt(3, eltNumber);
            stat.setInt(4, offset);
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    listComputers.add(computerMapper.createComputer(rs));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", SELECT_LIST_COMPUTERS_SEARCH,
                    e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return listComputers;
    }

    @Override
    public Computer showDetails(final Computer c) throws DAOException {
        logger.info("show Details Computer");
        Computer newComputer = null;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(SELECT_ONE_COMPUTER)) {
            stat.setLong(1, c.getId());
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    newComputer = computerMapper.createComputer(rs);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", SELECT_ONE_COMPUTER, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return newComputer;
    }

    @Override
    public void updateComputer(final Computer c) throws DAOException {
        logger.info("update Computer");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(UPDATE_COMPUTER)) {
            setStatementsSQL(c, stat);
            stat.setLong(5, c.getId());
            stat.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", UPDATE_COMPUTER, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
    }

    @Override
    public int getPageCountComputers(final int eltNumber) throws DAOException {
        logger.info("count Computers");
        int pageNumber = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(COUNT_COMPUTERS)) {
            try (ResultSet rs = stat.executeQuery()) {
                rs.next();
                final int tailleListComputers = rs.getInt(1);
                pageNumber = tailleListComputers / eltNumber;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", COUNT_COMPUTERS, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return pageNumber;
    }

    @Override
    public int getCountComputers() throws DAOException {
        logger.info("count Computers");
        int tailleListComputers = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(COUNT_COMPUTERS)) {
            try (ResultSet rs = stat.executeQuery()) {
                rs.next();
                tailleListComputers = rs.getInt(1);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", COUNT_COMPUTERS, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
        return tailleListComputers;
    }

    @Override
    public int getCountComputersSearch(String search) throws DAOException {
        int nbrComputersResult = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(COUNT_COMPUTERS_SEARCH)) {
            String tmpSearch = "%" + search + "%";
            stat.setString(1, tmpSearch);
            stat.setString(2, tmpSearch);
            try (ResultSet rs = stat.executeQuery();) {
                rs.next();
                nbrComputersResult = rs.getInt(1);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", COUNT_COMPUTERS_SEARCH, e.getMessage());
            throw new DAOException("Un problème d'accès à la BDD a eu lieu");
        }
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
                stat.getParameterMetaData());
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
