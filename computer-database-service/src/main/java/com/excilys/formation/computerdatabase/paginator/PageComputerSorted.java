package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSorted extends PageComputer {
    private ColumnNames orderby = ColumnNames.NAME;
    private boolean ascdesc = true;

    public PageComputerSorted() {
        super();
    }

    public PageComputerSorted(PageLength tailleMax) {
        super(tailleMax);
    }

    public PageComputerSorted(PageLength tailleMax, ColumnNames orderby,
            boolean ascdesc) {
        super(tailleMax);
        this.orderby = orderby;
        this.ascdesc = ascdesc;
    }

    @Override
    protected void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSorted(pageNumber,
                tailleMax.getValue(), orderby.name().toLowerCase(), ascdesc);
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
