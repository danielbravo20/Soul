portal.registerCtrl('base_trabajartarea', function($scope, hostname, ajax) {
	
	$scope.baseTrabajar = {};
	
	$scope.instanciar = function(){
		$scope.baseTrabajar = {
			visibleBotonIniciar : true,
			visibleBotonGrabar : true,
			url : "",
			fechaInicio : new Date()
		};
	};
	
	$scope.cargarTrabajar = function(objTarea){
		$scope.instanciar();
		$scope.data.TAREA_CARGADA = objTarea;
		$scope.baseTrabajar.fechaInicio = new Date();
		$scope.baseTrabajar.url = hostname+"/portal/procesos/"+objTarea.proceso.aleas+"/tareas/vista/"+objTarea.aleas+".html";
	}
	
	$scope.trabajar = function(){
	    $scope.$broadcast('show-errors-reset');
	    $scope.baseIPConfig.data = {};
	};
	
	$scope.resetear = function(){
	    $scope.$broadcast('show-errors-reset');
	    $scope.baseIPConfig.data = {};
	};
	
	$scope.cerrarTrabajarTarea = function(){
		$scope.instanciar();
		$scope.tareasConfig.vista = "lista";
	};
	
});