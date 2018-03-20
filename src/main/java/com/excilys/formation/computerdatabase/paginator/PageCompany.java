/**
 *
 */
package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.service.CompanyService;

/**
 * @author excilys
 */
public class PageCompany extends Page<Company> {
    private final CompanyService companyService = CompanyService.INSTANCE;

    public PageCompany() {
        super();
    }

    @Override
    protected final int maxNumberOfPages() {
        return companyService.getPageCountCompanies(this.tailleMax.getValue());
    }

    @Override
    protected final void refresh(final int pageNumber) {
        this.page = companyService.getListCompanies(pageNumber,
                this.tailleMax.getValue());
    }
}
