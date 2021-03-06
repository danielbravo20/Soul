﻿mapeo.registerCtrl('tareagestionarresumen', function($scope, ajax, util) {

	$scope.instanciar = function(){
		$scope.cargado = {
			paquete : "modulo",
			clase : "Tarea"
		};
		$scope.seccionItem.subSeccion.lista = [];
		$scope.plantilla.accion="registrar";
		delete $scope.plantilla.nombre;
	}
		
	$scope.editor = {
		esEdicion : true,
		url_atributo_tipo : "plantilla/inc_editor_atributo_sololectura.html",
		fechaInicio : new Date()
	};
	
	$scope.seccionItem = {
		subSeccion : {
			lista : []
		}
	};
	
	func.consulta($scope, ajax, $scope.data.PROYECTO.cod_proyecto, $scope.data.TAREA_CARGADA.cod_con_trabajar);
	
	$scope.subSeccion = {
		agregar : function(){
			var eid = $scope.seccionItem.subSeccion.lista.length;
			$scope.seccionItem.subSeccion.lista.push({
				nombre : "Sub Seccion Nro "+(eid+1),
				atributo : {
					selected : null,
					lista : []
				}
			});
		},
		eliminar : function(){
			var index = $scope.seccionItem.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			$scope.seccionItem.subSeccion.lista.splice(index,1);
		},
		bajar : function(){
			var index = $scope.seccionItem.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index<$scope.seccionItem.subSeccion.lista.length-1){
				$scope.seccionItem.subSeccion.lista.move(index,index+1);
			}
		},
		subir : function(){
			var index = $scope.seccionItem.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index>0){
				$scope.seccionItem.subSeccion.lista.move(index-1,index);
			}
		},
		copiar : function(){
			var index = $scope.seccionItem.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			var newTab = angular.copy($scope.seccionItem.subSeccion.lista[index]);
				newTab.nombre += " Copia";
			$scope.seccionItem.subSeccion.lista.push(newTab);
		}
	};
	
	$scope.atributo = {
		getActual : function(subSeccionIndex){
			return $scope.seccionItem.subSeccion[subSeccionIndex].atributo.actual;
		},
		getLista : function(subSeccionIndex){
			return $scope.seccionItem.subSeccion[subSeccionIndex].atributo.lista;
		},
		eliminar : function(subSeccionIndex){
			var index = $scope.atributo.getActual(subSeccionIndex);
			$("i[eid='atributo_"+index+"']").next().hide();
			$scope.atributo.getLista(subSeccionIndex).splice(index,1);
		},
		bajar : function(subSeccionIndex){
			var index = $scope.atributo.getActual(subSeccionIndex);
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index>0){
				$scope.atributo.getLista(subSeccionIndex).move(index-1,index);
			}
		},
		subir : function(subSeccionIndex){
			var index = $scope.atributo.getActual(subSeccionIndex);
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index<this.getLista(subSeccionIndex).length-1){
				$scope.atributo.getLista(subSeccionIndex).move(index,index+1);
			}
		}
	};
	
	$scope.seccion = {
		url_subSeccion_editor 	: "plantilla/inc_editor_subseccion_editor.html",
		url_atributo 			: "plantilla/inc_editor_atributo.html",
		url_atributo_editor 	: "plantilla/inc_editor_atributo_editor_sololectura.html",
	};
	
	var Ttarea = new jsJPO("TAR");
	
	var Tplantilla = new jsJPO("PLA");
	
	var TsubSeccion = new jsJPO("SUB");
		
	var Tatributo = new jsJPO("ATR");
	
	$scope.cargarAtributo = function(){
		$scope.cargado = {
			paquete : "modulo",
			clase : "Tarea"
		};
		TsubSeccion.donde({cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea});
		Tatributo.donde({cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea});
		TsubSeccion.agregar($scope.cargado);
		Tatributo.agregar($scope.cargado);
		$scope.cargado.metodo = "listarResumen";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.seccionItem.subSeccion.lista = respuesta.SUB_SECCION;
			var index = {};
			for(var i = 0; i < $scope.seccionItem.subSeccion.lista.length ; i++){
				var item = $scope.seccionItem.subSeccion.lista[i];
				index[item.cod_sub_seccion] = i;
				item.atributo = {
					lista : []
				};
			}
			for(var i = 0; i < respuesta.ATRIBUTO.length ; i++){
				var atributoItem = respuesta.ATRIBUTO[i];
				var atributo = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
				atributoItem.cla_nombre = atributo.cla_nombre;
				$scope.seccionItem.subSeccion.lista[index[atributoItem.cod_sub_seccion]].atributo.lista.push(atributoItem);
			}
			$scope.cargado = {
				paquete : "modulo",
				clase : "Tarea"
			};
		});
	};
	
	$scope.plantilla = {
		accion : "registrar",
		lista : [],
		listarPlantillas : function(){
			Tplantilla.donde({});
			TsubSeccion.donde({cod_plantilla : "-1"});
			Tatributo.donde({cod_plantilla : "-1"});
			Tplantilla.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			$scope.cargado.metodo = "listarPlantillaResumen";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.plantilla.lista = respuesta.PLANTILLA;
				for(var i = 0; i < respuesta.PLANTILLA.length ; i++){
					if(respuesta.PLANTILLA[i].cod_plantilla == $scope.data.TAREA_CARGADA.web_plantilla_resumen){
						$scope.plantilla.cargar(respuesta.PLANTILLA[i].cod_plantilla);
						return ;
					}
				}
				$scope.cargarAtributo();
			});
		},
		cargar : function(cod_plantilla){
			$scope.cargado = {
				paquete : "modulo",
				clase : "Tarea"
			};
			$scope.plantilla.accion="esPlantilla";
			$scope.plantilla.cod_plantilla = cod_plantilla;
			Tplantilla.donde({cod_plantilla : cod_plantilla});
			TsubSeccion.donde({cod_plantilla : cod_plantilla});
			Tatributo.donde({cod_plantilla : cod_plantilla});
			Tplantilla.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			$scope.cargado.metodo = "listarPlantillaResumen";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.plantilla.nombre = respuesta.PLANTILLA[0].nombre;
				$scope.seccionItem.subSeccion.lista = respuesta.SUB_SECCION;
				var index = {};
				for(var i = 0; i < $scope.seccionItem.subSeccion.lista.length ; i++){
					var item = $scope.seccionItem.subSeccion.lista[i];
					index[item.cod_sub_seccion] = i;
					item.atributo = {
						lista : []
					};
				}
				for(var i = 0; i < respuesta.ATRIBUTO.length ; i++){
					var atributoItem = respuesta.ATRIBUTO[i];
					var atributo = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
					atributoItem.cla_nombre = atributo.cla_nombre;
					$scope.seccionItem.subSeccion.lista[index[atributoItem.cod_sub_seccion]].atributo.lista.push(atributoItem);
				}
				$scope.cargado = {
					paquete : "modulo",
					clase : "Tarea"
				};
			});
		},
		quitar : function(){
			$scope.instanciar();
		},
		cargarRegistro : function(){
			var condicion = {cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea};
			
			Ttarea.donde(condicion);
			TsubSeccion.donde(condicion);
			Tatributo.donde(condicion);
			
			if($scope.plantilla.accion=="esPlantilla"){
				Ttarea.registrar({web_plantilla_resumen : $scope.plantilla.cod_plantilla});
				$scope.data.TAREA_CARGADA.web_plantilla_resumen = $scope.plantilla.cod_plantilla;
				TsubSeccion.registrarMultiple({});
				Tatributo.registrarMultiple({});
			} else {
				Ttarea.registrar({web_plantilla_resumen : "IS_NULL"});
				var listaSubSeccion = [], listaAtributos = [];
				
				for(var i = 0; i < $scope.seccionItem.subSeccion.lista.length ; i++){
					var subSeccionItem = $scope.seccionItem.subSeccion.lista[i];
					
					listaSubSeccion.push({
						cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea,
						cod_sub_seccion : (i+1),
						nombre : subSeccionItem.nombre
					});
					
					for(var e = 0; e < subSeccionItem.atributo.lista.length ; e++){
						var atributoItem = subSeccionItem.atributo.lista[e];
						
						listaAtributos.push({
							cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea,
							cod_sub_seccion : (i+1),
							cod_tarea_resumen : (e+1),
							cod_atributo : atributoItem.cod_atributo,
							web_etiqueta : atributoItem.web_etiqueta
						});
						
					}
					
				}
				
				TsubSeccion.registrarMultiple(listaSubSeccion);
				Tatributo.registrarMultiple(listaAtributos);
			}
			
			Ttarea.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			
			return true;
		},
		registrar : function(){
			if($scope.plantilla.cargarRegistro()){
				$scope.cargado.metodo = "registrarResumen";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Grabado corréctamente");
					$scope.instanciar();
					$scope.plantilla.listarPlantillas();
				});
			}
		},
	};

	$scope.instanciar();
	$scope.plantilla.listarPlantillas();
	
});
