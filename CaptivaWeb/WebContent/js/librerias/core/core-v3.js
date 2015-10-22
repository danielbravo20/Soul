var core = angular.module('core', []);

	core.value("hostname","/CaptivaWeb/"); // NexoFinanciero PrototipoCFv3

	core.factory("ajax", function($http, hostname) {
		return {
			get : function(data,dataAdd,callback){
				var dataAddt = {};
				if(dataAdd && typeof(dataAdd) == "object"){
					dataAddt = dataAdd;
				}
				if(dataAdd && typeof(dataAdd) == "function"){
					callback = dataAdd;
				}
				$http({
					url		: hostname+"gestorController", 
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
			},
			jpo : function(data,callback){
				$http({
					url		: hostname+"gestorController", 
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
			}
		};
	});
	
	core.factory("util", function($http, hostname) {
		return {
			formatoMayus : function(texto){
				return texto.substring(0,1).toUpperCase()+texto.substring(1,texto.length).toLowerCase(); 
			},
			getClases : function(nombre){
				if(nombre){
					var palabras = nombre.split(" ");
					var java = "",proceso = "", bd = "";
					for(var i = 0; i < palabras.length; i++){
						java +=this.formatoMayus(palabras[i]);
						proceso += palabras[i].toLowerCase();
						bd += palabras[i].toUpperCase()+"_";
					}
					return [java,proceso,bd.substring(0,bd.length-1)];
				} else {
					return ["","",""];
				}
			},
			jpoCargar : function(objetoPadre, objetoDatos, Identificador){
				for(var e in objetoDatos){
					if(e != "$$hashKey"){
						objetoPadre[Identificador+"_"+e] = objetoDatos[e];
					}
				}
			},
			getObjeto : function(objeto, filtro, enviarTodo){
				if(typeof(enviarTodo)=="undefined"){
					enviarTodo = false;
				}
				var misObjetos = null;
				for(var i in objeto){
					var v_totales = 0;
					var v_conformes = 0;
					for(var e in filtro){
						v_totales++;
						if(objeto[i][e] && objeto[i][e] == filtro[e]){
							v_conformes++;
						}
					}
					if(v_totales == v_conformes){
						if(misObjetos == null){
							misObjetos = [];
						}
						misObjetos.push(objeto[i]);
					}
				}
				if(enviarTodo){
					return misObjetos;
				} else {
					if(misObjetos != null){
						return misObjetos[0];
					}
				}
			}
		};
	});