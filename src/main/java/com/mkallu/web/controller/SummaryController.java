package com.mkallu.web.controller;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkallu.web.service.SummaryService;

@Controller
@PropertySource("classpath:hunter_garments.properties")
public class SummaryController {

	@Autowired SummaryService summaryService;
	
	@Value("${INITIAL_PAGE_SIZE}")
	private Integer initialPageSize;

	@GetMapping({"/", "/home"})
	public String showLandingPage(Model model) {
		model.addAttribute("totalIncome", summaryService.getTotalIncome());
		model.addAttribute("totalExpense", summaryService.getTotalExpense());
		model.addAttribute("dashboardList", summaryService.getAllDashBoardItems());
		return "home";
	}

	@GetMapping("/account-report")
	public String showAccountReportPage(Model model,
			@RequestParam(value="fromDate", required = false) String fromDate,
			@RequestParam(value="toDate", required = false) String toDate,
			@RequestParam(value="category", required = false) String category) throws ParseException {
		
		model.addAttribute("totalIncome", summaryService.getTotalIncome());
		model.addAttribute("totalExpense", summaryService.getTotalExpense());
		
		if(fromDate == null && toDate == null && category == null) {
			model.addAttribute("incomeReportList", summaryService.getIncomeReport());
			model.addAttribute("expenseReportList", summaryService.getExpenseReport());
			
		} else {
			model.addAttribute("incomeReportList", summaryService.getIncomeReport(fromDate, toDate, category));
			model.addAttribute("expenseReportList", summaryService.getExpenseReport(fromDate, toDate, category));
		}
		
		return "account-report";
	}

}
