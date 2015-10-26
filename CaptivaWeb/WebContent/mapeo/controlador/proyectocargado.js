mapeo.registerCtrl('proyectocargado', function($scope) {
	
	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				id : "proyectodetalle",
				nombre : "Detalle de Proyecto",
				activo : true,
				url : 'proyectodetalle.html'
			},
			{
				id : "clase",
				nombre : "Clases",
				activo : false,
				url : 'clase.html'
			},
			{
				id : "proceso",
				nombre : "Procesos",
				activo : false,
				url : 'proceso.html'
			},
			{
				id : "tarea",
				nombre : "Tareas",
				activo : false,
				url : 'tarea.html'
			},
			{
				id : "consulta",
				nombre : "Consultas",
				activo : false,
				url : 'consulta.html'
			},
			{
				id : "mantenimiento",
				nombre : "Mantenimientos",
				activo : false,
				url : 'mantenimiento.html'
			},
			{
				id : "reporte",
				nombre : "Reportes",
				activo : false,
				url : 'reporte.html'
			}
		]
	};
	
	$scope.instanciar = function(){
		for(var i in $scope.tabs.lista){
			if($scope.tabs.lista[i].id == $scope.subModulo){
				$scope.tabs.lista[i].activo = true;
			}
			var ctrl = $scope.getControladorScope($scope.tabs.lista[i].id);
			if(ctrl && ctrl.instanciar){
				ctrl.instanciar();
			}
		}
	};
	
	$scope.cerrarProyecto = function(){
		$scope.cerrarTrabajarProyecto();
	};
	
});