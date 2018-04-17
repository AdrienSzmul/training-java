package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSearchSorted extends PageComputerSearch {
    private ColumnNames orderby = ColumnNames.NAME;
    private boolean ascdesc = true;

    public PageComputerSearchSorted(ComputerService computerService) {
        super(computerService);
    }

    public PageComputerSearchSorted(String search,
            ComputerService computerService) {
        super(search, computerService);
    }

    public PageComputerSearchSorted(String search, PageLength tailleMax,
            ComputerService computerService) {
        super(search, tailleMax, computerService);
    }

    public PageComputerSearchSorted(String search, PageLength tailleMax,
            ColumnNames orderby, boolean ascdesc,
            ComputerService computerService) {
        super(search, tailleMax, computerService);
        this.orderby = orderby;
        this.ascdesc = ascdesc;
    }

    @Override
    public void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSearchSorted(
                pageNumber, this.tailleMax.getValue(), search,
                orderby.getValue(), ascdesc);
    }

    public ColumnNames getOrderby() {
        return orderby;
    }

    public void setOrderby(ColumnNames orderby) {
        this.orderby = orderby;
    }

    public boolean isAscdesc() {
        return ascdesc;
    }

    public void setAscdesc(boolean ascdesc) {
        this.ascdesc = ascdesc;
    }
}
