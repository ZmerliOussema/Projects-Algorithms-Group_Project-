package com.codingdojo.employeeleaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.LeaveService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private EmployeeService employeeService;

	// Get leaves By Employee
	@GetMapping("/leaves/{id}")
	public String getLeaveByEmployeeId(Model model, @PathVariable("id") Long id, HttpSession session) {

		// Get Employee By Id
		Employee employee = employeeService.findEmployee(id);
		
		// Get All Leaves related to this employee
		

		return "leavesByEmployee.jsp";
	}
}
