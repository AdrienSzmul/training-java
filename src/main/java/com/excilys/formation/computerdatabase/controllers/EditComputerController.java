package com.excilys.formation.computerdatabase.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

/**
 * Servlet implementation class EditComputerServlet
 */
@Controller
public class EditComputerController {
    private static final Logger logger = LoggerFactory
            .getLogger(EditComputerController.class);
    private CompanyDAO companyDAO;
    private ComputerService computerService;
    private ComputerMapperDTO computerMapperDTO;
    private CompanyMapperDTO companyMapperDTO;

    public EditComputerController(CompanyDAO companyDAO,
            ComputerService computerService,
            ComputerMapperDTO computerMapperDTO,
            CompanyMapperDTO companyMapperDTO) {
        this.companyDAO = companyDAO;
        this.computerService = computerService;
        this.computerMapperDTO = computerMapperDTO;
        this.companyMapperDTO = companyMapperDTO;
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/editComputer", method = RequestMethod.GET)
    protected ModelAndView showFormFilled(
            @ModelAttribute("computerDTO") ComputerDTO computerDTO)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView(Views.EDIT_COMPUTER, "computerDTO",
                computerDTO);
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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/editComputer", method = RequestMethod.POST)
    protected ModelAndView doPost(@RequestParam Map<String, String> allParams)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView(Views.EDIT_COMPUTER);
        setPostRequest(mav, allParams);
        setRequest(mav);
        return mav;
    }

    private void setPostRequest(ModelAndView mav,
            Map<String, String> allParams) {
        String computerIdStr = allParams.get("computerId");
        String computerName = allParams.get("computerName");
        String introduced = allParams.get("introduced");
        String discontinued = allParams.get("discontinued");
        String companyIdStr = allParams.get("companyId");
        logger.info("Id de l'ordinateur: {}", computerIdStr);
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
            if (companyIdStr.matches("[0-9]+")) {
                int companyId = Integer.valueOf(companyIdStr);
                companyDTO.setId(companyId);
                computerDTO.setCompany(companyDTO);
            }
        }
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        computerDTO.setName(computerName);
        Computer computer = computerMapperDTO
                .createcomputerfromcomputerDTO(computerDTO);
        try {
            computerService.updateComputer(computer);
        } catch (ValidationException | ServiceException e) {
            logger.error("{}", e);
        }
    }
}
