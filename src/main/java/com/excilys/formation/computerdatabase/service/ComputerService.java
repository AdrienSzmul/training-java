/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.dao.ComputerDAO;

/**
 * @author excilys
 */
public enum ComputerService {
    INSTANCE;
    private final ComputerDAO computerDAO = ComputerDAO.INSTANCE;
    private final ValidatorComputer val = ValidatorComputer.INSTANCE;

    public void createComputer(final Computer c) throws NullNameException,
            DateMismatchException, MissingCompanyException {
        val.validateComputer(c);
        computerDAO.createComputer(c);
    }

    public List<Computer> getListComputers(final int pageNumber,
            final int eltNumber) {
        return computerDAO.getListComputers(pageNumber, eltNumber);
    }

    public int getPageCountComputers(final int eltNumber) {
        return computerDAO.getPageCountComputers(eltNumber);
    }

    public Computer showDetails(final Computer c) {
        return computerDAO.showDetails(c);
    }

    public void updateComputer(final Computer c) throws NullNameException,
            DateMismatchException, MissingCompanyException {
        val.validateComputer(c);
        computerDAO.updateComputer(c);
    }

    public void deleteComputer(final Computer c) {
        computerDAO.deleteComputer(c);
    }
}
