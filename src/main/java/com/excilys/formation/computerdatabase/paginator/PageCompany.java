/**
 * 
 */
package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.service.CompanyService;

/**
 * @author excilys
 *
 */
public class PageCompany extends Page<Company> {
	
	private CompanyService companyService = CompanyService.INSTANCE;
	
	public PageCompany() {
		super();
	}

	@Override
	protected int maxNumberOfPages() {
		return companyService.getPageCountCompanies(this.tailleMax.getValue());
	}
	
	@Override
	protected void refresh(int pageNumber) {
		this.page = companyService.getListCompanies(pageNumber, this.tailleMax.getValue());
	}

}
