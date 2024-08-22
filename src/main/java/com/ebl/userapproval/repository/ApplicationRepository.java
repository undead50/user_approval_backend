package com.ebl.userapproval.repository;

import com.ebl.userapproval.model.Application;
import com.ebl.userapproval.model.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application,Long> {
}
