package com.Employees_Leaves.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.LoginEmployee;
import com.Employees_Leaves.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public Employee register(Employee newEmployee, BindingResult result) {

		Optional<Employee> potentialEmployee = employeeRepo.findByEmail(newEmployee.getEmail());

		// Reject if email is taken (present in database)
		if (potentialEmployee.isPresent()) {

			result.rejectValue("email", "registerErrors", "An account with that email already exists!");
		}

		// Reject if password doesn't match confirmation
		if (!newEmployee.getPassword().equals(newEmployee.getConfirm())) {

			result.rejectValue("confirm", "registerErrors", "The Confirm Password must match Password!");
		}

		// Return null if result has errors
		if (result.hasErrors()) {
			return null;
		} else {
			// Hash and set password
			String hashed = BCrypt.hashpw(newEmployee.getPassword(), BCrypt.gensalt());
			newEmployee.setPassword(hashed);
			// Save the User to database
			return employeeRepo.save(newEmployee);
		}

	}

	public Employee login(LoginEmployee newLogin, BindingResult result) {

		Optional<Employee> potentialEmployee = employeeRepo.findByEmail(newLogin.getEmail());

		// check if employee is in the DB
		// Reject if NOT present
		if (!potentialEmployee.isPresent()) {

			result.rejectValue("email", "Matches", "Employee not found!");
		} else {

			// Employee exists, retrieve employee from DB
			Employee employee = potentialEmployee.get();

			// Reject if BCrypt password match fails
			if (!BCrypt.checkpw(newLogin.getPassword(), employee.getPassword())) {
				result.rejectValue("password", "Matches", "Invalid Password!");
			}

			// Return null if result has errors
			if (result.hasErrors()) {
				return null;
			} else {
				// Otherwise, return the employee object
				return employee;
			}
		}

		return null;
	}

	public Employee findById(Long id) {
		Optional<Employee> potentialEmployee = employeeRepo.findById(id);
		if (potentialEmployee.isPresent()) {
			return potentialEmployee.get();
		} else {
			return null;
		}
	}

	public List<Employee> allEmployees() {
		return employeeRepo.findAll();
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

//	test update with @Query

	public int updateOneEmployee(String firstName, String lastName, String email, String address, String title,
			String rangeEmployee, String category, int phoneNumber, String firstNameAr, String lastNameAr,
			String addressAr, String titleAr, String rangeAr, String categoryAr, Long id) {

		return employeeRepo.updateFirstName(firstName, lastName, email, address, title, rangeEmployee, category,
				phoneNumber, firstNameAr, lastNameAr, addressAr, titleAr, rangeAr, categoryAr, id);
	}

	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}

	public int chEmpPassword(String password, Long id) {

		// Hash password
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return employeeRepo.chEmpPassword(hashedPassword, id);
	}

}
