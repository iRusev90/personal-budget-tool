package bg.infa.pbt.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infa.pbt.budget.BudgetCategory;
import bg.infa.pbt.budget.MonthlyBudget;
import bg.infa.pbt.budget.Payment;
import bg.infa.pbt.controller.param.BudgetCategoryParams;
import bg.infa.pbt.exception.ApplicationException;
import bg.infa.pbt.user.AppUser;

@Service
public class BudgetCategoryService {
	@Autowired
	private UserService userService;

	public void addUserCategory(@Valid BudgetCategoryParams params) {
		BudgetCategory budgetCategory = getUserBudgetCategoryByName(params.getName());
		if (budgetCategory != null) {
			throw new ApplicationException("There is already a budget category with the name " + params.getName());
		}
		
		budgetCategory = new BudgetCategory();
		budgetCategory.setModifiable(true);
		budgetCategory.setEnabled(true);
		budgetCategory.setName(params.getName());
		budgetCategory.setDescription(params.getDescription());
		
		userService.getCurrentAppUser().getBudgetCategories().add(budgetCategory);
		
	}

	public void updateUserCategory(@Valid BudgetCategoryParams params) {
		BudgetCategory budgetCategory = getModifiableNonNullBudgetCategoryOrThrow(params.getName());
		
		budgetCategory.setDescription(params.getDescription());
	}

	public void deleteUserCategory(String budgetCategoryName) {
		BudgetCategory budgetCategory = getModifiableNonNullBudgetCategoryOrThrow(budgetCategoryName);
		AppUser appUser = userService.getCurrentAppUser();

		for (MonthlyBudget monthlyBudget: appUser.getMonthlyBudgets()) {
			if (monthlyBudget.getBudgetCategory().equals(budgetCategory)) {
				throw new ApplicationException("Cannot delete category as it's used in budget:" + monthlyBudget.getId());
			}
		}
		
		for (Payment payment: appUser.getPayments()) {
			if (payment.getBudgetCategory().equals(budgetCategory)) {
				throw new ApplicationException("Cannot delete category as it's used in payment:" + payment.getId());
			}
		}
		
		userService.getCurrentAppUser().getBudgetCategories().remove(budgetCategory);
	}

	public void setEnableToUserBudgetCategory(String budgetCategoryName, Boolean isEnabled) {
		BudgetCategory budgetCategory = getModifiableNonNullBudgetCategoryOrThrow(budgetCategoryName);
		budgetCategory.setEnabled(isEnabled);
	}
	

	public BudgetCategory getUserBudgetCategoryByName(String budgetCategoryName) {
		AppUser appUser = userService.getCurrentAppUser();
		BudgetCategory budgetCategory = appUser.getBudgetCategories().stream().filter(bc -> {
			return bc.getName().equals(budgetCategoryName);
		}).findAny().orElse(null);
		
		return budgetCategory;
	}
	
	public BudgetCategory getBudetCategoryOrThrow(String bcName) {
		BudgetCategory bc = this.getUserBudgetCategoryByName(bcName);
		
		if (bc == null) {
			throw new ApplicationException("No business category with name: " + bcName);
		}
		
		return bc;
	}
	
	private BudgetCategory getModifiableNonNullBudgetCategoryOrThrow(String budgetCategoryName) {
		BudgetCategory budgetCategory = getUserBudgetCategoryByName(budgetCategoryName);
		if (budgetCategory == null) {
			throw new ApplicationException("There is no budget category with the name " + budgetCategoryName);
		}
		
		if (!budgetCategory.isModifiable()) {
			throw new ApplicationException(budgetCategoryName + " category cannot be modified.");
		}
		
		return budgetCategory;
	}

}
