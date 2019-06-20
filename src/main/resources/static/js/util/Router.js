namespace("bg.infa.pbt.util");

bg.infa.pbt.util.Router = function() {
	var self = this;

	var containerElement = null;
	var oldVm = null;

	this.containerElementId = "";
	this.routes = [];
	

	this.start = function() {
		containerElement = document.getElementById(self.containerElementId);
		window.onhashchange = onHashChangeRouteFunc;
		onHashChangeRouteFunc();
	};

	function onHashChangeRouteFunc() {
		var newRoute = null;
		var newHash = location.hash;
		for (var i = 0; i < self.routes.length; i++) {
			var route = self.routes[i];
			if (route.hash == newHash) {
				newRoute = route;
				break
			}
		}

		if (newRoute == null) {
			newRoute = self.routes[0];
		}

		applyRoute(newRoute);

	}

	function applyRoute(route) {
		console.log("applying route: ");
		console.log(route);
		var vm = new route.vm();
		if ($.isFunction(vm["init"])) {
			vm.init();
		}
		$.get(route.view).then(function (viewContent) {
			if (oldVm !== null && $.isFunction(oldVm["dispose"])) {
				oldVm.dispose();
			}
			ko.cleanNode(containerElement);
			containerElement.innerHTML = viewContent;
			ko.applyBindings(vm, containerElement);
			oldVm = vm;
		});
	}

};
