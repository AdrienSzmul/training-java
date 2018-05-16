package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSearchSorted extends PageComputerSearch {
    private ColumnNames orderby = ColumnNames.NAME;
    private boolean ascdesc = true;

    public PageComputerSearchSorted() {
        super();
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
                orderby.name().toLowerCase(), ascdesc);
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
