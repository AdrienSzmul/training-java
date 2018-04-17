package com.excilys.formation.computerdatabase.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.controllers.constants.Views;
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
public class DashboardController {
    @Autowired
    private ComputerService computerService;
    private final Logger logger = LoggerFactory
            .getLogger(DashboardController.class);

    /**
     * @throws IOException
     * @throws ServletException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    protected ModelAndView doGet(@RequestParam Map<String, String> allParams)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView(Views.DASHBOARD);
        if (StringUtils.isBlank(allParams.get("search"))) {
            try {
                PageComputerSorted computerSortedPage = DashboardRequestMapper
                        .mapDoGet(allParams, computerService);
                mav = setRequest(mav, computerSortedPage);
            } catch (ServiceException | PageLengthException e) {
                logger.debug(e.getMessage());
            }
        } else {
            try {
                PageComputerSearchSorted computerSearchSortedPage = DashboardRequestMapper
                        .mapSearchDoGet(allParams, allParams.get("search"),
                                computerService);
                mav = setSearchRequest(mav, computerSearchSortedPage);
            } catch (ServiceException | PageLengthException e) {
                logger.debug(e.getMessage());
            }
        }
        return mav;
    }

    private ModelAndView setSearchRequest(ModelAndView mav,
            PageComputerSearchSorted computerSearchSortedPage)
            throws ServiceException {
        PageSearchDTO<ComputerDTO> computerSearchPageDTO = PageMapperDTO
                .createComputerSearchPageDTOFromComputerSearchPage(
                        computerSearchSortedPage,
                        computerService.getCountComputersSearch(
                                computerSearchSortedPage.getSearch()));
        mav.addObject("pageDTO", computerSearchPageDTO);
        mav.addObject("maxNumberPages",
                computerSearchPageDTO.getMaxPageNumber());
        mav.addObject("eltNumberList", PageLength.toIntList());
        mav.addObject("orderby", computerSearchSortedPage.getOrderby());
        mav.addObject("ascdesc", computerSearchSortedPage.isAscdesc());
        mav.addObject("search", computerSearchSortedPage.getSearch());
        return mav;
    }

    private ModelAndView setRequest(ModelAndView mav,
            PageComputerSorted computerSortedPage) throws ServiceException {
        PageDTO<ComputerDTO> computerPageDTO = PageMapperDTO
                .createComputerPageDTOFromComputerPage(computerSortedPage,
                        computerService.getCountComputers());
        mav.addObject("pageDTO", computerPageDTO);
        mav.addObject("maxNumberPages", computerPageDTO.getMaxPageNumber());
        mav.addObject("eltNumberList", PageLength.toIntList());
        mav.addObject("orderby", computerSortedPage.getOrderby());
        mav.addObject("ascdesc", computerSortedPage.isAscdesc());
        return mav;
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
    }
}
