mapeo.registerCtrl('procesogestionardetalle', function($scope, $filter, ajax, util) {

	// EDITOR ----------------------------------
	$scope.editor = {
		fechaInicio : new Date(),
		atributo_tipo : "sololectura",
		atributo_codElemento : "cod_proceso_detalle"
	}
	// EDITOR ----------------------------------
	
	var condicion = {
		cod_proyecto : $scope.data.PROYECTO.cod_proyecto,
		cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso
	};
	
	var Tproceso = new jsJPO("PRO");
		Tproceso.donde({
			cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso
		});
	
	var Tseccion = new jsJPO("SEC");
		Tseccion.donde({
			cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso
		});
		
	var TsubSeccion = new jsJPO("SUB");
		TsubSeccion.donde({
			cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso
		});
		
	var Tatributos = new jsJPO("ATR");
		Tatributos.donde({
			cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso
		});
	
	func.consulta($scope, ajax, $scope.data.PROYECTO.cod_proyecto, $scope.data.PROCESO_CARGADO.cod_con_detalle);
	
	$scope.accion = {
		plantilla : {
			cargarModal : function(call){
				$scope.registrarInicio(call);
			},
			agregar : function(idPlantilla,call,cod_seccion){
				ajax.jpo({
					paquete : "modulo",
					clase : "Proceso",
					metodo : "agregarSeccion",
					SEC_cod_proceso : $scope.data.PROCESO_CARGADO.cod_proceso,
					SEC_cod_seccion : cod_seccion,
					SEC_tipo : "A",
					SEC_cod_seccion_padre : idPlantilla,
				},function(respuesta){
					if(respuesta){
						if(call){
							$scope.listar();
							call();
						}
					}
				});
			},
			consulta : {
				paquete : "modulo",
				clase : "Proceso",
				metodo : "listarDetalle",
				SEC_W_tipo : 'P'
			},
			eliminar : {
				paquete : "modulo",
				clase : "Proceso",
				metodo : 'eliminarDetalle'
			},
			vistaPrevia : {
				paquete : "modulo",
				clase : "Proceso",
				metodo : "listarDetalle"
			}
		}
	};
	
	var detalle = {
		index_seccion : {},
		index_subSeccion : {},
		index_seccionPadre : {},
		seccionesPadre : [],
		cargar : function(){
			$scope.cargado.metodo = "listarDetalle";
			ajax.jpo($scope.cargado,function(respuesta){
				
				for(var i = 0;i < respuesta.SECCION.length; i++){
					var seccionItem = respuesta.SECCION[i];
					detalle.index_seccion[seccionItem.cod_seccion] = i;
					
					if(seccionItem.tipo=="W"){
						$scope.seccion.lista.push({
							nombre : $scope.widget.widgetNombre[seccionItem.tipo_widget],
							tipo : seccionItem.tipo,
							tipo_widget : seccionItem.tipo_widget,
							tipo_widget_url : $scope.widget.widgetUrl[seccionItem.tipo_widget],
							activo : true,
							subSeccion : {
								lista : []
							}
						});
					} else {
						$scope.seccion.lista.push({
							nombre : seccionItem.nombre,
							tipo : seccionItem.tipo,
							activo : true,
							plantilla : (seccionItem.tipo=="A")?"1":"0",
							cod_seccion_padre : seccionItem.cod_seccion_padre,
							subSeccion : {
								lista : []
							}
						});
					}
					if(seccionItem.tipo=="A"){
						detalle.index_seccionPadre[seccionItem.cod_seccion_padre] = i;
						detalle.seccionesPadre.push(seccionItem.cod_seccion_padre);
					}
				}
				
				for(var i = 0;i < respuesta.SUB_SECCION.length; i++){
					var subSeccionItem = respuesta.SUB_SECCION[i];
					if(!detalle.index_subSeccion[subSeccionItem.cod_seccion]){
						detalle.index_subSeccion[subSeccionItem.cod_seccion] = {};
					}
					detalle.index_subSeccion[subSeccionItem.cod_seccion][subSeccionItem.cod_sub_seccion] = $scope.seccion.lista[detalle.index_seccion[subSeccionItem.cod_seccion]].subSeccion.lista.length;
					$scope.seccion.lista[detalle.index_seccion[subSeccionItem.cod_seccion]].subSeccion.lista.push({
						nombre : subSeccionItem.nombre,
						atributo : {
							selected : null,
							lista : []
						}
					});
				}

				for(var i = 0;i < respuesta.DETALLE.length; i++){
					var atributoItem = respuesta.DETALLE[i];
					var indexSeccion = detalle.index_seccion[atributoItem.cod_seccion];
					var indexSubseccion = detalle.index_subSeccion[atributoItem.cod_seccion][atributoItem.cod_sub_seccion];
					
					var atributoConsulta = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
					
					$scope.seccion.lista[indexSeccion].subSeccion.lista[indexSubseccion].atributo.lista.push(angular.extend({},atributoConsulta,{
						web_etiqueta : atributoItem.web_etiqueta
					}));
				}
				
				detalle.cargarPlantillas();
				
			});
		},
		cargarPlantillas : function(){
			if(detalle.seccionesPadre.length>0){
				var codigoSecciones = detalle.seccionesPadre.join(",");
				$scope.cargado.metodo = "listarDetalle";
				ajax.jpo({
					paquete : "modulo",
					clase : "Proceso",
					metodo : "listarDetalle",
					PDS_I_COD_SECCION : codigoSecciones,
					PDB_I_COD_SECCION : codigoSecciones,
					PDA_I_COD_SECCION : codigoSecciones,
				},function(respuesta){
					for(var i = 0; i < respuesta.SECCION.length ; i++){
						var indexSeccion = detalle.index_seccionPadre[respuesta.SECCION[i].cod_seccion];
						$scope.seccion.lista[indexSeccion].nombre = respuesta.SECCION[i].nombre;
					}
					var index_subSeccionPadre = {};
					for(var e = 0; e < respuesta.SUB_SECCION.length ; e++){
						var subSeccionItem = respuesta.SUB_SECCION[e];
						var indexSeccion = detalle.index_seccionPadre[subSeccionItem.cod_seccion];
						
						if(!index_subSeccionPadre[subSeccionItem.cod_seccion]){
							index_subSeccionPadre[subSeccionItem.cod_seccion] = {};
						}
						index_subSeccionPadre[subSeccionItem.cod_seccion][subSeccionItem.cod_sub_seccion] = $scope.seccion.lista[indexSeccion].subSeccion.lista.length;
						$scope.seccion.lista[indexSeccion].subSeccion.lista.push({
							nombre : subSeccionItem.nombre,
							atributo : {
								selected : null,
								lista : []
							}
						});
					}
					for(var i = 0;i < respuesta.DETALLE.length; i++){
						var atributoItem = respuesta.DETALLE[i];
						var indexSeccion = detalle.index_seccionPadre[atributoItem.cod_seccion];
						var indexSubseccion = index_subSeccionPadre[atributoItem.cod_seccion][atributoItem.cod_sub_seccion];
						
						var atributoConsulta = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
						
						$scope.seccion.lista[indexSeccion].subSeccion.lista[indexSubseccion].atributo.lista.push(angular.extend({},atributoConsulta,{
							web_etiqueta : atributoItem.web_etiqueta
						}));
					}
				});
			}
		}
	};

	var cargarInstanciar = function(){
		var proceso =  $scope.data.PROCESO_CARGADO;
		$scope.editor.tipoVista = proceso.web_detalle_tipovista;
	};
	
	$scope.instanciar = function(vaAListar){
		$scope.cargado = { paquete : "modulo", clase : "Proceso"};
		$scope.consulta.cargar(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				$scope.listar();
			} else {
				cargarInstanciar();
			}
		});
	};
	
	$scope.listar = function(){
		Tseccion.agregar($scope.cargado);
		TsubSeccion.agregar($scope.cargado);
		Tatributos.agregar($scope.cargado);
		$scope.cargado.metodo = "listarDetalle";
		ajax.jpo($scope.cargado,function(respuesta){

			$scope.data.PROCESO = respuesta.PROCESO;
			$scope.data.PROCESO_CARGADO = $filter('filter')(respuesta.PROCESO, {cod_proceso : condicion.cod_proceso})[0];
			
			cargarInstanciar();
			
			if(respuesta.SECCION.length>0){
				$scope.editor.seccion.setSecciones(respuesta.SECCION);
			}
			if(respuesta.SECCION_PLANTILLA && respuesta.SECCION_PLANTILLA.length>0){
				$scope.editor.seccion.setSeccionesPlantillas(respuesta.SECCION_PLANTILLA);
			}
			if(respuesta.SUB_SECCION.length>0){
				$scope.editor.seccion.setSubSecciones(respuesta.SUB_SECCION);
			}
			if(respuesta.SUB_SECCION_PLANTILLA && respuesta.SUB_SECCION_PLANTILLA.length>0){
				$scope.editor.seccion.setSubSeccionesPlantillas(respuesta.SUB_SECCION_PLANTILLA);
			}
			if(respuesta.ATRIBUTO.length>0){
				$scope.editor.seccion.setAtributos(respuesta.ATRIBUTO);
			} 
			if(respuesta.ATRIBUTO_PLANTILLA && respuesta.ATRIBUTO_PLANTILLA.length>0){
				$scope.editor.seccion.setAtributosPlantillas(respuesta.ATRIBUTO_PLANTILLA);
			}
		});
	};
	
	var cargarRegistro = function(){
		
		Tproceso.registrar({
			web_detalle_tipovista : $scope.editor.tipoVista
		});
		Tproceso.agregar($scope.cargado);
		
		var secciones = $scope.editor.seccion.getSecciones();
		if(secciones){
			Tseccion.correlativo("cod_seccion");
			Tseccion.registrarMultiple(secciones.lista);
			Tseccion.registrarMultipleAdicional({
				cod_proceso : condicion.cod_proceso
			});
			Tseccion.agregar($scope.cargado);
			
			var TseccionAdd = new jsJPO("SEA");
				TseccionAdd.registrarMultiple(secciones.plantillas);
				TseccionAdd.agregar($scope.cargado);

		} else {
			return false;
		}
		
		var SubSecciones = $scope.editor.seccion.getSubSecciones();
		if(SubSecciones){
			TsubSeccion.registrarMultiple(SubSecciones);
			TsubSeccion.registrarMultiplePersonalizado(function(item){
				if(!(item.plantilla && item.plantilla=="1")){
					item.cod_proceso = condicion.cod_proceso;
				}
				delete item.plantilla;
				return item;
			});
			TsubSeccion.agregar($scope.cargado);
		} else {
			return false;
		}
		
		var atributos = [];

			if(!$scope.editor.seccion.getAtributos(atributos)){
				return false;
			}
			
			Tatributos.registrarMultiple(atributos);
			Tatributos.registrarMultiplePersonalizado(function(item){
				if(!(item.plantilla && item.plantilla=="1")){
					item.cod_proceso = condicion.cod_proceso;
				}
				delete item.plantilla;
				return item;
			});
			Tatributos.agregar($scope.cargado);
		
		return true;
		
	};
	
	$scope.registrarInicio = function(call){
		if(cargarRegistro()){
			$scope.cargado.metodo = "registrarDetalle";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Registrado corréctamente");
				$scope.instanciar(false);
				if(call){
					call();
				}
			});
		}
	};
	
	$scope.instanciar();

});