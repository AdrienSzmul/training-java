/**
 * 
 */
package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

/**
 * @author excilys
 *
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

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public static class Builder {
		private Long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;
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
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
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
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
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
	public void setCompany(Company company) {
		this.company = company;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("Computer : ").append(id).append(" name: ").append(name).append(" introduced in: ").append(introduced).append(" discontinued in: ").append(discontinued).append(" from the company: ").append(company.getName()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
}
