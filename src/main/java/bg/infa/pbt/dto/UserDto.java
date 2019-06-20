package bg.infa.pbt.dto;

import java.util.List;

import bg.infa.pbt.budget.BudgetCategory;
import bg.infa.pbt.budget.MonthlyBudget;
import bg.infa.pbt.budget.Payment;

public class UserDto {
	private String username;
	private String name;
	private String age;
	private String interests;
	private String gender;
	private List<BudgetCategory> budgetCategories;
	private List<MonthlyBudget> budgets;
	private List<Payment> payments;
	
	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<MonthlyBudget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<MonthlyBudget> budgets) {
		this.budgets = budgets;
	}

	public List<BudgetCategory> getBudgetCategories() {
		return budgetCategories;
	}

	public void setBudgetCategories(List<BudgetCategory> budgetCategories) {
		this.budgetCategories = budgetCategories;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
