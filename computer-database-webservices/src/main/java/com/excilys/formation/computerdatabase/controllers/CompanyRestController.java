package com.excilys.formation.computerdatabase.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.excilys.formation.computerdatabase.mapper.CompanyMapperDTO;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class CompanyRestController implements ICompanyRestController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyRestController.class);
    private CompanyService companyService;
    private CompanyMapperDTO companyMapperDTO;

    public CompanyRestController(CompanyService companyService,
            CompanyMapperDTO companyMapperDTO) {
        this.companyService = companyService;
        this.companyMapperDTO = companyMapperDTO;
    }

    @Override
    @GetMapping(value = "/company/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable long id) {
        ResponseEntity<CompanyDTO> response;
        try {
            Company company = companyService.getCompanyById(id);
            CompanyDTO companyDTO = companyMapperDTO
                    .createCompanyDTOfromCompany(company);
            response = new ResponseEntity<CompanyDTO>(companyDTO,
                    HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<CompanyDTO>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public List<CompanyDTO> getCompanies() {
        // TODO Auto-generated method stub
        return null;
    }
}
