package bg.infa.pbt.controller.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentParam {
	@NotNull
	private Integer amount;
	
	@NotNull
	private Integer monthNumber;
	
	@NotBlank
	private String budgetCategoryName;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(Integer monthNumber) {
		this.monthNumber = monthNumber;
	}

	public String getBudgetCategoryName() {
		return budgetCategoryName;
	}

	public void setBudgetCategoryName(String budgetCategoryName) {
		this.budgetCategoryName = budgetCategoryName;
	}
}
