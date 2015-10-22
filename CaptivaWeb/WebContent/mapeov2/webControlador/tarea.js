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
		if($scope.COD_PROCESO && $scope.COD_PROCESO!=""){
			$scope.cargado.TAR_W_COD_PROCESO = $scope.COD_PROCESO;
			$scope.cargado.TAR_COD_PROCESO = $scope.COD_PROCESO;
		}
		$scope.esEdicion = false;
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		}
		/*DUMMY */
		if(getParametro.accion && getParametro.accion=="gestionarTarea"){
			$scope.COD_PROCESO = "1";
		}
	};
	
	$scope.$watch("COD_PROCESO",function(oldVal,newVal){
		if(oldVal != newVal){
			if($scope.COD_PROCESO != ""){
				$scope.listar();
			}
		}
	});
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		$scope.cargado.TAR_W_COD_PROCESO = $scope.COD_PROCESO;
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
	
	$scope.eliminar = function(COD_TAREA){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.TAR_W_COD_TAREA = COD_TAREA;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(COD_TAREA){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.metodo = "editarCargar";
		$scope.cargado.TAR_W_COD_TAREA = COD_TAREA;			// WHERE-------
		$scope.cargado.TCB_W_COD_TAREA = COD_TAREA;			// WHERE-------
		$scope.cargado.TTB_W_COD_TAREA = COD_TAREA;			// WHERE-------
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
				var codObjBPM = $scope.consultaCompletar[i].COD_OBJ_BPM;
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
				var codObjBPM = $scope.consultaTrabajar[i].COD_OBJ_BPM;
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
			$scope.cargado["TCB_M_"+(i+1)+"_COD_TAREA"] = $scope.cargado.TAR_COD_TAREA;
			$scope.cargado["TCB_M_"+(i+1)+"_COD_OBJ_BPM"] = $scope.consultaCompletar[i].COD_OBJ_BPM;
		}
		for(var i = 0; i< $scope.consultaTrabajar.length; i++){
			$scope.cargado["TTB_M_"+(i+1)+"_COD_TAREA"] = $scope.cargado.TAR_COD_TAREA;
			$scope.cargado["TTB_M_"+(i+1)+"_COD_OBJ_BPM"] = $scope.consultaTrabajar[i].COD_OBJ_BPM;
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
	
	$scope.gestionarAtributos = function(TIPO_ACCION,COD_TAREA){
		$scope.vista = "atributos";
		$scope.getControladorScope("tareaaccionatributo").instanciar(TIPO_ACCION,$scope.COD_PROCESO,util.getObjeto($scope.data.TAREA,{COD_TAREA : COD_TAREA}));
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
	
	$scope.gestionarTarea = function(COD_TAREA){
		$scope.vista = "gestionar";
		$scope.getControladorScope("tareagestionar").instanciar($scope.COD_PROCESO,util.getObjeto($scope.data.TAREA,{COD_TAREA : COD_TAREA}));
	};
	
});