package com.codingdojo.employeeleaves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.LeaveService;

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
}
