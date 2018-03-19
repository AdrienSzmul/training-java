/**
 * 
 */
package com.excilys.formation.computerdatabase.service;

import java.time.LocalDate;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.dao.ComputerDAO;

/**
 * @author excilys
 *
 */
public enum ComputerService {

	INSTANCE;

	private ComputerDAO computerDAO = ComputerDAO.INSTANCE;
	private ValidatorComputer val = ValidatorComputer.INSTANCE;

	public void createComputer(Computer c)
			throws NullNameException, DateMismatchException, MissingCompanyException {
		val.validateComputer(c);
		computerDAO.createComputer(c);
	}

	public List<Computer> getListComputers(int pageNumber, int eltNumber) {
		return computerDAO.getListComputers(pageNumber, eltNumber);
	}

	public int getPageCountComputers(int eltNumber) {
		return computerDAO.getPageCountComputers(eltNumber);
	}

	public Computer showDetails(Computer c) {
		return computerDAO.showDetails(c);
	}

	public void updateComputer(Computer c)
			throws NullNameException, DateMismatchException, MissingCompanyException {
		val.validateComputer(c);
		computerDAO.updateComputer(c);
	}

//	private Computer createComputerFromSpecificCompanyId(String name, LocalDate introduced, LocalDate discontinued,
//			Long company_id) {
//		Company ca = new Company();
//		ca.setId(company_id);
//		Computer c = new Computer(name, introduced, discontinued, ca);
//		return c;
//	}

	public void deleteComputer(Computer c) {
		computerDAO.deleteComputer(c);
	}

//	private Computer createComputerWithOnlyId(Long id) {
//		Computer c = new Computer();
//		c.setId(id);
//		return c;
//	}

}
