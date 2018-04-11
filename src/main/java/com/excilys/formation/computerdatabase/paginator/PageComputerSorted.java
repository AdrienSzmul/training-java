package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSorted extends PageComputer {
    private ComputerService computerService = ComputerService.INSTANCE;
    private String orderby = "cu_id";
    private boolean ascdesc = true;

    public PageComputerSorted() {
    }

    public PageComputerSorted(PageLength tailleMax) {
        super(tailleMax);
    }

    public PageComputerSorted(PageLength tailleMax, String orderby,
            boolean ascdesc) {
        super(tailleMax);
        this.orderby = orderby;
        this.ascdesc = ascdesc;
    }

    @Override
    protected void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSorted(pageNumber,
                tailleMax.getValue(), orderby, ascdesc);
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public boolean isAscdesc() {
        return ascdesc;
    }

    public void setAscdesc(boolean ascdesc) {
        this.ascdesc = ascdesc;
    }
}
