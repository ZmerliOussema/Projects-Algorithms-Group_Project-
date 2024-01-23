package com.codingdojo.employeeleaves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.services.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

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

			// Save Employee in DB
			Employee newEmployee = employeeService.createEmployee(employee);

			return "redirect:/dashboard";
		}

	}

	// Get Employee By Id
	@GetMapping("/employees/{id}")
	public String getEmployeeById(Model model, @PathVariable("id") Long id, HttpSession session) {

		Employee employee = employeeService.findEmployee(id);
		model.addAttribute("employee", employee);
		return "employeeDetails.jsp";
	}

	// Display Route: Show Edit Form for Employee
	@GetMapping("/employees/{id}/edit")
	public String updateEmployee(@PathVariable("id") Long id, Model model, @ModelAttribute("book") Employee employee) {

		Employee foundedEmployee = employeeService.findEmployee(id);
		model.addAttribute("employee", foundedEmployee);

		return "editEmployeeForm.jsp";
	}

	// Action Route: Edit Employee
	@PutMapping("/employees/{id}")
	public String updateBook(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "editEmployeeForm.jsp";
		} else {

			employeeService.updateEmployee(employee);

			return "redirect:/dashboard";
		}
	}

	// Delete an Employee
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable("id") Long id, HttpSession session) {

		employeeService.deleteEmployee(id);

		return "redirect:/dashboard";
	}
}
