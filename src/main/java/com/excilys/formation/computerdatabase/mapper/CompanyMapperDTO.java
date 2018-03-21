package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;

public enum CompanyMapperDTO {
    INSTANCE;
    public CompanyDTO createCompanyDTOfromCompany(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }

    public Company createCompanyfromCompanyDTO(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        return company;
    }
}
