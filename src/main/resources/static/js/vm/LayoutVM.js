namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.LayoutVM = function() {
	var self = this;
	let restClient = bg.infa.pbt.public.restClinet;
	bg.infa.pbt.public.layoutVm = this;
	this.user = bg.infa.pbt.public.user; 

	this.init = function() {
		console.log("initing LayoutVM");

		self.getCurrentUser().done(function () {
	 		location.hash = "#/home";   
		}).fail(function () {
			location.hash = "#/login";   
		});
	};

	this.logout = function() {
		restClient.post("api/logout").done(() => {
			self.user(null);	
			location.hash = "#/login";   
		});
	};

	this.onLogin = function () {
	 	self.getCurrentUser().done(function () {
	 		location.hash = "#/home";   
	 	});   
	};

	this.getCurrentUser = function()  {
		return restClient.get("api/users/current").done(usrDto => {
			self.user(new bg.infa.pbt.model.User(usrDto));
		});
	};

};
