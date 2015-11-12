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
		$scope.cargado = { paquete : "modulo", clase : "Tarea"};
		$scope.cargado.TAR_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.TAR_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		
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
		if(getParametro.accion && getParametro.accion=="gestionarTarea"){
			$scope.cod_proceso = "1";
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
			if(getParametro.accion && getParametro.accion=="gestionarTarea"){
				$scope.gestionarTarea("1");
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
			$scope.consultaCompletar = respuesta.TAREA_COM_BPM;
			$scope.consultaTrabajar = respuesta.TAREA_TRA_BPM;
		});
	};
	
	var validarConsulta = function(){
		if($scope.consultaCompletar.length>0){
			var consultaCompletarRe = {};
			for(var i in $scope.consultaCompletar){
				var codObjBPM = $scope.consultaCompletar[i].cod_obj_bpm;
				if(consultaCompletarRe[codObjBPM]){
					$scope.agregarAlerta("danger","Accion Completar: No debes registrar objetos BPM repetidos, fila Nro "+(Number(i)+1));
					return false;
				} else {
					consultaCompletarRe[codObjBPM] = true;
				}
			}
		}
		if($scope.consultaTrabajar.length>0){
			var consultaTrabajarRe = {};
			for(var i in $scope.consultaTrabajar){
				var codObjBPM = $scope.consultaTrabajar[i].cod_obj_bpm;
				if(consultaTrabajarRe[codObjBPM]){
					$scope.agregarAlerta("danger","Accion Trabajar: No debes registrar objetos BPM repetidos, fila Nro "+(Number(i)+1));
					return false;
				} else {
					consultaTrabajarRe[codObjBPM] = true;
				}
			}
		}
		return true;
	};
	
	var cargarEnvio = function(){
		for(var i = 0; i< $scope.consultaCompletar.length; i++){
			$scope.cargado["TCB_M_"+(i+1)+"_cod_tarea"] = $scope.cargado.TAR_cod_tarea;
			$scope.cargado["TCB_M_"+(i+1)+"_cod_obj_bpm"] = $scope.consultaCompletar[i].cod_obj_bpm;
		}
		for(var i = 0; i< $scope.consultaTrabajar.length; i++){
			$scope.cargado["TTB_M_"+(i+1)+"_cod_tarea"] = $scope.cargado.TAR_cod_tarea;
			$scope.cargado["TTB_M_"+(i+1)+"_cod_obj_bpm"] = $scope.consultaTrabajar[i].cod_obj_bpm;
		}
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_TAREA.$invalid) { return; }
		if(validarConsulta()){
			cargarEnvio();
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
		}
	};
	
	$scope.gestionarAtributos = function(TIPO_ACCION,cod_tarea){
		$scope.vista = "atributos";
		$scope.getControladorScope("tareaaccionatributo").instanciar(TIPO_ACCION,$scope.cod_proceso,util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea}));
	};
		
	$scope.CompletaCompletarAgregar = function(){
		$scope.consultaCompletar.push({});
	};
	
	$scope.CompletaCompletarEliminar = function($index){
		$scope.consultaCompletar.splice($index,1);
	};
	
	$scope.CompletaTrabajarAgregar = function(){
		$scope.consultaTrabajar.push({});
	};
	
	$scope.CompletaTrabajarEliminar = function($index){
		$scope.consultaTrabajar.splice($index,1);
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.gestionarTarea = function(cod_tarea){
		$scope.vista = "gestionar";
		$scope.getControladorScope("tareagestionar").instanciar($scope.cod_proceso,util.getObjeto($scope.data.TAREA,{cod_tarea : cod_tarea}));
	};
	
});