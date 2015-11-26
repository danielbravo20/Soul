mapeo.registerCtrl('consulta', function($scope, ajax, util) {

	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				id : "catalogo",
				nombre : "CATALOGO",
				activo : true,
				url : 'maestro_catalogo.html'
			},
			{
				id : "documento",
				nombre : "DOCUMENTO",
				activo : false,
				url : 'maestro_documento.html'
			}
		]
	};
	
});