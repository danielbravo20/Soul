portal.registerCtrl('base_iniciarproceso', function($scope, $modal, hostname, ajax) {
	
	$scope.baseIPConfig = {};
	
	$scope.instanciar = function(){
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
						$scope.resetear();
						$scope.abrirModalCreado(respuesta);
					}
				}
			});
		}
	}
	
	$scope.abrirModalCreado = function(objRespuesta){
		var modalInstance = $modal.open({
			animation: true,
			templateUrl: 'modulo/base_iniciarproceso_creado.html',
			controller: 'Modal_base_iniciarproceso_creado',
			resolve: {
				config : function(){
					return {
						codigoProceso : objRespuesta.codigoProceso,
						trabajar : function(objTarea){
							$scope.cerrarProcesoInicio();
							$scope.trabajar(objTarea);
						}
					};
				}
			}
		});
		modalInstance.result.then(function () {
			$scope.cerrarProcesoInicio();
	    }, function () {
	    	$scope.cerrarProcesoInicio();
	    });
	};
	
	$scope.resetear = function(){
	    $scope.$broadcast('show-errors-reset');
	    $scope.baseIPConfig.data = {};
	};
	
	$scope.cerrarProcesoInicio = function(){
		$scope.instanciar();
		$scope.$parent.instanciar();
	};
	
});

portal.registerCtrl('Modal_base_iniciarproceso_creado', function ($scope, $modalInstance, $timeout, ajax, cargador, config) {

	$scope.haBuscado = false;
	$scope.contador = 0;
	
	$scope.Trabajar = function(){
		ajax.get({
			url : "tarea",
			data : {accion:"consultar", codigoProceso : config.codigoProceso},
			getRespuesta : function(objTarea){
				if(Tarea.codigoTarea){
					config.trabajar(objTarea);
					$modalInstance.close();
				} else {
					if($scope.contador>=2){
						$scope.haBuscado = true;
						$scope.contador = 0;
						cargador.ocultar();
					} else {
						$timeout(function(){
							$scope.contador++;
							$scope.Trabajar();
						}, 2*1000);
					}
				}
			},
			mostrarCargadorInicial : true
		});
	};
	
	$scope.VerTareas = function(){
		$modalInstance.close();
	};
	
});