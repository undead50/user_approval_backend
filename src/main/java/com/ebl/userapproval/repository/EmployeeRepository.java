package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Branch;
import com.ebl.userapproval.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
