portal.registerCtrl('base_iniciarproceso', function($scope, hostname, ajax) {
	
	$scope.baseIPConfig = {};
	
	$scope.instanciar = function(codigoProcesoPlantilla){
		$scope.baseIPConfig = {
			visibleBotonIniciar : true,
			url : "",
			fechaInicio : new Date(),
			data : {}
		};
	};
	
	$scope.cargarIniciarProceso = function(codigoProcesoPlantilla){
		$scope.instanciar();
		var proceso = util.getObjeto($scope.data.procesoPotenciales,{codigoProcesoPlantilla : codigoProcesoPlantilla});
		$scope.data.PROCESO_CARGADO = proceso;
		$scope.baseIPConfig.fechaInicio = new Date();
		$scope.baseIPConfig.url = hostname+"/portal/procesos/"+proceso.aleas+"/proceso/vista/iniciarproceso.html";
	}
	
	$scope.iniciarProceso = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.frm_iniciarproceso.$invalid) { return; }
		if ($scope.frm_iniciarproceso.$valid) {
			ajax.getAjax({
				url 			: hostname+"/portal/"+$scope.data.PROCESO_CARGADO.aleas,
				data 			: angular.extend({},{accion : "crear"},$scope.baseIPConfig.data),
				getRespuesta 	: function(respuesta){
					if(respuesta.codigoProceso && respuesta.codigoProcesoPlantilla){
						alert("Solicitud Creada Corréctamente");
						$scope.resetear();
					}
				}
			});
		}
	}
	
	$scope.resetear = function(){
	    $scope.$broadcast('show-errors-reset');
	    $scope.baseIPConfig.data = {};
	}
	
	$scope.cerrarProcesoInicio = function(){
		$scope.instanciar();
		$scope.tareasConfig.vista = "lista";
	}
	
});