mapeo.registerCtrl('clase', function($scope, ajax, util, $modal) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Clase"};
		$scope.cargado.CLA_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.CLA_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.esEdicion = false;
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.CLASE.length;
		}
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.CLASE = respuesta;
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.eliminar = function(COD_CLASE){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.ATR_W_COD_CLASE = COD_CLASE;		// WHERE-------
			$scope.cargado.CLA_W_COD_CLASE = COD_CLASE;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(COD_CLASE){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.CLASE,{COD_CLASE : COD_CLASE}),"CLA");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_CLASE.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.CLA_W_COD_CLASE = $scope.cargado.CLA_COD_CLASE;			// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Editado corréctamente");
				$scope.instanciar(true);
			});
		} else {
			$scope.cargado.metodo = "registrar";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Creado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.gestionarAtributos = function(COD_CLASE){
		$scope.vista = "atributos";
		$scope.getControladorScope("atributo").instanciar(true,util.getObjeto($scope.data.CLASE,{COD_CLASE : COD_CLASE}));
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.clonar = function(COD_CLASE){
		var modalInstance = $modal.open({
			animation: true,
			templateUrl: 'claseclonar.html',
			controller: 'Modal_claseclonar',
			resolve: {
				config : function(){
					return {
						objClase : util.getObjeto($scope.data.CLASE,{COD_CLASE : COD_CLASE}),
						ultimoCodigo : getUltimoCodigo("CLASE","COD_CLASE"),
						clonarClase : function(objetoConfig,callback){ debugger;
							$scope.editarCargar(COD_CLASE);
							
							/*$scope.cargado.metodo = "clonar";
							ajax.jpo($scope.cargado,function(respuesta){
								$scope.data.CLASE = respuesta;
								$scope.pag.total = respuesta.length;
							});*/
							callback();
						}
					};
				}
			}
		});
		modalInstance.result.then(function(){
			$scope.instanciar(true);
		});
	};
	
	var getUltimoCodigo = function(objeto,nombreCodigo){
		var contador = 0;
		var listaObj = $scope.data[objeto];
		for(var i = 0; i<listaObj.length; i++){
			var obj = listaObj[i];
			var nro = Number(obj[nombreCodigo]);
			if(nro>contador){
				contador = nro;
			}
		}
		return contador+1;
	};
	
});

mapeo.registerCtrl('Modal_claseclonar', function ($scope, $modalInstance, config) {

	$scope.clonarObj = {
		CLA_COD_CLASE : config.ultimoCodigo,
		CLA_NOMBRE : config.objClase.NOMBRE + " 2",
		ADC_ATRIBUTOS : "1"
	};
	
	$scope.clonar = function(){
		config.clonarClase($scope.clonarObj,function(){
			$modalInstance.close();
		});
	};
	
	$scope.cancelar = function(){
		$modalInstance.dismiss('cancel');
	};
	
});