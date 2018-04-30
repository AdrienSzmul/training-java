package com.excilys.formation.computerdatabase.model;

public class Authorities {
    private String username;
    private Authority authority;

    public Authorities() {
    }

    public Authorities(String username, Authority authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
