package com.mkallu.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.ExpenseType;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseType, Long>{
	
}
