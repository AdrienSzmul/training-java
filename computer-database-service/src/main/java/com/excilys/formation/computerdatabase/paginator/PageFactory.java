package com.excilys.formation.computerdatabase.paginator;

import org.springframework.stereotype.Component;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ComputerService;

@Component
public class PageFactory implements IPageFactory {
    private ComputerService computerService;
    private CompanyService companyService;

    public PageFactory(ComputerService computerService,
            CompanyService companyService) {
        this.companyService = companyService;
        this.computerService = computerService;
    }

    @Override
    public PageComputer createPageComputer() {
        PageComputer pageComputer = new PageComputer();
        pageComputer.setComputerService(computerService);
        return pageComputer;
    }

    @Override
    public PageComputer createPageComputer(PageLength tailleMax) {
        PageComputer pageComputer = new PageComputer(tailleMax);
        pageComputer.setComputerService(computerService);
        return null;
    }

    @Override
    public PageComputerSearch createPageComputerSearch() {
        PageComputerSearch pageComputerSearch = new PageComputerSearch();
        pageComputerSearch.setComputerService(computerService);
        return pageComputerSearch;
    }

    @Override
    public PageComputerSearch createPageComputerSearch(String search) {
        PageComputerSearch pageComputerSearch = new PageComputerSearch(search);
        pageComputerSearch.setComputerService(computerService);
        return pageComputerSearch;
    }

    @Override
    public PageComputerSearch createPageComputerSearch(String search,
            PageLength tailleMax) {
        PageComputerSearch pageComputerSearch = new PageComputerSearch(search,
                tailleMax);
        pageComputerSearch.setComputerService(computerService);
        return pageComputerSearch;
    }

    @Override
    public PageComputerSorted createPageComputerSorted() {
        PageComputerSorted pageComputerSorted = new PageComputerSorted();
        pageComputerSorted.setComputerService(computerService);
        return pageComputerSorted;
    }

    @Override
    public PageComputerSorted createPageComputerSorted(PageLength tailleMax) {
        PageComputerSorted pageComputerSorted = new PageComputerSorted(
                tailleMax);
        pageComputerSorted.setComputerService(computerService);
        return pageComputerSorted;
    }

    @Override
    public PageComputerSorted createPageComputerSorted(PageLength tailleMax,
            ColumnNames orderby, boolean ascdesc) {
        PageComputerSorted pageComputerSorted = new PageComputerSorted(
                tailleMax, orderby, ascdesc);
        pageComputerSorted.setComputerService(computerService);
        return pageComputerSorted;
    }

    @Override
    public PageComputerSearchSorted createPageComputerSearchSorted() {
        PageComputerSearchSorted pageComputerSearchSorted = new PageComputerSearchSorted();
        pageComputerSearchSorted.setComputerService(computerService);
        return pageComputerSearchSorted;
    }

    @Override
    public PageComputerSearchSorted createPageComputerSearchSorted(
            String search) {
        PageComputerSearchSorted pageComputerSearchSorted = new PageComputerSearchSorted(
                search);
        pageComputerSearchSorted.setComputerService(computerService);
        return pageComputerSearchSorted;
    }

    @Override
    public PageComputerSearchSorted createPageComputerSearchSorted(
            String search, PageLength tailleMax) {
        PageComputerSearchSorted pageComputerSearchSorted = new PageComputerSearchSorted(
                search, tailleMax);
        pageComputerSearchSorted.setComputerService(computerService);
        return pageComputerSearchSorted;
    }

    @Override
    public PageComputerSearchSorted createPageComputerSearchSorted(
            String search, PageLength tailleMax, ColumnNames orderby,
            boolean ascdesc) {
        PageComputerSearchSorted pageComputerSearchSorted = new PageComputerSearchSorted(
                search, tailleMax, orderby, ascdesc);
        pageComputerSearchSorted.setComputerService(computerService);
        return pageComputerSearchSorted;
    }

    @Override
    public PageCompany createPageCompany() {
        PageCompany pageCompany = new PageCompany();
        pageCompany.setCompanyService(companyService);
        return pageCompany;
    }
}
