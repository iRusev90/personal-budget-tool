namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.PaymentVM = function() {

	var self = this;
	var layoutVm = bg.infa.pbt.public.layoutVm;
	var restClient = bg.infa.pbt.public.restClinet;

	this.user = bg.infa.pbt.public.user;

	this.budgetCategories = ko.pureComputed(function() {
		var bcs = self.user().budgetCategories;
		var enabledBcs = [];
		for (var i = 0; i < bcs.length; i++) {
			var bc = bcs[i];
			if (bc.isEnabled) {
				enabledBcs.push(bc);
			}
		}

		return enabledBcs;
	});

	this.selectedBudgetCategory = ko.observable();
	this.monthInput = ko.observable();
	this.amount = ko.observable();
	this.paymentId = ko.observable();

	this.init = function() {
		console.log("initing PaymentVM");
	};

	this.createPayment = function() {
		var requestDto = getRequestParams();
		restClient.post("api/payment/", JSON.stringify(requestDto)).done(function() {
			layoutVm.getCurrentUser().done(function() {
				var allowanceForCategoryForMonth = 0;
				for (var i = 0; i < self.user().budgets.length; i++) {
					var budget = self.user().budgets[i];
					if (budget.budgetCategory.name == requestDto.budgetCategoryName &&
						budget.monthNumber == requestDto.monthNumber) {
						allowanceForCategoryForMonth = budget.allowance;
						break;
					}
				}

				if (allowanceForCategoryForMonth > 0) {
					var spentForCategoryForMonth = 0;
					for (var i = 0; i < self.user().payments.length; i++) {
						var payment = self.user().payments[i];
						if (payment.budgetCategory.name == requestDto.budgetCategoryName &&
							payment.monthNumber == requestDto.monthNumber) {
							spentForCategoryForMonth += payment.amount;
						}
					}

					if (spentForCategoryForMonth >= (allowanceForCategoryForMonth / 4)) {
						alert("Planned spending was " + allowanceForCategoryForMonth +
							" but current spending is: " + spentForCategoryForMonth +
							" for month: " + requestDto.monthNumber + " for category " +
							requestDto.budgetCategoryName);
					}
				}
			});
		});
	};

	this.editPayment = function() {
		restClient.put("api/payment/" + self.paymentId(), getRequestParams()).done(function() {
			layoutVm.getCurrentUser();
		});
	};

	this.deletePayment = function(paymentId) {
		restClient.delete("api/payment/" + paymentId).done(function() {
			layoutVm.getCurrentUser();
		});
	};

	function getRequestParams() {
		return {
			paymentId: self.paymentId(),
			monthNumber: self.monthInput(),
			budgetCategoryName: self.selectedBudgetCategory() ? self.selectedBudgetCategory().name : null,
			amount: self.amount()
		};
	}

};
