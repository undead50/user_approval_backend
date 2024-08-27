package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Application;
import com.ebl.userapproval.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/application")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Get all categories
    @GetMapping("fetchApplications")
    public List<Application> getAllApplication() {
        return (List<Application>) applicationRepository.findAll();
    }
    // Get a specific application by ID
    @GetMapping("fetchApplicationByID/{id}")
    public Optional<Application> getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id);
    }

    // Create a new application
    @PostMapping("createApplication")
    public Application createApplication(@Validated  @RequestBody Application application) {
        return applicationRepository.save(application);
    }
    // Update a application
    @PutMapping("updateApplication/{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody Application updatedApplication) {
        if (applicationRepository.existsById(id)) {
            updatedApplication.setId(id);
            return applicationRepository.save(updatedApplication);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a application by ID
    @DeleteMapping("deleteApplication/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
    }

}