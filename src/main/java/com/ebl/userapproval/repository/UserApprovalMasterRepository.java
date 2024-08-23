package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.UserApprovalMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalMasterRepository extends JpaRepository<UserApprovalMaster, Long> {
}
