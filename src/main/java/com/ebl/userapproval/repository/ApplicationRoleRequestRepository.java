package com.ebl.userapproval.repository;

import com.ebl.userapproval.model.ApplicationRoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRequestRepository extends JpaRepository<ApplicationRoleRequest, Long> {
    // Custom query methods if needed
}