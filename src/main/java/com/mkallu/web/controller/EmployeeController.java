package com.mkallu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkallu.web.model.Employee;
import com.mkallu.web.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired private EmployeeService employeeService;

	@GetMapping("/employee")
	public String showEmployeePage(Model model) {
		model.addAttribute("employeeList", employeeService.getAllUsers());
		return "employee";
	}

	@GetMapping("/add-employee-page")
	public String showAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("header", "Create Employee");
		return "employee-create";
	}

	@RequestMapping(value = "/create-employee",
			method = RequestMethod.POST)
	public String createEmployee(Model model, Employee employee, 
			RedirectAttributes redirectAttributes) throws Exception{

		employee = employeeService.saveUserToDB(employee);
		redirectAttributes.addFlashAttribute("successMessage", "New user " + employee.getName() + " added successfully as Employee!");
		return "redirect:/employee";

	}

	@RequestMapping(value = "/viewEmployee",
			method = RequestMethod.GET)
	public String viewEmployee(Model model,
			@RequestParam("action") String action,
			@RequestParam("id") String id) throws Exception{

		System.out.println("Got view request for employee id " + id);
		model.addAttribute("employee", employeeService.findUserById(Long.parseLong(id)));
		return "view-employee";

	}

	@RequestMapping(value = "/editEmployee",
			method = RequestMethod.GET)
	public String editEmployee(Model model,
			@RequestParam("action") String action,
			@RequestParam("id") String id) throws Exception{

		System.out.println("Got edit request for employee id " + id);
		model.addAttribute("employee", employeeService.findUserById(Long.parseLong(id)));
		model.addAttribute("header", "Edit Employee");
		return "employee-create";

	}

	@RequestMapping(value = "/deleteEmployee",
			method = RequestMethod.GET)
	public String deleteEmployee(RedirectAttributes redirectAttributes,
			@RequestParam("action") String action,
			@RequestParam("id") String id) throws Exception{

		System.out.println("Got delete request for employee id " + id);
		employeeService.deleteUserById(Long.parseLong(id));
		redirectAttributes.addFlashAttribute("successMessage", "Employee with id " + id + " deleted successfully!");
		return "redirect:/employee";
		
	}

}
