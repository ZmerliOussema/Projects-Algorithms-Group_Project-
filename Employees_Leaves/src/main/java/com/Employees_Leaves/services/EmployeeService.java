package com.Employees_Leaves.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.LoginEmployee;
import com.Employees_Leaves.repositories.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
public Employee register(Employee newEmployee, BindingResult result) {
		
		Optional<Employee> potentialEmployee = employeeRepo.findByEmail(newEmployee.getEmail());
    	
    	// Reject if email is taken (present in database)
    	if(potentialEmployee.isPresent()) {
    		result.rejectValue("email", "Matches", "An account with that email already exists!");
    	}
    	
        // Reject if password doesn't match confirmation
    	if(!newEmployee.getPassword().equals(newEmployee.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	
    	// Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    
        // Hash and set password, save employee to database
    	String hashed = BCrypt.hashpw(newEmployee.getPassword(), BCrypt.gensalt());
    	newEmployee.setPassword(hashed);
    	return employeeRepo.save(newEmployee);
    	
	}
	
	public Employee login(LoginEmployee newLogin, BindingResult result) {

		Optional<Employee> potentialEmployee = employeeRepo.findByEmail(newLogin.getEmail());
        
    	// Find employee in the DB by email
        // Reject if NOT present
    	if(!potentialEmployee.isPresent()) {
    		result.rejectValue("email", "Matches", "Employee not found!");
    		return null;
    	}
    	
    	// Employee exists, retrieve employee from DB
    	Employee employee = potentialEmployee.get();
        
        // Reject if BCrypt password match fails
    	if(!BCrypt.checkpw(newLogin.getPassword(), employee.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
    	// Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    	
        // Otherwise, return the employee object
        return employee;
        
    }
	
	public List<Employee> allEmployees(){
		return employeeRepo.findAll();
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteEmployee(Employee employee) {
		employeeRepo.delete(employee);
	}
	
	
	public Employee findById(Long id) {
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}else {
			return null;
		}
	}
	
}
