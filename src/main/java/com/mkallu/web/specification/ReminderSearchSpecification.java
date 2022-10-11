package com.mkallu.web.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.mkallu.web.model.Reminder;
import com.mkallu.web.util.SearchCriteria;
import com.mkallu.web.util.SearchSpecification;

@SuppressWarnings("serial")
public class ReminderSearchSpecification extends SearchSpecification<Reminder> implements Specification<Reminder>{

	public ReminderSearchSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
	}
	
}
