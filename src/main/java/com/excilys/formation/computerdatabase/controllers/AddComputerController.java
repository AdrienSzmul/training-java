package com.excilys.formation.computerdatabase.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.controllers.constants.Views;
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

@Controller
public class AddComputerController {
    private static final Logger logger = LoggerFactory
            .getLogger(AddComputerController.class);
    private CompanyDAO companyDAO;
    private ComputerService computerService;
    private CompanyMapperDTO companyMapperDTO;
    private ComputerMapperDTO computerMapperDTO;
    private DashboardController dashboardController;

    public AddComputerController(CompanyDAO companyDAO,
            ComputerService computerService, CompanyMapperDTO companyMapperDTO,
            ComputerMapperDTO computerMapperDTO,
            DashboardController dashboardController) {
        this.companyDAO = companyDAO;
        this.computerService = computerService;
        this.companyMapperDTO = companyMapperDTO;
        this.computerMapperDTO = computerMapperDTO;
        this.dashboardController = dashboardController;
    }

    @RequestMapping(value = "/addComputer", method = RequestMethod.GET)
    protected ModelAndView showForm() throws ServletException, IOException {
        ModelAndView mav = new ModelAndView(Views.ADD_COMPUTER, "computerDTO",
                new ComputerDTO());
        mav = setRequest(mav);
        return mav;
    }

    private ModelAndView setRequest(ModelAndView mav) {
        try {
            List<Company> listCompanies = companyDAO.getListCompanies(0, 100);
            List<CompanyDTO> listCompaniesDTO = new ArrayList<>();
            listCompanies.forEach(company -> listCompaniesDTO.add(
                    companyMapperDTO.createCompanyDTOfromCompany(company)));
            mav.addObject("listCompanies", listCompaniesDTO);
        } catch (DAOException e) {
            logger.error("Erreur lors de la lecture en BDD", e);
        }
        return mav;
    }

    @RequestMapping(value = "/addComputer", method = RequestMethod.POST)
    protected ModelAndView submit(
            @ModelAttribute("computerDTO") ComputerDTO computerDTO)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView(Views.ADD_COMPUTER, "computerDTO",
                computerDTO);
        mav = addComputer(mav, computerDTO);
        return mav;
    }

    private ModelAndView addComputer(ModelAndView mav,
            ComputerDTO computerDTO) {
        if (computerDTO.getCompany().getId() == 0) {
            computerDTO.setCompany(null);
        }
        Computer computer = computerMapperDTO
                .createcomputerfromcomputerDTO(computerDTO);
        try {
            computerService.createComputer(computer);
            mav = dashboardController
                    .showDashboard(new HashMap<String, String>());
        } catch (ValidationException | ServiceException | ServletException
                | IOException e) {
            logger.debug(e.getMessage());
            mav = setRequest(mav);
        }
        return mav;
    }
}
