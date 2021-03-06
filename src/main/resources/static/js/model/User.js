namespace("bg.infa.pbt.model");

bg.infa.pbt.model.User = function(userDto) {
	this.name = userDto.name !== undefined ? userDto.name : null;
	this.username = userDto.username !== undefined ? userDto.username : null;
	this.age = userDto.age !== undefined ? userDto.age : null;
	this.interests = userDto.interests !== undefined ? userDto.interests : null;
	this.gender = userDto.gender !== undefined ? userDto.gender : null;
	this.budgetCategories = ko.utils.arrayMap(userDto.budgetCategories, function(cDto) {
		return new bg.infa.pbt.model.BudgetCategory(cDto);
	});

	this.budgets = ko.utils.arrayMap(userDto.budgets, function(bDto) {
		return new bg.infa.pbt.model.Budget(bDto);
	});
	this.payments = ko.utils.arrayMap(userDto.payments, function(pDto) {
		return new bg.infa.pbt.model.Payment(pDto);
	});
};
