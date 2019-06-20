namespace("bg.infa.pbt.model");

bg.infa.pbt.model.User = function(userDto) {
	this.name = userDto.name !== undefined ? userDto.name : null;
	this.username = userDto.username !== undefined ? userDto.username : null;
	this.age = userDto.age !== undefined ? userDto.age : null;
	this.interests = userDto.interests !== undefined ? userDto.interests : null;
	this.gender = userDto.gender !== undefined ? userDto.gender : null;
};
