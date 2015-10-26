var acceder = angular.module('acceder', ['core','ui.bootstrap']);

	acceder.controller('logeo', function ($scope, $http, ajax) {
		
		// SCOPE
		$scope.data = {};
		
		ajax.cargarConfiguracion(function(respuesta){
			$scope.data.config = respuesta.config;
			document.title = respuesta.config.proyecto + " v"+respuesta.config.version;
		});
		
		$scope.frm = {
			USU_W_COD_USUARIO : "",
			USU_W_CLAVE : ""
		};
		
		$scope.anho = new Date().getFullYear();
		
		$scope.mensaje = "";
		
		$scope.validarAcceso = function(){
			if($("#usuario").val().length==0){
				$("#usuario").focus();
				return false;
			}
			
			if($("#clave").val().length==0){
				$("#clave").focus();
				return false;
			}
			return true;
		};
		
		$scope.conectar = function(){
			if($scope.validarAcceso){
				var dataReq = { paquete: "seguridad", clase: "Acceso", metodo: "acceder"};
				ajax.get(dataReq,$scope.frm,function(respuesta){
					if(respuesta){
						setTimeout(function(){location.href = respuesta; },1200);
					} else {
						$scope.mensaje = "Usuario o Contraseña Incorrecto";
					}
				});
			}
		};
		
	});