package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.servlets.constants.ColumnNames;

public class PageComputerSearchSorted extends PageComputerSearch {
    private ComputerService computerService = ComputerService.INSTANCE;
    private ColumnNames orderby = ColumnNames.NAME;
    private boolean ascdesc = true;

    public PageComputerSearchSorted() {
    }

    public PageComputerSearchSorted(String search) {
        super(search);
    }

    public PageComputerSearchSorted(String search, PageLength tailleMax) {
        super(search, tailleMax);
    }

    public PageComputerSearchSorted(String search, PageLength tailleMax,
            ColumnNames orderby, boolean ascdesc) {
        super(search, tailleMax);
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
