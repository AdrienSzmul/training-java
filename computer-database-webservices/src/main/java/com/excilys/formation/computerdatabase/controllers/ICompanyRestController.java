package com.excilys.formation.computerdatabase.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;

public interface ICompanyRestController {
    ResponseEntity<CompanyDTO> getCompany(long id);

    ResponseEntity<List<CompanyDTO>> getCompanies();
}
