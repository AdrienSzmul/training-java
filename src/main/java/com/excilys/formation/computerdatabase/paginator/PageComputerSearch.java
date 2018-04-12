package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSearch extends PageComputer {
    protected String search;

    public PageComputerSearch(ComputerService computerService) {
        super(computerService);
    }

    public PageComputerSearch(String search, ComputerService computerService) {
        super(computerService);
        this.search = search;
    }

    public PageComputerSearch(String search, PageLength tailleMax,
            ComputerService computerService) {
        super(tailleMax, computerService);
        this.search = search;
    }

    @Override
    public int maxNumberOfPages() throws ServiceException {
        return computerService.getPageCountComputersSearch(
                this.tailleMax.getValue(), this.search);
    }

    @Override
    protected void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSearch(pageNumber,
                this.tailleMax.getValue(), this.search);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
