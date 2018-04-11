package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.paginator.PageComputer;
import com.excilys.formation.computerdatabase.paginator.PageComputerSearch;
import com.excilys.formation.computerdatabase.paginator.dto.PageDTO;
import com.excilys.formation.computerdatabase.paginator.dto.PageSearchDTO;
import com.excilys.formation.computerdatabase.service.ServiceException;

public class PageMapperDTO {
    private PageMapperDTO() {
    }

    public static PageDTO<ComputerDTO> createComputerPageDTOFromComputerPage(
            PageComputer computerPage, int nombreElt) throws ServiceException {
        List<ComputerDTO> listComputersDTO = new ArrayList<>();
        computerPage.getPage().stream()
                .map(ComputerMapperDTO::createcomputerDTOfromcomputer)
                .forEach(listComputersDTO::add);
        return new PageDTO<>(listComputersDTO, computerPage.getPageNumber(),
                computerPage.maxNumberOfPages(),
                computerPage.getTailleMax().getValue(), nombreElt);
    }

    public static PageSearchDTO<ComputerDTO> createComputerSearchPageDTOFromComputerSearchPage(
            PageComputerSearch computerSearchPage, int nombreElt)
            throws ServiceException {
        List<ComputerDTO> listComputersDTO = new ArrayList<>();
        computerSearchPage.getPage().stream()
                .map(ComputerMapperDTO::createcomputerDTOfromcomputer)
                .forEach(listComputersDTO::add);
        return new PageSearchDTO<>(listComputersDTO,
                computerSearchPage.getPageNumber(),
                computerSearchPage.maxNumberOfPages(),
                computerSearchPage.getTailleMax().getValue(), nombreElt,
                computerSearchPage.getSearch());
    }
}
