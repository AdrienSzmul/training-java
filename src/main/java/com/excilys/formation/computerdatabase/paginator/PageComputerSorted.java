package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.servlets.constants.ColumnNames;

public class PageComputerSorted extends PageComputer {
    private ColumnNames orderby = ColumnNames.NAME;
    private boolean ascdesc = true;

    public PageComputerSorted(ComputerService computerService) {
        super(computerService);
    }

    public PageComputerSorted(PageLength tailleMax,
            ComputerService computerService) {
        super(tailleMax, computerService);
    }

    public PageComputerSorted(PageLength tailleMax, ColumnNames orderby,
            boolean ascdesc, ComputerService computerService) {
        super(tailleMax, computerService);
        this.orderby = orderby;
        this.ascdesc = ascdesc;
    }

    @Override
    protected void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSorted(pageNumber,
                tailleMax.getValue(), orderby.getValue(), ascdesc);
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
