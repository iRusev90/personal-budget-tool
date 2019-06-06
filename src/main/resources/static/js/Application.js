namespace("bg.infa.pbt");

bg.infa.pbt.Application = function() {

	this.run = function() {
		console.log("initing pbt app");

		bg.infa.pbt.public = {};

		bg.infa.pbt.public.restClinet = new bg.infa.pbt.util.RestClient();
		var router = new bg.infa.pbt.util.Router();

		var layoutVm = new bg.infa.pbt.vm.LayoutVM();
		layoutVm.init();
		bg.infa.pbt.public.user = ko.observable();

		ko.applyBindings(layoutVm);

		configureRouter(router);
		router.start();

	};


	function configureRouter(router) {

		var vms = bg.infa.pbt.vm;

		var routes = [{
			hash: "#/login",
			view: "views/login-view.html",
			vm: vms.LoginVM
		}, {
			hash: "#/home",
			view: "views/home-view.html",
			vm: vms.HomeVM
		}];

		router.containerElementId = "content-wrapper";

		router.routes = routes;
	}


};
