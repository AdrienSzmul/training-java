package com.excilys.formation.computerdatabase.mapper;

import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.dto.CompanyDTO;

@Component
public class CompanyMapperDTO {
    public CompanyDTO createCompanyDTOfromCompany(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId().intValue());
        companyDTO.setName(company.getName());
        return companyDTO;
    }

    public Company createCompanyfromCompanyDTO(CompanyDTO companyDTO) {
        final CompanyBuilder b = new Company.CompanyBuilder();
        b.withId(Long.valueOf(companyDTO.getId()));
        b.withName(companyDTO.getName());
        return b.build();
    }
}
