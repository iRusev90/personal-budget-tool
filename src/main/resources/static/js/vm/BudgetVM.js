namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.BudgetVM = function() {
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
	this.allowance = ko.observable();
	this.budgetId = ko.observable();

	this.init = function() {
		console.log("initing BudgetVM");
	};


	this.createBudget = function () {
	   restClient.post("api/budget", JSON.stringify(getRequestParams())).done(function () {
			layoutVm.getCurrentUser();
	   }); 
	};

	this.editBudget = function () {
	   restClient.put("api/budget/" + self.budgetId(), getRequestParams()).done(function () {
			layoutVm.getCurrentUser();
	   }); 
	};

	this.cloneBudget = function (budgetId) {
	   restClient.post("api/budget/" + budgetId + "/clone").done(function () {
			layoutVm.getCurrentUser();
	   }); 
	};

	function getRequestParams() {
	    return {
			budgetId: self.budgetId(),
			monthId: self.monthInput(),
			budgetCategoryName: self.selectedBudgetCategory() ? self.selectedBudgetCategory().name : null,
			allowance: self.allowance()
		};
	}

};
