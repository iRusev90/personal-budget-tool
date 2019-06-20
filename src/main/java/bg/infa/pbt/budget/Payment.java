package bg.infa.pbt.budget;

public class Payment {
	private static int seqId;

	private int id;
	private BudgetCategory budgetCategory;
	private int amount;
	private int monthNumber;

	public Payment(BudgetCategory budgetCategory, int amount, int monthNumber) {
		this.id = seqId++;
		this.budgetCategory = budgetCategory;
		this.amount = amount;
		this.monthNumber = monthNumber;
	}

	public int getId() {
		return id;
	}

	public BudgetCategory getBudgetCategory() {
		return budgetCategory;
	}

	public void setBudgetCategory(BudgetCategory budgetCategory) {
		this.budgetCategory = budgetCategory;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}
}
