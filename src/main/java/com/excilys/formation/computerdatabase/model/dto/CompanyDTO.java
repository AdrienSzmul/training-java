package com.excilys.formation.computerdatabase.model.dto;

public class CompanyDTO {
    private int id;
    private String name;

    public CompanyDTO() {
        // TODO Auto-generated constructor stub
    }

    public final int getId() {
        return id;
    }

    public final void setId(int idCompanyDTO) {
        this.id = idCompanyDTO;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String nameCompanyDTO) {
        this.name = nameCompanyDTO;
    }
}
