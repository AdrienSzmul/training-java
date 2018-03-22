package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

public enum ComputerMapperDTO {
    INSTANCE;
    public final ComputerDTO createcomputerDTOfromcomputer(Computer computer) {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(computer.getId().intValue());
        computerDTO.setName(computer.getName());
        if (computer.getDiscontinued() != null) {
            computerDTO.setIntroduced(computer.getIntroduced().toString());
        }
        if (computer.getDiscontinued() != null) {
            computerDTO.setDiscontinued(computer.getDiscontinued().toString());
        }
        if (computer.getCompany() != null) {
            computerDTO.setCompanyName(computer.getCompany().getName());
        }
        return computerDTO;
    }

    public final Computer createcomputerfromcomputerDTO(
            ComputerDTO computerDTO) {
        Computer computer = new Computer();
        computer.setId(Long.valueOf(computerDTO.getId()));
        computer.setName(computerDTO.getName());
        if (computerDTO.getDiscontinued() != null) {
            computer.setIntroduced(
                    LocalDate.parse(computerDTO.getIntroduced()));
        }
        if (computerDTO.getDiscontinued() != null) {
            computer.setDiscontinued(
                    LocalDate.parse(computerDTO.getDiscontinued()));
        }
        if (computerDTO.getCompanyName() != null) {
            final CompanyBuilder b = new Company.CompanyBuilder();
            b.withName(computerDTO.getCompanyName());
            computer.setCompany(b.build());
        }
        return computer;
    }
}
