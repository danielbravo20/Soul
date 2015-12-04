portal.registerCtrl('pre_detalleproceso', function($scope, $modal, ajax) {

	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				nombre : "Historial de Tareas",
				activo : true,
				url : $scope.urlplantilla+'detalleproceso_historialtareas.html'
			},			{
				nombre : "Seccion Nro 4",
				activo : true,
				url : $scope.urlseccion+'detalleproceso_2.html'
			},			{
				nombre : "Documentos",
				activo : true,
				url : $scope.urlplantilla+'detalleproceso_documentos.html'
			},			{
				nombre : "Observaciones y Subsanaciones",
				activo : true,
				url : $scope.urlplantilla+'detalleproceso_observacionsubsanacion.html'
			},			{
				nombre : "Seccion Nro 5",
				activo : true,
				url : $scope.urlplantillaDetalle+'1449153997038.html'
			}
		]
	};

});
