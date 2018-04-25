/**
 *
 */
package com.excilys.formation.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author excilys
 */
@Entity
@Table(name = "company")
public class Company {
    /**
     *
     */
    @Id
    @GeneratedValue
    @Column(name = "ca_id")
    private Long id;
    @Column(name = "ca_name")
    private String name;

    public Company() {
    }

    public Company(CompanyBuilder companyBuilder) {
        super();
        this.id = companyBuilder.id;
        this.name = companyBuilder.name;
    }

    public static class CompanyBuilder {
        private Long id;
        private String name;

        public CompanyBuilder() {
        }

        public CompanyBuilder(Long idCompany, String nameCompany) {
            this.id = idCompany;
            this.name = nameCompany;
        }

        public CompanyBuilder withId(Long idCompany) {
            this.id = idCompany;
            return this;
        }

        public CompanyBuilder withName(String nameCompany) {
            this.name = nameCompany;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Company : ").append(id)
                .append(" name : ").append(name).toString();
    }
}
