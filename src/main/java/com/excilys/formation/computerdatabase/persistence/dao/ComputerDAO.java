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

/**
 * @author excilys
 *
 */
public enum ComputerDAO implements IComputerDAO {

	INSTANCE;

	private DBConnection dbConnection = DBConnection.INSTANCE;
	private ComputerMapper computerMapper = ComputerMapper.INSTANCE;

	private String SELECT_LIST_COMPUTERS = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id FROM computer ORDER BY ca_id LIMIT ? OFFSET ?;";
	private String COUNT_COMPUTERS = "SELECT count(*) FROM computer;";
	private String SELECT_ONE_COMPUTER = "SELECT cu_id, cu_name, cu_introduced, cu_discontinued, cu_ca_id FROM computer WHERE ca_id = ?;";
	private String INSERT_NEW_COMPUTER = "INSERT INTO Computer (cu_name, cu_introduced, cu_discontinued, cu_ca_id) VALUES (?, ?, ?, ?)";
	private String DELETE_EXISTING_COMPUTER = "DELETE FROM computer WHERE cu_id = ?";
	private String UPDATE_EXISTING_COMPUTER = "UPDATE computer SET cu_name = ?, cu_introduced = ?, cu_discontinued = ?, cu_ca_id = ? WHERE cuid = ?";

	@Override
	public void createComputer(Computer c) {
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(INSERT_NEW_COMPUTER);) {
			setStatementsSQL(c, stat);
			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComputer(Computer c) {

		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(DELETE_EXISTING_COMPUTER);) {
			stat.setLong(1, c.getId());
			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Computer> getListComputers(int pageNumber, int eltNumber) {
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

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return listComputers;
	}

	@Override
	public Computer showDetails(Computer c) {
		Computer newComputer = new Computer();
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(SELECT_ONE_COMPUTER);) {
			stat.setLong(1, c.getId());
			rs = stat.executeQuery();
			rs.next();
			newComputer = computerMapper.fillFieldsForComputer(rs, c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return newComputer;

	}

	@Override
	public void updateComputer(Computer c) {
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(UPDATE_EXISTING_COMPUTER);) {
			setStatementsSQL(c, stat);
			stat.setLong(5, c.getId());
			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getPageCountComputers(int eltNumber) {
		int pageNumber = 0;
		ResultSet rs = null;
		try (Connection conn = dbConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(COUNT_COMPUTERS);) {
			rs = stat.executeQuery();
			rs.next();
			int tailleListComputers = rs.getInt(1);
			pageNumber = tailleListComputers / eltNumber;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(rs);
		return pageNumber;
	}

	private void closeConnection(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Ici on oblige la vérification nulle pour éviter d'avoir un crash peu parlant
	// si on rentre des valeurs nulles (même si on fait la vérif avec un validator
	// avant)
	private void setStatementsSQL(Computer c, PreparedStatement stat) throws SQLException {
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
