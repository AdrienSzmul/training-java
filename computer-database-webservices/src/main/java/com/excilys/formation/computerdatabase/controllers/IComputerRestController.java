package com.excilys.formation.computerdatabase.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

public interface IComputerRestController {
    public ResponseEntity<ComputerDTO> getComputerById(long id);

    public ResponseEntity<List<ComputerDTO>> getComputers();

    public ResponseEntity<List<ComputerDTO>> getComputersSorted(String orderby);

    public ResponseEntity<List<ComputerDTO>> getComputersSearch(String search);

    public ResponseEntity<List<ComputerDTO>> getComputersSearchSorted(
            String orderby, String search);

    public ResponseEntity<String> createComputer(ComputerDTO computerDTO);

    public ResponseEntity<String> updateComputer(ComputerDTO computerDTO);

    public ResponseEntity<String> deleteComputer(ComputerDTO computerDTO);

    public ResponseEntity<Integer> getCountComputers();

    public ResponseEntity<Integer> getCountComputersSearch(String search);
}
