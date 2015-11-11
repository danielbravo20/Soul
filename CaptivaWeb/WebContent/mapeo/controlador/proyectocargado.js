mapeo.registerCtrl('proyectocargado', function($scope) {
	
	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				id : "proyectodetalle",
				nombre : "PROYECTO DETALLE",
				activo : true,
				url : 'proyectodetalle.html'
			},
			{
				id : "rol",
				nombre : "ROL",
				activo : false,
				url : 'rol.html'
			},
			{
				id : "clase",
				nombre : "CLASE",
				activo : false,
				url : 'clase.html'
			},
			{
				id : "tabla",
				nombre : "TABLA",
				activo : false,
				url : 'tabla.html'
			},
			{
				id : "proceso",
				nombre : "PROCESO",
				activo : false,
				url : 'proceso.html'
			},
			{
				id : "consulta",
				nombre : "CONSULTA",
				activo : false,
				url : 'consulta.html'
			}/*,
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
			}*/
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
	
	$scope.cargarTab = function(tipo,tipoCargado){
		if(typeof(tipo)=="string"){
			for(var i = 0;i< $scope.tabs.lista.length;i++){
				if($scope.tabs.lista[i].id==tipo){
					$scope.tabs.lista[i].activo = true;
				}
			}
		} else if(typeof(tipo)=="number"){
			$scope.tabs.lista[tipo].activo = true;
		}
		if(typeof(tipoCargado)=="undefined"){
			tipoCargado = true;
		}
		if(tipoCargado){
			$scope.$apply();
		}
	};
	
});