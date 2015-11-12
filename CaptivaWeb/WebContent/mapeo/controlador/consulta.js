mapeo.registerCtrl('consulta', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.tablaId = {};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Consulta"};
		$scope.cargado.CON_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.CON_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.esEdicion = false;
		$scope.cargado.CON_cod_consulta = util.getUltimoCodigo($scope.data.CONSULTA,"cod_consulta");
		$scope.consultaTablas = [];
		$scope.consultaAtributos = [];
		$scope.tablasSeleccionadas = [];
		$scope.listaAtributos = {};
		$scope.consultaTablaEsFK = "";
		
		$scope.tablaId = {};
		for(var i in $scope.data.TABLA){
			if(!$scope.tablaId[$scope.data.TABLA[i].cod_tabla]){
				$scope.tablaId[$scope.data.TABLA[i].cod_tabla] = $scope.data.TABLA[i];
			}
		}
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.CONSULTA.length;
		}
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.CONSULTA = respuesta;
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.eliminar = function(cod_consulta){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.CAT_W_cod_consulta = cod_consulta;		// WHERE-------
			$scope.cargado.CTA_W_cod_consulta = cod_consulta;		// WHERE-------
			$scope.cargado.CON_W_cod_consulta = cod_consulta;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_consulta){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.metodo = "editarCargar";
		$scope.cargado.CON_W_cod_consulta = cod_consulta;			// WHERE-------
		$scope.cargado.CTA_W_cod_consulta = cod_consulta;			// WHERE-------
		$scope.cargado.CAT_W_cod_consulta = cod_consulta;			// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			util.jpoCargar($scope.cargado,respuesta.CONSULTA,"CON");
			$scope.consultaTablas = respuesta.CONSULTA_TABLA;
			for(var i in respuesta.CONSULTA_TABLA){
				if(respuesta.CONSULTA_TABLA[i].fk=="0"){
					$scope.consultaTablaEsFK = i;
				}
			}
			$scope.consultaAtributos = respuesta.CONSULTA_ATRIBUTO;
			$scope.cargarSeleccionadas();
			$scope.cargarAtributos(0,true);
		});
	};
	
	var validarConsulta = function(){
		if($scope.consultaTablas.length == 0){
			$scope.agregarAlerta("danger","Debes agregar por lo menos 1 tabla");
			return false;
		}
		if($scope.consultaAtributos.length == 0){
			$scope.agregarAlerta("danger","Debes agregar por lo menos 1 atributo");
			return false;
		}
		var tablasRepeditas = {};
		for(var i in $scope.consultaTablas){
			var codTabla = $scope.consultaTablas[i].cod_tabla;
			if(tablasRepeditas[codTabla]){
				$scope.agregarAlerta("danger","No debes registrar tablas repetidas, fila Nro "+(Number(i)+1));
				return false;
			} else {
				tablasRepeditas[codTabla] = true;
			}
		}
		return true;
	};
	
	var cargarEnvio = function(){
		var cont = 1;
		var tablaPK = $scope.consultaTablas[Number($scope.consultaTablaEsFK)];
		$scope.cargado["CTA_W_cod_consulta"] = $scope.cargado.CON_cod_consulta;
		for(var i = 0; i< $scope.consultaTablas.length; i++){
			var tabla = $scope.consultaTablas[i];
			$scope.cargado["CTA_M_"+cont+"_cod_consulta"] = $scope.cargado.CON_cod_consulta;
			$scope.cargado["CTA_M_"+cont+"_cod_tabla"] = tabla.cod_tabla;
			$scope.cargado["CTA_M_"+cont+"_FK"] = (Number($scope.consultaTablaEsFK)==i)?"0":"1";
			$scope.cargado["CTA_M_"+cont+"_FLG_UNO_MUCHOS"] = (tabla.FLG_UNO_MUCHOS)?tabla.FLG_UNO_MUCHOS:"0";
			$scope.cargado["CTA_M_"+cont+"_COD_TAB_PADRE"] = tablaPK.cod_tabla;
			cont++;
		}
		cont = 1;
		$scope.cargado["CAT_W_cod_consulta"] = $scope.cargado.CON_cod_consulta;
		for(var i = 0; i< $scope.consultaAtributos.length; i++){
			var atributo = $scope.consultaAtributos[i];
			$scope.cargado["CAT_M_"+cont+"_cod_consulta"] = $scope.cargado.CON_cod_consulta;
			$scope.cargado["CAT_M_"+cont+"_cod_tabla"] = atributo.cod_tabla;
			$scope.cargado["CAT_M_"+cont+"_cod_atributo"] = atributo.cod_atributo;
			$scope.cargado["CAT_M_"+cont+"_flg_condicion"] = (atributo.flg_condicion)?atributo.flg_condicion:"0";
			cont++;
		}
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_CONSULTA.$invalid) { return; }
		if(validarConsulta()){
			cargarEnvio();
			if($scope.esEdicion){
				$scope.cargado.metodo = "editar";
				$scope.cargado.CON_W_cod_consulta = $scope.cargado.CON_cod_consulta;			// WHERE-------
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
	
	$scope.gestionarAtributos = function(cod_consulta){
		$scope.vista = "atributos";
		$scope.getControladorScope("atributo").instanciar(true,util.getObjeto($scope.data.CONSULTA,{cod_consulta : cod_consulta}));
	};
	
	// TABLAS Y ATRIBUTOS 
	
	$scope.tablaAgregar = function(){
		for(var i = 0 ; i < $scope.consultaTablas.length ; i++){
			if($scope.consultaTablas[i].cod_tabla == $scope.codTablaNueva){
				$scope.agregarAlerta("danger","La tabla seleccionada ya se encuentra ingresada");
				return false;
			}
		}
		$scope.consultaTablas.push({
			cod_consulta : $scope.CON_W_cod_consulta,
			cod_tabla : $scope.codTablaNueva,
			cod_tab_padre : 1,
			fk : "0",
			flg_uno_muchos : "0"
		});
		$scope.cargarSeleccionadas();
		delete $scope.codTablaNueva;
	};
	
	$scope.tablaEliminar = function($index){
		var codTab = $scope.consultaTablas[$index].cod_tabla;
		eliminarTablaAtributo(codTab);
		$scope.consultaTablas.splice($index,1);
	};
	
	var eliminarTablaAtributo = function(codTabla){
		for(var i = 0;i < $scope.consultaAtributos.length; i++){
			if($scope.consultaAtributos[i].cod_tabla == codTabla){
				$scope.consultaAtributos.splice(i,1);
				eliminarTablaAtributo(codTabla);
				break;
			}
		}
	}
	
	$scope.cargarSeleccionadas = function(){
		$scope.tablasSeleccionadas = [];
		var repetidas = {};
		for(var i in $scope.consultaTablas){
			if($scope.consultaTablas[i].cod_tabla && !repetidas[$scope.consultaTablas[i].cod_tabla]){
				$scope.tablasSeleccionadas.push(util.getObjeto($scope.data.TABLA,{cod_tabla : $scope.consultaTablas[i].cod_tabla}));
				repetidas[$scope.consultaTablas[i].cod_tabla] = true;
			}
		}
	};
	
	$scope.listaAtributosId = {};
	
	$scope.cargarAtributo = function(){
		var codTabla = $scope.Atrib_codTablaNueva;
		ajax.jpo({
			paquete : "modulo", 
			clase 	: "Atributo",
			metodo 	: "listaAtributoxPK",
			ATR_W_COD_CLASE : codTabla
		},function(respuesta){
			$scope.Atrib_listaAtributos = respuesta;
			$scope.listaAtributos[codTabla] = respuesta;
			$scope.listaAtributosId[codTabla] = {};
			for(var i = 0;i < respuesta.length; i++){
				if(!$scope.listaAtributosId[codTabla][respuesta[i].cod_atributo]){
					$scope.listaAtributosId[codTabla][respuesta[i].cod_atributo] = respuesta[i]
				}
			}
		});
	};
	
	$scope.cargarAtributos = function(index,iterativo){
		if(typeof(iterativo)=="undefined"){
			iterativo = false;
		}
		if($scope.consultaAtributos[index]){
			var codTabla = $scope.consultaAtributos[index].cod_tabla;
			if(!$scope.listaAtributos[codTabla]){
				$scope.listaAtributosId[codTabla] = {};
				ajax.jpo({
					paquete : "modulo", 
					clase 	: "Atributo",
					metodo 	: "listaAtributoxPK",
					ATR_W_COD_CLASE : codTabla
				},function(respuesta){
					$scope.listaAtributos[codTabla] = respuesta;
					for(var i = 0;i < respuesta.length; i++){
						if(!$scope.listaAtributosId[codTabla][respuesta[i].cod_atributo]){
							$scope.listaAtributosId[codTabla][respuesta[i].cod_atributo] = respuesta[i]
						}
					}
					if(iterativo){
						$scope.cargarAtributos(index+1,true);
					}
				});
			} else {
				if(iterativo){
					$scope.cargarAtributos(index+1,true);
				}
			}
		}
	};
	
	$scope.atributoAgregar = function(){
		if(!$scope.Atrib_codAtributoNuevo){
			$scope.agregarAlerta("danger","Debes seleccionar un atributo");
			return false;
		}
		for(var i = 0;i< $scope.consultaAtributos.length; i++){
			if($scope.consultaAtributos[i].cod_atributo == $scope.Atrib_codAtributoNuevo.cod_atributo){
				$scope.agregarAlerta("danger","El atributo seleccionado ya se encuentra ingresado");
				return false;
			}
		}
		$scope.consultaAtributos.push({
			cod_consulta : $scope.CON_W_cod_consulta,
			cod_tabla : $scope.Atrib_codTablaNueva,
			cod_atributo : $scope.Atrib_codAtributoNuevo.cod_atributo,
			flg_condicion : "0"
		});
		delete $scope.Atrib_codTablaNueva;
		delete $scope.Atrib_codAtributoNuevo;
	};
	
	$scope.atributoEliminar = function($index){
		$scope.consultaAtributos.splice($index,1);
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.cargarReplicar = function($index){
	};
	
	
});