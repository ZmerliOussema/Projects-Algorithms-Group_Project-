package com.Employees_Leaves.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.LoginEmployee;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
//	DISPLAY ROUTES
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String index(Model model) {

		// Bind empty LoginUser object to the JSP
		// to capture the form input

		model.addAttribute("newLogin", new LoginEmployee());
		return "login.jsp";
	}
	
	// Display admin_dashboard Page
	@GetMapping("/admin_dashboard")
	public String admin_dashboard() {
		return "admin/admin_dashboard.jsp";
	}
	
	// Display emp_dashboard Page
		@GetMapping("/employees/test")
		public String emp_dashboard() {
			return "emp/emp_dashboard.jsp";
		}
		
	// Display add_emp Page
		@GetMapping("/employees/add_emp")
		public String add_emp(Model model) {
			model.addAttribute("newEmployee", new Employee());
			return "emp/add_emp.jsp";
		}
		
		
		
//	ACTION ROUTES
	
	// For Displaying Dashboard Page
		@PostMapping("/login")
		public String login(@Valid @ModelAttribute("newLogin") LoginEmployee newLogin,
				BindingResult result,
				Model model,
				HttpSession session) {
	
			return "redirect:/admin_dashboard";
		}
	
	// For Displaying add_emp Page
	
}
