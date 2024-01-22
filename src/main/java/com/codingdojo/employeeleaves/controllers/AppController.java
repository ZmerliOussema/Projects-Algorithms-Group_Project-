package com.codingdojo.employeeleaves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.models.Leave;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.LeaveService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AppController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private EmployeeService employeeService;

	// Get All Employees
	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		// Grab all employees
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);

		return "dashboard.jsp";
	}

	// Display Route: Show add-Employee Form
	@GetMapping("/employees/new")
	public String employeeForm(@ModelAttribute("employee") Employee employee, HttpSession session) {

		return "createEmployeeForm.jsp";

	}

	// Action Route: Create an Employee
	@PostMapping("/employees")
	public String createEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "createEmployeeForm.jsp";
		} else {
			
			// Set default values for annualRest, specificRest, and sickRest
	        Leave leave = new Leave();
	        leave.setAnnualRest(30);
	        leave.setAnnualTaken(0);
	        leave.setSpecificRest(6);
	        leave.setSpecificTaken(0);
	        leave.setSickRest(60);
	        leave.setSickTaken(0);
	        // Set the Leave object in the Employee
	        employee.setLeave(leave);

	        // Save the Leave entity first
	        leaveService.addLeave(leave);
			
			// Save Employee in DB
			Employee newEmployee = employeeService.createEmplyee(employee);
			return "redirect:/dashboard";
		}

	}

}
