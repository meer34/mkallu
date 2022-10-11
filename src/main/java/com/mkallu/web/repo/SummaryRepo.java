package com.mkallu.web.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mkallu.web.model.AccountReport;
import com.mkallu.web.model.DashBoard;
import com.mkallu.web.model.Income;

public interface SummaryRepo extends JpaRepository<Income, Long>{
	
	//Dash board
	@Query("SELECT '' AS productName, '' AS brand, 0 AS stockInQuantity, 0 AS stockOutQuantity FROM Income")
	List<DashBoard> findAllDashBoardItems();
	
	@Query("SELECT '' AS productName, '' AS brand, 0 AS stockInQuantity, 0 AS stockOutQuantity FROM Income")
	List<DashBoard> findAllDashBoardItemsByDateAndMahajan(Date fromDate, Date toDate, String keyword);
	
	
	//Income Expense
	@Query("SELECT COALESCE(SUM(income.totalAmount), 0) FROM Income income")
	Long findTotalIncome();
	
	@Query("SELECT COALESCE(SUM(expense.totalAmount), 0) FROM Expense expense")
	Long findTotalExpense();
	
	@Query("SELECT type.name AS category, COALESCE(SUM(income.totalAmount), 0) AS totalAmount "
			+ "FROM IncomeType type LEFT JOIN Income income ON type.id = income.incomeType GROUP BY type.name")
	List<AccountReport> findIncomeReport();
	
	@Query("SELECT type.name AS category, COALESCE(SUM(income.totalAmount), 0) AS totalAmount "
			+ "FROM IncomeType type LEFT JOIN Income income ON type.id = income.incomeType "
			+ "WHERE type.name LIKE %:incomeType% AND income.date >= :fromDate AND income.date <= :toDate GROUP BY type.name")
	List<AccountReport> findIncomeReport(Date fromDate, Date toDate, String incomeType);
	
	@Query("SELECT type.name AS category, COALESCE(SUM(expense.totalAmount), 0) AS totalAmount "
			+ "FROM ExpenseType type LEFT JOIN Expense expense ON type.id = expense.expenseType GROUP BY type.name")
	List<AccountReport> findExpenseReport();
	
	@Query("SELECT type.name AS category, COALESCE(SUM(expense.totalAmount), 0) AS totalAmount "
			+ "FROM ExpenseType type LEFT JOIN Expense expense ON type.id = expense.expenseType "
			+ "WHERE type.name like %:expenseType% AND expense.date >= :fromDate AND expense.date <= :toDate GROUP BY type.name")
	List<AccountReport> findExpenseReport(Date fromDate, Date toDate, String expenseType);


}
