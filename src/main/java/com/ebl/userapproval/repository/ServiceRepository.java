package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository  extends CrudRepository<Service,Long> {
    @Query(value = "select * from service_table where role_id = :id", nativeQuery = true)
    List<Service> getServiceByRoleId(@Param("id") Long id);
}
