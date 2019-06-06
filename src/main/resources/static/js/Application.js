namespace("bg.infa.pbt");

bg.infa.pbt.Application = function() {

	this.run = function() {
		bg.infa.pbt.public = {};
		bg.infa.pbt.public.restClinet = new bg.infa.pbt.util.RestClient();
		console.log("initing pbt app");
	}


};
