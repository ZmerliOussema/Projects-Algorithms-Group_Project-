package com.Employees_Leaves.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.Leave;
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

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/login";
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

	// Display emp_annual_leaves.jsp Page
	@GetMapping("/employees/annual/1")
	public String emp_annual_leaves(Model model) {
		model.addAttribute("newLeave", new Leave());
		return "emp/emp_annual_leaves.jsp";
	}

	// Display emp_specific_leaves.jsp Page
	@GetMapping("/employees/specific/1")
	public String emp_specific_leaves(Model model) {
		model.addAttribute("newLeave", new Leave());
		return "emp/emp_specific_leaves.jsp";
	}

	// Display emp_sick_leaves.jsp Page
	@GetMapping("/employees/sick/1")
	public String emp_sick_leaves(Model model) {
		model.addAttribute("newLeave", new Leave());
		return "emp/emp_sick_leaves.jsp";
	}

	// Display emp_details.jsp Page
	@GetMapping("/employees/show/1")
	public String emp_sdetails() {
		return "emp/emp_details.jsp";
	}

	// Display ch_password.jsp Page
	@GetMapping("/employees/ch_password/1")
	public String ch_password(@ModelAttribute("updateEmp") Employee updateEmp) {
		return "emp/ch_password.jsp";
	}

	// Display ch_password.jsp Page
	@GetMapping("/employees/edit/1")
	public String emp_update(@ModelAttribute("updateEmp") Employee updateEmp) {
		return "emp/emp_update.jsp";
	}

//	ACTION ROUTES

	// For Displaying Dashboard Page
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginEmployee newLogin, BindingResult result, Model model,
			HttpSession session) {

		return "redirect:/admin_dashboard";
	}

	// For Displaying emp_annual_leaves Page
	@PostMapping("/employees/annual/1/add")
	public String add_emp_annual_leaves(@Valid @ModelAttribute("newLeave") Leave newLeave, BindingResult result,
			Model model, HttpSession session) {

		return "redirect:/employees/annual/1";
	}

	// For Displaying emp_specific_leaves Page
	@PostMapping("/employees/specific/1/add")
	public String add_emp_specific_leaves(@Valid @ModelAttribute("newLeave") Leave newLeave, BindingResult result,
			Model model, HttpSession session) {

		return "redirect:/employees/specific/1";
	}

	// For Displaying emp_sick_leaves Page
	@PostMapping("/employees/sick/1/add")
	public String add_emp_sick_leaves(@Valid @ModelAttribute("newLeave") Leave newLeave, BindingResult result,
			Model model, HttpSession session) {

		return "redirect:/employees/sick/1";
	}

	// For Displaying ch_password Page
	@PostMapping("/employees/ch_password/1")
	public String ch_password(@Valid @ModelAttribute("updateEmp") Employee updateEmp, BindingResult result, Model model,
			HttpSession session) {

		return "redirect:/employees/show/1";
	}

	// For Displaying ch_password Page
	@PostMapping("/employees/edit/1")
	public String update_employee(@Valid @ModelAttribute("updateEmp") Employee updateEmp, BindingResult result, Model model,
			HttpSession session) {

		return "redirect:/employees/show/1";
	}

}
