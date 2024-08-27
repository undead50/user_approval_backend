package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.ApplicationOwner;
import com.ebl.userapproval.repository.ApplicationOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ApplicationOwner")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationOwnerController {

    @Autowired
    private ApplicationOwnerRepository ApplicationOwnerRepository;

    // Get all categories
    @GetMapping("fetchApplicationOwners")
    public List<ApplicationOwner> getAllApplicationOwner() {
        return (List<ApplicationOwner>) ApplicationOwnerRepository.findAll();
    }
    // Get a specific ApplicationOwner by ID
    @GetMapping("fetchApplicationOwnerByID/{id}")
    public Optional<ApplicationOwner> getApplicationOwnerById(@PathVariable Long id) {
        return ApplicationOwnerRepository.findById(id);
    }

    // Create a new ApplicationOwner
    @PostMapping("createApplicationOwner")
    public ApplicationOwner createApplicationOwner(@Validated  @RequestBody ApplicationOwner ApplicationOwner) {
        return ApplicationOwnerRepository.save(ApplicationOwner);
    }
    // Update a ApplicationOwner
    @PutMapping("updateApplicationOwner/{id}")
    public ApplicationOwner updateApplicationOwner(@PathVariable Long id, @RequestBody ApplicationOwner updatedApplicationOwner) {
        if (ApplicationOwnerRepository.existsById(id)) {
            updatedApplicationOwner.setId(id);
            return ApplicationOwnerRepository.save(updatedApplicationOwner);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a ApplicationOwner by ID
    @DeleteMapping("deleteApplicationOwner/{id}")
    public void deleteApplicationOwner(@PathVariable Long id) {
        ApplicationOwnerRepository.deleteById(id);
    }

}