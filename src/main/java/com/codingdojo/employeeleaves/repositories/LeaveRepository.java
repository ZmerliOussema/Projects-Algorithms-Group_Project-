package com.codingdojo.employeeleaves.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.employeeleaves.models.Leave;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, Long> {

	List<Leave> findAll();
}
