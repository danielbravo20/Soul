mapeo.registerCtrl('proyectogestionar', function($scope, util) {
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", modulo : "Proyecto"};
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
		
		$scope.cargado.PRO_codigoProyecto = $scope.codigoSecuencial;
		$scope.USUARIOS = angular.copy($scope.data.USUARIOS);

	};
	
	$scope.trabajar = function(index){
		$scope.cargarTrabajarProyecto($scope.data.PROYECTOS[index]);
	};
	
	$scope.editarCargar = function(index){
		$scope.cargado.PRO_COD_PROYECTO 	= $scope.data.PROYECTOS[index].COD_PROYECTO;		// VAL-------
		$scope.cargado.PRO_nombre 			= $scope.data.PROYECTOS[index].nombre;				// VAL-------
		$scope.cargado.PRO_descripcion 		= $scope.data.PROYECTOS[index].descripcion;			// VAL-------
		$scope.cargado.PRO_estado 			= $scope.data.PROYECTOS[index].estado;				// VAL-------
		$scope.cargado.PRO_fechaCreacion 	= $scope.data.PROYECTOS[index].fechaCreacion;		// VAL-------
		$scope.cargado.PRO_fechaFinalizacion= $scope.data.PROYECTOS[index].fechaFinalizacion;	// VAL-------
		$scope.cargado.versiones 			= $scope.data.PROYECTOS[index].versiones;
		var usuarioId = [];
		for(var i in $scope.USUARIOs){
			usuarioId[$scope.USUARIOs[i].COD_USUARIO] = i;
		}
		for(var i in $scope.data.PROYECTOS[index].equipo){
			var equipo = $scope.data.PROYECTOS[index].equipo[i];
			$scope.USUARIOs[usuarioId[equipo.COD_USUARIO]].esDelEquipo = true;
			$scope.USUARIOs[usuarioId[equipo.COD_USUARIO]].CARPETA_DESTINO_WORKSPACE = equipo.CARPETA_DESTINO_WORKSPACE;
			$scope.USUARIOs[usuarioId[equipo.COD_USUARIO]].CARPETA_DESTINO_PARCIAL = equipo.CARPETA_DESTINO_PARCIAL;
			if(equipo.esResponsable){
				$scope.cargado.USUARIOResponsable = equipo.COD_USUARIO;
			}
		}
		$scope.esEdicion = true;
		$scope.vista = 'mantener';
	};
	
	$scope.nuevoCargar = function(index){
		$scope.instanciar();
		$scope.vista = 'mantener';
	};
	
	$scope.guardar = function(){
		var cont = 1;
		for(var i in $scope.USUARIOS){
			var user = $scope.USUARIOs[i];
			if(user.esDelEquipo){
				$scope.cargado["EQU_M_"+cont+"_cod_proyecto"] = $scope.cargado.PRO_COD_PROYECTO;
				$scope.cargado["EQU_M_"+cont+"_cod_usuario"] = user.cod_usuario;
				$scope.cargado["EQU_M_"+cont+"_es_Responsable"] = ($scope.cargado.UsuarioResponsable==user.cod_usuario)?1:0;
				if($scope.esEdicion){
					$scope.cargado["EQU_M_"+cont+"_carpeta_destino_workspace"] = user.carpeta_destino_workspace;
					$scope.cargado["EQU_M_"+cont+"_carpeta_destino_parcial"] = user.carpeta_destino_parcial;
				}
				cont++;
			}
		}
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.PRO_W_COD_PROYECTO = $scope.cargado.PRO_COD_PROYECTO;	// WHERE-------
			$scope.cargado.EQU_W_COD_PROYECTO = $scope.cargado.PRO_COD_PROYECTO;	// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				delete $scope.cargado.PRO_W_COD_PROYECTO;									// WHERE-------
				delete $scope.cargado.EQU_W_COD_PROYECTO;									// WHERE-------
				$scope.agregarAlerta("success","Esquema editado corréctamente");
				$scope.instanciar(true);
			});
		} else {
			$scope.cargado.metodo = "registrar";
			$scope.cargado.VER_COD_VERSION	= 1;
			$scope.cargado.VER_COD_PROYECTO 	= $scope.cargado.PRO_COD_PROYECTO;
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Proyecto creado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.eliminar = function(index){
		if(confirm("Confirma eliminar el seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.PRO_W_COD_PROYECTO = $scope.cargado.PRO_COD_PROYECTO;	// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				delete $scope.cargado.PRO_W_COD_PROYECTO;									// WHERE-------
				$scope.agregarAlerta("success","Proyecto eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.getPerfil = function(perfil){
		return util.getObjeto($scope.data.config.perfiles,{id : perfil}).valor;
	};
	
});