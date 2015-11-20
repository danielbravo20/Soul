mapeo.registerCtrl('editor', function($scope, $modal, $filter, ajax, util) {
	
	angular.extend($scope.$parent.$parent.editor,{
		esEdicion : true,
		fechaInicio : new Date(),
		url_atributo_tipo : "plantilla/inc_editor_atributo_"+$scope.editor.atributo_tipo+".html", // detallado | sololectura
		documento : {
			listaDocumentos : [],
			gestionar : function(){
				var modalInstance = $modal.open({
					animation: true,
					templateUrl: "plantilla/inc_editor_seccion_modelo_documentos_gestionar.html",
					controller: 'inc_editor_seccion_modelo_documentos_gestionar',
					resolve: {
						config : function(){
							return {
								guardar : function(listaDocumentos){
									$scope.editor.documento.listaDocumentos = listaDocumentos;
								},
								cargarMaeDocumentos : function(){
									return $scope.data.MAE_DOCUMENTO;
								},
								cargar : function(){
									return $scope.editor.documento.listaDocumentos;
								},
								cargarObservaciones : function(){
									return $scope.editor.observacion.listaObservacion;
								}
							};
						}
					}
				});
				modalInstance.result.then(function(){
				});
				
			}
		},
		observacion : {
			listaObservacion : [],
			gestionarTipoObservacion : function(){
				var modalInstance = $modal.open({
					animation: true,
					templateUrl: "plantilla/inc_editor_seccion_modelo_observacion_tipoobservacion.html",
					controller: 'inc_editor_seccion_modelo_observacion_tipoobservacion',
					resolve: {
						config : function(){
							return {
								guardar : function(listaObservacion){
									$scope.editor.observacion.listaObservacion = listaObservacion;
									$scope.editor.observacion.contarSubsanaciones();
								},
								cargar : function(){
									return $scope.editor.observacion.listaObservacion;
								}
							};
						}
					}
				});
				modalInstance.result.then(function(){
				});
				
			},
			subsanacionesAgregadas : 0,
			contarSubsanaciones : function(){
				var totalSubsanaciones = 0;
				for(var i = 0 ; i < $scope.editor.observacion.listaObservacion.length; i++){
					if(
						$scope.editor.observacion.listaObservacion[i].listaSubsanaciones &&
						$scope.editor.observacion.listaObservacion[i].listaSubsanaciones.length>0
					){
						totalSubsanaciones++;
					}
				}
				$scope.editor.observacion.subsanacionesAgregadas = totalSubsanaciones;
			},
			gestionarTipoSubsanacion : function(){
				var modalInstance = $modal.open({
					animation: true,
					templateUrl: "plantilla/inc_editor_seccion_modelo_observacion_tiposubsanacion.html",
					controller: 'inc_editor_seccion_modelo_observacion_tiposubsanacion',
					resolve: {
						config : function(){
							return {
								guardar : function(listaObservacion){
									$scope.editor.observacion.listaObservacion = listaObservacion;
									$scope.editor.observacion.contarSubsanaciones();
								},
								cargar : function(){
									return $scope.editor.observacion.listaObservacion;
								},
								cargarDocumentos : function(){
									return $filter('filter')($scope.editor.documento.listaDocumentos, {tipo : "F",es_obligatorio : "1"});
								}
							};
						}
					}
				});
				modalInstance.result.then(function(){
				});
			}
		}
	});
	
	$scope.$watch('editor.tipoVista', function(newValue, oldValue) {
		if(newValue != oldValue){
			if(newValue=="H"){
				$scope.editor.url_seccion = "plantilla/inc_editor_seccion_acordeon.html";
			} else if (newValue=="V"){
				$scope.editor.url_seccion = "plantilla/inc_editor_seccion_tab.html";
			}
		}
	});
	
	$scope.tipoClase = {
		"Integer" : "I",
		"Long" : "L",
		"long" : "l",
		"String" : "S",
		"boolean" : "b",
		"java.math.BigDecimal" : "B",
		"java.util.Date" : "D",
		"java.sql.Date" : "D",
		"java.sql.Timestamp" : "T"
	};
	
	var tipoCampo_Integer = [
         {
        	 id 	: "L",
        	 nombre : "Solo Lectura"
         },
         {
        	 id 	: "C",
        	 nombre : "Caja de Texto Numérico"
         }
	];
	
	$scope.tipoCampo = {
		"I" : angular.copy(tipoCampo_Integer),
		"L" : angular.copy(tipoCampo_Integer),
		"l" : angular.copy(tipoCampo_Integer),
		"S" : [
				{
					 id 	: "L",
					 nombre : "Solo Lectura"
				},
		        {
		       	 	id 		: "C",
		       	 	nombre : "Caja de Texto"
		        },
		        {
		       	 	id 		: "A",
		       	 	nombre : "Area de Texto"
		        },
		        {
		       	 	id 		: "E",
		       	 	nombre : "Lista desplegable"
		        }
			],
		"b" : [
				{
					 id 	: "L",
					 nombre : "Solo Lectura"
				},
		        {
		       	 	id 		: "H",
		       	 	nombre : "Checkbox"
		        }             
			],
		"B" : [
				{
					 id 	: "L",
					 nombre : "Solo Lectura"
				},
		        {
		       	 	id 		: "C",
		       	 	nombre : "Caja de Texto Decimal"
		        }             
			],
		"D" : [
				{
					 id 	: "L",
					 nombre : "Solo Lectura"
				},
		        {
		       	 	id 		: "C",
		       	 	nombre : "Caja de Texto Fecha"
		        }             
			],
		"T" : [
				{
					 id 	: "L",
					 nombre : "Solo Lectura"
				},
		        {
		       	 	id 		: "C",
		       	 	nombre : "Caja de Texto Fecha y Hora"
		        }             
			]
	};
	
	$scope.seccion = {
		actual : -1,
		lista : [],
		url_seccion_editor 		: "plantilla/inc_editor_seccion_editor.html",
		url_subSeccion 			: "plantilla/inc_editor_subseccion.html",
		url_subSeccion_editor 	: "plantilla/inc_editor_subseccion_editor.html",
		url_atributo 			: "plantilla/inc_editor_atributo.html",
		url_atributo_editor 	: "plantilla/inc_editor_atributo_editor_"+$scope.editor.atributo_tipo+".html",
		agregar : function(){
			var eid = $scope.seccion.lista.length+1;
			$scope.seccion.lista.push({
				nombre : "Seccion Nro "+eid,
				tipo : "S",
				activo : true,
				subSeccion : {
					lista : []
				}
			});
			$scope.seccion.lista[eid-1].activo = true;
		},
		agregarWidget : function(widget){
			for(var i = 0; i< $scope.seccion.lista.length;i++){
				if($scope.seccion.lista[i].tipo=="W" && $scope.seccion.lista[i].tipo_widget == widget){
					return false;
				}
			}
			var eid = $scope.seccion.lista.length+1;
			$scope.seccion.lista.push({
				nombre : $scope.widget.widgetNombre[widget],
				tipo : "W",
				tipo_widget : widget,
				tipo_widget_url : $scope.widget.widgetUrl[widget],
				activo : true,
				subSeccion : {
					lista : []
				}
			});
			$scope.seccion.lista[eid-1].activo = true;
		},
		quitarWidget : function(widget){
			for(var i = 0; i< $scope.seccion.lista.length;i++){
				if($scope.seccion.lista[i].tipo=="W" && $scope.seccion.lista[i].tipo_widget == widget){
					$("i[eid='seccionEditar_"+i+"']").next().hide();
					$scope.seccion.lista.splice(i,1);
				}
			}
		},
		eliminar : function(){
			var index = $scope.seccion.actual-1;
			$("i[eid='seccionEditar_"+index+"']").next().hide();
			if($scope.seccion.lista[index].tipo=="W"){
				$scope.config[$scope.widget.widgetRadios[$scope.seccion.lista[index].tipo_widget]] = "0";
			}
			$scope.seccion.lista.splice(index,1);
		},
		bajar : function(){
			var index = $scope.seccion.actual-1;
			$("i[eid='seccionEditar_"+index+"']").next().hide();
			if(index>0){
				$scope.seccion.lista.move(index-1,index);
			}
			$scope.seccion.actual = $scope.seccion.actual-1;
		},
		subir : function(){
			var index = $scope.seccion.actual-1;
			$("i[eid='seccionEditar_"+index+"']").next().hide();
			if(index<$scope.seccion.lista.length-1){
				$scope.seccion.lista.move(index,index+1);
			}
			$scope.seccion.actual = $scope.seccion.actual+1;
		},
		copiar : function(){
			var index = $scope.seccion.actual-1;
			$("i[eid='seccionEditar_"+index+"']").next().hide();
			var newTab = angular.copy($scope.seccion.lista[index]);
				newTab.nombre += " Copia";
			$scope.seccion.lista.push(newTab);
		},
		asignarTab : function(nroIndex){
			$("i").next(".popover").hide($index);
			$scope.seccion.actual = nroIndex + 1;
		},
		buscarPlantilla : function(){
			if($scope.seccion.lista.length>0){
				$scope.seccion.lista[0].activo = true;
			}
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'modal_agregarPlantilla.html',
				controller: 'modal_agregarPlantilla',
				resolve: {
					config : function(){
						return {
							accionEliminar : function(idPlantilla){
								for(var i = 0;i<$scope.seccion.lista.length;i++){
									if($scope.seccion.lista[i].cod_seccion_padre == idPlantilla){
										$scope.seccion.lista.splice(i,1);
										break;
									}
								}
							}
						};
					}
				}
			});
			modalInstance.result.then(function(){	
			});
		}
	};
	
	$scope.subSeccion = {
		getSubSeccion : function(){
			return $scope.seccion.lista[$scope.seccion.actual-1].subSeccion;
		},
		agregar : function(){
			var eid = this.getSubSeccion().lista.length;
			this.getSubSeccion().lista.push({
				nombre : "Sub Seccion Nro "+(eid+1),
				atributo : {
					selected : null,
					lista : []
				}
			});
		},
		eliminar : function(){
			var index = this.getSubSeccion().actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			this.getSubSeccion().lista.splice(index,1);
		},
		bajar : function(){
			var index = this.getSubSeccion().actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index<this.getSubSeccion().lista.length-1){
				this.getSubSeccion().lista.move(index,index+1);
			}
		},
		subir : function(){
			var index = this.getSubSeccion().actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index>0){
				this.getSubSeccion().lista.move(index-1,index);
			}
		},
		copiar : function(){
			var index = this.getSubSeccion().actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			var newTab = angular.copy(this.getSubSeccion().lista[index]);
				newTab.nombre += " Copia";
			this.getSubSeccion().lista.push(newTab);
		}
	};
		
	$scope.atributo = {
		getSeccion : function(subSeccionIndex){
			return $scope.subSeccion.getSubSeccion().lista[subSeccionIndex];
		},
		getActual : function(subSeccionIndex){
			return $scope.atributo.getSeccion(subSeccionIndex).atributo.actual;
		},
		getLista : function(subSeccionIndex){
			return $scope.atributo.getSeccion(subSeccionIndex).atributo.lista;
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
		},
		restaurarAtributo : function(atributoItem){
			atributoItem.web_requerido = "0";
			delete atributoItem.web_mensaje_validacion;
			delete atributoItem.web_catalogo;
			delete atributoItem.web_tipo_lista;
			delete atributoItem.web_catalogo;
			
			if(atributoItem.web_tipo_campo == "L"){
				atributoItem.web_modelo = "Valor Solo Lectura";
			}
			if(($scope.tipoClase[atributoItem.web_tipo]=='I' || $scope.tipoClase[atributoItem.web_tipo]=='L' || $scope.tipoClase[atributoItem.web_tipo]=='l') && atributoItem.web_tipo_campo == 'C'){
				atributoItem.web_modelo_numero = 0;
			}
			if($scope.tipoClase[atributoItem.web_tipo]=='S' && atributoItem.web_tipo_campo == 'C'){ // input
				atributoItem.web_modelo = "";
			}
			if($scope.tipoClase[atributoItem.web_tipo]=='S' && atributoItem.web_tipo_campo == 'A'){ // textarea
				atributoItem.web_modelo = "";
			}
			if($scope.tipoClase[atributoItem.web_tipo]=='S' && atributoItem.web_tipo_campo == 'E'){ // Select
				atributoItem.web_modelo = "";
			}
			if($scope.tipoClase[atributoItem.web_tipo]=='b' && atributoItem.web_tipo_campo == 'H'){ // checkbox
				atributoItem.web_modelo = true;
			}
			if($scope.tipoClase[atributoItem.web_tipo]=='B' && atributoItem.web_tipo_campo == 'C'){ // Decimal
				atributoItem.web_modelo = "";
			}		
			if($scope.tipoClase[atributoItem.web_tipo]=='D' && atributoItem.web_tipo_campo == 'C'){
				atributoItem.web_modelo_fecha = new Date();
			}
		}
	};
	
	// WIDGETS
	
	$scope.$watch('editor.seccionHistorial', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue){
				 $scope.seccion.agregarWidget("HIS");
			 } else {
				 $scope.seccion.quitarWidget("HIS");
			 }
		}
	});
	$scope.$watch('editor.seccionDocumentos', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue){
				 $scope.seccion.agregarWidget("DOC");
			 } else {
				 $scope.seccion.quitarWidget("DOC");
			 }
		}
	});
	$scope.$watch('editor.seccionObservaciones', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue){
				 $scope.seccion.agregarWidget("OBS");
			 } else {
				 $scope.seccion.quitarWidget("OBS");
			 }
		}
	});
	
	$scope.widget = {
		widgetNombre : {
			"HIS" : "Historial de Tareas",
			"DOC" : "Documentos",
			"OBS" : "Observaciones y Subsanaciones"
		},
		widgetRadios : {
			"HIS" : "seccionHistorial",
			"DOC" : "seccionDocumentos",
			"OBS" : "seccionObservaciones"
		},
		widgetUrl : {
			"HIS" : "plantilla/inc_editor_seccion_modelo_historial.html",
			"DOC" : "plantilla/inc_editor_seccion_modelo_documentos.html",
			"OBS" : "plantilla/inc_editor_seccion_modelo_observaciones.html"
		}
	};
	
});

mapeo.registerCtrl('modal_agregarPlantilla', function ($scope, $modalInstance, ajax, config) {

	$scope.listaPlantillas = [];
	
	$scope.Agregar = function(){
		$modalInstance.close();
	};
	
	$scope.Cancelar = function(){
		$modalInstance.close();
	};
	
	var cargarPlantillas = function(){
		ajax.jpo({
			paquete : "modulo",
			clase : "Tarea",
			metodo : "listarAccion",
			TSE_W_TIPO : 'P',
			TSU_W_COD_SECCION : "-1",
			TAC_W_COD_SECCION : "-1"
		},function(respuesta){
			$scope.listaPlantillas = respuesta.SECCION;
		});
	};
	
	cargarPlantillas();
	
	$scope.vistaPrevia = function(){
		ajax.jpo({
			paquete : "modulo",
			clase : "Tarea",
			metodo : "listarAccion",
			TSE_W_COD_SECCION : $scope.idPlantilla,
			TSU_W_COD_SECCION : $scope.idPlantilla,
			TAC_W_COD_SECCION : $scope.idPlantilla
		},function(respuesta){
			$scope.subSeccionLista = [];
			var indexSubSeccion = {};
			for(var i = 0; i < respuesta.SUB_SECCION.length ; i++){
				indexSubSeccion[respuesta.SUB_SECCION[i].cod_sub_seccion] = i;
				$scope.subSeccionLista.push({
					nombre : respuesta.SUB_SECCION[i].nombre,
					atributo : {
						lista : []
					}
				});
			}

			for(var i = 0;i < respuesta.ATRIBUTO.length; i++){
				var atributoItem = respuesta.ATRIBUTO[i];
				$scope.subSeccionLista[indexSubSeccion[atributoItem.cod_sub_seccion]].atributo.lista.push({
					web_etiqueta : atributoItem.web_etiqueta
				});
			}
		});
	};
	
	$scope.eliminar = function(){
		if(confirm("Desea eliminar la plantilla seleccionada?")){
			ajax.jpo({
				paquete : "modulo",
				clase : "Tarea",
				metodo : 'eliminarAccion',
				TSE_W_COD_SECCION : $scope.idPlantilla,
				TSU_W_COD_SECCION : $scope.idPlantilla,
				TAC_W_COD_SECCION : $scope.idPlantilla
			},function(respuesta){
				if(respuesta){
					editor.accionEliminar($scope.idPlantilla);
					alert("atributo eliminado correctamente");
					$modalInstance.close();
				}
			});
		};
	};
	
});

mapeo.registerCtrl('inc_editor_seccion_modelo_documentos_gestionar', function ($scope, $modalInstance, config) {

	$scope.maeDocumentos = angular.copy(config.cargarMaeDocumentos());

	$scope.listaDocumentos = angular.copy(config.cargar());
	$scope.agregarDocumento = function(){
		$scope.mensaje = "";
		if(typeof($scope.meastroDocumento)!="object"){
			$scope.mensaje = "Seleccione el documento corréctamente";
			return;
		}
		$scope.listaDocumentos.push({
			maeDocumento : $scope.meastroDocumento,
			tipo : "F",
			es_obligatorio : "1",
			estado : "1"
		});
		delete $scope.meastroDocumento;
	};
	$scope.eliminarDocumento = function(index){
		var objObservaciones = config.cargarObservaciones();
		for(var i = 0;i < objObservaciones.length; i++){
			var obse = objObservaciones[i];
			for(var e = 0;e < obse.listaSubsanaciones.length; e++){
				var subsa = obse.listaSubsanaciones[e];
				if(subsa.cod_mae_documento_tarea == $scope.listaDocumentos[index].cod_mae_documento_tarea){
					if(confirm("El documento se encuentra asociado a subsanaciones, desea eliminarlo de todas maneras?")){
						$scope.listaDocumentos.splice(index,1);
						delete subsa.cod_mae_documento_tarea;
						break;
					} else {
						$scope.$apply();
						return;
					}
				}
			}
		}
		$scope.listaDocumentos.splice(index,1);
	};
	
	$scope.guardar = function(){
		if($scope.listaDocumentos.length==0){
			$scope.mensaje = "Debe ingresar por lo menos un documento";
			return;
		}
		for(var i = 0;i < $scope.listaDocumentos.length; i++){
			$scope.listaDocumentos[i].cod_mae_documento_tarea = i+1;
		}
		config.guardar($scope.listaDocumentos);
		$modalInstance.close();
	};
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});

mapeo.registerCtrl('inc_editor_seccion_modelo_observacion_tipoobservacion', function ($scope, $modalInstance, config) {

	$scope.listaObservacion = angular.copy(config.cargar());
	$scope.agregar = function(){
		$scope.mensaje = "";
		if(!($scope.observacion && $scope.observacion.nombre.length!=0)){
			$scope.mensaje = "Ingrese una observación correcta";
			return;
		}
		for(var i = 0;i < $scope.listaObservacion.length; i++){
			if($scope.listaObservacion[i].nombre == $scope.observacion.nombre){
				$scope.mensaje = "Observación repetida";
				return;
			}
		}
		$scope.observacion.estado = "1";
		$scope.listaObservacion.push($scope.observacion);
		delete $scope.observacion;
	};
	$scope.eliminar = function(index){
		$scope.listaObservacion.splice(index,1);
	};
	
	$scope.guardar = function(){
		if($scope.listaObservacion.length==0){
			$scope.mensaje = "Debe ingresar por lo menos una observación";
			return;
		}
		config.guardar($scope.listaObservacion);
		$modalInstance.close();
	};
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});

mapeo.registerCtrl('inc_editor_seccion_modelo_observacion_tiposubsanacion', function ($scope, $modalInstance, config) {

	$scope.listaObservacion = angular.copy(config.cargar()); // listaSubsanaciones
	$scope.listaDocumentos = angular.copy(config.cargarDocumentos());
	$scope.agregar = function(){
		if(!$scope.observacion.listaSubsanaciones){
			$scope.observacion.listaSubsanaciones = [];
		}
		$scope.mensaje = "";
		if(!($scope.subsanacion && $scope.subsanacion.nombre.length!=0)){
			$scope.mensaje = "Ingrese una subsanación correcta";
			return;
		}
		for(var i = 0;i < $scope.observacion.listaSubsanaciones.length; i++){
			if($scope.observacion.listaSubsanaciones[i].nombre == $scope.subsanacion.nombre){
				$scope.mensaje = "Subsanación repetida";
				return;
			}
		}
		$scope.subsanacion.estado = "1";
		$scope.subsanacion.tipo_sustento = "T";
		$scope.observacion.listaSubsanaciones.push($scope.subsanacion);
		delete $scope.subsanacion;
	};
	$scope.eliminar = function(index){
		$scope.observacion.listaSubsanaciones.splice(index,1);
	};
	
	$scope.limpiarSubsanacion = function(){
		delete $scope.subsanacion;
	};
	$scope.guardar = function(){
		var totalSubsana = 0;
		for(var i = 0;i < $scope.listaObservacion.length; i++){
			if($scope.listaObservacion[i].listaSubsanaciones && $scope.listaObservacion[i].listaSubsanaciones.length>0){
				totalSubsana++;
			}
		}
		if(totalSubsana!=$scope.listaObservacion.length){
			$scope.mensaje = "Debe registrar todas las subsanaciones por observación";
			return;
		}
		for(var i = 0;i < $scope.listaObservacion.length; i++){
			var obse = $scope.listaObservacion[i];
			for(var e = 0;e < obse.listaSubsanaciones.length; e++){
				var subsa = obse.listaSubsanaciones[e];
				if(subsa.tipo_sustento == "A" && !subsa.cod_mae_documento_tarea){
					$scope.mensaje = "Debe asociar el adjunto obligatorio para la subsanación \""+subsa.nombre+"\" del adjunto \""+obse.nombre+"\"";
					return;
				}
			}
		}
		config.guardar($scope.listaObservacion);
		$modalInstance.close();
	};
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});