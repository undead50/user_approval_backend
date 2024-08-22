package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Request;
import com.ebl.userapproval.model.Service;
import com.ebl.userapproval.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    // Get all categories
    @GetMapping("fetchRequests")
    public List<Request> getAllRequest() {
        return (List<Request>) requestRepository.findAll();
    }
    // Get a specific request by ID
    @GetMapping("fetchRequestByID/{id}")
    public Optional<Request> getRequestById(@PathVariable Long id) {
        return requestRepository.findById(id);
    }


    @GetMapping("fetchRequestByApplicationId/{id}")
    public List<Request> getRequestByApplicationId(@PathVariable Long id){
        return requestRepository.getRequestByApplicationId(id);
    }

    // Create a new request
    @PostMapping("createRequest")
    public Request createRequest(@Validated  @RequestBody Request request) {
        return requestRepository.save(request);
    }
    // Update a request
    @PutMapping("updateRequest/{id}")
    public Request updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        if (requestRepository.existsById(id)) {
            updatedRequest.setId(id);
            return requestRepository.save(updatedRequest);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a request by ID
    @DeleteMapping("deleteRequest/{id}")
    public void deleteRequest(@PathVariable Long id) {
        requestRepository.deleteById(id);
    }

}