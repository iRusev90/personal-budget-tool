namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.PaymentVM = function() {

	var self = this;
	var layoutVm = bg.infa.pbt.public.layoutVm;
	var restClient = bg.infa.pbt.public.restClinet;

	this.user = bg.infa.pbt.public.user;

	this.budgetCategories = ko.pureComputed(function () {
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

	this.createPayment = function () {
	   restClient.post("api/payment/", JSON.stringify(getRequestParams())).done(function () {
			layoutVm.getCurrentUser();
	   }); 
	};

	this.editPayment = function () {
	   restClient.put("api/payment/" + self.paymentId(), getRequestParams()).done(function () {
			layoutVm.getCurrentUser();
	   }); 
	};

	this.deletePayment = function (paymentId) {
	   restClient.delete("api/payment/" + paymentId).done(function () {
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
