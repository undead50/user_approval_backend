package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Request;
import com.ebl.userapproval.model.UserApprovalMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserApprovalMasterRepository extends JpaRepository<UserApprovalMaster, Long> {

    @Query(value = "select * from user_approval_master where del_flag = 'N'", nativeQuery = true)
    List<UserApprovalMaster> fetchApproval();

    @Query(value = "SELECT DISTINCT um.id AS id, um.access_type, um.approved_by, um.created_at, um.current_handler, um.del_flag, um.from_date, um.recommended_by, um.requested_by, um.status, um.to_date, um.updated_at,um.cbs_user_name FROM user_approval_master um LEFT JOIN application_role_requests arr ON um.id = arr.approval_master_id LEFT JOIN application_owner ao ON arr.application_id = ao.application_id WHERE ao.email = :current_handler AND um.status in ('APPROVED','IMPLEMENTED') AND um.del_flag = 'N'", nativeQuery = true)
    List<UserApprovalMaster> fetchApprovalApproved(@Param("current_handler") String current_handler);

    @Query(value = "select * from user_approval_master where del_flag = 'N' and current_handler = :current_handler", nativeQuery = true)
    List<UserApprovalMaster> fetchApprovalByCurrentHandler(@Param("current_handler") Long current_handler);

    @Query(value = "select * from user_approval_master where requested_by = :current_handler and del_flag = 'N'", nativeQuery = true)
    List<UserApprovalMaster> fetchRequestChain(@Param("current_handler") Long current_handler);

}
