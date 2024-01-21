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

		model.addAttribute("newLogin", new Employee());
		return "login.jsp";
	}
	
	// Display Dashboard Page
	@GetMapping("/admin_dashboard")
	public String dashboard() {
		return "admin/admin_dashboard.jsp";
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
	
}
