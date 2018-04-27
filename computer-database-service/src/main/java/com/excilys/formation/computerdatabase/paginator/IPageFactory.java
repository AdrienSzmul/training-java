package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.controllers.constants.ColumnNames;

public interface IPageFactory {
    PageComputer createPageComputer();

    PageCompany createPageCompany();

    PageComputer createPageComputer(PageLength tailleMax);

    PageComputerSearch createPageComputerSearch();

    PageComputerSearch createPageComputerSearch(String search);

    PageComputerSearch createPageComputerSearch(String search,
            PageLength tailleMax);

    PageComputerSorted createPageComputerSorted();

    PageComputerSorted createPageComputerSorted(PageLength tailleMax);

    PageComputerSorted createPageComputerSorted(PageLength tailleMax,
            ColumnNames orderby, boolean ascdesc);

    PageComputerSearchSorted createPageComputerSearchSorted();

    PageComputerSearchSorted createPageComputerSearchSorted(String search);

    PageComputerSearchSorted createPageComputerSearchSorted(String search,
            PageLength tailleMax);

    PageComputerSearchSorted createPageComputerSearchSorted(String search,
            PageLength tailleMax, ColumnNames orderby, boolean ascdesc);
}
