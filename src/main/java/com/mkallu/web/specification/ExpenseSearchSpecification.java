package com.mkallu.web.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.mkallu.web.model.Expense;
import com.mkallu.web.util.SearchCriteria;
import com.mkallu.web.util.SearchSpecification;

@SuppressWarnings("serial")
public class ExpenseSearchSpecification extends SearchSpecification<Expense> implements Specification<Expense>{

	public ExpenseSearchSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
	}
	
}
