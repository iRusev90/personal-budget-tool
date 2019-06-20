namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.BudgetCategoryVM = function() {

	var self = this;
	var layoutVm = bg.infa.pbt.public.layoutVm;
	var restClient = bg.infa.pbt.public.restClinet;

	this.user = bg.infa.pbt.public.user;
	this.inputBcName = ko.observable();
	this.inputBcDescription = ko.observable();

	this.init = function() {
		console.log("initing BudgetCategoryVM");
	};

	this.switchEnabled = function(bcName, isEnabled) {
		restClient.put("api/category/" + bcName + "/enable", {isTrue: !isEnabled}).
			done(function () {
				layoutVm.getCurrentUser();
			});
	};

	this.addNewBc = function() {
		restClient.post("api/category",JSON.stringify(getRequestDto())).done(function() {
			layoutVm.getCurrentUser();
		});
	};

	this.updateBc = function() {
		restClient.put("api/category", getRequestDto()).done(function() {
			layoutVm.getCurrentUser();
		});
	};

	this.deleteBc = function () {
	    restClient.delete("api/category/" + self.inputBcName()).done(function () {
			layoutVm.getCurrentUser();
	    });
	}

	function getRequestDto() {
		return {
			name: self.inputBcName(),
			description: self.inputBcDescription()
		};
	}

};
