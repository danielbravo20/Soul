mapeo.registerCtrl('proyectogestionar', function($scope, ajax, util) {
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", clase : "Proyecto"};
		$scope.vista = 'lista';
		$scope.esEdicion = false;
		if(listar){
			$scope.cargarProyecto();
		}
		
		$scope.codigoSecuencial = 1;
		$scope.paquetes = [];
		var miPaquete = {};
		for(var i =0;i< $scope.data.PROYECTOS.length; i++){
			if($scope.data.PROYECTOS[i].cod_proyecto > $scope.codigoSecuencial){
				$scope.codigoSecuencial = $scope.data.PROYECTOS[i].cod_proyecto;
			}
			if(!miPaquete[$scope.data.PROYECTOS[i].paquete]){
				miPaquete[$scope.data.PROYECTOS[i].paquete] = $scope.data.PROYECTOS[i].paquete;
				$scope.paquetes.push($scope.data.PROYECTOS[i].paquete);
			}
		}
		$scope.codigoSecuencial++;
		
		$scope.cargado.PRO_cod_proyecto = $scope.codigoSecuencial;
		$scope.USUARIOS = angular.copy($scope.data.USUARIOS);

	};
	
	$scope.trabajar = function(index){
		$scope.cargarTrabajarProyecto($scope.data.PROYECTOS[index]);
	};
	
	$scope.editarCargar = function(index){
		$scope.cargado.PRO_cod_proyecto 	= $scope.data.PROYECTOS[index].cod_proyecto;		// VAL-------
		$scope.cargado.PRO_nombre 			= $scope.data.PROYECTOS[index].nombre;				// VAL-------
		$scope.cargado.PRO_proyecto 		= $scope.data.PROYECTOS[index].proyecto;			// VAL-------
		$scope.cargado.PRO_paquete 			= $scope.data.PROYECTOS[index].paquete;				// VAL-------

		var usuarioId = [];
		for(var i = 0;i<$scope.USUARIOS.length;i++){
			usuarioId[$scope.USUARIOS[i].cod_usuario] = i;
		}
		for(var i = 0;i<$scope.data.PROYECTOS[index].equipo.length;i++){
			var equipo = $scope.data.PROYECTOS[index].equipo[i];
			$scope.USUARIOS[usuarioId[equipo.cod_usuario]].esDelEquipo = true;
			$scope.USUARIOS[usuarioId[equipo.cod_usuario]].carpeta_destino_workspace = equipo.carpeta_destino_workspace;
			$scope.USUARIOS[usuarioId[equipo.cod_usuario]].carpeta_destino_parcial = equipo.carpeta_destino_parcial;
			if(equipo.es_responsable=="1"){
				$scope.cargado.UsuarioResponsable = equipo.cod_usuario;
			}
		}
		$scope.esEdicion = true;
		$scope.vista = 'mantener';
	};
	
	$scope.nuevoCargar = function(index){
		$scope.instanciar();
		$scope.vista = 'mantener';
	};
	
	var validar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_PROYECTOGESTIONAR.$invalid) { return false; }
		
		var cEquipo = 0;
		for(var i in $scope.USUARIOS){
			var user = $scope.USUARIOS[i];
			if($scope.USUARIOS[i].esDelEquipo){
				cEquipo++;
			}
		}
		
		if(cEquipo == 0){
			$scope.agregarAlerta("danger","Asigne por lo menos 1 usuario al proyecto");
			return false;
		}
		
		
		if(typeof($scope.cargado.UsuarioResponsable)=="undefined"){
			$scope.agregarAlerta("danger","Asigne un responsable");
			return false;
		}
		
		return true;
		
	};
	
	$scope.guardar = function(){

		if(validar()){
			var cont = 1;
			for(var i in $scope.USUARIOS){
				var user = $scope.USUARIOS[i];
				if(user.esDelEquipo){
					$scope.cargado["EQU_M_"+cont+"_cod_proyecto"] = $scope.cargado.PRO_cod_proyecto;
					$scope.cargado["EQU_M_"+cont+"_cod_usuario"] = user.cod_usuario;
					$scope.cargado["EQU_M_"+cont+"_es_Responsable"] = ($scope.cargado.UsuarioResponsable==user.cod_usuario)?1:0;
					$scope.cargado["EQU_M_"+cont+"_carpeta_destino_workspace"] = (user.carpeta_destino_workspace)?user.carpeta_destino_workspace:"";
					$scope.cargado["EQU_M_"+cont+"_carpeta_destino_parcial"] = (user.carpeta_destino_parcial)?user.carpeta_destino_parcial:"";
					cont++;
				}
			}
			if($scope.esEdicion){
				$scope.cargado.metodo = "editar";
				$scope.cargado.PRO_W_cod_proyecto = $scope.cargado.PRO_cod_proyecto;	// WHERE-------
				$scope.cargado.EQU_W_cod_proyecto = $scope.cargado.PRO_cod_proyecto;	// WHERE-------
				ajax.jpo($scope.cargado,function(respuesta){
					delete $scope.cargado.PRO_W_cod_proyecto;							// WHERE-------
					delete $scope.cargado.EQU_W_cod_proyecto;							// WHERE-------
					$scope.agregarAlerta("success","Proyecto editado corréctamente");
					$scope.instanciar(true);
				});
			} else {
				$scope.cargado.metodo = "registrar";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Proyecto creado corréctamente");
					$scope.instanciar(true);
				});
			}
		}
		
	};
	
	$scope.eliminar = function(index){
		if(confirm("Confirma eliminar el seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.PRO_W_cod_proyecto = $scope.data.PROYECTOS[index].cod_proyecto;	// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				delete $scope.cargado.PRO_W_cod_proyecto;									// WHERE-------
				$scope.agregarAlerta("success","Proyecto eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.getPerfil = function(perfil){
		return util.getObjeto($scope.data.config.perfiles,{id : perfil}).valor;
	};
	
});