package com.codingdojo.employeeleaves.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.codingdojo.employeeleaves.models.Leave;
import com.codingdojo.employeeleaves.models.User;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.LeaveService;
import com.codingdojo.employeeleaves.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private UserService userService;
	
	//*****View all leaves for admin******		
		@GetMapping("/dashboard")
		public String adminDashboard(HttpSession session, Model model, Leave leave) {

			List<Leave> leaves = leaveService.all(); // get all leaves
			Map<Long, Map<String, Object>> combinedLeaves = new HashMap<>(); // create map for combined leaves

			for (Leave leave1 : leaves) { // loop through leaves
				Long ownerId = leave1.getEmployee().getId(); // get Employee id
				String ownerFirstName = leave1.getEmployee().getFirstNameAr(); // get owner first name
				String ownerLastName = leave1.getEmployee().getLastNameAr(); // get owner last name

				Map<String, Object> ownerData = combinedLeaves.computeIfAbsent(ownerId, k -> new HashMap<>()); // create map for owner data
				ownerData.put("firstName", ownerFirstName); // add owner first name to map
				ownerData.put("lastName", ownerLastName); // add owner last name to map
				ownerData.put("annual", (int) ownerData.getOrDefault("annual", 0) + leave1.getAnnual()); // add annual leave
																											// to map
				ownerData.put("specificLeave",
						(int) ownerData.getOrDefault("specificLeave", 0) + leave1.getSpecificLeave()); // add specific leave
																										// to map
				ownerData.put("sick", (int) ownerData.getOrDefault("sick", 0) + leave1.getSick()); // add sick leave to map
			}

			// Loop through all employees and include those who don't have leaves
			List<Employee> allEmployees = employeeService.getAllEmployees();
			for (Employee employee : allEmployees) {
				Long employeeId = employee.getId();
				if (!combinedLeaves.containsKey(employeeId)) {
					Map<String, Object> ownerData = new HashMap<>();
					ownerData.put("firstName", employee.getFirstName());
					ownerData.put("lastName", employee.getLastName());
					ownerData.put("annual", 0);
					ownerData.put("specificLeave", 0);
					ownerData.put("sick", 0);

					combinedLeaves.put(employeeId, ownerData);
				}
			}

			model.addAttribute("combinedLeaves", combinedLeaves); // add combined leaves to model

//			model.addAttribute("user", employeeService.findById(loggedInUserId));
			model.addAttribute("employees", allEmployees);
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

	        // Create a corresponding User with role "USER"
	        User newUser = new User();
	        newUser.setEmail(newEmployee.getEmail());
	        newUser.setPassword(newEmployee.getPassword());
	        newUser.setConfirmPW(newEmployee.getConfirmPassword());
	        newUser.setRole("USER");
	        newUser.setEmployee(newEmployee);

	        // Save the User in DB
	        userService.register(newUser, result);

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
