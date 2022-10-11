package com.mkallu.web.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mkallu.web.model.Income;
import com.mkallu.web.repo.IncomeRepo;
import com.mkallu.web.specification.IncomeSearchSpecification;
import com.mkallu.web.util.SearchSpecificationBuilder;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepo incomeRepo;

	public Income saveIncomeToDB(Income income) {
		return incomeRepo.save(income);
	}
	
	public Income findIncomeById(Long id) {
		return incomeRepo.findById(id).get();
	}

	public Page<Income> getAllIncomes(Integer pageNo, Integer pageSize) {
		return incomeRepo.findAll(PageRequest.of(pageNo, pageSize));
	}
	
	public Page<Income> getAllIncomesForType(Long incomeTypeId, Integer pageNo, Integer pageSize) {
		return incomeRepo.findAllForType(PageRequest.of(pageNo, pageSize), incomeTypeId);
	}

	public void deleteIncomeById(Long id) {
		incomeRepo.deleteById(id);
	}

	public Page<Income> searchIncomeByDateAndKeyword(String keyword, 
			String fromDate, String toDate, int pageNo, Integer pageSize) throws ParseException {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		IncomeSearchSpecification spec = (IncomeSearchSpecification) SearchSpecificationBuilder.build(fromDate, toDate, keyword, Income.class);
		return incomeRepo.findAll(spec, pageRequest);

	}

}
