/**
 *
 */
package com.excilys.formation.computerdatabase.model;

/**
 * @author excilys
 */
public class Company {
    /**
     *
     */
    private Long id;
    private String name;

    public Company(final CompanyBuilder companyBuilder) {
        super();
        this.id = companyBuilder.id;
        this.name = companyBuilder.name;
    }

    public static class CompanyBuilder {
        private Long id;
        private String name;

        public CompanyBuilder() {
        }

        public CompanyBuilder(final Long idCompany, final String nameCompany) {
            this.id = idCompany;
            this.name = nameCompany;
        }

        public final CompanyBuilder withId(final Long idCompany) {
            this.id = idCompany;
            return this;
        }

        public final CompanyBuilder withName(final String nameCompany) {
            this.name = nameCompany;
            return this;
        }

        public final Company build() {
            return new Company(this);
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
    public final void setId(final Long id) {
        this.id = id;
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
    public final void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return new StringBuilder().append("Company : ").append(id)
                .append(" name : ").append(name).toString();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        final Company other = (Company) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
