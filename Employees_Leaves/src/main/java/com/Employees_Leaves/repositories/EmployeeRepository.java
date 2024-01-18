package com.Employees_Leaves.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Employees_Leaves.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
