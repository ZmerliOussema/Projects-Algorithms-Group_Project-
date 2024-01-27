package com.Employees_Leaves.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	    // Verify admin and normal user
	    Long loggedInUserId = (Long) session.getAttribute("employeeId");
	    if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
	        return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/employees/" + loggedInUserId;
	    }

	    List<Leave> allLeaves = leaveService.all(); // get all leaves
	    Map<Long, Map<String, Object>> combinedLeaves = new HashMap<>(); // create map for combined leaves

	    // Filter and process approved leaves
	    List<Leave> approvedLeaves = allLeaves.stream()
	            .filter(l -> "Approved".equals(l.getStatus()))
	            .collect(Collectors.toList());

	    for (Leave leave1 : approvedLeaves) { // loop through approved leaves
	        Long ownerId = leave1.getOwner().getId(); // get owner id
	        String ownerFirstName = leave1.getOwner().getFirstNameAr(); // get owner first name
	        String ownerLastName = leave1.getOwner().getLastNameAr() ;  // get owner last name

	        Map<String, Object> ownerData = combinedLeaves.computeIfAbsent(ownerId, k -> new HashMap<>()); // create map for owner data
	        ownerData.put("firstNameAr", ownerFirstName); // add owner first name to map
	        ownerData.put("lastNameAr", ownerLastName); // add owner last name to map
	        ownerData.put("annual", (int) ownerData.getOrDefault("annual", 0) + leave1.getAnnual()); // add annual leave to map
	        ownerData.put("specificLeave", (int) ownerData.getOrDefault("specificLeave", 0) + leave1.getSpecificLeave()); // add specific leave to map
	        ownerData.put("sick", (int) ownerData.getOrDefault("sick", 0) + leave1.getSick()); // add sick leave to map
	    }

	    // Loop through all employees and include those who don't have leaves
	    List<Employee> allEmployees = employeeService.allEmployees();
	    for (Employee employee : allEmployees) {
	        Long employeeId = employee.getId();
	        if (!combinedLeaves.containsKey(employeeId)) {
	            Map<String, Object> ownerData = new HashMap<>();
	            ownerData.put("firstNameAr", employee.getFirstNameAr());
	            ownerData.put("lastNameAr", employee.getLastNameAr());
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

		  Long loggedInUserId = (Long) session.getAttribute("employeeId");

		    if (loggedInUserId == null) {
		        return "redirect:/logout";
		    }

		    Employee user = employeeService.findById(loggedInUserId);
		    String userRole = user.getRole();

		    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
		        return "redirect:/employees/" + loggedInUserId;
		    }

	    List<Leave> allLeaves = leaveService.getLeavesByEmployeeId(id);
	    List<Leave> approvedLeaves = allLeaves.stream()
	            .filter(leave -> "Approved".equals(leave.getStatus()))
	            .collect(Collectors.toList());

	    model.addAttribute("leaves", approvedLeaves); // Set approved leaves for the employee dashboard
	    model.addAttribute("user", user); // Get user name
	    model.addAttribute("employee", employeeService.findById(id)); // Get employee name

	    return "emp/emp_dashboard.jsp";
	}

//*****View and add annual leaves for employee******
	@GetMapping("/employees/annual/{id}")
	public String viewAnnualLeave(@PathVariable("id") Long id,@ModelAttribute("newLeave") Leave leave, HttpSession session, Model model) {
	    Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/employees/annual/" + loggedInUserId;
	    }

	    List<Leave> allLeaves = leaveService.getAnnualLeavesByEmployeeId(id);
	    List<Leave> approvedLeaves = allLeaves.stream()
	            .filter(l -> "Approved".equals(l.getStatus()))
	            .collect(Collectors.toList());

	    model.addAttribute("leaves", approvedLeaves);
	    model.addAttribute("user", user);
	    model.addAttribute("employee", employeeService.findById(id));
	    session.setAttribute("tempId", id);

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
	public String viewSpecificLeave(@PathVariable("id") Long id,@ModelAttribute("newLeave") Leave leave, HttpSession session, Model model) {
	    Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/employees/specific/" + loggedInUserId;
	    }

	    List<Leave> allLeaves = leaveService.getSpecificLeavesByEmployeeId(id);
	    List<Leave> approvedLeaves = allLeaves.stream()
	            .filter(l -> "Approved".equals(l.getStatus()))
	            .collect(Collectors.toList());

	    model.addAttribute("leaves", approvedLeaves);
	    model.addAttribute("user", user);
	    model.addAttribute("employee", employeeService.findById(id));
	    session.setAttribute("tempId", id);

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
	public String viewSickLeave(@PathVariable("id") Long id,@ModelAttribute("newLeave") Leave leave, HttpSession session, Model model) {
	    Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/employees/sick/" + loggedInUserId;
	    }

	    List<Leave> allLeaves = leaveService.getSickLeavesByEmployeeId(id);
	    List<Leave> approvedLeaves = allLeaves.stream()
	            .filter(l -> "Approved".equals(l.getStatus()))
	            .collect(Collectors.toList());

	    model.addAttribute("leaves", approvedLeaves);
	    model.addAttribute("user", user);
	    model.addAttribute("employee", employeeService.findById(id));
	    session.setAttribute("tempId", id);

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

    // Employee Request Leave
    @GetMapping("/requestLeave/{id}")
    public String requestLeave(@PathVariable("id") Long id,Model model, HttpSession session) {
    	
  	  Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/requestLeave/" + loggedInUserId;
	    }
	    
    	model.addAttribute("user", user);
        model.addAttribute("newLeave", new Leave());
        return "emp/request_leave_form.jsp";
    }

    @PostMapping("/requestLeave")
    public String submitLeaveRequest(@Valid @ModelAttribute("newLeave") Leave leave, BindingResult result,
                                     HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("employeeId");
        if (loggedInUserId == null) {
            return "redirect:/logout";
        }
        if (result.hasErrors()) {
            return "emp/request_leave_form.jsp";
        }
        leave.setOwner(employeeService.findById(loggedInUserId));
        leaveService.addLeave(leave);
        return "redirect:/employees/" + loggedInUserId;
    }

    // Admin View Employee Leave Requests
    @GetMapping("/employeeLeaveRequests")
    public String viewEmployeeLeaveRequests(HttpSession session, Model model) {
        Long loggedInUserId = (Long) session.getAttribute("employeeId");
        if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
            return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
        }
        List<Leave> leaveRequests = leaveService.all();
        model.addAttribute("leaveRequests", leaveRequests);
        model.addAttribute("user", employeeService.findById(loggedInUserId));
        return "admin/leave_requests.jsp";
    }

    // Admin Approve/Deny Leave Request
    @PostMapping("/approveLeaveRequest/{leaveId}")
    public String approveLeaveRequest(@PathVariable("leaveId") Long leaveId, HttpSession session) {
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}
		
        Leave leave = leaveService.findById(leaveId);
        leave.setStatus("Approved");
        leaveService.update(leave);
        return "redirect:/employeeLeaveRequests";
    }

    @PostMapping("/denyLeaveRequest/{leaveId}")
    public String denyLeaveRequest(@PathVariable("leaveId") Long leaveId, HttpSession session) {
		Long loggedInUserId = (Long) session.getAttribute("employeeId");
		if (loggedInUserId == null || !"admin".equals(employeeService.findById(loggedInUserId).getRole())) {
			return (loggedInUserId == null) ? "redirect:/logout" : "redirect:/access-denied";
		}
		
        Leave leave = leaveService.findById(leaveId);
        leave.setStatus("Denied");
        leaveService.update(leave);
        return "redirect:/employeeLeaveRequests";
    }
    
    @GetMapping("/leavestatus/{id}")
    public String leaveStatus(@PathVariable("id") Long id,Model model, HttpSession session) {
    	
  	  Long loggedInUserId = (Long) session.getAttribute("employeeId");

	    if (loggedInUserId == null) {
	        return "redirect:/logout";
	    }

	    Employee user = employeeService.findById(loggedInUserId);
	    String userRole = user.getRole();

	    if (!"admin".equals(userRole) && !loggedInUserId.equals(id)) {
	        return "redirect:/leavestatus/" + loggedInUserId;
	    }
	    
    	model.addAttribute("user", user);
    	 List<Leave> leaveRequests = leaveService.getLeavesByEmployeeId(id);
    	 model.addAttribute("leaves", leaveRequests);
        return "emp/leave_status.jsp";
    }
}
