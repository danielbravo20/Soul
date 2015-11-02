mapeo.registerCtrl('proceso', function($scope, $modal, ajax, util) {

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
		$scope.cargado.PRO_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.PRO_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
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
		// Datos por defecto
			$scope.cargado.PRO_cod_proceso = util.getUltimoCodigo($scope.data.PROCESO,"cod_proceso");
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.$parent.data.PROCESO = respuesta;
		});
	};
	
	$scope.editarCargar = function(cod_proceso){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.PROCESO,{cod_proceso : cod_proceso}),"PRO");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_PROCESO.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.PRO_W_cod_proceso = $scope.cargado.PRO_cod_proceso;			// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				delete $scope.cargado.PRO_W_cod_proceso;									// WHERE-------
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
	
	$scope.eliminar = function(cod_proceso){
		if(confirm("Desea eliminar el proceso seleccionada?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.PRO_W_cod_proceso = cod_proceso;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Proceso eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.gestionarAtributos = function(cod_proceso,inf_nombre){
		$scope.vista = "atributos";
		$scope.getControladorScope("procesoatributoinicio").instanciar(cod_proceso,inf_nombre);
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.gestionarRoles = function(cod_proceso){
		var miProceso = util.getObjeto($scope.data.PROCESO,{cod_tabla : cod_proceso});
		var modal = $modal.open({
			animation: true,
			templateUrl: 'rol_modal_entidad.html',
			controller: 'rol_modal_entidad',
			resolve: {
				config : function(){
					return {
						atributo : cod_proceso,
						titulo	 : "Roles Potenciales del Proceso '"+miProceso.inf_nombre+"'",
						rol		 : $scope.data.ROL,
						tabla	 : "proceso_rol_potencial",
						cod_entidad	 : "cod_proceso"
					};
				}
			}
		});
			modal.result.then(function(){
				$scope.instanciar();
			});
	};

});

mapeo.controller('modal_rol_entidad', function ($scope, $modalInstance, config) {

	$scope.config = config;
	
	var cargarAsociacion = function(){
		var consulta = {
			paquete : "modulo", 
			clase : "Rol",
			metodo : "listarRolxEntidad",
			tabla : config.tabla
		};
			consulta["ROL_W_"+config.cod_entidad] = config.atributo;
			
		ajax.jpo(consulta,function(respuesta){
			var misRojes = {};
			for(var i = 0;i< respuesta.length; i++){
				if(typeof(misRojes[respuesta[i].cod_rol]=="undefined")){
					misRojes[respuesta[i].cod_rol] = true;
				}
			}
			
			for(var i = 0;i< $scope.config.length; i++){
				$scope.config[i].cod_rol
			}
			$scope.data.TABLA_ATRIBUTO = respuesta;
			$scope.pagAtributo.total = respuesta.length;
		});
	};
	
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
	$scope.salir = function(){
		$modalInstance.dismiss('cancel');
	};
	
});