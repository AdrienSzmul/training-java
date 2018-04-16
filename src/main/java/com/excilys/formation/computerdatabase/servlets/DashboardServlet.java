package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.computerdatabase.mapper.DashboardRequestMapper;
import com.excilys.formation.computerdatabase.mapper.PageLengthException;
import com.excilys.formation.computerdatabase.mapper.PageMapperDTO;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.paginator.PageComputerSearchSorted;
import com.excilys.formation.computerdatabase.paginator.PageComputerSorted;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.paginator.dto.PageDTO;
import com.excilys.formation.computerdatabase.paginator.dto.PageSearchDTO;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;

/**
 * Servlet implementation class Dashboard
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardServlet {
    @Autowired
    private ComputerService computerService;
    private final Logger logger = LoggerFactory
            .getLogger(DashboardServlet.class);

    /**
     * @throws IOException
     * @throws ServletException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (StringUtils.isBlank(search)) {
            try {
                PageComputerSorted computerSortedPage = DashboardRequestMapper
                        .mapDoGet(request, computerService);
                request = setRequest(request, computerSortedPage);
            } catch (ServiceException | PageLengthException e) {
                logger.debug(e.getMessage());
            }
        } else {
            try {
                PageComputerSearchSorted computerSearchSortedPage = DashboardRequestMapper
                        .mapSearchDoGet(request, search, computerService);
                request = setSearchRequest(request, computerSearchSortedPage);
            } catch (ServiceException | PageLengthException e) {
                logger.debug(e.getMessage());
            }
        }
    }

    private HttpServletRequest setSearchRequest(HttpServletRequest request,
            PageComputerSearchSorted computerSearchSortedPage)
            throws ServiceException {
        PageSearchDTO<ComputerDTO> computerSearchPageDTO = PageMapperDTO
                .createComputerSearchPageDTOFromComputerSearchPage(
                        computerSearchSortedPage,
                        computerService.getCountComputersSearch(
                                computerSearchSortedPage.getSearch()));
        request.setAttribute("pageDTO", computerSearchPageDTO);
        request.setAttribute("maxNumberPages",
                computerSearchPageDTO.getMaxPageNumber());
        request.setAttribute("eltNumberList", PageLength.toIntList());
        request.setAttribute("orderby", computerSearchSortedPage.getOrderby());
        request.setAttribute("ascdesc", computerSearchSortedPage.isAscdesc());
        request.setAttribute("search", computerSearchSortedPage.getSearch());
        return request;
    }

    private HttpServletRequest setRequest(HttpServletRequest request,
            PageComputerSorted computerSortedPage) throws ServiceException {
        PageDTO<ComputerDTO> computerPageDTO = PageMapperDTO
                .createComputerPageDTOFromComputerPage(computerSortedPage,
                        computerService.getCountComputers());
        request.setAttribute("pageDTO", computerPageDTO);
        request.setAttribute("maxNumberPages",
                computerPageDTO.getMaxPageNumber());
        request.setAttribute("eltNumberList", PageLength.toIntList());
        request.setAttribute("orderby", computerSortedPage.getOrderby());
        request.setAttribute("ascdesc", computerSortedPage.isAscdesc());
        return request;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String delComputerIdsStr = request.getParameter("selection");
        List<Long> listDelComputerIds = new ArrayList<>();
        Arrays.stream(delComputerIdsStr.split(",")) // allows to make actions on
                                                    // each elt between ','
                .filter(s -> s.matches("[0-9]+")) // doesn't take anything else
                                                  // but numbers
                .map(Long::valueOf) // maps the string to long
                .forEach(listDelComputerIds::add); // add the result to the list
        try {
            computerService.deleteMultipleComputers(listDelComputerIds);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        this.doGet(request, response);
    }
}
