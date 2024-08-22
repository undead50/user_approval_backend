package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.Role;
import com.ebl.userapproval.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    // Get all categories
    @GetMapping("fetchRoles")
    public List<Role> getAllRole() {
        return (List<Role>) roleRepository.findAll();
    }
    // Get a specific role by ID
    @GetMapping("fetchRoleByID/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id);
    }

    @GetMapping("fetchRoleByApplicationId/{id}")
    public List<Role> getRoleByApplicationId(@PathVariable Long id){
        return roleRepository.getRoleByApplicationId(id);
    }



    // Create a new role
    @PostMapping("createRole")
    public Role createRole(@Validated  @RequestBody Role role) {
        return roleRepository.save(role);
    }
    // Update a role
    @PutMapping("updateRole/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        if (roleRepository.existsById(id)) {
            updatedRole.setId(id);
            return roleRepository.save(updatedRole);
        } else {
            // Handle resource not found error
            return null;
        }
    }
    // Delete a role by ID
    @DeleteMapping("deleteRole/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }

}