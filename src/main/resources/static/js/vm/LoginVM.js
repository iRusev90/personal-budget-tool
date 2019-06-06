namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.LoginVM = function() {

	this.name = ko.observable();
	this.password = ko.observable();

	this.init = function() {
		console.log("initing LoginVM");
	};


	this.login = function () {
	    console.log("logging in as " + this.name() + " " + this.password());
	};

	this.register = function () {
	    console.log("registering");
	};

};
