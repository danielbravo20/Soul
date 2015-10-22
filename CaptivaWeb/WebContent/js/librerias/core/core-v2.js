var coreVar = {
	urls : {
		hostname : "/CaptivaWeb/"
	}
};

var Core = {};

	Core.getQueryString = function(){
		var query_string = {};
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i=0;i<vars.length;i++) {
			var pair = vars[i].split("=");
			if (typeof query_string[pair[0]] === "undefined") {
				query_string[pair[0]] = pair[1];
			} else if (typeof query_string[pair[0]] === "string") {
			var arr = [ query_string[pair[0]], pair[1] ];
			query_string[pair[0]] = arr;
			} else {
				query_string[pair[0]].push(pair[1]);
			}
		}
		return query_string;
	};
	
	Core.ajax = function($http,data,dataAdd,callback){
		var dataAddt = {};
		if(dataAdd && typeof(dataAdd) == "object"){
			dataAddt = dataAdd;
		}
		if(dataAdd && typeof(dataAdd) == "function"){
			callback = dataAdd;
		}
		$http({
			url		: coreVar.urls.hostname+"gestorController", 
			method	: "GET",
			params	: angular.extend({}, data, dataAddt)
		}).success(function(response) {
			if(response.resultado){
				if(callback){
					callback(response.objeto);
				}
			} else {
				alert(response.mensaje);
			}
		});
	};
	
	Core.jpo = function($http,data,callback){
		$http({
			url		: coreVar.urls.hostname+"gestorController", 
			method	: "GET",
			params	: data
		}).success(function(response) {
			if(response.resultado){
				if(callback){
					callback(response.objeto);
				}
			} else {
				alert(response.mensaje);
			}
		});
	};
	