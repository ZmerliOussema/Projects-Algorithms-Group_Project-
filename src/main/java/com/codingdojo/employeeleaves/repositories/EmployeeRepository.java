package com.codingdojo.employeeleaves.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.employeeleaves.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAll();
	
	Optional<Employee> findByUserId(Long userId);
}
