package com.ebl.userapproval.repository;

import com.ebl.userapproval.model.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends CrudRepository<Request,Long> {
    @Query(value = "select * from request_table where applicaiton_id = :id", nativeQuery = true)
    List<Request> getRequestByApplicationId(@Param("id") Long id);
}
