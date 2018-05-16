package com.excilys.formation.computerdatabase.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.computerdatabase.mapper.ComputerMapperDTO;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.ServiceException;
import com.excilys.formation.computerdatabase.service.ValidationException;

@RestController
public class ComputerRestController implements IComputerRestController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerRestController.class);
    private ComputerService computerService;
    private ComputerMapperDTO computerMapperDTO;

    public ComputerRestController(ComputerService computerService,
            ComputerMapperDTO computerMapperDTO) {
        this.computerService = computerService;
        this.computerMapperDTO = computerMapperDTO;
    }

    @Override
    @GetMapping(value = "/computer/{id}")
    public ResponseEntity<ComputerDTO> getComputerById(@PathVariable long id) {
        ResponseEntity<ComputerDTO> response;
        try {
            Computer computer = computerService.getComputerById(id);
            ComputerDTO computerDTO = computerMapperDTO
                    .createcomputerDTOfromcomputer(computer);
            response = new ResponseEntity<ComputerDTO>(computerDTO,
                    HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    @GetMapping(value = "/computers")
    public ResponseEntity<List<ComputerDTO>> getComputers() {
        try {
            return new ResponseEntity<List<ComputerDTO>>(computerService
                    .getListComputers(0, computerService.getCountComputers())
                    .stream()
                    .map(computer -> computerMapperDTO
                            .createcomputerDTOfromcomputer(computer))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            return new ResponseEntity<List<ComputerDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping(value = "/computer/orderby/{orderby}")
    public ResponseEntity<List<ComputerDTO>> getComputersSorted(
            @PathVariable String orderby) {
        try {
            return new ResponseEntity<List<ComputerDTO>>(computerService
                    .getListComputersSorted(0,
                            computerService.getCountComputers(), orderby, true)
                    .stream()
                    .map(computer -> computerMapperDTO
                            .createcomputerDTOfromcomputer(computer))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            return new ResponseEntity<List<ComputerDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping(value = "/computer/search/{search}")
    public ResponseEntity<List<ComputerDTO>> getComputersSearch(
            @PathVariable String search) {
        try {
            return new ResponseEntity<List<ComputerDTO>>(computerService
                    .getListComputersSearch(0,
                            computerService.getCountComputers(), search)
                    .stream()
                    .map(computer -> computerMapperDTO
                            .createcomputerDTOfromcomputer(computer))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            return new ResponseEntity<List<ComputerDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping(value = "/computer/orderby/{orderby}/search/{search}")
    public ResponseEntity<List<ComputerDTO>> getComputersSearchSorted(
            @PathVariable String orderby, @PathVariable String search) {
        try {
            return new ResponseEntity<List<ComputerDTO>>(computerService
                    .getListComputersSearchSorted(0,
                            computerService.getCountComputers(), search,
                            orderby, true)
                    .stream()
                    .map(computer -> computerMapperDTO
                            .createcomputerDTOfromcomputer(computer))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            return new ResponseEntity<List<ComputerDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping(value = "/computer")
    public ResponseEntity<String> createComputer(
            @RequestBody ComputerDTO computerDTO) {
        ResponseEntity<String> response;
        try {
            computerService.createComputer(computerMapperDTO
                    .createcomputerfromcomputerDTO(computerDTO));
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | ValidationException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    @PutMapping(value = "/computer")
    public ResponseEntity<String> updateComputer(
            @RequestBody ComputerDTO computerDTO) {
        ResponseEntity<String> response;
        try {
            computerService.updateComputer(computerMapperDTO
                    .createcomputerfromcomputerDTO(computerDTO));
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | ValidationException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    @DeleteMapping(value = "/computer")
    public ResponseEntity<String> deleteComputer(
            @RequestBody ComputerDTO computerDTO) {
        ResponseEntity<String> response;
        try {
            computerService.deleteComputer(computerMapperDTO
                    .createcomputerfromcomputerDTO(computerDTO));
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    @GetMapping(value = "/computer/count")
    public ResponseEntity<Integer> getCountComputers() {
        ResponseEntity<Integer> response;
        try {
            response = new ResponseEntity<Integer>(
                    computerService.getCountComputers(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    @GetMapping(value = "/computer/search/{search}/count")
    public ResponseEntity<Integer> getCountComputersSearch(
            @PathVariable String search) {
        ResponseEntity<Integer> response;
        try {
            response = new ResponseEntity<Integer>(
                    computerService.getCountComputersSearch(search),
                    HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.debug("{}", e.getMessage());
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
