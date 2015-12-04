portal.registerCtrl('pre_acciontarea', function($scope, $modal, ajax) {

	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				nombre : "Documentos",
				activo : true,
				url : $scope.urlplantilla+'acciontarea_documentos.html'
			},			{
				nombre : "Observaciones y Subsanaciones",
				activo : true,
				url : $scope.urlplantilla+'acciontarea_observacionsubsanacion.html'
			},			{
				nombre : "Seccion Nro 3",
				activo : true,
				url : $scope.urlseccion+'acciontarea_3.html'
			},			{
				nombre : "Seccion Nro 4",
				activo : true,
				url : $scope.urlseccion+'acciontarea_4.html'
			},			{
				nombre : "Seccion Nro 5",
				activo : true,
				url : $scope.urlplantillaAccion+'1449178124055.html'
			}
		]
	};

});
