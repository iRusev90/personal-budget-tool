namespace("bg.infa.pbt.model");

bg.infa.pbt.model.Payment = function(dto) {
	this.id = dto.id !== undefined ? dto.id : null;
	this.budgetCategory = dto.budgetCategory !== undefined ? new bg.infa.pbt.model.BudgetCategory(dto.budgetCategory) : null;
	this.monthNumber = dto.monthNumber !== undefined ? dto.monthNumber : null;
	this.amount = dto.amount !== undefined ? dto.amount : null;
};
