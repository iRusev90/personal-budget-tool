namespace("bg.infa.pbt");

bg.infa.pbt.Application = function() {

	this.run = function() {
		console.log("initing pbt app");

		bg.infa.pbt.public = {};

		bg.infa.pbt.public.restClinet = new bg.infa.pbt.util.RestClient();
		var router = new bg.infa.pbt.util.Router();

		bg.infa.pbt.public.user = ko.observable();
		var layoutVm = new bg.infa.pbt.vm.LayoutVM();
		bg.infa.pbt.public.layoutVm = layoutVm;
		layoutVm.init();

		ko.applyBindings(layoutVm);

		configureRouter(router);
		router.start();

	};


	function configureRouter(router) {

		var vms = bg.infa.pbt.vm;

		var routes = [{
			hash: "#/home",
			view: "views/home-view.html",
			vm: vms.HomeVM
		}, {
			hash: "#/login",
			view: "views/login-view.html",
			vm: vms.LoginVM
		}, {
			hash: "#/profile",
			view: "views/profile-view.html",
			vm: vms.ProfileVM,
		}, {
			hash: "#/budget-categories",
			view: "views/budget-categories-view.html",
			vm: vms.BudgetCategoryVM,
		}, {
			hash: "#/budget",
			view: "views/budget-view.html",
			vm: vms.BudgetVM,
		}];

		router.containerElementId = "content-wrapper";

		router.routes = routes;
	}


};
