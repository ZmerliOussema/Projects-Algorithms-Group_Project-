package com.Employees_Leaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.LoginEmployee;
import com.Employees_Leaves.services.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	// Add once service is implemented:
	@Autowired
	private EmployeeService employeeService;

//	DISPLAY ROUTES

	// ******** Login page ******//
	@GetMapping("/")
	public String viewLogin(Model model, HttpSession session) {

		// Bind empty LoginEmployee object to the JSP
		// to capture the form input
		model.addAttribute("newLogin", new LoginEmployee());

		// grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

		if (loggedInUserId != null) {
			return "redirect:/admin_dashboard";
		}
		return "login.jsp";
	}

	// ******** create admin for first time only ******//
	@GetMapping("/create/admin")
	public String viewCreateAdmin(Model model, HttpSession session) {

		// Bind empty User object to the JSP
		// to capture the form input
		model.addAttribute("newEmployee", new Employee());

		// check if there is user logged in or other employees
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId != null || !employeeService.allEmployees().isEmpty()) {
			return "redirect:/admin_dashboard";
		}
		return "create_admin.jsp";
	}

//	ACTION ROUTES

	// ******** Login page ******//
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginEmployee newLogin, BindingResult result, Model model,
			HttpSession session) {

		// Add once service is implemented:
		Employee employee = employeeService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new Employee());
			return "login.jsp";
		}

		// No errors!
		// Store his ID from the DB in session,
		// in other words, log him in.
		session.setAttribute("employeeId", employee.getId());
		return "redirect:/admin_dashboard";
	}

	// ******** create admin for first time only ******//
	@PostMapping("/create/admin/add")
	public String createAdmin(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult result,
			Model model, HttpSession session) {

		// Execute the Service to Register
		employeeService.register(newEmployee, result);

		if (result.hasErrors()) {
			return "create_admin.jsp";
		}

		// No errors!
		// Store his ID from the DB in session,
		// in other words, log him in.
		session.setAttribute("employeeId", newEmployee.getId());

		return "redirect:/admin_dashboard";
	}

	
}


	
