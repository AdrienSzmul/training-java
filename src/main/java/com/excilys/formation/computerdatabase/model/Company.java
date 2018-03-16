/**
 * 
 */
package com.excilys.formation.computerdatabase.model;

/**
 * @author excilys
 *
 */
public class Company {

	/**
	 * 
	 */

	private Long id;
	private String name;

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(Builder builder) {
		super();
		this.id = builder.id;
		this.name = builder.name;
	}
	
	public static class Builder {
		private Long id;
		private String name;
		
		public Builder() {
			
		}
		
		public Builder(Long id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public Builder withId(Long id) {
			this.id =  id;
			return this;
		}
		public Builder withName(String name) {
			this.name =  name;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("Company : ").append(id).append(" name : ").append(name).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
