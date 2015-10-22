mapeo.registerCtrl('proyectogestionar', function($scope) {
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", modulo : "Proyecto"};
		$scope.vista = 'lista';
		$scope.esEdicion = false;
		if(listar){
			$scope.cargarProyecto();
		}
	};
	
	$scope.trabajar = function(index){
		$scope.cargarTrabajarProyecto($scope.data.PROYECTOS[index]);
		/*if($scope.data.PROYECTOS[index].versionSeleccionada && $scope.data.PROYECTOS[index].versionSeleccionada!=""){
			var versel = $scope.data.PROYECTOS[index].versiones[$scope.data.PROYECTOS[index].versionSeleccionada];
			$scope.data.PROYECTOS[index].versionSeleccionada = versel.numeroVersion;
			$scope.data.PROYECTOS[index].unidadNegocioSeleccionado = versel.codigoUnidadNegocio;
			$scope.$parent.cargarTrabajarProyecto($scope.data.PROYECTOS[index]);
		} else {
			$scope.agregarAlerta("danger","Selecciona una versión");
		}*/
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
		for(var i in $scope.USUARIOs){
			var user = $scope.USUARIOs[i];
			if(user.esDelEquipo){
				$scope.cargado["EQU_M_"+cont+"_COD_USUARIO"] = user.COD_USUARIO;
				$scope.cargado["EQU_M_"+cont+"_COD_PROYECTO"] = $scope.cargado.PRO_COD_PROYECTO;
				$scope.cargado["EQU_M_"+cont+"_esResponsable"] = ($scope.cargado.USUARIOResponsable==user.COD_USUARIO)?1:0;
				if($scope.esEdicion){
				$scope.cargado["EQU_M_"+cont+"_CARPETA_DESTINO_WORKSPACE"] = user.CARPETA_DESTINO_WORKSPACE;
				$scope.cargado["EQU_M_"+cont+"_CARPETA_DESTINO_PARCIAL"] = user.CARPETA_DESTINO_PARCIAL;
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
	
	$scope.versionarCargar = function(index){
		$scope.cargado.VER_COD_PROYECTO = $scope.data.PROYECTOS[index].COD_PROYECTO;
		$scope.versiones = $scope.data.PROYECTOS[index].versiones;
		$scope.vista = 'versionar';
	};
			
	$scope.validarVersion = function(){
		for(var i in $scope.versiones){
			var ver = $scope.versiones[i];
			if(ver.codigoUnidadNegocio==$scope.cargado.VER_codigoUnidadNegocio){
				$scope.cargado.VER_COD_VERSION = Number(ver.numeroVersion)+1;
				return;
			}
		}
		$scope.cargado.VER_COD_VERSION = 1;
	};
	
	$scope.versionar = function(index){
		$scope.cargado.metodo = "versionar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.agregarAlerta("success","Versión creada corréctamente");
			$scope.instanciar(true);
		});
	};
	
});