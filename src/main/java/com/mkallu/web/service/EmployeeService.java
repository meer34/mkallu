package com.mkallu.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkallu.web.model.Employee;
import com.mkallu.web.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee saveUserToDB(Employee admin) {
		return employeeRepo.save(admin);
	}
	
	public Employee findUserById(Long id) {
		return employeeRepo.findById(id).get();
	}

	public List<Employee> getAllUsers() {
		return employeeRepo.findAll();
	}

	public void deleteUserById(Long id) {
		employeeRepo.deleteById(id);
	}

}
