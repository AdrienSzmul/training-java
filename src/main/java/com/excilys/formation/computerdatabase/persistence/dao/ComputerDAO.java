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
    private final String selectListComputers = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id ORDER BY cu_id LIMIT ? OFFSET ?;";
    private final String countComputers = "SELECT count(cu_id) FROM computer;";
    private final String selectOneComputer = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_id = ?;";
    private final String insertNewComputer = "INSERT INTO Computer (cu_name, cu_introduced, cu_discontinued, cu_ca_id) VALUES (?, ?, ?, ?)";
    private final String deleteExistingComputer = "DELETE FROM computer WHERE cu_id = ?";
    private final String updateExistingComputer = "UPDATE computer SET cu_name = ?, cu_introduced = ?, cu_discontinued = ?, cu_ca_id = ? WHERE cuid = ?";

    @Override
    public void createComputer(final Computer c) {
        logger.info("create Computer");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(insertNewComputer);) {
            setStatementsSQL(c, stat);
            stat.executeUpdate();
        } catch (final SQLException e) {
            logger.debug(insertNewComputer, e.getMessage());
        } catch (final IOException e) {
            logger.debug(insertNewComputer, e.getMessage());
        }
    }

    @Override
    public void deleteComputer(final Computer c) {
        logger.info("delete Computer");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(deleteExistingComputer);) {
            stat.setLong(1, c.getId());
            stat.executeUpdate();
        } catch (final SQLException e) {
            logger.debug(deleteExistingComputer, e.getMessage());
        } catch (final IOException e) {
            logger.debug(deleteExistingComputer, e.getMessage());
        }
    }

    @Override
    public List<Computer> getListComputers(int pageNumber, int eltNumber) {
        logger.info("eltNumber:", eltNumber);
        final int offset = pageNumber * eltNumber;
        List<Computer> listComputers = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(selectListComputers);) {
            stat.setInt(1, eltNumber);
            stat.setInt(2, offset);
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    System.out.println(rs.toString());
                    listComputers.add(computerMapper.createComputer(rs));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.debug("{} : {}", selectListComputers, e.getMessage());
        }
        return listComputers;
    }

    @Override
    public Computer showDetails(final Computer c) {
        logger.info("show Details Computer");
        Computer newComputer = new Computer();
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(selectOneComputer);) {
            stat.setLong(1, c.getId());
            try (ResultSet rs = stat.executeQuery();) {
                rs.next();
                newComputer = computerMapper.createComputer(rs);
            }
        } catch (final SQLException e) {
            logger.debug(selectOneComputer, e.getMessage());
        } catch (final IOException e) {
            logger.debug(selectOneComputer, e.getMessage());
        }
        return newComputer;
    }

    @Override
    public void updateComputer(final Computer c) {
        logger.info("update Computer");
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(updateExistingComputer);) {
            setStatementsSQL(c, stat);
            stat.setLong(5, c.getId());
            stat.executeUpdate();
        } catch (final SQLException e) {
            logger.debug(updateExistingComputer, e.getMessage());
        } catch (final IOException e) {
            logger.debug(updateExistingComputer, e.getMessage());
        }
    }

    @Override
    public int getPageCountComputers(final int eltNumber) {
        logger.info("count Computers");
        int pageNumber = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(countComputers);) {
            try (ResultSet rs = stat.executeQuery();) {
                rs.next();
                final int tailleListComputers = rs.getInt(1);
                pageNumber = tailleListComputers / eltNumber;
            }
        } catch (final SQLException e) {
            logger.debug(countComputers, e.getMessage());
        } catch (final IOException e) {
            logger.debug(countComputers, e.getMessage());
        }
        return pageNumber;
    }

    @Override
    public int getCountComputers() {
        logger.info("count Computers");
        int tailleListComputers = 0;
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stat = conn
                        .prepareStatement(countComputers);) {
            try (ResultSet rs = stat.executeQuery();) {
                rs.next();
                tailleListComputers = rs.getInt(1);
            }
        } catch (final SQLException e) {
            logger.debug(countComputers, e.getMessage());
        } catch (final IOException e) {
            logger.debug(countComputers, e.getMessage());
        }
        return tailleListComputers;
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
        logger.info("setting values in sql requests as",
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
