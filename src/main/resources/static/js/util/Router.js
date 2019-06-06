namespace("bg.infa.pbt.util");

bg.infa.pbt.util.Router = function() {
	var self = this;

	var containerElement = null;

	this.containerElementId = "";
	this.routes = [];

	this.start = function() {
		containerElement = document.getElementById(self.containerElementId);
		window.onhashchange = onHashChangeRouteFunc;
		applyRoute(self.routes[0]);
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
			alert("Unknown address");
			return;
		}

		applyRoute(newRoute);

	}

	function applyRoute(route) {
		console.log("applying route: ");
		console.log(route);
		var vm = new route.vm();
		$.get(route.view).then(function (viewContent) {
			ko.cleanNode(containerElement);
			containerElement.innerHTML = viewContent;
			ko.applyBindings(vm, containerElement);
		});
	}

};
