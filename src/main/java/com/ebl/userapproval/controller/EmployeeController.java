package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Employee;
import com.ebl.userapproval.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all categories
    @GetMapping("fetchEmployees")
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }
    // Get a specific employee by ID
    @GetMapping("fetchEmployeeByID/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    // Create a new employee
    @PostMapping("createEmployee")
    public Employee createEmployee(@Validated  @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    // Update a employee
    @PutMapping("updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            updatedEmployee.setId(id);
            return employeeRepository.save(updatedEmployee);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a employee by ID
    @DeleteMapping("deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

}