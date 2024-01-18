package com.Employees_Leaves.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Employees_Leaves.models.Leave;

@Repository
public interface LeaveRepository extends CrudRepository<Leave , Long> {


}
