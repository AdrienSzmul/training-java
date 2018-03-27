/**
 *
 */
package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

/**
 * @author excilys
 */
public class Computer {
    /**
     *
     */
    private Long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    public Computer(final ComputerBuilder builder) {
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

        public final ComputerBuilder withId(final Long idComputer) {
            this.id = idComputer;
            return this;
        }

        public final ComputerBuilder withName(final String nameComputer) {
            this.name = nameComputer;
            return this;
        }

        public final ComputerBuilder withIntroduced(
                final LocalDate introducedComputer) {
            this.introduced = introducedComputer;
            return this;
        }

        public final ComputerBuilder withDiscontinued(
                final LocalDate discontinuedComputer) {
            this.discontinued = discontinuedComputer;
            return this;
        }

        public final ComputerBuilder withCompany(
                final Company companyComputer) {
            this.company = companyComputer;
            return this;
        }

        public final Computer build() {
            return new Computer(this);
        }
    }

    /**
     * @return the id
     */
    public final Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final Long idComputer) {
        this.id = idComputer;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(final String nameComputer) {
        this.name = nameComputer;
    }

    /**
     * @return the introduced
     */
    public final LocalDate getIntroduced() {
        return introduced;
    }

    /**
     * @param introduced
     *            the introduced to set
     */
    public final void setIntroduced(final LocalDate introducedComputer) {
        this.introduced = introducedComputer;
    }

    /**
     * @return the discontinued
     */
    public final LocalDate getDiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued
     *            the discontinued to set
     */
    public final void setDiscontinued(final LocalDate discontinuedComputer) {
        this.discontinued = discontinuedComputer;
    }

    /**
     * @return the company_id
     */
    public final Company getCompany() {
        return company;
    }

    /**
     * @param company_id
     *            the company_id to set
     */
    public final void setCompany(final Company companyComputer) {
        this.company = companyComputer;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return new StringBuilder().append("Computer : ").append(id)
                .append(" name: ").append(name).append(" introduced in: ")
                .append(introduced).append(" discontinued in: ")
                .append(discontinued).append(" from the company: ")
                .append(company.getName()).toString();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result
                + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Computer other = (Computer) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
