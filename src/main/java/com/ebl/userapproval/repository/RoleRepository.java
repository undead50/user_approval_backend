package com.ebl.userapproval.repository;

import com.ebl.userapproval.model.Branch;
import com.ebl.userapproval.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    @Query(value = "select * from role_table where applicaiton_id = :id", nativeQuery = true)
    List<Role> getRoleByApplicationId(@Param("id") Long id);
}
