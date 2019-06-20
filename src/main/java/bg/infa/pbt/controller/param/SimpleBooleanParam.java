package bg.infa.pbt.controller.param;

import javax.validation.constraints.NotNull;

public class SimpleBooleanParam {
	@NotNull
	private Boolean isTrue;

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}
