﻿mapeo.registerCtrl('consulta', function($scope, ajax, util) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Consulta"};
		$scope.cargado.CON_W_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.CON_COD_PROYECTO = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.esEdicion = false;
		
		$scope.consultaTablas = [];
		$scope.consultaAtributos = [];
		$scope.tablasSeleccionadas = [];
		$scope.listaAtributos = {};
		$scope.consultaTablaEsFK = "";
		
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
	
	$scope.eliminar = function(COD_CONSULTA){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.CAT_W_COD_CONSULTA = COD_CONSULTA;		// WHERE-------
			$scope.cargado.CTA_W_COD_CONSULTA = COD_CONSULTA;		// WHERE-------
			$scope.cargado.CON_W_COD_CONSULTA = COD_CONSULTA;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(COD_CONSULTA){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.metodo = "editarCargar";
		$scope.cargado.CON_W_COD_CONSULTA = COD_CONSULTA;			// WHERE-------
		$scope.cargado.CTA_W_COD_CONSULTA = COD_CONSULTA;			// WHERE-------
		$scope.cargado.CAT_W_COD_CONSULTA = COD_CONSULTA;			// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			util.jpoCargar($scope.cargado,respuesta.CONSULTA,"CON");
			$scope.consultaTablas = respuesta.CONSULTA_TABLA;
			for(var i in respuesta.CONSULTA_TABLA){
				if(respuesta.CONSULTA_TABLA[i].FK=="0"){
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
			var codTabla = $scope.consultaTablas[i].COD_TABLA;
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
		$scope.cargado["CTA_W_COD_CONSULTA"] = $scope.cargado.CON_COD_CONSULTA;
		for(var i = 0; i< $scope.consultaTablas.length; i++){
			var tabla = $scope.consultaTablas[i];
			$scope.cargado["CTA_M_"+cont+"_COD_CONSULTA"] = $scope.cargado.CON_COD_CONSULTA;
			$scope.cargado["CTA_M_"+cont+"_COD_TABLA"] = tabla.COD_TABLA;
			$scope.cargado["CTA_M_"+cont+"_FK"] = (Number($scope.consultaTablaEsFK)==i)?"0":"1";
			$scope.cargado["CTA_M_"+cont+"_FLG_UNO_MUCHOS"] = (tabla.FLG_UNO_MUCHOS)?tabla.FLG_UNO_MUCHOS:"0";
			$scope.cargado["CTA_M_"+cont+"_COD_TAB_PADRE"] = tablaPK.COD_TABLA;
			cont++;
		}
		cont = 1;
		$scope.cargado["CAT_W_COD_CONSULTA"] = $scope.cargado.CON_COD_CONSULTA;
		for(var i = 0; i< $scope.consultaAtributos.length; i++){
			var atributo = $scope.consultaAtributos[i];
			$scope.cargado["CAT_M_"+cont+"_COD_CONSULTA"] = $scope.cargado.CON_COD_CONSULTA;
			$scope.cargado["CAT_M_"+cont+"_COD_TABLA"] = atributo.COD_TABLA;
			$scope.cargado["CAT_M_"+cont+"_COD_ATRIBUTO"] = atributo.COD_ATRIBUTO;
			$scope.cargado["CAT_M_"+cont+"_FLG_CONDICION"] = (atributo.FLG_CONDICION)?atributo.FLG_CONDICION:"0";
			$scope.cargado["CAT_M_"+cont+"_FLG_VISIBLE"] = "1";
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
				$scope.cargado.CON_W_COD_CONSULTA = $scope.cargado.CON_COD_CONSULTA;			// WHERE-------
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
	
	$scope.gestionarAtributos = function(COD_CONSULTA){
		$scope.vista = "atributos";
		$scope.getControladorScope("atributo").instanciar(true,util.getObjeto($scope.data.CONSULTA,{COD_CONSULTA : COD_CONSULTA}));
	};
	
	// TABLAS Y ATRIBUTOS 
	
	$scope.tablaAgregar = function(){
		$scope.consultaTablas.push({});
	};
	
	$scope.tablaEliminar = function($index){
		$scope.consultaTablas.splice($index,1);
	};
	
	$scope.cargarSeleccionadas = function(){
		$scope.tablasSeleccionadas = [];
		var repetidas = [];
		for(var i in $scope.consultaTablas){
			if($scope.consultaTablas[i].COD_TABLA && !repetidas[$scope.consultaTablas[i].COD_TABLA]){
				$scope.tablasSeleccionadas.push(util.getObjeto($scope.data.TABLA,{COD_TABLA : $scope.consultaTablas[i].COD_TABLA}));
				repetidas.push($scope.consultaTablas[i].COD_TABLA);
			}
		}
	};

	$scope.cargarAtributos = function(index,iterativo){
		if(typeof(iterativo)=="undefined"){
			iterativo = false;
		}
		if($scope.consultaAtributos[index]){
			var codTabla = $scope.consultaAtributos[index].COD_TABLA;
			if(!$scope.listaAtributos[codTabla]){
				ajax.jpo({
					paquete : "modulo", 
					clase 	: "Atributo",
					metodo 	: "listaAtributoxPK",
					ATR_W_COD_CLASE : codTabla
				},function(respuesta){
					$scope.listaAtributos[codTabla] = respuesta;
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
		$scope.consultaAtributos.push({});
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