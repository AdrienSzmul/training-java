package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.mapper.ComputerMapperDTO;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.servlets.constants.Views;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory
            .getLogger(DashboardServlet.class);

    /**
     * @throws IOException
     * @throws ServletException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (StringUtils.isBlank(search)) {
            request = setRequest(request);
        } else {
            request = setSearchRequest(request, search);
        }
        this.getServletContext().getRequestDispatcher(Views.DASHBOARD)
                .forward(request, response);
    }

    private HttpServletRequest setSearchRequest(HttpServletRequest request,
            String search) {
        int eltNumber = 20;
        int pageNumber = 0;
        try {
            eltNumber = Integer.parseInt(request.getParameter("eltNumber"));
            logger.info("Numéro de page : {}", eltNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            logger.info("taillePage : {}", pageNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        List<Computer> listComputers = null;
        try {
            listComputers = ComputerService.INSTANCE
                    .getListComputersSearch(pageNumber, eltNumber, search);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        List<ComputerDTO> listComputersDTO = new ArrayList<>();
        for (Computer computer : listComputers) {
            listComputersDTO.add(ComputerMapperDTO.INSTANCE
                    .createcomputerDTOfromcomputer(computer));
        }
        int nombreRes = listComputersDTO.size();
        int pageMax = nombreRes / eltNumber;
        request.setAttribute("pageIndex", pageNumber);
        request.setAttribute("eltNumber", eltNumber);
        request.setAttribute("countComputers", nombreRes);
        request.setAttribute("maxNumberPages", pageMax);
        request.setAttribute("listComputers", listComputersDTO);
        request.setAttribute("eltNumberList", PageLength.toIntList());
        return request;
    }

    private HttpServletRequest setRequest(HttpServletRequest request) {
        int eltNumber = 20;
        int pageNumber = 0;
        try {
            eltNumber = Integer.parseInt(request.getParameter("eltNumber"));
            logger.info("Numéro de page : {}", eltNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            logger.info("taillePage : {}", pageNumber);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        List<Computer> listComputers = null;
        try {
            listComputers = ComputerService.INSTANCE
                    .getListComputers(pageNumber, eltNumber);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        List<ComputerDTO> listComputersDTO = new ArrayList<>();
        for (Computer computer : listComputers) {
            listComputersDTO.add(ComputerMapperDTO.INSTANCE
                    .createcomputerDTOfromcomputer(computer));
        }
        int nombreRes = 0;
        try {
            nombreRes = ComputerService.INSTANCE.getCountComputers();
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        int pageMax = 0;
        try {
            pageMax = ComputerService.INSTANCE.getPageCountComputers(eltNumber);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        request.setAttribute("pageIndex", pageNumber);
        request.setAttribute("eltNumber", eltNumber);
        request.setAttribute("countComputers", nombreRes);
        request.setAttribute("maxNumberPages", pageMax);
        request.setAttribute("listComputers", listComputersDTO);
        request.setAttribute("eltNumberList", PageLength.toIntList());
        return request;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
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
            ComputerService.INSTANCE
                    .deleteMultipleComputers(listDelComputerIds);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        request = setRequest(request);
        this.getServletContext().getRequestDispatcher(Views.DASHBOARD)
                .forward(request, response);
    }
}
