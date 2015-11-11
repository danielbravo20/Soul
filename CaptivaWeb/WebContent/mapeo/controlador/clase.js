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
		$scope.cargado.CLA_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.CLA_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.esEdicion = false;
		
		$scope.paquetes = [];
		var miPaquete = {};
		for(var i =0;i< $scope.data.CLASE.length; i++){
			if(!miPaquete[$scope.data.CLASE[i].paquete]){
				miPaquete[$scope.data.CLASE[i].paquete] = $scope.data.CLASE[i].paquete;
				$scope.paquetes.push($scope.data.CLASE[i].paquete);
			}
		}
		
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
		// Datos por defecto
		$scope.cargado.CLA_cod_clase = util.getUltimoCodigo($scope.data.CLASE,"cod_clase");
	};
	
	$scope.eliminar = function(cod_clase){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.ATR_W_cod_clase = cod_clase;		// WHERE-------
			$scope.cargado.CLA_W_cod_clase = cod_clase;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_clase){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.CLASE,{cod_clase : cod_clase}),"CLA");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_CLASE.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.CLA_W_cod_clase = $scope.cargado.CLA_cod_clase;			// WHERE-------
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
	
	$scope.gestionarAtributos = function(cod_clase){
		$scope.vista = "atributos";
		$scope.getControladorScope("atributo").instanciar(true,util.getObjeto($scope.data.CLASE,{cod_clase : cod_clase}));
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
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