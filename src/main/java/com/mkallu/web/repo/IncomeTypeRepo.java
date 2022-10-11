package com.mkallu.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.IncomeType;

public interface IncomeTypeRepo extends JpaRepository<IncomeType, Long>{
	
}
