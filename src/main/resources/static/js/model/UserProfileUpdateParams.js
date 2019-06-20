namespace("bg.infa.pbt.model");

bg.infa.pbt.model.UserProfileUpdateParams = function(user) {
	var self = this;
	this.name = user.name !== undefined ? ko.observable(user.name) : ko.observable(null);
	this.age = user.age !== undefined ? ko.observable(user.age) : ko.observable(null);
	this.gender = user.gender !== undefined ? ko.observable(user.gender) : ko.observable(null);
	this.interests = user.interests !== undefined ? ko.observable(user.interests) : ko.observable(null);


	this.toDto = function() {
		return {
			name: self.name(),
			age: self.age(),
			gender: self.gender(),
			interests: self.interests()
		};
	};

};
