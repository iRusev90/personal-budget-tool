package bg.infa.pbt.controller.param;

import javax.validation.constraints.NotBlank;

public class BudgetCategoryParams {
	@NotBlank
	private String name;
	@NotBlank
	private String description;

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
}
