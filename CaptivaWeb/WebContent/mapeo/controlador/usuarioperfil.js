mapeo.registerCtrl('usuarioperfil', function($scope, $modal, ajax, util) {

	$scope.cargado = {};
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "gestion", clase : "Usuario"};
		$scope.editarCargar($scope.data.USUARIO.cod_usuario);
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
		if ($scope.FRM_PERFIL.$invalid) { return; }
		if($scope.cargado.USU_clave!="" && $scope.cargado.USU_clave != $scope.cargado.repetirClave){
			$scope.agregarAlerta("danger","Las contraseñas deben ser las mismas");
			return false;
		}
		return true;
	};
	
	var consultarClave = function(cargarEditar){
		$scope.cargado.metodo = "consultarClave";
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
			$scope.data.USUARIO.descripcion = $scope.cargado.USU_descripcion;
			$scope.data.USUARIO.nombre = $scope.cargado.USU_nombre;
			$scope.data.USUARIO.perfil = $scope.cargado.USU_perfil;
			var miObj = util.getObjeto($scope.data.USUARIOS,{cod_usuario : $scope.cargado.USU_cod_usuario});
			miObj.descripcion = $scope.cargado.USU_descripcion;
			miObj.nombre = $scope.cargado.USU_nombre;
			miObj.perfil = $scope.cargado.USU_perfil;
			$scope.agregarAlerta("success","Editado corréctamente");
			$scope.instanciar();
		});
	};
	
	$scope.guardar = function(){
		if(validar()){
			if($scope.cargado.claveAnterior!=""){
				consultarClave(cargarEditar);
			} else {
				cargarEditar();
			}
		}

	};
	
});