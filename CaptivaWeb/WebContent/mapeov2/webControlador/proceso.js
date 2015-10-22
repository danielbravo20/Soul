mapeo.registerCtrl('proceso', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		max : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Proceso"};
		$scope.cargado.PRO_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.PRO_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.esEdicion = false;
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.PROCESO.length;
		}
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.$parent.data.PROCESO = respuesta;
		});
	};
	
	$scope.editarCargar = function(COD_PROCESO){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.PROCESO,{COD_PROCESO : COD_PROCESO}),"PRO");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_PROCESO.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.PRO_W_COD_PROCESO = $scope.cargado.PRO_COD_PROCESO;			// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				delete $scope.cargado.PRO_W_COD_PROCESO;									// WHERE-------
				$scope.agregarAlerta("success","Proceso editado corréctamente");
				$scope.instanciar(true);
			});
		} else {
			$scope.cargado.metodo = "registrar";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Entidad creado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.eliminar = function(COD_PROCESO){
		if(confirm("Desea eliminar el proceso seleccionada?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.PRO_W_COD_PROCESO = COD_PROCESO;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Proceso eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.gestionarAtributos = function(COD_PROCESO,INF_NOMBRE){
		$scope.vista = "atributos";
		$scope.getControladorScope("procesoatributoinicio").instanciar(COD_PROCESO,INF_NOMBRE);
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});

});