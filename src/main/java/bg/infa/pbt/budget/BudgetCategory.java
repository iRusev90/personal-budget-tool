package bg.infa.pbt.budget;

import java.util.Arrays;
import java.util.List;

public class BudgetCategory {
	private boolean isModifiable;
	private String name;
	private String description;
	private boolean isEnabled;

	public boolean isModifiable() {
		return isModifiable;
	}

	public void setModifiable(boolean isModifiable) {
		this.isModifiable = isModifiable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public static List<BudgetCategory> getDefaultBudgetCategories() {
		BudgetCategory cooking = new BudgetCategory();
		cooking.setEnabled(true);
		cooking.setDescription("Expenses related to household cooking");
		cooking.setModifiable(false);
		cooking.setName("cooking");
		
		BudgetCategory bills = new BudgetCategory();
		bills.setEnabled(true);
		bills.setDescription("Expenses related to household bills");
		bills.setModifiable(false);
		bills.setName("bills");
		
		return Arrays.asList(cooking, bills);
	}
}
