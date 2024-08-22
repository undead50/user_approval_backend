package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Role;
import com.ebl.userapproval.model.Service;
import com.ebl.userapproval.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // Get all categories
    @GetMapping("fetchServices")
    public List<Service> getAllService() {
        return (List<Service>) serviceRepository.findAll();
    }
    // Get a specific service by ID
    @GetMapping("fetchServiceByID/{id}")
    public Optional<Service> getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id);
    }

    @GetMapping("fetchServiceByRoleId/{id}")
    public List<Service> getServiceByRoleId(@PathVariable Long id){
        return serviceRepository.getServiceByRoleId(id);
    }

    // Create a new service
    @PostMapping("createService")
    public Service createService(@Validated  @RequestBody Service service) {
        return serviceRepository.save(service);
    }
    // Update a service
    @PutMapping("updateService/{id}")
    public Service updateService(@PathVariable Long id, @RequestBody Service updatedService) {
        if (serviceRepository.existsById(id)) {
            updatedService.setId(id);
            return serviceRepository.save(updatedService);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a service by ID
    @DeleteMapping("deleteService/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }

}