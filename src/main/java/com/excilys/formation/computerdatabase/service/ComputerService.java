/**
 *
 */
package com.excilys.formation.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.dao.ComputerDAO;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;

/**
 * @author excilys
 */
@Service
public class ComputerService {
    @Autowired
    private ComputerDAO computerDAO;
    private final ValidatorComputer val = ValidatorComputer.INSTANCE;

    public void createComputer(final Computer c)
            throws ValidationException, ServiceException {
        val.validateComputer(c);
        try {
            computerDAO.createComputer(c);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Computer> getListComputers(int pageNumber, int eltNumber)
            throws ServiceException {
        try {
            return computerDAO.getListComputers(pageNumber, eltNumber);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Computer> getListComputersSorted(int pageNumber, int eltNumber,
            String orderby, boolean ascdesc) throws ServiceException {
        try {
            return computerDAO.getListComputersSorted(pageNumber, eltNumber,
                    orderby, ascdesc);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Computer> getListComputersSearch(int pageNumber, int eltNumber,
            String search) throws ServiceException {
        try {
            return computerDAO.getListComputersSearch(pageNumber, eltNumber,
                    search);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Computer> getListComputersSearchSorted(int pageNumber,
            int eltNumber, String search, String orderby, boolean ascdesc)
            throws ServiceException {
        try {
            return computerDAO.getListComputersSearchSorted(pageNumber,
                    eltNumber, search, orderby, ascdesc);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int getPageCountComputers(final int eltNumber)
            throws ServiceException {
        try {
            return computerDAO.getPageCountComputers(eltNumber);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int getPageCountComputersSearch(final int eltNumber, String search)
            throws ServiceException {
        try {
            return computerDAO.getPageCountComputersSearch(eltNumber, search);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int getCountComputers() throws ServiceException {
        try {
            return computerDAO.getCountComputers();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int getCountComputersSearch(String search) throws ServiceException {
        try {
            return computerDAO.getCountComputersSearch(search);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Computer showDetails(final Computer c) throws ServiceException {
        try {
            return computerDAO.showDetails(c);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Computer getComputerById(final Long id) throws ServiceException {
        try {
            return computerDAO.getComputerById(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateComputer(final Computer c)
            throws ValidationException, ServiceException {
        if (c.getId() != null) {
            val.validateComputer(c);
        } else {
            throw new ServiceException(
                    "L'id du computer que vous voulez modifié est null");
        }
        try {
            computerDAO.updateComputer(c);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteComputer(final Computer c) throws ServiceException {
        try {
            computerDAO.deleteComputer(c);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteMultipleComputers(final List<Long> listDelComputers)
            throws ServiceException {
        try {
            computerDAO.deleteMultipleComputers(listDelComputers);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
