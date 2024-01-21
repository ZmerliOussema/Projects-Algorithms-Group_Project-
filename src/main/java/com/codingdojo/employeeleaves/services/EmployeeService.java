package com.codingdojo.employeeleaves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	// Get all employees
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	// Create an employee
	public Employee createEmplyee(Employee e) {
		return employeeRepo.save(e);
	}

	// Get an employee by Id
	public Employee findBook(Long id) {
		Optional<Employee> maybeEmployee = employeeRepo.findById(id);
		if (maybeEmployee.isPresent()) {
			return maybeEmployee.get();
		} else {
			return null;
		}
	}

	// Update an employee
	public Employee updateEmployee(Employee e) {
		return employeeRepo.save(e);
	}

	// Delete an employee
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}
}
