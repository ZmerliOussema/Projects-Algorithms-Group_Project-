package com.Employees_Leaves.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Employees_Leaves.models.Leave;

@Repository
public interface LeaveRepository extends CrudRepository<Leave , Long> {
	List<Leave> findAll();
	List<Leave> findByOwner_IdAndAnnualGreaterThan(Long employeeId, int annual);
	List<Leave> findByOwner_IdAndSickGreaterThan(Long employeeId, int sick);
	List<Leave> findByOwner_IdAndSpecificLeaveGreaterThan(Long employeeId, int specificLeave);
	List<Leave> findByOwner_Id(Long employeeId);
}
