//package com.Employees_Leaves.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.Employees_Leaves.models.Employee;
//import com.Employees_Leaves.models.Leave;
//import com.Employees_Leaves.services.EmployeeService;
//import com.Employees_Leaves.services.LeaveService;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//
//@Controller
//public class LeaveController {
//	
//	@Autowired
//	private LeaveService leaveService;
//
//	@Autowired
//	private EmployeeService employeeService;
//
////*****View all leaves for admin******		
//	@GetMapping("/admin_dashboard")
//	public String adminDashboard(HttpSession session, Model model, Leave leave) {
//	 
//		if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//			return "redirect:/logout";
//		}
//
//		//complete here
//		return "admin_dashboard.jsp";
//	}
//
////*****View all leaves for employee******	
//	@GetMapping("/employees/{id}")
//	public String employeeDashboard(@PathVariable("id") Long id, HttpSession session, Model model) {
//
//		if (session.getAttribute("userId") == null) {
//			return "redirect:/logout";
//		}		
//
//		//complete here
//		return "employee_dashboard.jsp";
//	}
//	
////*****View and add annual leaves for employee******
//	@GetMapping("/employees/annual/{id}")
//	public String annualLeave(@PathVariable("id") Long id, HttpSession session, Model model) {
//
//		if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//			return "redirect:/logout";
//		}	
//
//		//complete here
//		return "annual_leave.jsp";
//	}
//	
//	@PostMapping("/employees/annual/{id}/addLeave")
//	public String addAnnualLeave(@Valid @ModelAttribute("leave") Leave leave, BindingResult result, HttpSession session) {
//
//		if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//			return "redirect:/logout";
//		}	
//
//		if (result.hasErrors()) {
//			return "annual_leave.jsp";
//		} else {
//			leaveService.addLeave(leave);
//
//			Long userId = (Long) session.getAttribute("userId");
//			Employee employee = employeeService.findById(userId);
//			employeeService.updateEmployee(user);
//			return "redirect:/employees/annual/{id}";
//		}
//	}
//
////*****View and add specific leaves for employee******
//
//    @GetMapping("/employees/specific/{id}")
//    public String specificLeave(@PathVariable("id") Long id, HttpSession session, Model model) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }
//      //complete here
//        return "specific_leave.jsp";
//    }
//
//    @PostMapping("/employees/specific/{id}/addLeave")
//    public String addSpecificLeave(@Valid @ModelAttribute("leave") Leave leave, BindingResult result, HttpSession session) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }
//
//        if (result.hasErrors()) {
//            return "specific_leave.jsp";
//        } else {
//            leaveService.addLeave(leave);
//
//            Long userId = (Long) session.getAttribute("userId");
//            Employee employee = employeeService.findById(userId);
//            employeeService.updateEmployee(user);
//            return "redirect:/employees/specific/{id}";
//        }
//    }
//
////*****View and add sick leaves for employee******
//    @GetMapping("/employees/sick/{id}")
//    public String sickLeave(@PathVariable("id") Long id, HttpSession session, Model model) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }
//      //complete here
//        return "sick_leave.jsp";
//    }
//
//    @PostMapping("/employees/sick/{id}/addLeave")
//    public String addSickLeave(@Valid @ModelAttribute("leave") Leave leave, BindingResult result, HttpSession session) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }
//
//        if (result.hasErrors()) {
//            return "sick_leave.jsp";
//        } else {
//            leaveService.addLeave(leave);
//
//            Long userId = (Long) session.getAttribute("userId");
//            Employee employee = employeeService.findById(userId);
//            employeeService.updateEmployee(user);
//            return "redirect:/employees/sick/{id}";
//        }
//    }
//
////**** delete employee for admin******
//    @DeleteMapping("/employees/{id}/delete")
//    public String deleteEmployee(@PathVariable("id") Long id, HttpSession session) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }	
//        
//        Employee employee = employeeService.findById(id);
//        employeeService.deleteEmployee(employee);
//        return "redirect:/admin_dashboard";
//    }
//
////**** delete leave for admin******
//    @DeleteMapping("/employees/{id}/delete")
//    public String deleteLeave(@PathVariable("id") Long id, HttpSession session) {
//
//        if(session.getAttribute("userId") == null && session.getAttribute("isAdmin")== true) {
//            return "redirect:/logout";
//        }
//        
//        Leave leave = leaveService.findById(id);
//        leaveService.deleteLeave(leave);
//        return "redirect:/employees/{id}";
//    }
//
//}
