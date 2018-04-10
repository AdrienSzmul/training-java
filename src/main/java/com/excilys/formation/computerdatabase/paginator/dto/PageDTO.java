package com.excilys.formation.computerdatabase.paginator.dto;

import java.util.List;

public class PageDTO<T> {
    protected List<T> listContent;
    protected int currentPageNumber;
    protected int maxPageNumber;
    protected int tailleMax;
    protected int nombreElt;

    public PageDTO() {
    }

    public PageDTO(List<T> listContent, int currentPageNumber,
            int maxPageNumber, int tailleMax, int nombreElt) {
        this.listContent = listContent;
        this.currentPageNumber = currentPageNumber;
        this.maxPageNumber = maxPageNumber;
        this.tailleMax = tailleMax;
        this.nombreElt = nombreElt;
    }

    public List<T> getListContent() {
        return listContent;
    }

    public void setListContent(List<T> listContent) {
        this.listContent = listContent;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    public void setMaxPageNumber(int maxPageNumber) {
        this.maxPageNumber = maxPageNumber;
    }

    public int getTailleMax() {
        return tailleMax;
    }

    public void setTailleMax(int tailleMax) {
        this.tailleMax = tailleMax;
    }

    public int getNombreElt() {
        return nombreElt;
    }

    public void setNombreElt(int nombreElt) {
        this.nombreElt = nombreElt;
    }
}
