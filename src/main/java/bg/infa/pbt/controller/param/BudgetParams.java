package bg.infa.pbt.controller.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BudgetParams {
	
	private Integer budgetId;

	@Max(12)
	@NotNull
	private Integer monthId;

	@NotBlank
	private String budgetCategoryName;

	@Min(1)
	@NotNull
	private Integer allowance;

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public String getBudgetCategoryName() {
		return budgetCategoryName;
	}

	public void setBudgetCategoryName(String budgetCategoryName) {
		this.budgetCategoryName = budgetCategoryName;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}
	
	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
}
