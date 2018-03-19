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

import com.excilys.formation.computerdatabase.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.DBConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author excilys
 *
 */
public enum ComputerDAO implements IComputerDAO {

	INSTANCE;

	final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private DBConnection dbConnection = DBConnection.INSTANCE;
	private ComputerMapper computerMapper = ComputerMapper.INSTANCE;

	private String SELECT_LIST_COMPUTERS = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id ORDER BY cu_id LIMIT ? OFFSET ?;";
	private String COUNT_COMPUTERS = "SELECT count(cu_id) FROM computer;";
	private String SELECT_ONE_COMPUTER = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id, ca_id, ca_name FROM computer LEFT JOIN company ON cu_ca_id = ca_id WHERE cu_id = ?;";
	private String INSERT_NEW_COMPUTER = "INSERT INTO Computer (cu_name, cu_introduced, cu_discontinued, cu_ca_id) VALUES (?, ?, ?, ?)";
	private String DELETE_EXISTING_COMPUTER = "DELETE FROM computer WHERE cu_id = ?";
	private String UPDATE_EXISTING_COMPUTER = "UPDATE computer SET cu_name = ?, cu_introduced = ?, cu_discontinued = ?, cu_ca_id = ? WHERE cuid = ?";

	@Override
	public void createComputer(Computer c) {
		logger.info("create Computer");
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(INSERT_NEW_COMPUTER);) {
			setStatementsSQL(c, stat);
			stat.executeUpdate();
		} catch (SQLException e) {
			logger.debug(INSERT_NEW_COMPUTER, e.getMessage());
		} catch (IOException e) {
			logger.debug(INSERT_NEW_COMPUTER, e.getMessage());
		}
	}

	@Override
	public void deleteComputer(Computer c) {
		logger.info("delete Computer");
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(DELETE_EXISTING_COMPUTER);) {
			stat.setLong(1, c.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			logger.debug(DELETE_EXISTING_COMPUTER, e.getMessage());
		} catch (IOException e) {
			logger.debug(DELETE_EXISTING_COMPUTER, e.getMessage());
		}

	}

	@Override
	public List<Computer> getListComputers(int pageNumber, int eltNumber) {
		logger.info("get List Computers");
		int offset = pageNumber * eltNumber;
		ResultSet rs = null;
		List<Computer> listComputers = new ArrayList<>();
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(SELECT_LIST_COMPUTERS);) {
			stat.setInt(1, eltNumber);
			stat.setInt(2, offset);
			rs = stat.executeQuery();

			while (rs.next()) {
				listComputers.add(computerMapper.createComputer(rs));
			}

		} catch (SQLException e) {
			logger.debug(SELECT_LIST_COMPUTERS, e.getMessage());
		} catch (IOException e) {
			logger.debug(SELECT_LIST_COMPUTERS, e.getMessage());
		}
		closeConnection(rs);
		return listComputers;
	}

	@Override
	public Computer showDetails(Computer c) {
		logger.info("show Details Computer");
		Computer newComputer = new Computer();
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(SELECT_ONE_COMPUTER);) {
			stat.setLong(1, c.getId());
			rs = stat.executeQuery();
			rs.next();
			newComputer = computerMapper.createComputer(rs);
		} catch (SQLException e) {
			logger.debug(SELECT_ONE_COMPUTER, e.getMessage());
		} catch (IOException e) {
			logger.debug(SELECT_ONE_COMPUTER, e.getMessage());
		}
		closeConnection(rs);
		return newComputer;

	}

	@Override
	public void updateComputer(Computer c) {
		logger.info("update Computer");
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(UPDATE_EXISTING_COMPUTER);) {
			setStatementsSQL(c, stat);
			stat.setLong(5, c.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			logger.debug(UPDATE_EXISTING_COMPUTER, e.getMessage());
		} catch (IOException e) {
			logger.debug(UPDATE_EXISTING_COMPUTER, e.getMessage());
		}

	}

	@Override
	public int getPageCountComputers(int eltNumber) {
		logger.info("count Computers");
		int pageNumber = 0;
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(COUNT_COMPUTERS);) {
			rs = stat.executeQuery();
			rs.next();
			int tailleListComputers = rs.getInt(1);
			pageNumber = tailleListComputers / eltNumber;
		} catch (SQLException e) {
			logger.debug(COUNT_COMPUTERS, e.getMessage());
		} catch (IOException e) {
			logger.debug(COUNT_COMPUTERS, e.getMessage());
		}
		closeConnection(rs);
		return pageNumber;
	}

	private void closeConnection(ResultSet rs) {
		logger.info("Closing ocnenction");
		try {
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	// Ici on oblige la vérification nulle pour éviter d'avoir un crash peu parlant
	// si on rentre des valeurs nulles (même si on fait la vérif avec un validator
	// avant)
	private void setStatementsSQL(Computer c, PreparedStatement stat) throws SQLException {
		logger.info("setting values in sql requests as", stat.getParameterMetaData());
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
