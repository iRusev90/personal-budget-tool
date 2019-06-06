namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.LoginVM = function() {
	var self = this;
	let restClient = bg.infa.pbt.public.restClinet;

	this.name = ko.observable();
	this.password = ko.observable();

	this.init = function() {
		console.log("initing LoginVM");
	};


	this.register = function() {
		let data = {
			name: self.name(),
			password: self.password()

		};
		restClient.post("api/users", JSON.stringify(data),
			{
				contentType: "application/json"	
			}).done(() => {
			alert("Successfully registered");	
		}).fail(() => {
			alert("could not register");	
		});
	};

	this.login = function() {
		let url = "api/login?name=" + encodeURIComponent(self.name()) + "&password=" + encodeURIComponent(self.password());
		restClient.post(url).done(() => {
			bg.infa.pbt.public.layoutVm.onLogin();
		}).fail(() => {
			alert("Could not login");	
		});
	};

};
