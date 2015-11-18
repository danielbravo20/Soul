mapeo.registerCtrl('tareagestionarresumen', function($scope, ajax, util) {

	$scope.config = {
		esEdicion : true,
		fechaInicio : new Date()
	};
	
	$scope.instanciar = function(vaAListar){ //s
		$scope.cargado = { paquete : "modulo", clase : "Tarea"};
		$scope.cargado["TRS_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		$scope.cargado["TAR_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		
		$scope.consulta.cargar(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				$scope.listar();
			}
		});
	};
	
	$scope.listar = function(cargarPlantillas){
		
		if($scope.data.TAREA_CARGADA.web_plantilla_resumen && $scope.data.TAREA_CARGADA.web_plantilla_resumen.length>0){
			$scope.config.esPlantilla = true;
		} else {
			$scope.config.esPlantilla = false;
		}
		
		if(typeof(cargarPlantillas)=="undefined"){
			cargarPlantillas = true;
		}
		$scope.subSeccion.lista = [];
		$scope.cargado.metodo = "listarResumen";
		ajax.jpo($scope.cargado,function(respuesta){
			if(cargarPlantillas){
				$scope.plantilla.lista = respuesta.PLANTILLA;
				if($scope.config.esPlantilla==true){
					for(var i = 0; i< $scope.plantilla.lista.length ; i++){
						if($scope.plantilla.lista[i].cod_plantilla == $scope.data.TAREA_CARGADA.web_plantilla_resumen){
							$scope.plantilla.cargar(i);
						}
					}
					return false;
				}
				
			}
			if(respuesta.SUB_SECCION && respuesta.SUB_SECCION.length>0){
				for(var i = 0;i< respuesta.SUB_SECCION.length;i++){
					$scope.subSeccion.lista.push({
						nombre : respuesta.SUB_SECCION[i].nombre,
						atributo : {
							selected : null,
							lista : []
						}
					});
				}
				if(respuesta.RESUMEN && respuesta.RESUMEN.length>0){
					for(var i = 0;i< respuesta.RESUMEN.length;i++){
						
						var atributoItem = respuesta.RESUMEN[i];
						
						if(atributoItem.cod_sub_seccion && atributoItem.cod_tarea_resumen){
							if($scope.subSeccion.lista[atributoItem.cod_sub_seccion-1]){
								
								var objAtributo = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
							
								$scope.subSeccion.lista[atributoItem.cod_sub_seccion-1].atributo.lista.push({
									cod_atributo : atributoItem.cod_atributo,
									web_etiqueta : atributoItem.web_etiqueta,
									atr_nombre : atributoItem.nombre,
									cla_nombre : objAtributo.cla_nombre
								});
							}
						}
					}
				}
			}
		});
	};
	
	$scope.subSeccion = {
		lista : [],
		agregar : function(){
			var eid = $scope.subSeccion.lista.length;
			$scope.subSeccion.lista.push({
				nombre : "Sub Seccion Nro "+(eid+1),
				atributo : {
					selected : null,
					lista : []
				}
			});
		},
		eliminar : function(){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			$scope.subSeccion.lista.splice(index,1);
		},
		bajar : function(){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index<$scope.subSeccion.lista.length-1){
				$scope.subSeccion.lista.move(index,index+1);
			}
		},
		subir : function(){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index>0){
				$scope.subSeccion.lista.move(index-1,index);
			}
		},
		copiar : function(){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			var newTab = angular.copy($scope.subSeccion.lista[index]);
				newTab.nombre += " Copia";
			$scope.subSeccion.lista.push(newTab);
		}
	};
		
	$scope.atributo = {
		lista : [],
		listaId : {},
		agregar : function(indice){
			if(typeof($scope.subSeccion.lista[indice].nuevoAtributo)!="undefined"){
				var item = $scope.subSeccion.lista[indice].nuevoAtributo;
				
				$scope.subSeccion.lista[indice].atributo.lista.push({
					cod_atributo : item.cod_atributo,
					web_etiqueta : item.nombre,
					// apoyo
					cla_nombre : item.cla_nombre,
					atr_nombre : item.nombre
				});
				delete $scope.subSeccion.lista[indice].nuevoAtributo;
			}
		},
		eliminar : function(subSeccionIndex){
			var index = $scope.subSeccion.lista[subSeccionIndex].atributo.actual;
			$("i[eid='atributo_"+index+"']").next().hide();
			$scope.subSeccion.lista[subSeccionIndex].atributo.lista.splice(index,1);
		},
		bajar : function(subSeccionIndex){
			var index = $scope.subSeccion.lista[subSeccionIndex].atributo.actual;
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index>0){
				$scope.subSeccion.lista[subSeccionIndex].atributo.lista.move(index-1,index);
			}
		},
		subir : function(subSeccionIndex){
			var index = $scope.subSeccion.lista[subSeccionIndex].atributo.actual;
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index<$scope.subSeccion.lista[subSeccionIndex].atributo.lista.length-1){
				$scope.subSeccion.lista[subSeccionIndex].atributo.lista.move(index,index+1);
			}
		}
	};
	
	var validarRegistro = function(){
		
		var contaAtri = 1;
		
		if($scope.subSeccion.lista.length==0){
			$scope.agregarAlerta("danger","Debes registrar por lo menos una Sub sección");
			return false;
		}
		
		$scope.cargado["TAE_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		
		var codPlantilla = "";
		if($scope.config.esPlantilla==true){
			$scope.cargado["esPlantilla"] = "true";
			if($scope.config.cargoPlantilla){
				codPlantilla = $scope.data.TAREA_CARGADA.web_plantilla_resumen;
			} else {
				codPlantilla = new Date().getTime();
			}
				
			$scope.cargado["TRP_cod_plantilla"] = codPlantilla;
			$scope.cargado["TRP_nombre"] = $scope.config.nombrePlantilla;
			
			$scope.cargado["TAE_web_plantilla_resumen"] = codPlantilla;
		} else {
			$scope.cargado["esPlantilla"] = "false";
			$scope.cargado["TAE_web_plantilla_resumen"] = "";
			$scope.cargado["TRS_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
			$scope.cargado["TAR_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		}
		
		for(var i = 0;i<$scope.subSeccion.lista.length;i++){
			
			var subSeccionItem = $scope.subSeccion.lista[i];
			
			if(subSeccionItem.atributo.lista.length==0){
				$scope.agregarAlerta("danger","Debes registrar por lo menos un atributo en la Sub sección Nro "+(i+1));
				return false;
			}
			for(var e = 0;e<subSeccionItem.atributo.lista.length;e++){
				var atributoItem = subSeccionItem.atributo.lista[e];

				if(!(atributoItem.web_etiqueta && atributoItem.web_etiqueta.length!=0)){
					atributoItem.inf_error = "Falta registrar etiqueta";
					$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1));
					return false;
				}
				
				if($scope.config.esPlantilla==true){
					$scope.cargado["TAR_M_"+(contaAtri)+"_cod_plantilla"] = codPlantilla;
				} else {
					$scope.cargado["TAR_M_"+(contaAtri)+"_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
				}
				
				$scope.cargado["TAR_M_"+(contaAtri)+"_cod_sub_seccion"] = (i+1);
				$scope.cargado["TAR_M_"+(contaAtri)+"_cod_tarea_resumen"] = (e+1);
				$scope.cargado["TAR_M_"+(contaAtri)+"_cod_atributo"] = atributoItem.cod_atributo;
				$scope.cargado["TAR_M_"+(contaAtri)+"_web_etiqueta"] = atributoItem.web_etiqueta;
				
				contaAtri++;				
				
			}

			// CARGAR DATOS DE SUB SECCION
			if($scope.config.esPlantilla==true){
				$scope.cargado["TRS_M_"+(i+1)+"_cod_plantilla"] = codPlantilla;
			} else {
				$scope.cargado["TRS_M_"+(i+1)+"_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
			}
			$scope.cargado["TRS_M_"+(i+1)+"_cod_sub_seccion"] = (i+1);
			$scope.cargado["TRS_M_"+(i+1)+"_nombre"] = subSeccionItem.nombre;

		}
				
		return true;
		
	};
	
	$scope.registrarInicio = function(){
		if(validarRegistro()){
			$scope.cargado.metodo = "registrarResumen";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.data.TAREA_CARGADA.web_plantilla_resumen = $scope.cargado["TAR_W_cod_tarea"];
				$scope.agregarAlerta("success","Registrado corréctamente");
				$scope.instanciar(false);
			});
		}
	};
	
	$scope.consulta = {
		tablas : [],
		atributos : [],
		atributosId : {},
		cargar : function(callback){
			ajax.jpo({
				paquete : "modulo",
				clase : "Consulta",
				metodo : "cargarConsulta",
				CON_COD_PROYECTO : $scope.data.TAREA_CARGADA.cod_proyecto,
				CON_W_COD_CONSULTA : $scope.data.TAREA_CARGADA.cod_con_trabajar,
				CON_W_COD_PROYECTO : $scope.data.TAREA_CARGADA.cod_proyecto,
				CAT_W_COD_CONSULTA : $scope.data.TAREA_CARGADA.cod_con_trabajar,
				CTA_W_COD_CONSULTA : $scope.data.TAREA_CARGADA.cod_con_trabajar
			},function(respuesta){
				
				for(var i = 0; i<respuesta.CONSULTA_TABLA.length ; i++){
					if(!$scope.consulta.tablas[respuesta.CONSULTA_TABLA[i].cod_tabla]){
						$scope.consulta.tablas[respuesta.CONSULTA_TABLA[i].cod_tabla] = respuesta.CONSULTA_TABLA[i];
					}
				}
				
				for(var i = 0; i<respuesta.CONSULTA_ATRIBUTO.length ; i++){
					respuesta.CONSULTA_ATRIBUTO[i].web_tipo = respuesta.CONSULTA_ATRIBUTO[i].tipo;
					respuesta.CONSULTA_ATRIBUTO[i].web_etiqueta = respuesta.CONSULTA_ATRIBUTO[i].etiqueta;
					respuesta.CONSULTA_ATRIBUTO[i].atr_nombre = respuesta.CONSULTA_ATRIBUTO[i].nombre;
					if(!$scope.consulta.atributosId[respuesta.CONSULTA_ATRIBUTO[i].cod_atributo]){
						$scope.consulta.atributosId[respuesta.CONSULTA_ATRIBUTO[i].cod_atributo] = i;
					}
				}
				
				$scope.consulta.atributos = respuesta.CONSULTA_ATRIBUTO;
				
				if(callback){
					callback();
				}
				
			});
		}
	};
	
	$scope.plantilla = {
		lista : [],
		cargar : function($index){
			
			delete $scope.cargado["TRS_W_cod_tarea"];
			delete $scope.cargado["TAR_W_cod_tarea"];
			
			$scope.cargado["TAR_W_cod_plantilla"] = $scope.plantilla.lista[$index].cod_plantilla;
			$scope.cargado["TRS_W_cod_plantilla"] = $scope.plantilla.lista[$index].cod_plantilla;
			$scope.cargado["TRP_W_cod_plantilla"] = $scope.plantilla.lista[$index].cod_plantilla;
			
			$scope.config.nombrePlantilla = $scope.plantilla.lista[$index].nombre;
			$scope.config.cargoPlantilla = true;
			$scope.listar(false);
			
		},
		clonar : function(){
			delete $scope.cargado["TRS_W_cod_plantilla"];
			$scope.cargado["TRS_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
			
			delete $scope.cargado["TAR_W_cod_plantilla"];
			$scope.cargado["TAR_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
			
			$scope.cargado["TRP_W_cod_plantilla"] = "";
			$scope.config.cargoPlantilla = false;
			
			$scope.config.esClonar = true;
			$scope.config.nombrePlantilla = $scope.config.nombrePlantilla + " Copia";
		},
		eliminar : function($index){
			if(confirm("Desea eliminar la plantilla seleccionada")){
				$scope.cargado.metodo = "eliminarPlantilla";
				$scope.cargado["TRP_W_cod_plantilla"] = $scope.plantilla.lista[$index].cod_plantilla;
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Eliminado corréctamente");
					$scope.plantilla.lista.splice($index,1);
				});
			}
		}
	};
	
	$scope.instanciar();

});
