package com.excilys.formation.computerdatabase.paginator;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.ComputerService;

public class PageComputer extends Page<Computer> {

	private ComputerService computerService = ComputerService.INSTANCE;

	public PageComputer() {
		super();
	}

	@Override
	protected int maxNumberOfPages() {
		return computerService.getPageCountComputers(this.tailleMax.getValue());
	}

	@Override
	protected void refresh(int pageNumber) {
		this.page = computerService.getListComputers(pageNumber, this.tailleMax.getValue());
	}

}
