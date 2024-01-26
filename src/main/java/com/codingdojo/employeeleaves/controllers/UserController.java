package com.codingdojo.employeeleaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.employeeleaves.models.Employee;
import com.codingdojo.employeeleaves.models.LoginUser;
import com.codingdojo.employeeleaves.models.User;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String index(Model model) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors() || user == null) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		} else {
			if ("ADMIN".equals(user.getRole())) {
				session.setAttribute("user_id", user.getId());
				return "redirect:/dashboard";
			} else {
				Employee employee = user.getEmployee();

				if (employee == null) {
					// Handle the case when the employee is not associated with the user
					return "redirect:/login"; // Redirect to login page or handle appropriately
				}

				session.setAttribute("user_id", user.getId());
				return "redirect:/leaves/" + employee.getId();
			}
		}
	}

	// Action Route: Update Password
	@PutMapping("/users/{id}")
	public String updateUserPW(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			// Grab the the current User from Employee
			User currentUser = userService.findUser(userId);
			
			// Set the old properties to the User
			user.setFirstName(currentUser.getFirstName());
			user.setLastName(currentUser.getLastName());
			user.setFirstNameAr(currentUser.getFirstNameAr());
			user.setLastNameAr(currentUser.getLastNameAr());
					
			userService.updateUserPW(user, result);
			if (result.hasErrors()) {
				return "editPassword.jsp";
			} else {
				Employee employee = employeeService.findEmployeeByUserId(user.getId());
				return "redirect:/employees/" + employee.getId();
			}
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
