package com.Employees_Leaves.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Employees_Leaves.models.Employee;

@Controller
public class LoginController {
	
//	DISPLAY ROUTES
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {

		// Bind empty LoginUser object to the JSP
		// to capture the form input

		model.addAttribute("newLogin", new Employee());
		return "login.jsp";
	}
		

}
