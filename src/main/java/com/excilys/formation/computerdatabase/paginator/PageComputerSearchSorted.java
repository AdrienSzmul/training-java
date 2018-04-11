package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageComputerSearchSorted extends PageComputerSearch {
    private ComputerService computerService = ComputerService.INSTANCE;
    private String orderby = "cu_id";
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
            String orderby, boolean ascdesc) {
        super(search, tailleMax);
        this.orderby = orderby;
        this.ascdesc = ascdesc;
    }

    @Override
    public void refresh(int pageNumber) throws ServiceException {
        this.pageActive = computerService.getListComputersSearchSorted(
                pageNumber, this.tailleMax.getValue(), search, orderby,
                ascdesc);
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
