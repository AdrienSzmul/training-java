/**
 *
 */
package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ServiceException;

/**
 * @author excilys
 */
public class PageCompany extends Page<Company> {
    protected CompanyService companyService;

    public PageCompany(CompanyService companyService) {
        super();
        this.companyService = companyService;
    }

    @Override
    protected final int maxNumberOfPages() throws ServiceException {
        return companyService.getPageCountCompanies(this.tailleMax.getValue());
    }

    @Override
    protected final void refresh(final int pageNumber) throws ServiceException {
        this.pageActive = companyService.getListCompanies(pageNumber,
                this.tailleMax.getValue());
    }
}
