package com.excilys.formation.computerdatabase.paginator;

import java.util.List;

public abstract class Page<T> {
	
	private static final Integer FIRST_PAGE = 0;
	private Integer pageNumber;

	protected PageLength tailleMax;
	protected List<T> page = null;
	protected abstract int maxNumberOfPages();
	protected abstract void refresh(int pageNumber);
	
	
	public Page () {
		this.pageNumber = FIRST_PAGE;
	}
	
	public List<T> getPage() {
		return page; 
	}
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	public List<T> previousPage() {
		this.checkPreviousPageNumber();
		this.refresh(this.pageNumber);
		return this.page;
	}
	
	private void checkPreviousPageNumber() {
		if (this.pageNumber > FIRST_PAGE) {
			this.pageNumber--;
		}
	}

	public List<T> nextPage() {
		this.checkNextPageNumber(this.maxNumberOfPages());
		this.refresh(this.pageNumber);
		return this.page;
	}
	
	private void checkNextPageNumber(int maxNumberOfPages) {
		if (this.pageNumber < maxNumberOfPages) {
			this.pageNumber++;
		}
	}

	public List<T> firstPage() {
		this.refresh(FIRST_PAGE);
		return this.page;
	}
	
	public List<T> lastPage() {
		this.refresh(this.maxNumberOfPages());
		return this.page;
	}
	
	
	
}
