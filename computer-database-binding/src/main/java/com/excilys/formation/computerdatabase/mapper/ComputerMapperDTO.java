package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;

import org.apache.maven.shared.utils.StringUtils;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

@Component
public class ComputerMapperDTO {
    private CompanyMapperDTO companyMapperDTO;

    public ComputerMapperDTO(CompanyMapperDTO companyMapperDTO) {
        this.companyMapperDTO = companyMapperDTO;
    }

    public final ComputerDTO createcomputerDTOfromcomputer(Computer computer) {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(computer.getId().intValue());
        computerDTO.setName(computer.getName());
        if (computer.getIntroduced() != null) {
            computerDTO.setIntroduced(computer.getIntroduced().toString());
        }
        if (computer.getDiscontinued() != null) {
            computerDTO.setDiscontinued(computer.getDiscontinued().toString());
        }
        if (computer.getCompany() != null) {
            computerDTO.setCompany(companyMapperDTO
                    .createCompanyDTOfromCompany(computer.getCompany()));
        }
        return computerDTO;
    }

    public final Computer createcomputerfromcomputerDTO(
            ComputerDTO computerDTO) {
        final ComputerBuilder bComputer = new Computer.ComputerBuilder();
        bComputer.withId(Long.valueOf(computerDTO.getId()));
        if (!computerDTO.getName().isEmpty()) {
            bComputer.withName(computerDTO.getName());
        }
        if (!StringUtils.isBlank(computerDTO.getIntroduced())) {
            bComputer.withIntroduced(
                    LocalDate.parse(computerDTO.getIntroduced()));
        }
        if (!StringUtils.isBlank(computerDTO.getDiscontinued())) {
            bComputer.withDiscontinued(
                    LocalDate.parse(computerDTO.getDiscontinued()));
        }
        if (computerDTO.getCompany() != null) {
            bComputer.withCompany(companyMapperDTO
                    .createCompanyfromCompanyDTO(computerDTO.getCompany()));
        }
        return bComputer.build();
    }
}
