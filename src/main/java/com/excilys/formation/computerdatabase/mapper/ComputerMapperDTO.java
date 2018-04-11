package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

public enum ComputerMapperDTO {
    INSTANCE;
    public static final ComputerDTO createcomputerDTOfromcomputer(
            Computer computer) {
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
            computerDTO.setCompany(CompanyMapperDTO.INSTANCE
                    .createCompanyDTOfromCompany(computer.getCompany()));
        }
        return computerDTO;
    }

    public static final Computer createcomputerfromcomputerDTO(
            ComputerDTO computerDTO) {
        final ComputerBuilder bComputer = new Computer.ComputerBuilder();
        bComputer.withId(Long.valueOf(computerDTO.getId()));
        if (!computerDTO.getName().isEmpty()) {
            bComputer.withName(computerDTO.getName());
        }
        if (!computerDTO.getIntroduced().isEmpty()) {
            bComputer.withIntroduced(
                    LocalDate.parse(computerDTO.getIntroduced()));
        }
        if (!computerDTO.getDiscontinued().isEmpty()) {
            bComputer.withDiscontinued(
                    LocalDate.parse(computerDTO.getDiscontinued()));
        }
        if (computerDTO.getCompany() != null) {
            bComputer.withCompany(CompanyMapperDTO.INSTANCE
                    .createCompanyfromCompanyDTO(computerDTO.getCompany()));
        }
        return bComputer.build();
    }
}
