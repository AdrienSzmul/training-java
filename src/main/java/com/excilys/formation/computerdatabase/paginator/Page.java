package com.excilys.formation.computerdatabase.paginator;

import java.util.List;

import com.excilys.formation.computerdatabase.service.ServiceException;

public abstract class Page<T> {
    private static final Integer FIRST_PAGE = 0;
    private Integer pageNumber;
    protected PageLength tailleMax = PageLength.TWENTY;
    protected List<T> page = null;

    protected abstract int maxNumberOfPages() throws ServiceException;

    protected abstract void refresh(int pageNumberChosen)
            throws ServiceException;

    public Page() {
        this.pageNumber = FIRST_PAGE;
    }

    public final List<T> getPage() {
        return page;
    }

    public final Integer getPageNumber() {
        return pageNumber;
    }

    public final List<T> previousPage() throws ServiceException {
        this.checkPreviousPageNumber();
        this.refresh(this.pageNumber);
        return this.page;
    }

    private void checkPreviousPageNumber() {
        if (this.pageNumber > FIRST_PAGE) {
            this.pageNumber--;
        }
    }

    public final List<T> nextPage() throws ServiceException {
        this.checkNextPageNumber(this.maxNumberOfPages());
        this.refresh(this.pageNumber);
        return this.page;
    }

    private void checkNextPageNumber(final int maxNumberOfPages) {
        if (this.pageNumber < maxNumberOfPages) {
            this.pageNumber++;
        }
    }

    public final List<T> firstPage() throws ServiceException {
        this.refresh(FIRST_PAGE);
        return this.page;
    }

    public final List<T> lastPage() throws ServiceException {
        this.refresh(this.maxNumberOfPages());
        return this.page;
    }
}
