namespace("bg.infa.pbt");

bg.infa.pbt.Application = function() {

	this.run = function() {
		bg.infa.pbt.public = {};

		bg.infa.pbt.public.restClinet = new bg.infa.pbt.util.RestClient();
		var router = new bg.infa.pbt.util.Router();

		var layoutVm = new bg.infa.pbt.vm.LayoutVM();
		layoutVm.init();

		ko.applyBindings(layoutVm);

		configureRouter(router);
		router.start();
		console.log("initing pbt app");
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
