package com.excilys.formation.computerdatabase.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.paginator.Page;
import com.excilys.formation.computerdatabase.paginator.PageComputerSearchSorted;
import com.excilys.formation.computerdatabase.paginator.PageComputerSorted;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

@Component
public class DashboardRequestMapper {
    private static final Logger logger = LoggerFactory
            .getLogger(DashboardRequestMapper.class);
    private ComputerService computerService;

    private DashboardRequestMapper(ComputerService computerService) {
        this.computerService = computerService;
    }

    public PageComputerSorted mapDoGet(Map<String, String> allParams)
            throws PageLengthException, ServiceException {
        PageComputerSorted pageComputerSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(allParams, "orderby",
                ColumnNames.NAME);
        Integer pageNumber = RequestMapper.mapPageNumber(allParams,
                "pageNumber", Page.FIRST_PAGE);
        PageLength eltNumber = RequestMapper.mapPageSize(allParams, "eltNumber",
                PageLength.TWENTY);
        boolean ascdesc = RequestMapper.mapAscDesc(allParams, "ascdesc", true);
        pageComputerSorted = new PageComputerSorted(eltNumber, orderByEnum,
                ascdesc, computerService);
        pageComputerSorted.goToPage(pageNumber);
        return pageComputerSorted;
    }

    public PageComputerSearchSorted mapSearchDoGet(
            Map<String, String> allParams, String search)
            throws PageLengthException, ServiceException {
        PageComputerSearchSorted pageComputerSearchSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(allParams, "orderby",
                ColumnNames.NAME);
        Integer pageNumber = RequestMapper.mapPageNumber(allParams,
                "pageNumber", Page.FIRST_PAGE);
        PageLength eltNumber = RequestMapper.mapPageSize(allParams, "eltNumber",
                PageLength.TWENTY);
        boolean ascdesc = RequestMapper.mapAscDesc(allParams, "ascdesc", true);
        pageComputerSearchSorted = new PageComputerSearchSorted(search,
                eltNumber, orderByEnum, ascdesc, computerService);
        pageComputerSearchSorted.goToPage(pageNumber);
        return pageComputerSearchSorted;
    }
}
