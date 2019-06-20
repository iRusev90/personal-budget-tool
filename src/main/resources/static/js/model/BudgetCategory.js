namespace("bg.infa.pbt.model");

bg.infa.pbt.model.BudgetCategory = function(dto) {
	this.isModifiable = dto.modifiable !== undefined ? dto.modifiable : null;
	this.name = dto.name !== undefined ? dto.name : null;
	this.description = dto.description !== undefined ? dto.description : null;
	this.isEnabled = dto.enabled !== undefined ? dto.enabled : null;
};
