namespace("bg.infa.pbt.model");

bg.infa.pbt.model.User = function(userDto) {
	this.name = userDto.name !== undefined ? userDto.name : null;
};
