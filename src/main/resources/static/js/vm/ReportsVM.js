namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.ReportsVM = function() {
	var self = this;
	var layoutVm = bg.infa.pbt.public.layoutVm;
	var restClient = bg.infa.pbt.public.restClinet;

	this.user = bg.infa.pbt.public.user;

	this.budgetCategories = ko.pureComputed(function() {
		var user = self.user();
		if (user) {
			return user.budgetCategories;
		} else {
			return [];
		}
	});

	this.selectedBudgetCategory = ko.observable();
	this.monthInput = ko.observable(null);

	this.filteredPayments = ko.pureComputed(function() {
		var payments = self.user().payments;
		var category = self.selectedBudgetCategory();
		var monthInput = self.monthInput();

		if (category != null) {
			var filtered = [];

			for (var i = 0; i < payments.length; i++) {
				var payment = payments[i];
				if (payment.budgetCategory.name ==
					category.name) {
					filtered.push(payment);
				}
			}

			payments = filtered;
		}

		if (monthInput != null && monthInput.trim() != "") {

			var filtered = [];

			for (var i = 0; i < payments.length; i++) {
				var payment = payments[i];
				if (payment.monthNumber ==
					monthInput) {
					filtered.push(payment);
				}
			}

			payments = filtered;
		}

		return payments;
	});

	this.init = function() {
		console.log("initing ReportsVM");
	};

	this.plannedAndSpent = ko.pureComputed(function() {
		var result = [];
		var user = self.user();
		if (!user) {
			return [];
		}
		var budgets = user.budgets;
		var payments = user.payments;

		for (var i = 0; i < budgets.length; i++) {
			var budget = budgets[i];
			var spent = 0;

			for (var j = 0; j < payments.length; j++) {
				var payment = payments[j];
				if (payment.budgetCategory.name == budget.budgetCategory.name &&
					payment.monthNumber == budget.monthNumber) {
					spent += payment.amount;
				}
			}

			budget.spent = spent;
			result.push(budget);
		}

		return result;

	});

};
