package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Branch;
import com.ebl.userapproval.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Query(value = "select * from employee e where upper(e.domain_name) = upper(:domain_name) and is_active = 'Y'",nativeQuery = true)
    List<Employee> findBydomainName(@Param("domain_name") String domain_name);


    @Query(value = "select * from employee where upper(cbs_username) = upper(:cbs_username)",nativeQuery = true)
    List<Employee> findByCbsUsername(@Param("cbs_username") String cbs_username);


}
