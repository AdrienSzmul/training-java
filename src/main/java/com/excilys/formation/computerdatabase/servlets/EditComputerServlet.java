package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.mapper.CompanyMapperDTO;
import com.excilys.formation.computerdatabase.mapper.ComputerMapperDTO;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.service.ValidationException;
import com.excilys.formation.computerdatabase.servlets.constants.Views;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
            .getLogger(EditComputerServlet.class);
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private ComputerService computerService;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request = setRequest(request);
        this.getServletContext().getRequestDispatcher(Views.EDIT_COMPUTER)
                .forward(request, response);
    }

    private HttpServletRequest setRequest(HttpServletRequest request) {
        try {
            List<Company> listCompanies = companyDAO.getListCompanies(0, 100);
            List<CompanyDTO> listCompaniesDTO = new ArrayList<>();
            listCompanies.forEach(
                    company -> listCompaniesDTO.add(CompanyMapperDTO.INSTANCE
                            .createCompanyDTOfromCompany(company)));
            request.setAttribute("listCompanies", listCompaniesDTO);
        } catch (DAOException e) {
            logger.error("Erreur lors de la lecture en BDD", e);
        }
        return request;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        setPostRequest(request);
        setRequest(request);
    }

    private void setPostRequest(HttpServletRequest request) {
        String computerIdStr = request.getParameter("computerId");
        String computerName = request.getParameter("computerName");
        String introduced = request.getParameter("introduced");
        String discontinued = request.getParameter("discontinued");
        String companyIdStr = request.getParameter("companyId");
        logger.info("Nom rentré: {}", computerName);
        logger.info("Date de mise en place:{}", introduced);
        logger.info("Date d'arrêt de commercialisation:{}", discontinued);
        logger.info("Id de la compagnie:{}", companyIdStr);
        ComputerDTO computerDTO = new ComputerDTO();
        if (!StringUtils.isBlank(computerIdStr)) {
            int computerId = Integer.valueOf(computerIdStr);
            computerDTO.setId(computerId);
        }
        if (!StringUtils.isBlank(companyIdStr)) {
            CompanyDTO companyDTO = new CompanyDTO();
            int companyId = Integer.valueOf(companyIdStr);
            companyDTO.setId(companyId);
            computerDTO.setCompany(companyDTO);
        }
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        computerDTO.setName(computerName);
        Computer computer = ComputerMapperDTO
                .createcomputerfromcomputerDTO(computerDTO);
        try {
            computerService.updateComputer(computer);
        } catch (ValidationException e) {
            String error = "" + e.getMessage();
            logger.error("{}", e);
            request.setAttribute("error", error);
        } catch (ServiceException e) {
            logger.error("{}", e);
        }
    }
}
