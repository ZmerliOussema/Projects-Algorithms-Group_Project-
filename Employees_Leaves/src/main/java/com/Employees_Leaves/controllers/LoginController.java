package com.Employees_Leaves.controllers;

import java.util.List;

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

	@GetMapping("/")
	public String index(Model model, Employee employee, HttpSession session) {
		model.addAttribute("newEmployee", new Employee());
		model.addAttribute("newLogin", new LoginEmployee());

		List<Employee> allEmployees = employeeService.allEmployees();
		model.addAttribute("employees", allEmployees);
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if(loggedInUserId != null) {
			return "redirect:/admin_dashboard";
		}
		return "login.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult result,
			Model model, HttpSession session) {

		Employee employee = employeeService.register(newEmployee, result);

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginEmployee());
			return "login.jsp";
		}
		session.setAttribute("employeeId", employee.getId());

		return "redirect:/admin_dashboard";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginEmployee newLogin, BindingResult result, Model model,
			HttpSession session) {

		Employee employee = employeeService.login(newLogin, result);

		if (result.hasErrors() || employee == null) {
			model.addAttribute("newEmployee", new Employee());
			return "login.jsp";
		}

		session.setAttribute("employeeId", employee.getId());

		return "redirect:/admin_dashboard";
	}

	@GetMapping("/create/employee")
	public String viewCreateEmployee(Model model, Employee employee, HttpSession session) {
		// Verify admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("newEmployee", new Employee());

		return "emp/add_emp.jsp";
	}

	@PostMapping("/create/employee")
	public String createEmployee(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult result,
			Model model, HttpSession session) {
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		if (result.hasErrors()) {
			return "emp/add_emp.jsp";
		}

		Employee employee = employeeService.register(newEmployee, result);

		return "redirect:/admin_dashboard";
	}

	@GetMapping("/employees/show/{id}")
	public String viewEmployee(@PathVariable("id") Long id, Model model, Employee employee, HttpSession session) {
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/employees/show/" + loggedInUserId;
	    }

		model.addAttribute("user", employeeService.findById(loggedInUserId)); // Get user name
		model.addAttribute("employee", employeeService.findById(id)); // Get employee name
		return "emp/emp_details.jsp";
	}

	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("updateEmp") Employee employee,
			HttpSession session, Model model) {
		// Verify admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		Employee user = employeeService.findById(loggedInUserId);
		model.addAttribute("user", user); // Get user name
		model.addAttribute("employee", employeeService.findById(id)); // Get employee name
		session.setAttribute("tempId", id);
		return "emp/emp_update.jsp";
	}

//	@PostMapping("/employees/edit")
//	public String editJob(@Valid @ModelAttribute("updateEmp") Employee employee, BindingResult result,
//			HttpSession session) {
//
//		// Verify admin and normal user
//		Long loggedInUserId = (Long) session.getAttribute("employeeId");
//		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
//			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
//		}
//		
//		Long tempId = (Long) session.getAttribute("tempId"); // get employee id
//
//		if (result.hasErrors()) {
//			return "emp/emp_update.jsp";
//		} else {
//
//			Employee emp = employeeService.findById(tempId);
//			employeeService.updateEmployee(emp);
//			return "redirect:/employees/show/" + tempId;
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.setAttribute("employeeId", null);
//		return "redirect:/";
//	}

}
