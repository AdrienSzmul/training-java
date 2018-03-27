package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputer extends Page<Computer> {
    private final ComputerService computerService = ComputerService.INSTANCE;

    public PageComputer() {
        super();
    }

    @Override
    protected final int maxNumberOfPages() throws ServiceException {
        return computerService.getPageCountComputers(this.tailleMax.getValue());
    }

    @Override
    protected final void refresh(final int pageNumber) throws ServiceException {
        this.page = computerService.getListComputers(pageNumber,
                this.tailleMax.getValue());
    }
}
