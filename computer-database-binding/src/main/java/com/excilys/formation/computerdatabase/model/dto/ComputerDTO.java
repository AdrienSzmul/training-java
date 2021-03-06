package com.excilys.formation.computerdatabase.model.dto;

public class ComputerDTO {
    private int id;
    private String name;
    private String introduced;
    private String discontinued;
    private CompanyDTO company;

    public ComputerDTO() {
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getIntroduced() {
        return introduced;
    }

    public final void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public final String getDiscontinued() {
        return discontinued;
    }

    public final void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public final CompanyDTO getCompany() {
        return company;
    }

    public final void setCompany(CompanyDTO company) {
        this.company = company;
    }
}
