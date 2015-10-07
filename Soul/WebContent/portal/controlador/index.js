var portal = angular.module('portal', ['core','ui.bootstrap','drahak.hotkeys']);

	portal.controller("principal", function($scope, $http, $timeout, datepickerPopupConfig, hostname, ajax, unidadNegocio, texto, cargador, $modal) {
		
		$scope.seccion = "Modulos";
		
		$scope.$on('templateError', function(e, data) {
			$scope.templateError = true;
			$scope.templateErrorUrl = data.url;
		});
		
		datepickerPopupConfig.closeText = 'Cerrar';
		datepickerPopupConfig.currentText = 'Hoy';
		datepickerPopupConfig.clearText = 'Limpiar';
		
		$scope.fecha = {
			formato : "dd/MM/yyyy",
			abierto : {},
			abrir : function($event,tipo) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.fecha.abierto[tipo] = true;
			}
		};
		
		$scope.anho = new Date().getFullYear();
		$scope.haCargado = false;
		
		$scope.data = {};
		$scope.texto = {
			url : {
				hostname : hostname,
				plantillaPortal : hostname+"/portal/plantillas/",
				desconectar : hostname+texto.url.desconectar
			}
		};

		var getCU = function(){
			ajax.get({
				url : "portal",
				data : {accion:"cu"},
				getRespuesta : function(cu){
					console.log(cu);
					
					/*DUMMY*/
					var iconoModulo = {
						1 : "glyphicon glyphicon-check",
						2 : "glyphicon glyphicon-play",
						3 : "glyphicon glyphicon-flag",
						5 : "glyphicon glyphicon-cog",
						4 : "glyphicon glyphicon-print"
					};
					for(var i in cu.modulos){
						cu.modulos[i].icono = iconoModulo[cu.modulos[i].codigoModulo];
					}
					
					$scope.data = angular.extend({},$scope.data,cu);
				}/*,
				mostrarCargador : true*/
			});
		};
		
		
		$scope.tiempo = {
				contador : 0,
			segundo : 0,
			maximo : 30*60,
			mensaje : "",
			progreso : function(){
		    	if($scope.tiempo.segundo<$scope.tiempo.maximo){
		    		$scope.tiempo.contador = Math.floor(($scope.tiempo.segundo/$scope.tiempo.maximo)*100);
		    		$scope.tiempo.getMensaje();
		    		$scope.tiempo.segundo++;
		    		$timeout( function(){ $scope.tiempo.progreso(); }, 1000);
		    	} else {
		    		location.href = $scope.texto.url.desconectar;
		    	}
			},
			getMensaje : function(){
				var segundoActual = $scope.tiempo.maximo-$scope.tiempo.segundo;
				var segundos = segundoActual%60;
				var minutos = (segundoActual-segundos)/60;
				$scope.tiempo.mensaje = minutos+":"+(segundos<10?"0"+segundos:segundos);
			}
		};
	   

		getCU();
		
		$scope.getControlador = function(nombreControlador){
			return angular.element(document.querySelector('[ng-controller='+nombreControlador+']')).scope();
		}
		
	});
	
	portal.run(function(pagerConfig, paginationConfig){
	   pagerConfig.previousText = '<< Anterior';
	   pagerConfig.nextText = 'Siguiente >>';
	   
	   paginationConfig.previousText = '<< Anterior';
	   paginationConfig.nextText = 'Siguiente >>';
	   paginationConfig.firstText = 'Primero';
	   paginationConfig.lastText = 'Ultimo';
	   
	});
	
	portal.config(function($controllerProvider, $httpProvider) {
		portal.registerCtrl = $controllerProvider.register;
		$httpProvider.interceptors.push('templateInterceptor');
	});
	
	// register the interceptor as a service
	portal.factory('templateInterceptor', function($q, hostname, texto) {
	  return {
		'response': function(response) {
		  if(response.data=="NO_ACCESO" || (response.data && typeof(response.data) == "string" && response.data.indexOf("ng-app=\"acceder\" ng-controller=\"Logeo\"")!=-1)){
			  location.href = hostname+texto.url.desconectar;
		  }
	      return response;
	    },
	    'responseError': function(rejection) {
	         rejection.data = '<div class="well" style="text-align: center;"><img src="../imagenes/error.png" width="80" height="80"><br><h3 style="color: #B80000;">Ha ocurrido un error al intentar acceder al enlace, vuelva a conectarse para probar o contáctese con el administrador</h3></div>';
	         return rejection;
	    }
	  };
	});

	portal.directive('slideToggle', function() {  
		return {
			restrict: 'A',      
			scope:{
				isOpen: "=slideToggle" // 'data-slide-toggle' in our html
			},  
			link: function(scope, element, attr) {
				var slideDuration = parseInt(attr.slideToggleDuration, 10) || 200;      
				// Watch for when the value bound to isOpen changes
				// When it changes trigger a slideToggle
				scope.$watch('isOpen', function(newIsOpenVal, oldIsOpenVal){
					if(newIsOpenVal !== oldIsOpenVal){ 
						element.stop().slideToggle(slideDuration);
					}
				});
			}
		};  
	});
	
	portal.directive('showErrors', function($timeout) {
		return {
		  restrict: 'A',
		  require:  '^form',
		  link: function (scope, el, attrs, formCtrl) {
			// find the text box element, which has the 'name' attribute
			var inputEl   = el[0].querySelector("[name]");
			// convert the native text box element to an angular element
			var inputNgEl = angular.element(inputEl);
			// get the name on the text box so we know the property to check
			// on the form controller
			var inputName = inputNgEl.attr('name');
			// only apply the has-error class after the user leaves the text box
			inputNgEl.bind('blur', function() {
			  el.toggleClass('has-error', formCtrl[inputName].$invalid);
			});
			
			scope.$on('show-errors-check-validity', function() {
			  el.toggleClass('has-error', formCtrl[inputName].$invalid);
			});
			
			scope.$on('show-errors-reset', function() {
			  $timeout(function() {
				el.removeClass('has-error');
			  }, 0, false);
			});
		  }
		};
	});
	
	
	
	portal.controller('Modal_buscarUsuario', function ($scope, $modalInstance, ajax, cargador, config) {

		var dataPlantilla = {
			accion 			: "buscarUsuarioVMM",
			rnd				: Math.random()
		};
		
		$scope.nombreBoton = config.nombreBoton;
		$scope.usuarios = [];
		$scope.usuarioSeleccionado = -1;
		
		$scope.instanciar = function(){
			$scope.usuarios = [];
			$scope.usuarioSeleccionado = -1;
			$scope.data = angular.copy(dataPlantilla);
		};
			
		$scope.data = {
			accion 			: "buscarUsuarioVMM",
			rnd				: Math.random()
		};
		
		$scope.buscar = function(){
			ajax.get({
				url: "portal",
				data: $scope.data,
				getRespuesta : function(respuesta){
					$scope.usuarios = respuesta;
				},
				mostrarCargador : true
			});
		};
				
		$scope.seleccionar = function(index){
			$scope.usuarioSeleccionado = index;
		};
		
		$scope.cancelar = function(){
			$modalInstance.dismiss('cancel');
		};
		
		$scope.ejecutar = function(){
			config.ejecutarAccion($scope.usuarios[$scope.usuarioSeleccionado],function(){
				$modalInstance.close();
			});
		};
		
	});
	
	portal.controller('Modal_cancelarTarea', function ($scope, $modalInstance, config) {

		$scope.motivos = config.motivos;
		
		$scope.data = {};
		
		$scope.cancelar = function(){
			$scope.$broadcast('show-errors-check-validity');
			if($scope.data['cancelacion.tipo'] && $scope.data['cancelacion.tipo'].length>0){
				config.cancelarTarea($scope.data,function(){
					$modalInstance.close();
				});
			}
		};
		
		$scope.salir = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});
	
	portal.controller('Modal_rechazarTarea', function ($scope, $modalInstance, config) {

		$scope.motivos = config.motivos;
		
		$scope.data = {};
		
		$scope.rechazar = function(){
			$scope.$broadcast('show-errors-check-validity');
			if($scope.data['rechazo.tipo'] && $scope.data['rechazo.tipo'].length>0){
				config.rechazarTarea($scope.data,function(){
					$modalInstance.close();
				});
			}
		};
		
		$scope.salir = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});
	
	portal.controller('Modal_agregarObservacion', function ($scope, $modalInstance, config) {

		$scope.data = {};
		$scope.motivos = config.motivos;
		
		$scope.agregar = function(){
			$scope.$broadcast('show-errors-check-validity');
			if($scope.data.codigoTipoObservacion && $scope.data.codigoTipoObservacion.length>0){
				config.agregarObservacion($scope.data,function(){
					$modalInstance.close();
				});
			}
		};
		
		$scope.cancelar = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});
	
	portal.controller('Modal_agregarDocumentoAdicional', function ($scope, $modalInstance, $filter, config) {

		$scope.data = {};
		$scope.listaAdicionales = config.listaAdicionales;
		
		$scope.agregar = function(){
			$scope.$broadcast('show-errors-check-validity');
			if($scope.data.codigoTipoDocumento && $scope.data.codigoTipoDocumento.length>0){
				config.agregarAdicional($scope.data,function(){
					$modalInstance.close();
				});
			}
		};
		
		$scope.cancelar = function(){
			$modalInstance.dismiss('cancel');
		};
		
		$scope.asignarTipoAdjunto = function(){
			var com = $scope.data.comentario;
			$scope.data = angular.copy($filter('filter')($scope.listaAdicionales, {codigoTipoDocumento: $scope.codigoTipoDocumento}))[0];
			$scope.data.adjunto = {};
			$("#incTareaAdjAdiAdjunto").val("");
			$scope.data.comentario = com;
		};
		
	});
	
	portal.controller('Modal_confirmarEnvio', function ($scope, $modalInstance, config) {

		$scope.confirmar = function(){
			config.confirmarEnvio($scope.comentarioHistorial);
			$modalInstance.close();
		};
		
		$scope.cancelar = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});
	
	portal.filter('truncar', function () {
	    return function (text, length) {
	    	if(typeof text === 'undefined' || isNaN(length)){
	    		text = "";
	    		length = 0;
	    		return "";
	    	}
	        if (text.length <= length) {
	            return text;
	        }else{
	            return String(text).substring(0, length);
	        }
	    };
	});
	
var queryString = function() {
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = pair[1];
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], pair[1]];
            query_string[pair[0]] = arr;
        } else {
            query_string[pair[0]].push(pair[1]);
        }
    }
    return query_string;
};

var parametro = queryString();