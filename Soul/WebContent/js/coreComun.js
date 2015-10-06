var coreComun = angular.module('coreComun', []);
	
	coreComun.value("hostname","/Soul");
	
	coreComun.directive('cargasDe',function(cargador, hostname) {
		return {
			restrict: 'E',
			controller : function($scope){
			},
			link: function($scope) {
				cargador.instanciar();
				$scope.haCargado = true;
				$scope.cargador = cargador;
			},
		    templateUrl: hostname+'/web/cargador.html'
	  	};
	});
	
	coreComun.factory("cargador", function($timeout) {
		debugger;
		var config = {
			esVisible : false,
			tipoMensaje : 1,
			mensaje : "",
			mostrarMensajeError : false
		};
		
		var autocentrar = function(){
			$(".CARGADOR_MODAL").css("top",Math.ceil(($(window).height()-($(".CARGADOR_MODAL").height()+22))/2)+"px");
			$(".CARGADOR_MODAL").css("left",Math.ceil(($(window).width()-($(".CARGADOR_MODAL").width()+22))/2)+"px");
		};
		
		return {
			data : config,
			mostrarAcceder : function(callback){
				this.mostrar(callback);
				this.data.tipoMensaje = 2;
			},
			mostrar : function(callback){
				this.data.tipoMensaje = 1; this.data.mostrarMensajeError = false;
				if(config.esVisible){
					if(typeof(callback)!="undefined"){
						callback();
					}
				} else {
					config.esVisible = true;
					$(".CARGADOR").fadeIn("fast",function(){
						if(typeof(callback)!="undefined"){
							callback();
						}
					});
				}
			},
			mostrarMensaje : function(mensaje,callback){
				this.mostrar(callback);
				this.data.tipoMensaje = 0; 
				this.data.mensaje = mensaje;
			},
			mostrarError : function(mensaje,callback){
				this.mostrar(callback);
				this.data.tipoMensaje = 3;
				this.data.mensajeError = mensaje;
			},
			ocultar : function(callback){
				if(!config.esVisible){
					if(typeof(callback)!="undefined"){
						callback();
					}
				} else {
					$(".CARGADOR").fadeOut("fast",function(){
						config.esVisible = false;
						if(typeof(callback)!="undefined"){
							callback();
						}
					});
				}
			},
			instanciar : function(){
				$(window).resize(function() {
					autocentrar();
				});
				autocentrar();
				$(".CARGADOR").hide();
			}
		};
	});