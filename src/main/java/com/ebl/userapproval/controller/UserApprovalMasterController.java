package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.ApplicationRoleRequest;
import com.ebl.userapproval.model.Upr;
import com.ebl.userapproval.model.UserApprovalHistory;
import com.ebl.userapproval.model.UserApprovalMaster;
import com.ebl.userapproval.repository.UserApprovalMasterRepository;
import com.ebl.userapproval.service.CallCbs;
import com.ebl.userapproval.service.UserApprovalMasterService;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-approvals")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApprovalMasterController {

    @Autowired
    private UserApprovalMasterService service;

    @Autowired
    private UserApprovalMasterRepository repository;

    @PostMapping
    public ResponseEntity<UserApprovalMaster> createApproval(@RequestBody UserApprovalMaster approval) {
        UserApprovalMaster savedApproval = service.saveApproval(approval);
        return new ResponseEntity<>(savedApproval, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUprCbs/{userId}")
    public ResponseEntity<List<Upr>> fetchUprCbs(@PathVariable String userId) {
        // Query the user data
        CallCbs callCbs = new CallCbs();
        List<Upr> cbsData = callCbs.queryUserData(userId);

        // Check if data is empty and return a suitable response
        if (cbsData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no data found
        }

        // Return the list of data with OK status
        return new ResponseEntity<>(cbsData, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserApprovalMaster> getApprovalById(@PathVariable Long id) {
        Optional<UserApprovalMaster> approval = service.getApprovalById(id);
        return approval.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fetchApprovalByCurrentHandler/{id}")
    public ResponseEntity<List<UserApprovalMaster>> fetchApprovalByCurrentHandler(@PathVariable Long id) {
        List<UserApprovalMaster> approval = repository.fetchApprovalByCurrentHandler(id);
        return ResponseEntity.ok(approval); // Return the list wrapped in a ResponseEntity
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserApprovalMaster> updateApproval(
            @PathVariable Long id, @RequestBody UserApprovalMaster updatedApproval) {

        Optional<UserApprovalMaster> existingApprovalOptional = service.getApprovalById(id);

        if (existingApprovalOptional.isPresent()) {
            UserApprovalMaster existingApproval = existingApprovalOptional.get();

            // Update fields of UserApprovalMaster
            existingApproval.setAccessType(updatedApproval.getAccessType());
            existingApproval.setFromDate(updatedApproval.getFromDate());
            existingApproval.setToDate(updatedApproval.getToDate());
            existingApproval.setStatus(updatedApproval.getStatus());
            existingApproval.setCurrentHandler(updatedApproval.getCurrentHandler());
            existingApproval.setRequestedBy(updatedApproval.getRequestedBy());
            existingApproval.setRecommendedBy(updatedApproval.getRecommendedBy());
            existingApproval.setApprovedBy(updatedApproval.getApprovedBy());
            existingApproval.setDelFlag(updatedApproval.getDelFlag());

            // Handle the applicationRoleRequests collection
            existingApproval.getApplicationRoleRequests().clear();  // Clear the existing list
            existingApproval.getApplicationRoleRequests().addAll(updatedApproval.getApplicationRoleRequests());  // Add all new requests

            // Handle the userApprovalHistories collection
            existingApproval.getUserApprovalHistories().clear();  // Clear the existing list
            existingApproval.getUserApprovalHistories().addAll(updatedApproval.getUserApprovalHistories());  // Add all new histories

            // Save the updated entity
            UserApprovalMaster savedApproval = service.saveApproval(existingApproval);
            return ResponseEntity.ok(savedApproval);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserApprovalMaster>> getAllApprovals() {
        List<UserApprovalMaster> approvals = service.getAllApprovals();
        return ResponseEntity.ok(approvals);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApproval(@PathVariable Long id) {
        service.deleteApproval(id);
        return ResponseEntity.noContent().build();
    }
}
