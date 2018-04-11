package com.excilys.formation.computerdatabase.mapper;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            throws ServiceException {
        PageComputerSorted pageComputerSorted;
        boolean ascdesc = true;
        PageLength eltNumber = PageLength.TWENTY;
        int pageNumber = 0;
        String orderByEnum = "cu_id";
        String ascdescString = request.getParameter("ascdesc");
        String orderBy = request.getParameter("orderby");
        ascdesc = Boolean.valueOf(ascdescString);
        try {
            orderByEnum = ColumnNames.valueOf(orderBy.toUpperCase()).getValue();
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error(e.getMessage());
        }
        try {
            eltNumber = PageLength.valueOf(request.getParameter("eltNumber"));
            logger.info("Numéro de page : {}", eltNumber);
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            logger.info("taillePage : {}", pageNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        pageComputerSorted = new PageComputerSorted(eltNumber, orderByEnum,
                ascdesc);
        pageComputerSorted.goToPage(pageNumber);
        return pageComputerSorted;
    }

    public static PageComputerSearchSorted mapSearchDoGet(
            HttpServletRequest request, String search) throws ServiceException {
        PageComputerSearchSorted pageComputerSearchSorted;
        boolean ascdesc = true;
        PageLength eltNumber = PageLength.TWENTY;
        int pageNumber = 0;
        String orderByEnum = "cu_id";
        String ascdescString = request.getParameter("ascdesc");
        String orderBy = request.getParameter("orderby");
        ascdesc = Boolean.valueOf(ascdescString);
        try {
            orderByEnum = ColumnNames.valueOf(orderBy.toUpperCase()).getValue();
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error(e.getMessage());
        }
        try {
            eltNumber = PageLength.valueOf(request.getParameter("eltNumber"));
            logger.info("Numéro de page : {}", eltNumber);
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            logger.info("taillePage : {}", pageNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        pageComputerSearchSorted = new PageComputerSearchSorted(search,
                eltNumber, orderByEnum, ascdesc);
        pageComputerSearchSorted.goToPage(pageNumber);
        return pageComputerSearchSorted;
    }
}
