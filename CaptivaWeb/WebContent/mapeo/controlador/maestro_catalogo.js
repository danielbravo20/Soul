mapeo.registerCtrl('maestro_catalogo', function($scope, ajax, util, $modal) {
	
	$scope.buscar = {
		cabecera : "0"
	};
	
	$scope.objCatalogo = {};
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", clase : "MaestroCatalogo"};
		$scope.cargado.CAT_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.CAT_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.esEdicion = false;
		$scope.cargado.CAT_cabecera = "1";
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		}
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.MAE_CATALOGO = respuesta.MAE_CATALOGO;
			$scope.data.MAE_CATALOGO_PADRE = respuesta.MAE_CATALOGO_PADRE;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
	};
	
	$scope.eliminar = function(cod_catalogo,cod_atributo){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.CAT_W_cod_catalogo = cod_catalogo;		// WHERE-------
			$scope.cargado.CAT_W_cod_atributo = cod_atributo;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.eliminarPadre = function(cod_catalogo){
		if(confirm("Desea eliminar el elemento padre y sus hijos?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.CAT_W_cod_catalogo = $scope.buscar.cod_catalogo;		// WHERE-------
			delete $scope.cargado.CAT_W_cod_atributo;
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_catalogo,cod_atributo){
		$scope.esEdicion = true;
		for(var i = 0; i < $scope.data.MAE_CATALOGO_PADRE.length;i++){
			if($scope.data.MAE_CATALOGO_PADRE[i].cod_catalogo == cod_catalogo && $scope.data.MAE_CATALOGO_PADRE[i].cod_atributo == cod_catalogo){
				$scope.objCatalogo = $scope.data.MAE_CATALOGO_PADRE[i];
				break;
			}
		}
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.MAE_CATALOGO,{cod_catalogo : cod_catalogo,cod_atributo : cod_atributo}),"CAT");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_CAT.$invalid) { return; }
		
		if($scope.cargado.CAT_cabecera=="1"){
			$scope.cargado.CAT_cod_atributo = $scope.cargado.CAT_cod_catalogo;
		}
		if($scope.cargado.CAT_cabecera=="0"){
			$scope.cargado.CAT_cod_catalogo = $scope.objCatalogo.cod_catalogo;
		}
		
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.CAT_W_cod_catalogo = $scope.cargado.CAT_cod_catalogo;			// WHERE-------
			if($scope.cargado.CAT_cabecera=="1"){
				$scope.cargado.CAT_W_cod_atributo = $scope.cargado.CAT_cod_catalogo;		// WHERE-------s
			}
			if($scope.cargado.CAT_cabecera=="0"){
				$scope.cargado.CAT_W_cod_atributo = $scope.cargado.CAT_cod_atributo;		// WHERE-------s
			}
			
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
	
	$scope.instanciar(true);
	
});