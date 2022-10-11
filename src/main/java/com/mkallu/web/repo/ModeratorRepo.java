package com.mkallu.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkallu.web.model.Moderator;

public interface ModeratorRepo extends JpaRepository<Moderator, Long>{

	List<Moderator> findByPhoneAndIdNot(String phone, Long id);
	
}
