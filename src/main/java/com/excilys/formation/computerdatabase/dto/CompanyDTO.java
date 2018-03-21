package com.excilys.formation.computerdatabase.dto;

public class CompanyDTO {
    private Long id;
    private String name;

    public CompanyDTO() {
        // TODO Auto-generated constructor stub
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long idCompanyDTO) {
        this.id = idCompanyDTO;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String nameCompanyDTO) {
        this.name = nameCompanyDTO;
    }
}
