namespace("bg.infa.pbt.util");

bg.infa.pbt.util.RestClient = function() {
	let beforeExecutionInterceptors = [];
	let afterExecutionInterceptors = [];

	this.addBeforeExecutionInterceptor = function(beforeExecutionInterceptor) {
		beforeExecutionInterceptors.push(beforeExecutionInterceptor);
	};

	this.addAfterExecutionInterceptor = function(afterExecutionInterceptor) {
		afterExecutionInterceptors.push(afterExecutionInterceptor);
	}

	this.get = function(url) {
		return this.execute({
			method: "GET",
			url: url
		});
	};

	this.post = function(url, data, options) {
		return this.execute($.extend({}, {
			method: "POST",
			url: url,
			data: data,
			contentType: "application/json"
		}, options));
	};

	this.put = function(url, data, options) {
		return this.execute($.extend({}, {
			method: "PUT",
			url: url,
			data: JSON.stringify(data),
			contentType: "application/json"
		}, options));
	}

	this.execute = function(ajaxOptions) {
		let deferred = $.Deferred();

		beforeExecutionInterceptors.forEach(interceptor => {
			interceptor(ajaxOptions);
		});

		$.ajax(ajaxOptions).done(function(resp) {
			deferred.resolve(resp);
		}).fail(function(resp) {
			deferred.reject(resp);
		});

		afterExecutionInterceptors.forEach(interceptor => {
			interceptor(deferred);
		});

		return deferred;
	}
};
