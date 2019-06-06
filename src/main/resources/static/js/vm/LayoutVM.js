namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.LayoutVM = function() {
	var self = this;
	let restClient = bg.infa.pbt.public.restClinet;
	this.user = bg.infa.pbt.public.user; 

	this.init = function() {
		console.log("initing LayoutVM");

		getCurrentUser().done(function () {
	 		location.hash = "#/home";   
		}).fail(function () {
			location.hash = "#/login";   
		});
	};

	this.logout = function() {
		restClient.post("api/logout").done(() => {
			self.user(null);	
			location.hash = "#/login";   
		}).fail(() => {
			alert("Could not logout");	
		});
	};

	this.onLogin = function () {
	 	getCurrentUser().done(function () {
	 		location.hash = "#/home";   
	 	});   
	};

	function getCurrentUser() {
		return restClient.get("api/users/current").done(usrDto => {
			self.user(new bg.infa.pbt.model.User(usrDto));
		});
	}

};
