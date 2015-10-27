mapeo.registerCtrl('usuariogestionar', function($scope, $modal, ajax, util) {

	$scope.cargado = {};
	
	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "gestion", clase : "Usuario"};
		$scope.esEdicion = false;
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.USUARIOS.length;
		}
		
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.USUARIOS = respuesta;
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
	};
	
	$scope.eliminar = function(cod_usuario){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.USU_W_cod_usuario = cod_usuario;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_usuario){
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.USUARIOS,{cod_usuario : cod_usuario}),"USU");
		$scope.cargado.claveAnterior = "";
		$scope.cargado.USU_clave = "";
		$scope.cargado.repetirClave = "";
	};
	
	var validar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_USUARIO.$invalid) { return; }
		
		if((!$scope.esEdicion || ($scope.esEdicion && $scope.cargado.claveAnterior!="")) && $scope.cargado.USU_clave != $scope.cargado.repetirClave){
			$scope.agregarAlerta("danger","Las contraseñas deben ser las mismas");
			return false;
		}
		
		return true;
	};
	
	var consultarClave = function(cargarEditar){
		$scope.cargado.metodo = "consultarClave";
		//$scope.cargado.claveAnterior = $scope.cargado.claveAnterior;			// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			if(respuesta.cod_usuario){
				cargarEditar();
			} else {
				$scope.agregarAlerta("danger","La clave anterior debe ser la misma");
			}
		});
	};
	
	var cargarEditar = function(){
		$scope.cargado.metodo = "editar";
		$scope.cargado.USU_W_cod_usuario = $scope.cargado.USU_cod_usuario;			// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.agregarAlerta("success","Editado corréctamente");
			$scope.instanciar(true);
		});
	};
	
	$scope.guardar = function(){
		if(validar()){
			if($scope.esEdicion){
				if($scope.cargado.claveAnterior!=""){
					consultarClave(cargarEditar);
				} else {
					cargarEditar();
				}
			} else {
				$scope.cargado.metodo = "registrar";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Creado corréctamente");
					$scope.instanciar(true);
				});
			}
		}

	};
	
});