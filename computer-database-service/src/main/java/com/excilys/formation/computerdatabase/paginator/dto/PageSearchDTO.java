package com.excilys.formation.computerdatabase.paginator.dto;

import java.util.List;

public class PageSearchDTO<T> extends PageDTO<T> {
    protected String search = null;

    public PageSearchDTO() {
    }

    public PageSearchDTO(List<T> listContent, int currentPageNumber,
            int maxPageNumber, int tailleMax, int nombreElt, String search) {
        super(listContent, currentPageNumber, maxPageNumber, tailleMax,
                nombreElt);
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
