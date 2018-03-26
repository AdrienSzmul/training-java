package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.mapper.ComputerMapperDTO;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.paginator.PageLength;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.servlets.constants.Views;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(Dashboard.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
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
        List<Computer> listComputers = ComputerService.INSTANCE
                .getListComputers(pageNumber, eltNumber);
        List<ComputerDTO> listComputersDTO = new ArrayList<>();
        for (Computer computer : listComputers) {
            listComputersDTO.add(ComputerMapperDTO.INSTANCE
                    .createcomputerDTOfromcomputer(computer));
        }
        int nombreRes = ComputerService.INSTANCE.getCountComputers();
        int pageMax = ComputerService.INSTANCE.getPageCountComputers(eltNumber);
        request.setAttribute("pageIndex", pageNumber);
        request.setAttribute("countComputers", nombreRes);
        request.setAttribute("maxNumberPages", pageMax);
        request.setAttribute("listComputers", listComputersDTO);
        request.setAttribute("eltNumberList", PageLength.toIntList());
        this.getServletContext().getRequestDispatcher(Views.DASHBOARD)
                .forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
