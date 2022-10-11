package com.mkallu.web.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.mkallu.web.model.Income;
import com.mkallu.web.util.SearchCriteria;
import com.mkallu.web.util.SearchSpecification;

@SuppressWarnings("serial")
public class IncomeSearchSpecification extends SearchSpecification<Income> implements Specification<Income>{

	public IncomeSearchSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
	}
	
}
