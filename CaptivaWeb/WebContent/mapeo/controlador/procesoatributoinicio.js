mapeo.registerCtrl('procesoatributoinicio', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		max : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	$scope.COD_PROCESO = "";
	
	$scope.instanciar = function(codigoProceso, nombreProceso){
		$scope.ATRIBUTOS_CLASE = [];
		if(codigoProceso){
			$scope.COD_PROCESO = codigoProceso;
		} else {
			codigoProceso = $scope.COD_PROCESO;
		}
		if(nombreProceso){
			$scope.INF_NOMBRE = nombreProceso;
		} else {
			nombreProceso = $scope.INF_NOMBRE;
		}
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "ProcesoInicio"};
		$scope.cargado.PRI_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.PRI_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.PRI_W_COD_PROCESO = codigoProceso;
		$scope.cargado.PRI_COD_PROCESO = codigoProceso;
		$scope.esEdicion = false;
		$scope.listar();
		
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.PROCESO_INICIO = respuesta;
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.editarCargar = function(COD_ATRIBUTO){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.PRI_W_COD_ATRIBUTO = COD_ATRIBUTO;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.PROCESO_INICIO,{COD_ATRIBUTO : COD_ATRIBUTO}),"PRI");
		$scope.cargarComboAtributo();
	};
	
	$scope.cargarComboAtributo = function(){
		ajax.jpo({
			paquete : "modulo", 
			clase : "Atributo",
			metodo : "listaAtributoxPK",
			ATR_W_COD_CLASE : $scope.cargado.PRI_COD_CLASE
		},function(respuesta){
			$scope.ATRIBUTOS_CLASE = respuesta;
		});
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_PROCESO_ATRIBUTO_INICIO.$invalid) { return; }
		delete $scope.cargado.PRI_NOM_ATRIBUTO;
		delete $scope.cargado.PRI_COD_CLASE;
		delete $scope.cargado.PRI_NOM_CLASE;
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";			
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","El atributo fue editado corréctamente");
				$scope.instanciar();
			});
		} else {
			$scope.cargado.metodo = "registrar";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","El atributo fue ingresado corréctamente");
				$scope.instanciar();
			});
		}
	};
	
	$scope.eliminar = function(COD_ATRIBUTO){
		if(confirm("Desea eliminar el atributo seleccionada?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.PRI_W_COD_ATRIBUTO = COD_ATRIBUTO;
			ajax.jpo($scope.cargado,function(respuesta){							
				$scope.agregarAlerta("success","Entidad eliminado corréctamente");
				$scope.instanciar();
			});
		}
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});

});