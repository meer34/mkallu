package com.mkallu.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
}
