package com.mkallu.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.Party;

public interface PartyRepo extends JpaRepository<Party, Long>{
	
}
