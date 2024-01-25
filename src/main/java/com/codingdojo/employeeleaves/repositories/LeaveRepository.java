package com.codingdojo.employeeleaves.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.employeeleaves.models.Leave;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, Long> {

	List<Leave> findAll();
	List<Leave> findByEmployee_IdAndAnnualGreaterThan(Long employeeId, int annual);
	List<Leave> findByEmployee_IdAndSickGreaterThan(Long employeeId, int sick);
	List<Leave> findByEmployee_IdAndSpecificLeaveGreaterThan(Long employeeId, int specificLeave);
	List<Leave> findByEmployee_Id(Long employeeId);
}
