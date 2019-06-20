package bg.infa.pbt.budget;

public class MonthlyBudget {
	private static int idSeq = 0;

	private int id;
	private BudgetCategory budgetCategory;
	
	public MonthlyBudget(BudgetCategory budgetCategory, int monthNumber, int allowance) {
		this.id = idSeq++;
		this.budgetCategory = budgetCategory;
		this.monthNumber = monthNumber;
		this.allowance = allowance;
	}

	public BudgetCategory getBudgetCategory() {
		return budgetCategory;
	}

	public void setBudgetCategory(BudgetCategory budgetCategory) {
		this.budgetCategory = budgetCategory;
	}

	public int getId() {
		return id;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	private int monthNumber;
	private int allowance;
}
