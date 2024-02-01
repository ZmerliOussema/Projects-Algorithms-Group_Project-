package com.Employees_Leaves.controllers;

import com.Employees_Leaves.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.services.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;

//	DISPLAY ROUTES

	// ******** create employee ******//
	@GetMapping("/create/employee")
	public String viewCreateEmployee(@ModelAttribute("newEmployee") Employee newEmployee, HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		// Bind empty newEmployee object to the JSP
		// to capture the form input
//		model.addAttribute("newEmployee", new Employee());

		return "emp/add_emp.jsp";
	}

	// ******** show employee ******//
	@GetMapping("/employees/show/{id}")
	public String viewEmployee(@PathVariable("id") Long id, Model model, HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		if (loggedInUserId == null) {
			return "redirect:/logout";
		}

		Employee user = employeeService.findById(loggedInUserId);
		String userRole = user.getRole();

		// if id not the same of logged in user redirect to his employee dashboard
		if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
			return "redirect:/employees/show/" + loggedInUserId;
		}

		model.addAttribute("user", employeeService.findById(loggedInUserId)); // Get user
		model.addAttribute("employee", employeeService.findById(id)); // Get employee
		return "emp/emp_details.jsp";
	}

	// ******** edit employee ******//
	@GetMapping("/employees/edit/{id}")
	public String viewUpdateEmployee(@PathVariable("id") Long id, @ModelAttribute("updateEmp") Employee employee,
			Model model, HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("user", employeeService.findById(loggedInUserId)); // Get user
		model.addAttribute("employee", employeeService.findById(id)); // Get employee

//		session.setAttribute("tempId", id);
		return "emp/emp_update.jsp";
	}

	// ******** edit employee password******//
	@GetMapping("/employees/ch_password/{id}")
	public String ch_emp_password(@PathVariable("id") Long id,
			@ModelAttribute("newEmpPassword") Employee newEmpPassword, Model model, HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		Employee thisEmployee = employeeService.findById(id);
		model.addAttribute("thisEmployee", thisEmployee);

		return "emp/ch_emp_password.jsp";
	}

	// ******** logout ******//
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}

//	ACTION ROUTES

	// ******** create employee ******//
	@PostMapping("/create/employee")
	public String createEmployee(@Valid @ModelAttribute("newEmployee") Employee newEmployee, BindingResult result,
			HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		} else {

			if (result.hasErrors()) {
				return "emp/add_emp.jsp";
			} else {

				employeeService.register(newEmployee, result);
			}

			return "redirect:/admin_dashboard";
		}

	}

	// ******** edit employee ******//
	@PostMapping("/employees/edit/{id}")
	public String updateEmployee(@PathVariable("id") Long id,
			@Valid @ModelAttribute("updateEmp") Employee updatedEmployee, BindingResult result, Model model,
			HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		} else {

			if (result.hasErrors()) {
				model.addAttribute("user", employeeService.findById(loggedInUserId)); // Get user
				model.addAttribute("employee", employeeService.findById(id)); // Get employee
				System.out.println("has errors");
				return "emp/emp_update.jsp";
			} else {

				Employee thisEmployee = employeeService.findById(id);
				updatedEmployee.setId(id);
				updatedEmployee.setLeaves(thisEmployee.getLeaves());
				int phoneNumber = (int) updatedEmployee.getPhoneNumber();
				employeeService.updateOneEmployee(updatedEmployee.getFirstName(), updatedEmployee.getLastName(),
						updatedEmployee.getEmail(), updatedEmployee.getAddress(), updatedEmployee.getTitle(),
						updatedEmployee.getRangeEmployee(), updatedEmployee.getCategory(), phoneNumber,
						updatedEmployee.getFirstNameAr(), updatedEmployee.getLastNameAr(),
						updatedEmployee.getAddressAr(), updatedEmployee.getTitleAr(), updatedEmployee.getRangeAr(),
						updatedEmployee.getCategoryAr(), updatedEmployee.getId());

				return "redirect:/employees/show/" + id;
			}
		}

	}

	// ******** edit employee password******//
	@PostMapping("/employees/ch_password/{id}")
	public String ch_emp_password(@PathVariable("id") Long id,
			@Valid @ModelAttribute("newEmpPassword") Employee newEmpPassword, BindingResult result, Model model,
			HttpSession session) {

		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		} else {
			
			if (newEmpPassword.getPassword().length() < 8 || newEmpPassword.getPassword().length() > 128) {

				result.rejectValue("confirm", "chPasswordErrors", "Password must be between 8 and 128 characters");
				return "emp/ch_emp_password.jsp";
				
			} else {
				
				if (newEmpPassword.getConfirm().length() < 8 || newEmpPassword.getConfirm().length() > 128) {

					result.rejectValue("confirm", "chPasswordErrors", "Confirm Password must be between 8 and 128 characters");
					return "emp/ch_emp_password.jsp";
					
				} else {
					
					// Reject if password doesn't match confirmation
					if (!newEmpPassword.getPassword().equals(newEmpPassword.getConfirm())) {

						result.rejectValue("confirm", "chPasswordErrors", "The Confirm Password must match Password!");
						return "emp/ch_emp_password.jsp";

					} else {

						Employee thisEmployee = employeeService.findById(id);
						newEmpPassword.setId(id);
						newEmpPassword.setLeaves(thisEmployee.getLeaves());
						employeeService.chEmpPassword(newEmpPassword.getPassword(), newEmpPassword.getId());

						return "redirect:/employees/show/" + id;

					}
				}
				
			}	

		}
	}

	// **** delete employee for admin******
	@DeleteMapping("/employees/{id}/delete")
	public String deleteEmployee(@PathVariable("id") Long id, HttpSession session, Model model) {
		// Grab the user id from session
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		// Route guard
		// Redirect to employee dashboard if is not admin
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		} else {
			if (!leaveService.getLeavesByEmployeeId(id).isEmpty())
            {
				return "redirect:/admin_dashboard?error=1";
			}
			employeeService.deleteEmployee(id);
		}
		return "redirect:/admin_dashboard";
	}

}
