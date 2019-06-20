namespace("bg.infa.pbt.vm");

bg.infa.pbt.vm.ProfileVM = function() {
	var self = this;
	var UserProfileUpdateParams = bg.infa.pbt.model.UserProfileUpdateParams;
	let restClient = bg.infa.pbt.public.restClinet;
	let layoutVm = bg.infa.pbt.public.layoutVm;

	this.user = bg.infa.pbt.public.user;
	this.userProfileUpdateParams = new UserProfileUpdateParams(self.user());

	this.init = function() {
		console.log("initing ProfileVM");
	};

	this.updateProfile = function() {
		restClient.put("api/users", self.userProfileUpdateParams.toDto()).done(function() {
			layoutVm.getCurrentUser();
		});
	};

	this.disableUser = function() {
		restClient.put("api/users/disable").
		done(function() {
			layoutVm.logout();
		});
	};
};
