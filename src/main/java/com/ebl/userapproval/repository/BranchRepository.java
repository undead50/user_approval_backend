package com.ebl.userapproval.repository;

import com.ebl.userapproval.model.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<Branch,Long> {
}
