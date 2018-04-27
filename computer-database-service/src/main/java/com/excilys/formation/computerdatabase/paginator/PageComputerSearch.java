package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSearch extends PageComputer {
    protected String search;

    public PageComputerSearch() {
        super();
    }

    public PageComputerSearch(String search) {
        super();
        this.search = search;
    }

    public PageComputerSearch(String search, PageLength tailleMax) {
        super(tailleMax);
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
