package com.excilys.formation.computerdatabase.model;

public enum Authority {
    ADMIN("admin"), UTILISATEUR("utilisateur");
    private String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    public String getValue() {
        return authority;
    }
}
