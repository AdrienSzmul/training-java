/**
 *
 */
package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author excilys
 */
@Entity
@Table(name = "computer")
public class Computer {
    /**
     *
     */
    @Id
    @GeneratedValue
    @Column(name = "cu_id")
    private Long id;
    @Column(name = "cu_name")
    private String name;
    @Column(name = "cu_introduced")
    private LocalDate introduced;
    @Column(name = "cu_discontinued")
    private LocalDate discontinued;
    @ManyToOne
    @JoinColumn(name = "cu_ca_id", foreignKey = @ForeignKey(name = "ca_id"))
    private Company company;

    public Computer(ComputerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
    }

    public static class ComputerBuilder {
        private Long id;
        private String name;
        private LocalDate introduced;
        private LocalDate discontinued;
        private Company company;

        public ComputerBuilder withId(Long idComputer) {
            this.id = idComputer;
            return this;
        }

        public ComputerBuilder withName(String nameComputer) {
            this.name = nameComputer;
            return this;
        }

        public ComputerBuilder withIntroduced(LocalDate introducedComputer) {
            this.introduced = introducedComputer;
            return this;
        }

        public ComputerBuilder withDiscontinued(
                LocalDate discontinuedComputer) {
            this.discontinued = discontinuedComputer;
            return this;
        }

        public ComputerBuilder withCompany(Company companyComputer) {
            this.company = companyComputer;
            return this;
        }

        public Computer build() {
            return new Computer(this);
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
    public void setId(Long idComputer) {
        this.id = idComputer;
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
    public void setName(String nameComputer) {
        this.name = nameComputer;
    }

    /**
     * @return the introduced
     */
    public LocalDate getIntroduced() {
        return introduced;
    }

    /**
     * @param introduced
     *            the introduced to set
     */
    public void setIntroduced(LocalDate introducedComputer) {
        this.introduced = introducedComputer;
    }

    /**
     * @return the discontinued
     */
    public LocalDate getDiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued
     *            the discontinued to set
     */
    public void setDiscontinued(LocalDate discontinuedComputer) {
        this.discontinued = discontinuedComputer;
    }

    /**
     * @return the company_id
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company_id
     *            the company_id to set
     */
    public void setCompany(Company companyComputer) {
        this.company = companyComputer;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Computer : ").append(id)
                .append(" name: ").append(name).append(" introduced in: ")
                .append(introduced).append(" discontinued in: ")
                .append(discontinued).append(" from the company: ")
                .append(company.getName()).toString();
    }
}
