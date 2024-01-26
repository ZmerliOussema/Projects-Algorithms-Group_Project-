package com.codingdojo.employeeleaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.employeeleaves.models.Leave;
import com.codingdojo.employeeleaves.models.User;
import com.codingdojo.employeeleaves.services.EmployeeService;
import com.codingdojo.employeeleaves.services.LeaveService;
import com.codingdojo.employeeleaves.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	// *****View all leaves for employee******
	@GetMapping("/leaves/{id}")
	public String employeeDashboard(@PathVariable("id") Long id, HttpSession session, Model model) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("leaves", leaveService.getLeavesByEmployeeId(id)); // get all leaves for employee by id
			model.addAttribute("employee", employeeService.findEmployee(id)); // get employee name
			
			User currentUser = userService.findUser(userId);
			model.addAttribute("user", currentUser);

			return "employeeWithAllLeaves.jsp";
		}
	}

	// *****View and add annual leaves for employee******
	@GetMapping("/leaves/annual/{id}")
	public String viewAnnualLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("leaves", leaveService.getAnnualLeavesByEmployeeId(id)); // get all annual leaves for

			model.addAttribute("employee", employeeService.findEmployee(id)); // get employee name
			session.setAttribute("tempId", id); // for employee id
			
			User currentUser = userService.findUser(userId);
			model.addAttribute("user", currentUser);
			
			return "employeeWithAnnualLeaves.jsp";
		}
	}

	@PostMapping("/leaves/annual/add")
	public String addAnnualLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session, Model model) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
				return "employeeWithAnnualLeaves.jsp";
			} else {
				// Grab the the current User from Employee
				User currentUser = userService.findUser(userId);

				// Check if the Current User is an ADMIN or Not!
				if (!("ADMIN".equals(currentUser.getRole())))
					return "redirect:/";

				leaveService.addLeave(leave); // add leave to db

				Long tempId = (Long) session.getAttribute("tempId"); // get employee id
				return "redirect:/leaves/annual/" + tempId;
			}
		}
	}

	// *****View and add specific leaves for employee******

	@GetMapping("/leaves/specific/{id}")
	public String viewSpecificLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("leaves", leaveService.getSpecificLeavesByEmployeeId(id)); // get all specific leaves for
			model.addAttribute("employee", employeeService.findEmployee(id)); // for employee name
			session.setAttribute("tempId", id); // for employee id
			
			User currentUser = userService.findUser(userId);
			model.addAttribute("user", currentUser);
			
			return "employeeWithSpecificLeaves.jsp";
		}
	}

	@PostMapping("/leaves/specific/add")
	public String addSpecificLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
				return "employeeWithSpecificLeaves.jsp";
			} else {
				// Grab the the current User from Employee
				User currentUser = userService.findUser(userId);

				// Check if the Current User is an ADMIN or Not!
				if (!("ADMIN".equals(currentUser.getRole())))
					return "redirect:/";

				leaveService.addLeave(leave); // add leave to db

				Long tempId = (Long) session.getAttribute("tempId"); // get employee id
				return "redirect:/leaves/specific/" + tempId;
			}
		}
	}

	// *****View and add sick leaves for employee******
	@GetMapping("/leaves/sick/{id}")
	public String viewSickLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("leaves", leaveService.getSickLeavesByEmployeeId(id)); // get all sick leaves for
																						// employee by
																						// id
			model.addAttribute("employee", employeeService.findEmployee(id)); // for employee name
			session.setAttribute("tempId", id); // for employee id
			
			User currentUser = userService.findUser(userId);
			model.addAttribute("user", currentUser);
			
			return "employeeWithSickLeaves.jsp";
		}
	}

	@PostMapping("/leaves/sick/add")
	public String addSickLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			if (result.hasErrors()) {
				return "employeeWithSickLeaves.jsp";
			} else {

				// Grab the the current User from Employee
				User currentUser = userService.findUser(userId);

				// Check if the Current User is an ADMIN or Not!
				if (!("ADMIN".equals(currentUser.getRole())))
					return "redirect:/";

				leaveService.addLeave(leave); // add leave to db

				Long tempId = (Long) session.getAttribute("tempId"); // get employee id
				return "redirect:/leaves/sick/" + tempId;
			}
		}
	}

	// **** delete leave for admin******
	@DeleteMapping("/leave/{id}/delete")
	public String deleteLeave(@PathVariable("id") Long id, HttpSession session) {

		// Grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// Route Guard
		if (userId == null) {
			return "redirect:/";
		} else {
			
			// Grab the the current User from Employee
			User currentUser = userService.findUser(userId);

			// Check if the Current User is an ADMIN or Not!
			if (!("ADMIN".equals(currentUser.getRole())))
				return "redirect:/";
			
			Leave leave = leaveService.findById(id);
			leaveService.deleteLeave(leave);
			return "redirect:/employees/" + leave.getEmployee().getId();
		}
	}
}
