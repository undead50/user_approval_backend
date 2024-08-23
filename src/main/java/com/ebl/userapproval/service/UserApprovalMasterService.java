package com.ebl.userapproval.service;

import com.ebl.userapproval.model.ApplicationRoleRequest;
import com.ebl.userapproval.model.UserApprovalMaster;
import com.ebl.userapproval.repository.ApplicationRoleRequestRepository;
import com.ebl.userapproval.repository.UserApprovalMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserApprovalMasterService {

    @Autowired
    private UserApprovalMasterRepository repository;

    @Autowired
    private ApplicationRoleRequestRepository roleRequestRepository;

    @Transactional
    public UserApprovalMaster saveApproval(UserApprovalMaster approval) {
        return repository.save(approval);
    }

    public Optional<UserApprovalMaster> getApprovalById(Long id) {
        return repository.findById(id);
    }

    public List<UserApprovalMaster> getAllApprovals() {
        return repository.findAll();
    }

    public void deleteApproval(Long id) {
        repository.deleteById(id);
    }
}