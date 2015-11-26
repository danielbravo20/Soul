mapeo.registerCtrl('tarea', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.urlGestionar = "";
		$scope.cargado = { paquete : "modulo", clase : "Tarea"};
		$scope.cargado.TAR_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.TAR_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.TAR_cod_tarea = util.getUltimoCodigo($scope.data.TAREA,"cod_tarea");
		$scope.consultaCompletar = [];
		$scope.consultaTrabajar = [];
		if($scope.cod_proceso && $scope.cod_proceso!=""){
			$scope.cargado.TAR_W_cod_proceso = $scope.cod_proceso;
			$scope.cargado.TAR_cod_proceso = $scope.cod_proceso;
		}
		$scope.esEdicion = false;
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		}
		/*DUMMY */
		if(getParametro.accion && getParametro.accion=="gestionarResumen"){
			$scope.cod_proceso = 1;
		}
		if(getParametro.accion && getParametro.accion=="gestionarAccion"){
			$scope.cod_proceso = 1;
		}
		if(getParametro.accion && getParametro.accion=="gestionarConsultaResumen"){
			$scope.gestionarPlantillaResumen();
		}
	};
	
	$scope.$watch("cod_proceso",function(oldVal,newVal){
		if(oldVal != newVal){
			if($scope.cod_proceso != ""){
				$scope.listar();
			}
		}
	});
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		$scope.cargado.TAR_W_cod_proceso = $scope.cod_proceso;
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.TAREA = respuesta;
			$scope.pag.total = $scope.data.TAREA.length;
			/*DUMMY */
			if(getParametro.accion && getParametro.accion=="gestionarResumen"){
				$scope.gestionarResumen("2");
			}
			if(getParametro.accion && getParametro.accion=="gestionarAccion"){
				$scope.gestionarAccion("2");
			}
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.eliminar = function(cod_tarea){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.TAR_W_cod_tarea = cod_tarea;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_tarea){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.metodo = "editarCargar";
		$scope.cargado.TAR_W_cod_tarea = cod_tarea;			// WHERE-------
		$scope.cargado.TCB_W_cod_tarea = cod_tarea;			// WHERE-------
		$scope.cargado.TTB_W_cod_tarea = cod_tarea;			// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			util.jpoCargar($scope.cargado,respuesta.TAREA,"TAR");
			$scope.consultaTrabajar = respuesta.TAREA_TRA_BPM;
		});
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_TAREA.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
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
	
	$scope.gestionarAtributos = function(TIPO_ACCION,cod_tarea){
		$scope.vista = "atributos";
		$scope.getControladorScope("tareaaccionatributo").instanciar(TIPO_ACCION,$scope.cod_proceso,util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea}));
	};

	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.gestionarAccion = function(cod_tarea){
		$scope.data.PROCESO_CARGADO = util.getObjeto($scope.data.PROCESO,{cod_proceso : $scope.cod_proceso});
		$scope.data.TAREA_CARGADA = util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea});
		$scope.vista = "gestionar";
		$scope.urlGestionar = "tareagestionaraccion.html";
	};
	
	$scope.gestionarResumen = function(cod_tarea){
		$scope.data.PROCESO_CARGADO = util.getObjeto($scope.data.PROCESO,{cod_proceso : $scope.cod_proceso});
		$scope.data.TAREA_CARGADA = util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea});
		$scope.vista = "gestionar";
		$scope.urlGestionar = "tareagestionarresumen.html";
	};
	
	$scope.gestionarRoles = function(cod_tarea,tipo){
		var miTarea = util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea});
		var p_tabla,p_titulo;
		if(tipo=="Administrador"){
			p_tabla = "tarea_rol_administrador";
			p_titulo = "ROLES DE ADMINISTRADOR DE TAREA '"+miTarea.nombre+"'";
		}
		if(tipo=="Potencial"){
			p_tabla = "tarea_rol_potencial";
			p_titulo = "ROLES POTENCIALES DE TAREA '"+miTarea.nombre+"'";
		}
		$scope.asociarRoles(cod_tarea,p_titulo,p_tabla,"cod_tarea");
	};
	
	$scope.gestionarPlantillaResumen = function(){
		$scope.vista = "gestionar";
		$scope.urlGestionar = "tarea_plantilla_resumen.html";
	};
	
});