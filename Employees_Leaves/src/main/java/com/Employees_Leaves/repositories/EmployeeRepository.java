package com.Employees_Leaves.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Employees_Leaves.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();
	Optional<Employee> findByEmail(String email);
	Employee findByIdIs(Long id);
}
