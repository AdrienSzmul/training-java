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

import com.excilys.formation.computerdatabase.mapper.CompanyMapperDTO;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;
import com.excilys.formation.computerdatabase.persistence.dao.CompanyDAO;
import com.excilys.formation.computerdatabase.persistence.dao.DAOException;
import com.excilys.formation.computerdatabase.servlets.constants.Views;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
            .getLogger(EditComputerServlet.class);

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
            List<Company> listCompanies = CompanyDAO.INSTANCE
                    .getListCompanies(0, 100);
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
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
