mapeo.registerCtrl('tarea_plantilla_resumen', function($scope, ajax, util) {

	$scope.instanciar = function(){
		$scope.cargado = {
			paquete : "modulo",
			clase : "Tarea"
		};
		$scope.seccionItem.subSeccion.lista = [];
		$scope.plantilla.accion="registrar";
		delete $scope.plantilla.nombre;
		delete $scope.cod_consulta;
		$scope.plantilla.listarPlantillas();
	}
		
	$scope.editor = {
		esEdicion : true,
		url_atributo_tipo : "plantilla/inc_editor_atributo_sololectura.html"
	};
	
	$scope.seccionItem = {
		subSeccion : {
			lista : []
		}
	};
	
	func.consulta($scope, ajax, $scope.data.PROYECTO.cod_proyecto, 1);
	
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
	
	$scope.cambiarConsulta = function(){
		$scope.consulta.cargarPer($scope.data.PROYECTO.cod_proyecto,$scope.cod_consulta);
	};
	
	var Tseccion = new jsJPO("PLA");
	
	var TsubSeccion = new jsJPO("SUB");
		
	var Tatributo = new jsJPO("ATR");
	
	$scope.plantilla = {
		accion : "registrar",
		lista : [],
		listarPlantillas : function(){
			Tseccion.donde({});
			TsubSeccion.donde({cod_plantilla : "-1"});
			Tatributo.donde({cod_plantilla : "-1"});
			Tseccion.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			$scope.cargado.metodo = "listarPlantillaResumen";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.plantilla.lista = respuesta.PLANTILLA;
			});
		},
		cargar : function(cod_plantilla){
			$scope.plantilla.accion="editar";
			$scope.plantilla.cod_plantilla = cod_plantilla;
			Tseccion.donde({cod_plantilla : cod_plantilla});
			TsubSeccion.donde({cod_plantilla : cod_plantilla});
			Tatributo.donde({cod_plantilla : cod_plantilla});
			Tseccion.agregar($scope.cargado);
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
			});
		},
		cargarRegistro : function(){
			if($scope.plantilla.accion=="editar"){
				var codPlantilla = $scope.plantilla.cod_plantilla;
				var condicion = {cod_plantilla : codPlantilla};
				Tseccion.donde(condicion);
				TsubSeccion.donde(condicion);
				Tatributo.donde(condicion);
			} else {
				var codPlantilla = new Date().getTime();
			}
			
			var listaSubSeccion = [], listaAtributos = [];
			
			for(var i = 0; i < $scope.seccionItem.subSeccion.lista.length ; i++){
				var subSeccionItem = $scope.seccionItem.subSeccion.lista[i];
				
				listaSubSeccion.push({
					cod_plantilla : codPlantilla,
					cod_sub_seccion : (i+1),
					nombre : subSeccionItem.nombre
				});
				
				for(var e = 0; e < subSeccionItem.atributo.lista.length ; e++){
					var atributoItem = subSeccionItem.atributo.lista[e];
					
					listaAtributos.push({
						cod_plantilla : codPlantilla,
						cod_sub_seccion : (i+1),
						cod_tarea_resumen_plantilla_atributo : (e+1),
						cod_atributo : atributoItem.cod_atributo,
						web_etiqueta : atributoItem.web_etiqueta
					});
					
				}
				
			}
						
			Tseccion.registrar({
				cod_plantilla : codPlantilla,
				nombre : $scope.plantilla.nombre
			});
			TsubSeccion.registrarMultiple(listaSubSeccion);
			Tatributo.registrarMultiple(listaAtributos);
			
			Tseccion.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			
			return true;
		},
		registrar : function(){
			if($scope.plantilla.cargarRegistro()){
				$scope.cargado.metodo = $scope.plantilla.accion=="registrar"?"registrarPlantillaResumen":"editarPlantillaResumen";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Grabado corréctamente");
					$scope.instanciar();
				});
			}
		},
		eliminar : function(){
			var condicion = {cod_plantilla : $scope.plantilla.cod_plantilla};
			Tseccion.donde(condicion);
			TsubSeccion.donde(condicion);
			Tatributo.donde(condicion);
			Tseccion.agregar($scope.cargado);
			TsubSeccion.agregar($scope.cargado);
			Tatributo.agregar($scope.cargado);
			$scope.cargado.metodo = "eliminarPlantillaResumen";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("info","Eliminado corréctamente");
				$scope.instanciar();
			});
		},
		clonar : function(){
			$scope.plantilla.accion="registrar";
			$scope.plantilla.registrar();
		}
	};

	$scope.instanciar();
});
