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
	
	@Autowired
	private HttpSession session;
	
	//******** Login page ******//
	@GetMapping("/")
	public String index(Model model, Employee employee) {
		model.addAttribute("newLogin", new LoginEmployee());

		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if(loggedInUserId != null) {
			return "redirect:/admin_dashboard";
		}
		return "login.jsp";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginEmployee newLogin, BindingResult result, Model model) {

		Employee employee = employeeService.login(newLogin, result);

		if (result.hasErrors() || employee == null) {
			return "login.jsp";
		}

		session.setAttribute("employeeId", employee.getId());
		return "redirect:/admin_dashboard";
	}
	//******** Login page ******//
	
	//******** create admin for first time only ******//
	@GetMapping("/create/admin")
	public String createAdmin(Model model, Employee employee) {
		model.addAttribute("newEmployee", new Employee());

		Long loggedInUserId = (Long) session.getAttribute("employeeId");
	    if (loggedInUserId != null || !employeeService.allEmployees().isEmpty()) {
	        return "redirect:/admin_dashboard";
	    }
		return "create_admin.jsp";
	}
	
	@PostMapping("/create/admin/add")
	public String register(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult result,
			Model model) {

		Employee employee = employeeService.register(newEmployee, result);

		if (result.hasErrors()) {
			return "create_admin.jsp";
		}
		session.setAttribute("employeeId", employee.getId());

		return "redirect:/admin_dashboard";
	}
	//******** create admin for first time only ******//
	
	
	//******** create employee ******//
	@GetMapping("/create/employee")
	public String viewCreateEmployee(Model model, Employee employee) {
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
			Model model) {
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
	//******** create employee ******//
	
	//******** show employee ******//
	@GetMapping("/employees/show/{id}")
	public String viewEmployee(@PathVariable("id") Long id, Model model, Employee employee) {
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
	//******** show employee ******//
	
	//******** edit employee ******//
	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("updateEmp") Employee employee, Model model) {
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

	@PostMapping("/employees/edit/{id}")
	public String editJob(@PathVariable("id") Long id,@Valid @ModelAttribute("updateEmp") Employee employee, BindingResult result, Model model) {

		// Verify admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}
		
		Employee user = employeeService.findById(loggedInUserId);
		model.addAttribute("employee", employeeService.findById(id)); // Get employee name
		model.addAttribute("user", user); // Get user name
		
		if (result.hasErrors()) {
			return "emp/emp_update.jsp";
		} else {

			Employee emp = employeeService.findById(id);
			employeeService.updateEmployee(emp);
			return "redirect:/employees/show/" + id;
		}
	}
	//******** edit employee ******//
	
	//******** logout ******//
	@GetMapping("/logout")
	public String logout() {
		session.setAttribute("employeeId", null);
		return "redirect:/";
	}
	//******** logout ******//
}
