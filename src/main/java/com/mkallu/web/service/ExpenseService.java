package com.mkallu.web.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mkallu.web.model.Expense;
import com.mkallu.web.repo.ExpenseRepo;
import com.mkallu.web.specification.ExpenseSearchSpecification;
import com.mkallu.web.util.SearchSpecificationBuilder;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepo expenseRepo;

	public Expense saveExpenseToDB(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	public Expense findExpenseById(Long id) {
		return expenseRepo.findById(id).get();
	}

	public Page<Expense> getAllExpenses(Integer pageNo, Integer pageSize) {
		return expenseRepo.findAll(PageRequest.of(pageNo, pageSize));
	}
	
	public Page<Expense> getAllExpensesForType(Long expenseTypeId, Integer pageNo, Integer pageSize) {
		return expenseRepo.findAllForType(PageRequest.of(pageNo, pageSize), expenseTypeId);
	}

	public void deleteExpenseById(Long id) {
		expenseRepo.deleteById(id);
	}

	public Page<Expense> searchExpenseByDateAndKeyword(String keyword, 
			String fromDate, String toDate, int pageNo, Integer pageSize) throws ParseException {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		ExpenseSearchSpecification spec = (ExpenseSearchSpecification) SearchSpecificationBuilder.build(fromDate, toDate, keyword, Expense.class);
		return expenseRepo.findAll(spec, pageRequest);

	}

}
