package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Request;
import com.ebl.userapproval.model.UserApprovalMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserApprovalMasterRepository extends JpaRepository<UserApprovalMaster, Long> {

    @Query(value = "select * from user_approval_master where del_flag = 'N'", nativeQuery = true)
    List<UserApprovalMaster> fetchApproval();

}
