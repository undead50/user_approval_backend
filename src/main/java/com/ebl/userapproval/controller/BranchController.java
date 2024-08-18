package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Branch;
import com.ebl.userapproval.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branch")
@CrossOrigin(origins = "http://localhost:3000")
public class BranchController {

    @Autowired
    private BranchRepository BranchRepository;

    // Get all categories
    @GetMapping("fetchBranchs")
    public List<Branch> getAllBranch() {
        return (List<Branch>) BranchRepository.findAll();
    }
    // Get a specific Branch by ID
    @GetMapping("fetchBranchByID/{id}")
    public Optional<Branch> getBranchById(@PathVariable Long id) {
        return BranchRepository.findById(id);
    }

    // Create a new Branch
    @PostMapping("createBranch")
    public Branch createBranch(@Validated  @RequestBody Branch Branch) {
        return BranchRepository.save(Branch);
    }
    // Update a Branch
    @PutMapping("updateBranch/{id}")
    public Branch updateBranch(@PathVariable Long id, @RequestBody Branch updatedBranch) {
        if (BranchRepository.existsById(id)) {
            updatedBranch.setId(id);
            return BranchRepository.save(updatedBranch);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a Branch by ID
    @DeleteMapping("deleteBranch/{id}")
    public void deleteBranch(@PathVariable Long id) {
        BranchRepository.deleteById(id);
    }

}