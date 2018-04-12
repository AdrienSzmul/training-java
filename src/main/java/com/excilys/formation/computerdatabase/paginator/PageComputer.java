package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputer extends Page<Computer> {
    protected ComputerService computerService;

    public PageComputer(ComputerService computerService) {
        super();
        this.computerService = computerService;
    }

    public PageComputer(PageLength tailleMax, ComputerService computerService) {
        super(tailleMax);
        this.computerService = computerService;
    }

    @Override
    public int maxNumberOfPages() throws ServiceException {
        return computerService.getPageCountComputers(this.tailleMax.getValue());
    }

    @Override
    protected void refresh(final int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputers(pageNumber,
                this.tailleMax.getValue());
    }
}
