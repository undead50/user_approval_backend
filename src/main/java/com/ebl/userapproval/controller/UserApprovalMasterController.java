package com.ebl.userapproval.controller;

import com.ebl.userapproval.model.UserApprovalMaster;
import com.ebl.userapproval.service.UserApprovalMasterService;
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

    @PostMapping
    public ResponseEntity<UserApprovalMaster> createApproval(@RequestBody UserApprovalMaster approval) {
        UserApprovalMaster savedApproval = service.saveApproval(approval);
        return new ResponseEntity<>(savedApproval, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserApprovalMaster> getApprovalById(@PathVariable Long id) {
        Optional<UserApprovalMaster> approval = service.getApprovalById(id);
        return approval.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
