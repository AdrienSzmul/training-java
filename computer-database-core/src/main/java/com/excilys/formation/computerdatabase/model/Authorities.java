package com.excilys.formation.computerdatabase.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {
    @Id
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name = "username"))
    private String username;
    private Authority authority;
    @ManyToMany(mappedBy = "authorities")
    private List<Users> listUsers;

    public Authorities() {
    }

    public Authorities(String username, Authority authority) {
        this.username = username;
        this.authority = authority;
        this.listUsers = new ArrayList<>();
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

    public List<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Users> listUsers) {
        this.listUsers = listUsers;
    }
}
