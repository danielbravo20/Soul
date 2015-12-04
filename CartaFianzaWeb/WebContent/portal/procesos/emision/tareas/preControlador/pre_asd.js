portal.registerCtrl('pre_acciontarea', function($scope, $modal, ajax) {

	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				nombre : "Observaciones y Subsanaciones",
				activo : true,
				url : $scope.urlplantilla+'acciontarea_observacionsubsanacion.html'
			},			{
				nombre : "Documentos",
				activo : true,
				url : $scope.urlplantilla+'acciontarea_documentos.html'
			},			{
				nombre : "Seccion Nro 1",
				activo : true,
				url : $scope.urlseccion+'acciontarea_3.html'
			},			{
				nombre : "Historial de Tareas",
				activo : true,
				url : $scope.urlplantilla+'acciontarea_historialtareas.html'
			}
		]
	};

});
