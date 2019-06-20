package bg.infa.pbt.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infa.pbt.budget.BudgetCategory;
import bg.infa.pbt.budget.MonthlyBudget;
import bg.infa.pbt.controller.param.BudgetParams;
import bg.infa.pbt.exception.ApplicationException;

@Service
public class BudgetService {
	@Autowired
	private BudgetCategoryService budgetCategoryService;
	
	@Autowired
	private UserService userService;

	public void createUserBudget(@Valid BudgetParams params) {
		BudgetCategory bc = getBudetCategoryOrThrow(params.getBudgetCategoryName());
		MonthlyBudget monthlyBudget = new MonthlyBudget(bc, params.getMonthId(), params.getAllowance());
		userService.getCurrentAppUser().getMonthlyBudgets().add(monthlyBudget);
	}

	public void editUserBudget(int budgetId, @Valid BudgetParams params) {
		MonthlyBudget budget = getMonthlyBudgetByIdOrThrow(params.getBudgetId());
		BudgetCategory bc = getBudetCategoryOrThrow(params.getBudgetCategoryName());
		budget.setAllowance(params.getAllowance());
		budget.setBudgetCategory(bc);
		budget.setMonthNumber(params.getMonthId());
	}

	public void cloneUserBudget(int budgetId) {
		MonthlyBudget budget = getMonthlyBudgetByIdOrThrow(budgetId);
		
		Integer monthNumber = null;
		if (budget.getMonthNumber() == 12) {
			monthNumber = 1;
		} else {
			monthNumber = budget.getMonthNumber() + 1;
		}
		
		MonthlyBudget clone = new MonthlyBudget(budget.getBudgetCategory(), monthNumber, budget.getAllowance());
		
		userService.getCurrentAppUser().getMonthlyBudgets().add(clone);
	}
	
	public MonthlyBudget getMonthlyBudgetByIdOrThrow(int budgetId) {
		MonthlyBudget monthlyBudget = userService.getCurrentAppUser().getMonthlyBudgets()
				.stream().filter(mb -> {
					return mb.getId() == budgetId;
				}).findFirst().get();
		
		if (monthlyBudget == null) {
			throw new ApplicationException("No budget with id:" + budgetId);
		}
		
		return monthlyBudget;
 	};
	
	private BudgetCategory getBudetCategoryOrThrow(String bcName) {
		BudgetCategory bc = budgetCategoryService.getUserBudgetCategoryByName(bcName);
		
		if (bc == null) {
			throw new ApplicationException("No business category with name: " + bcName);
		}
		
		return bc;
	}

}
