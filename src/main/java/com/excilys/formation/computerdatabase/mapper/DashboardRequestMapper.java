package com.excilys.formation.computerdatabase.mapper;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.paginator.Page;
import com.excilys.formation.computerdatabase.paginator.PageComputerSearchSorted;
import com.excilys.formation.computerdatabase.paginator.PageComputerSorted;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.servlets.constants.ColumnNames;

public class DashboardRequestMapper {
    private static final Logger logger = LoggerFactory
            .getLogger(DashboardRequestMapper.class);

    public DashboardRequestMapper() {
    }

    public static PageComputerSorted mapDoGet(HttpServletRequest request)
            throws PageLengthException, ServiceException {
        PageComputerSorted pageComputerSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(request, "orderby",
                ColumnNames.NAME);
        Integer pageNumber = RequestMapper.mapPageNumber(request, "pageNumber",
                Page.FIRST_PAGE);
        PageLength eltNumber = RequestMapper.mapPageSize(request, "eltNumber",
                PageLength.TWENTY);
        boolean ascdesc = RequestMapper.mapAscDesc(request, "ascdesc", true);
        pageComputerSorted = new PageComputerSorted(eltNumber, orderByEnum,
                ascdesc);
        pageComputerSorted.goToPage(pageNumber);
        return pageComputerSorted;
    }

    public static PageComputerSearchSorted mapSearchDoGet(
            HttpServletRequest request, String search)
            throws PageLengthException, ServiceException {
        PageComputerSearchSorted pageComputerSearchSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(request, "orderby",
                ColumnNames.NAME);
        Integer pageNumber = RequestMapper.mapPageNumber(request, "pageNumber",
                Page.FIRST_PAGE);
        PageLength eltNumber = RequestMapper.mapPageSize(request, "eltNumber",
                PageLength.TWENTY);
        boolean ascdesc = RequestMapper.mapAscDesc(request, "ascdesc", true);
        pageComputerSearchSorted = new PageComputerSearchSorted(search,
                eltNumber, orderByEnum, ascdesc);
        pageComputerSearchSorted.goToPage(pageNumber);
        return pageComputerSearchSorted;
    }
}
