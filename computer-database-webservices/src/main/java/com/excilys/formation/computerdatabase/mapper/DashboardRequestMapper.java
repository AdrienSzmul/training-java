package com.excilys.formation.computerdatabase.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.paginator.Page;
import com.excilys.formation.computerdatabase.paginator.PageComputerSearchSorted;
import com.excilys.formation.computerdatabase.paginator.PageComputerSorted;
import com.excilys.formation.computerdatabase.paginator.PageFactory;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ServiceException;

@Component
public class DashboardRequestMapper {
    private static final Logger logger = LoggerFactory
            .getLogger(DashboardRequestMapper.class);
    private PageFactory pageFactory;

    public DashboardRequestMapper(PageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    public PageComputerSorted mapDoGet(Map<String, String> allParams)
            throws PageLengthException, ServiceException {
        PageComputerSorted pageComputerSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(allParams, "orderby",
                ColumnNames.NAME);
        logger.debug(orderByEnum.getValue());
        Integer pageNumber = RequestMapper.mapPageNumber(allParams,
                "pageNumber", Page.FIRST_PAGE);
        logger.debug(pageNumber.toString());
        PageLength eltNumber = RequestMapper.mapPageSize(allParams, "eltNumber",
                PageLength.TWENTY);
        logger.debug(eltNumber.name());
        boolean ascdesc = RequestMapper.mapAscDesc(allParams, "ascdesc", true);
        pageComputerSorted = pageFactory.createPageComputerSorted(eltNumber,
                orderByEnum, ascdesc);
        logger.debug(pageComputerSorted.getOrderby().getValue());
        logger.debug(pageComputerSorted.getPageNumber().toString());
        logger.debug(((Integer) pageComputerSorted.getTailleMax().getValue())
                .toString());
        pageComputerSorted.goToPage(pageNumber);
        return pageComputerSorted;
    }

    public PageComputerSearchSorted mapSearchDoGet(
            Map<String, String> allParams, String search)
            throws PageLengthException, ServiceException {
        PageComputerSearchSorted pageComputerSearchSorted;
        ColumnNames orderByEnum = RequestMapper.mapOrderBy(allParams, "orderby",
                ColumnNames.NAME);
        logger.debug(orderByEnum.getValue());
        Integer pageNumber = RequestMapper.mapPageNumber(allParams,
                "pageNumber", Page.FIRST_PAGE);
        logger.debug(pageNumber.toString());
        PageLength eltNumber = RequestMapper.mapPageSize(allParams, "eltNumber",
                PageLength.TWENTY);
        logger.debug(eltNumber.name());
        boolean ascdesc = RequestMapper.mapAscDesc(allParams, "ascdesc", true);
        pageComputerSearchSorted = pageFactory.createPageComputerSearchSorted(
                search, eltNumber, orderByEnum, ascdesc);
        logger.debug(pageComputerSearchSorted.getOrderby().getValue());
        logger.debug(pageComputerSearchSorted.getPageNumber().toString());
        logger.debug(
                ((Integer) pageComputerSearchSorted.getTailleMax().getValue())
                        .toString());
        pageComputerSearchSorted.goToPage(pageNumber);
        return pageComputerSearchSorted;
    }
}
