package com.mkallu.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
}
