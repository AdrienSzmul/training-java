package com.excilys.formation.computerdatabase.paginator;

import java.util.List;

public abstract class Page<T> {
	
	private static final Integer FIRST_PAGE = 0;
	private Integer pageNumber;

	protected Integer TAILLE_MAX = 20;
	protected List<T> page = null;
	protected abstract int maxNumberOfPages();
	
	
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
		return this.page;
	}
	
	private void checkPreviousPageNumber() {
		if (this.pageNumber > FIRST_PAGE) {
			this.pageNumber--;
		}
	}

	public List<T> nextPage() {
		this.checkNextPageNumber(this.maxNumberOfPages());
		return this.page;
	}
	
	private void checkNextPageNumber(int maxNumberOfPages) {
		if (this.pageNumber < maxNumberOfPages) {
			this.pageNumber++;
		}
	}

	public List<T> firstPage() {
		return this.page;
	}
	
	public List<T> lastPage() {
		return this.page;
	}
	
	
	
}
