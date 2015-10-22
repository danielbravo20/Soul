mapeo.registerCtrl('tareaaccionatributo', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.buscarAtributo = {};
	$scope.instanciar = function(TIPO_ACCION,COD_PROCESO,tareaPadre){
		$scope.buscarAtributo.CLASE_NOMBRE = undefined;
		$scope.buscarAtributo.ATRIBUTO_NOMBRE = undefined;
		$scope.cargado = { paquete : "modulo", clase : "TareaAtributo"};
		$scope.listaAtributos = [];
		$scope.tareaPadre = tareaPadre;
		if(TIPO_ACCION=="COMPLETAR"){
			$scope.columnasNro = "8";
		} else {
			$scope.columnasNro = "6";
		}
		$scope.cargado.TIPO_ACCION = TIPO_ACCION;
		$scope.cargado.ATR_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.ATR_W_COD_PROCESO = COD_PROCESO;
		$scope.cargado.ATR_W_COD_TAREA = $scope.tareaPadre.COD_TAREA;
		$scope.listar();
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.TAREA_ATRIBUTO = respuesta;
			$scope.pag.total = $scope.data.TAREA_ATRIBUTO.length;
			cargarListaFiltroClase();
			$scope.cargarAtributos(0,true,function(){
				cargarListaFiltroAtributos();
			});
			
		});
	};
	
	var cargarListaFiltroClase = function(){
		var misClases = {};
		for(var i = 0; i< $scope.data.CLASE.length; i++){
			if(!misClases[$scope.data.CLASE[i].COD_CLASE]){
				misClases[$scope.data.CLASE[i].COD_CLASE] = $scope.data.CLASE[i].NOMBRE;
			}
		}
		for(var i = 0; i< $scope.data.TAREA_ATRIBUTO.length; i++){
			$scope.data.TAREA_ATRIBUTO[i].CLASE_NOMBRE = misClases[$scope.data.TAREA_ATRIBUTO[i].COD_CLASE];
		}
	};
	
	var cargarListaFiltroAtributos = function(){
		var misAtributos = {};
		for(var i in $scope.listaAtributos){
			if(!misAtributos[i]){
				misAtributos[i] = {};
			}
			for(var e in $scope.listaAtributos[i]){
				misAtributos[i][$scope.listaAtributos[i][e].COD_ATRIBUTO] = $scope.listaAtributos[i][e].NOMBRE;
			}
		}
		for(var i = 0; i< $scope.data.TAREA_ATRIBUTO.length; i++){
			var item = $scope.data.TAREA_ATRIBUTO[i];
			$scope.data.TAREA_ATRIBUTO[i].ATRIBUTO_NOMBRE = misAtributos[item.COD_CLASE][item.COD_ATRIBUTO];
		}
	};
	
	var validar = function(){
		var atributoRepetido = [];
		for(var i in $scope.data.TAREA_ATRIBUTO){
			var codAtributo = $scope.data.TAREA_ATRIBUTO[i].COD_ATRIBUTO;
			if(atributoRepetido[atributoRepetido]){
				$scope.agregarAlerta("danger","No debes registrar atributos repetidos, fila Nro "+(i+1));
				return false;
			} else {
				atributoRepetido.push(atributoRepetido);
			}
		}
		return true;
	};
	
	var cargarEnvio = function(){
		var cont = 1;
		for(var i = 0; i< $scope.data.TAREA_ATRIBUTO.length; i++){
			var atributo = $scope.data.TAREA_ATRIBUTO[i];
			$scope.cargado["ATR_M_"+cont+"_COD_PROCESO"] = $scope.cargado.ATR_W_COD_PROCESO;
			$scope.cargado["ATR_M_"+cont+"_COD_PROYECTO"] = $scope.cargado.ATR_W_COD_PROYECTO;
			$scope.cargado["ATR_M_"+cont+"_COD_TAREA"] = $scope.cargado.ATR_W_COD_TAREA;
			$scope.cargado["ATR_M_"+cont+"_COD_ATRIBUTO"] = atributo.COD_ATRIBUTO;
			$scope.cargado["ATR_M_"+cont+"_JAV_VAL_OMISION"] = atributo.JAV_VAL_OMISION;
			$scope.cargado["ATR_M_"+cont+"_WEB_FLG_VALIDACION"] = atributo.WEB_FLG_VALIDACION;
			if($scope.cargado.TIPO_ACCION == 'COMPLETAR'){
				$scope.cargado["ATR_M_"+cont+"_WEB_TAB_CAMPO"] = atributo.WEB_TAB_CAMPO;
				$scope.cargado["ATR_M_"+cont+"_WEB_ORD_VALIDACION"] = atributo.WEB_ORD_VALIDACION;
			}
			cont++;
		}
	};
	
	$scope.guardar = function(){
		if(validar()){
			cargarEnvio();
			$scope.cargado.metodo = "registrar";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Atributos registrados corréctamente");
				$scope.$parent.instanciar(true);
			});
		}
	};
	
	$scope.cargarAtributos = function(index,iterativo,callback){
		if(typeof(iterativo)=="undefined"){
			iterativo = false;
		}
		if($scope.data.TAREA_ATRIBUTO[index]){
			var codClase = $scope.data.TAREA_ATRIBUTO[index].COD_CLASE;
			if(!$scope.listaAtributos[codClase]){
				ajax.jpo({
					paquete : "modulo", 
					clase 	: "Atributo",
					metodo 	: "listaAtributoxPK",
					ATR_W_COD_CLASE : codClase
				},function(respuesta){
					$scope.listaAtributos[codClase] = respuesta;
					if(iterativo){
						$scope.cargarAtributos(index+1,true,callback);
					} else {
						if(callback){
							callback();
						}
					}
				});
			} else {
				if(iterativo){
					$scope.cargarAtributos(index+1,true,callback);
				}
			}
		} else {
			if(callback){
				callback();
			}
		}
	};
	
	$scope.atributoAgregar = function(){
		$scope.data.TAREA_ATRIBUTO.push({});
	};
	
	$scope.atributoEliminar = function($index){
		$scope.data.TAREA_ATRIBUTO.splice($index,1);
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
});