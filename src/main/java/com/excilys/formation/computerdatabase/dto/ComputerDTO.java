package com.excilys.formation.computerdatabase.dto;

public class ComputerDTO {
    private Long id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyName;

    public ComputerDTO() {
        // TODO Auto-generated constructor stub
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
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

    public final String getCompanyName() {
        return companyName;
    }

    public final void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}