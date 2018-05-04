package com.excilys.formation.computerdatabase.paginator;

import java.util.List;

import com.excilys.formation.computerdatabase.service.ServiceException;

public abstract class Page<T> {
    public static final Integer FIRST_PAGE = 0;
    private Integer pageNumber;
    protected PageLength tailleMax;
    protected List<T> pageActive = null;

    protected abstract int maxNumberOfPages() throws ServiceException;

    protected abstract void refresh(int pageNumberChosen)
            throws ServiceException;

    public Page() {
        this.pageNumber = FIRST_PAGE;
        this.tailleMax = PageLength.TWENTY;
    }

    public Page(PageLength tailleMax) {
        this.pageNumber = FIRST_PAGE;
        this.tailleMax = tailleMax;
    }

    public final List<T> getPage() {
        return pageActive;
    }

    public final Integer getPageNumber() {
        return pageNumber;
    }

    public final PageLength getTailleMax() {
        return tailleMax;
    }

    public final void setTailleMax(PageLength tailleMax) {
        this.tailleMax = tailleMax;
    }

    public final List<T> previousPage() throws ServiceException {
        this.checkPreviousPageNumber();
        this.refresh(this.pageNumber);
        return this.pageActive;
    }

    private void checkPreviousPageNumber() {
        if (this.pageNumber > FIRST_PAGE) {
            this.pageNumber--;
        }
    }

    public final List<T> nextPage() throws ServiceException {
        this.checkNextPageNumber(this.maxNumberOfPages());
        this.refresh(this.pageNumber);
        return this.pageActive;
    }

    private void checkNextPageNumber(final int maxNumberOfPages) {
        if (this.pageNumber < maxNumberOfPages) {
            this.pageNumber++;
        }
    }

    public final List<T> firstPage() throws ServiceException {
        this.refresh(FIRST_PAGE);
        return this.pageActive;
    }

    public final List<T> lastPage() throws ServiceException {
        this.refresh(this.maxNumberOfPages() - 1);
        return this.pageActive;
    }

    public final List<T> goToPage(Integer pageNumber) throws ServiceException {
        this.checkValidPageNumber(pageNumber, this.maxNumberOfPages());
        this.refresh(pageNumber);
        return this.pageActive;
    }

    private void checkValidPageNumber(Integer pageWanted,
            int maxNumberOfPages) {
        if (pageWanted >= FIRST_PAGE && pageWanted < maxNumberOfPages) {
            this.pageNumber = pageWanted;
        } else {
            pageNumber = FIRST_PAGE;
        }
    }
}
