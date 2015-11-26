mapeo.registerCtrl('proyectocargado', function($scope, $modal, ajax) {
	
	$scope.tabs = {
		unoALaVes : true,
		actual : 1,
		lista : [
			{
				id : "proyectodetalle",
				nombre : "PROYECTO DETALLE",
				activo : true,
				url : 'proyectodetalle.html'
			},
			{
				id : "rol",
				nombre : "ROL",
				activo : false,
				url : 'rol.html'
			},
			{
				id : "tabla",
				nombre : "TABLA",
				activo : false,
				url : 'tabla.html'
			},
			{
				id : "clase",
				nombre : "CLASE",
				activo : false,
				url : 'clase.html'
			},
			{
				id : "consulta",
				nombre : "CONSULTA",
				activo : false,
				url : 'consulta.html'
			},
			{
				id : "proceso",
				nombre : "PROCESO",
				activo : false,
				url : 'proceso.html'
			},
			{
				id : "tarea",
				nombre : "TAREA",
				activo : false,
				url : 'tarea.html'
			},
			{
				id : "maestro",
				nombre : "MAESTRO",
				activo : false,
				url : 'maestro.html'
			}
		]
	};
	
	$scope.instanciar = function(){
		for(var i in $scope.tabs.lista){
			if($scope.tabs.lista[i].id == $scope.subModulo){
				$scope.tabs.lista[i].activo = true;
			}
			var ctrl = $scope.getControladorScope($scope.tabs.lista[i].id);
			if(ctrl && ctrl.instanciar){
				ctrl.instanciar();
			}
		}
	};
	
	$scope.cerrarProyecto = function(){
		$scope.cerrarTrabajarProyecto();
	};
	
	$scope.cargarTab = function(tipo,tipoCargado){
		if(typeof(tipo)=="string"){
			for(var i = 0;i< $scope.tabs.lista.length;i++){
				if($scope.tabs.lista[i].id==tipo){
					$scope.tabs.lista[i].activo = true;
				}
			}
		} else if(typeof(tipo)=="number"){
			$scope.tabs.lista[tipo].activo = true;
		}
		if(typeof(tipoCargado)=="undefined"){
			tipoCargado = true;
		}
		if(tipoCargado){
			try {
				$scope.$apply();
			} catch (e) {
				// TODO: handle exception
			}
		}
	};
	
	$scope.asociarRoles = function(atributo,titulo,tabla,entidad){

		var modal = $modal.open({
			animation: true,
			templateUrl: 'rol_modal_entidad.html',
			controller: 'rol_modal_entidad',
			resolve: {
				config : function(){
					return {
						atributo 	: atributo,
						titulo	 	: titulo,
						rol		 	: $scope.data.ROL,
						tabla	 	: tabla,
						cod_entidad	: entidad,
						confirmar : function(){
							$scope.agregarAlerta("success","Roles asociados corréctamente");
						}
					};
				}
			}
		});
			modal.result.then(function(){
				//$scope.instanciar();
			});
	};
	
	$scope.borrarRoles = function(cod_tabla,tabla,atributo,call){

		var param = {
			paquete 			: "modulo", 
			clase 				: "Rol",
			metodo 				: "registrarRolxEntidad",
			tabla 				: tabla
		};
		
			param["ROL_W_"+atributo] = cod_tabla;
		
		ajax.jpo(param,function(respuesta){
			if(respuesta){
				if(call){
					call();
				}
			}
		});
		
	};
	
});

mapeo.registerCtrl('rol_modal_entidad', function ($scope, $modalInstance, ajax, config) {

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
			
			for(var i = 0;i< $scope.config.rol.length; i++){
				if(misRojes[$scope.config.rol[i].cod_rol]==true){
					$scope.config.rol[i].esSeleccionado = true;
				} else {
					$scope.config.rol[i].esSeleccionado = false;
				}
			}
		});
	};
	
	cargarAsociacion();
	
	$scope.guardar = function(){
		var consulta = {
			paquete : "modulo", 
			clase : "Rol",
			metodo : "registrarRolxEntidad",
			tabla : config.tabla
		};
			consulta["ROL_W_"+config.cod_entidad] = config.atributo;
		
		var cont = 1;
		for(var i = 0;i< $scope.config.rol.length; i++){
			if($scope.config.rol[i].esSeleccionado==true){
				consulta["ROL_M_"+cont+"_cod_rol"] = $scope.config.rol[i].cod_rol;
				consulta["ROL_M_"+cont+"_"+config.cod_entidad] = $scope.config.atributo;
				cont++;
			}
		}

		ajax.jpo(consulta,function(respuesta){
			if(respuesta==true){
				config.confirmar();
				$modalInstance.close();
			}
		});
	};
	
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});