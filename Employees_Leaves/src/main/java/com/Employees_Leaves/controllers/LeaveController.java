package com.Employees_Leaves.controllers;

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

import com.Employees_Leaves.models.Employee;
import com.Employees_Leaves.models.Leave;
import com.Employees_Leaves.services.EmployeeService;
import com.Employees_Leaves.services.LeaveService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private EmployeeService employeeService;

//*****View all leaves for admin******		
	@GetMapping("/admin_dashboard")
	public String adminDashboard(HttpSession session, Model model, Leave leave) {

		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
		}

		List<Leave> leaves = leaveService.all(); // get all leaves
		Map<Long, Map<String, Object>> combinedLeaves = new HashMap<>(); // create map for combined leaves

		for (Leave leave1 : leaves) { // loop through leaves
			Long ownerId = leave1.getOwner().getId(); // get owner id
			String ownerFirstName = leave1.getOwner().getFirstNameAr(); // get owner first name
			String ownerLastName = leave1.getOwner().getLastNameAr(); // get owner last name

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
		List<Employee> allEmployees = employeeService.allEmployees();
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

		model.addAttribute("user", employeeService.findById(loggedInUserId));
		model.addAttribute("employees", allEmployees);
		return "admin/admin_dashboard.jsp";
	}

//*****View all leaves for employee******	
	@GetMapping("/employees/{id}")
	public String employeeDashboard(@PathVariable("id") Long id, HttpSession session, Model model) {

		// Get the ID of the logged-in user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

		if (loggedInUserId == null) {
			return "redirect:/logout";
		}

		// Check if the logged-in user has the "admin" role
		Employee user = employeeService.findById(loggedInUserId);
		String userRole = user.getRole();
		if ("admin".equals(userRole)) {
			model.addAttribute("leaves", leaveService.getLeavesByEmployeeId(id)); // get all leaves for employee by id
			model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
			model.addAttribute("employee", employeeService.findById(id)); // get employee name
			return "emp/emp_dashboard.jsp";
		}

		// Check if the logged-in user has the same ID as the one specified in the path
		if (!loggedInUserId.equals(id)) {
			return "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("leaves", leaveService.getLeavesByEmployeeId(id)); // get all leaves for employee by id
		model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
		model.addAttribute("employee", employeeService.findById(id)); // get employee name

		return "emp/emp_dashboard.jsp";
	}

//*****View and add annual leaves for employee******
	@GetMapping("/employees/annual/{id}")
	public String viewAnnualLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {
		// Get the ID of the logged-in user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

		if (loggedInUserId == null) {
			return "redirect:/logout";
		}

		// Check if the logged-in user has the "admin" role
		Employee user = employeeService.findById(loggedInUserId);
		String userRole = user.getRole();
		if ("admin".equals(userRole)) {
			model.addAttribute("leaves", leaveService.getAnnualLeavesByEmployeeId(id)); // get all annual leaves for
																						// employee by id
			model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
			model.addAttribute("employee", employeeService.findById(id)); // get employee name
			session.setAttribute("tempId", id); // for employee id
			return "emp/emp_annual_leave.jsp";
		}

		// Check if the logged-in user has the same ID as the one specified in the path
		if (!loggedInUserId.equals(id)) {
			return "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("leaves", leaveService.getAnnualLeavesByEmployeeId(id)); // get all annual leaves for
																					// employee by id
		model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
		model.addAttribute("employee", employeeService.findById(id)); // get employee name
		session.setAttribute("tempId", id); // for employee id

		return "emp/emp_annual_leave.jsp";
	}

	@PostMapping("/employees/annual/add")
	public String addAnnualLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session, Model model) {
		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}
		
		if (result.hasErrors()) {
			return "emp/emp_annual_leave.jsp";
		} else {

			leaveService.addLeave(leave); // add leave to db

			Long tempId = (Long) session.getAttribute("tempId"); // get employee id
			return "redirect:/employees/annual/" + tempId;
		}
	}

//*****View and add specific leaves for employee******

	@GetMapping("/employees/specific/{id}")
	public String viewSpecificLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {

		// Get the ID of the logged-in user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

		if (loggedInUserId == null) {
			return "redirect:/logout";
		}
		// Check if the logged-in user has the "admin" role
		Employee user = employeeService.findById(loggedInUserId);
		String userRole = user.getRole();
		if ("admin".equals(userRole)) {
			model.addAttribute("leaves", leaveService.getSpecificLeavesByEmployeeId(id)); // get all annual leaves for
																							// employee by id
			model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
			model.addAttribute("employee", employeeService.findById(id)); // get employee name
			session.setAttribute("tempId", id); // for employee id
			return "emp/emp_specific_leaves.jsp";
		}

		// Check if the logged-in user has the same ID as the one specified in the path
		if (!loggedInUserId.equals(id)) {
			return "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("leaves", leaveService.getSpecificLeavesByEmployeeId(id)); // get all specific leaves for
																						// employee by id
		model.addAttribute("user", employeeService.findById(loggedInUserId)); // for user name
		model.addAttribute("employee", employeeService.findById(id)); // for employee name
		session.setAttribute("tempId", id); // for employee id
		return "emp/emp_specific_leaves.jsp";
	}

	@PostMapping("/employees/specific/add")
	public String addSpecificLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session) {
		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}

		if (result.hasErrors()) {
			return "emp/emp_specific_leaves.jsp";
		} else {

			leaveService.addLeave(leave); // add leave to db

			Long tempId = (Long) session.getAttribute("tempId"); // get employee id
			return "redirect:/employees/specific/" + tempId;
		}
	}

//*****View and add sick leaves for employee******

	@GetMapping("/employees/sick/{id}")
	public String viewSickLeave(@PathVariable("id") Long id, @ModelAttribute("newLeave") Leave leave,
			HttpSession session, Model model) {

		// Get the ID of the logged-in user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");

		if (loggedInUserId == null) {
			return "redirect:/logout";
		}
		// Check if the logged-in user has the "admin" role
		Employee user = employeeService.findById(loggedInUserId);
		String userRole = user.getRole();
		if ("admin".equals(userRole)) {
			model.addAttribute("leaves", leaveService.getSickLeavesByEmployeeId(id)); // get all annual leaves for
																						// employee by id
			model.addAttribute("user", employeeService.findById(loggedInUserId)); // get user name
			model.addAttribute("employee", employeeService.findById(id)); // get employee name
			session.setAttribute("tempId", id); // for employee id
			return "emp/emp_sick_leaves.jsp";
		}

		// Check if the logged-in user has the same ID as the one specified in the path
		if (!loggedInUserId.equals(id)) {
			return "redirect:/employees/" + loggedInUserId;
		}

		model.addAttribute("leaves", leaveService.getSickLeavesByEmployeeId(id)); // get all sick leaves for employee by
																					// id
		model.addAttribute("user", employeeService.findById(loggedInUserId)); // for user
																				// name
		model.addAttribute("employee", employeeService.findById(id)); // for employee name
		session.setAttribute("tempId", id); // for employee id
		return "emp/emp_sick_leaves.jsp";
	}

	@PostMapping("/employees/sick/add")
	public String addSickLeave(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
			HttpSession session) {
		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}

		if (result.hasErrors()) {
			return "emp/emp_sick_leaves.jsp";
		} else {

			leaveService.addLeave(leave); // add leave to db

			Long tempId = (Long) session.getAttribute("tempId"); // get employee id
			return "redirect:/employees/sick/" + tempId;
		}
	}

//**** delete employee for admin******
	@DeleteMapping("/employees/{id}/delete")
	public String deleteEmployee(@PathVariable("id") Long id, HttpSession session) {

		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}

		Employee employee = employeeService.findById(id);
		employeeService.deleteEmployee(employee);
		return "redirect:/admin_dashboard";
	}

//**** delete leave for admin******
	@DeleteMapping("/leave/{id}/delete")
	public String deleteLeave(@PathVariable("id") Long id, HttpSession session) {

		// verification admin and normal user
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}

		Leave leave = leaveService.findById(id);
		leaveService.deleteLeave(leave);
		return "redirect:/employees/" + leave.getOwner().getId();
	}

}
