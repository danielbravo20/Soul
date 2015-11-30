mapeo.registerCtrl('editor', function($scope, $modal, $filter, ajax, util) {
	
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
	
	angular.extend($scope.$parent.$parent.editor,{
		esEdicion : true,
		fechaInicio : new Date(),
		tipoClase : $scope.tipoClase,
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
			listaAtributos : [],
			gestionarTipoObservacion : function(){
				var modalInstance = $modal.open({
					animation: true,
					templateUrl: "plantilla/inc_editor_seccion_modelo_observacion_tipoobservacion.html",
					controller: 'inc_editor_seccion_modelo_observacion_tipoobservacion',
					resolve: {
						config : function(){
							return {
								cargarAtributosConsulta : function(){
									return $scope.consulta.atributos;
								},
								cargarAtributos : function(){
									return $scope.editor.observacion.listaAtributos;
								},
								guardar : function(listaObservacion,listaAtributos){
									$scope.editor.observacion.listaObservacion = listaObservacion;
									$scope.editor.observacion.listaAtributos = listaAtributos;
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
		},
		seccion : {
			index_codSeccion : {},
			getSecciones : function(){
				
				var secciones = {};
					secciones.lista = [];
					secciones.plantillas = [];

				for(var e = 0;e < $scope.seccion.lista.length;e++){
					
					var objSeccion = {};
					
					var seccionItem = $scope.seccion.lista[e];
					
					var codSeccion = "";
					if(seccionItem.plantilla && seccionItem.plantilla=="1"){
						
						if(seccionItem.cod_seccion_padre){
							codSeccion = seccionItem.cod_seccion_padre;
						} else {
							codSeccion = new Date().getTime();
							secciones.plantillas.push({
								cod_seccion : codSeccion,
								tipo 		: "P",
								nombre 		: seccionItem.nombre
							});
						}
						$scope.editor.seccion.index_codSeccion[e] = codSeccion;
						objSeccion.cod_seccion_padre = codSeccion;
					}
					
					objSeccion.tipo = (seccionItem.plantilla && seccionItem.plantilla=="1")?"A":seccionItem.tipo;
					
					if(seccionItem.tipo_widget){
						objSeccion.tipo_widget = seccionItem.tipo_widget;
					}
					
					if(!((seccionItem.tipo && seccionItem.tipo=="W") || (seccionItem.plantilla && seccionItem.plantilla=="1"))){
						objSeccion.nombre = seccionItem.nombre;
					}
					
					if(!((seccionItem.tipo && seccionItem.tipo=="W") || seccionItem.cod_seccion_padre)){
						
						if(seccionItem.subSeccion.lista.length==0){
							$scope.agregarAlerta("danger","Debes registrar por lo menos una Sub sección en la sección Nro "+(e+1));
							return false;
						}
						
					}
					
					secciones.lista.push(objSeccion);
					
				}
				
				return secciones;
			},
			index_seccion : {},
			index_seccionPadre : {},
			setSecciones : function(secciones){
				$scope.seccion.lista = [];
				for(var i = 0;i < secciones.length; i++){
					var seccionItem = secciones[i];
					
					$scope.editor.seccion.index_seccion[seccionItem.cod_seccion] = i;
					
					if(seccionItem.tipo=="W"){
						$scope.seccion.agregarWidget(seccionItem.tipo_widget);
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
						$scope.editor.seccion.index_seccionPadre[seccionItem.cod_seccion_padre] = i;
					}
				}
			},
			setSeccionesPlantillas : function(plantilla){
				for(var i = 0; i < plantilla.length ; i++){
					var indexSeccion = $scope.editor.seccion.index_seccionPadre[plantilla[i].cod_seccion];
					$scope.seccion.lista[indexSeccion].nombre = plantilla[i].nombre;
				}
			},
			getSubSecciones : function(){
				var subsecciones = [];
				for(var e = 0;e < $scope.seccion.lista.length;e++){
					var seccionItem = $scope.seccion.lista[e];
					if(!((seccionItem.tipo && seccionItem.tipo=="W") || seccionItem.cod_seccion_padre)){
						for(var i = 0;i<seccionItem.subSeccion.lista.length;i++){
							if(seccionItem.subSeccion.lista[i].atributo.lista.length==0){
								$scope.agregarAlerta("danger","Debes registrar por lo menos un atributo en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
								return false;
							}
							subsecciones.push({
								plantilla : seccionItem.plantilla,
								cod_seccion : (seccionItem.plantilla && seccionItem.plantilla=="1")?$scope.editor.seccion.index_codSeccion[e]:(e+1),
								cod_sub_seccion : (i+1),
								nombre : seccionItem.subSeccion.lista[i].nombre
							})
						}
					}
				}
				return subsecciones;
			},
			index_subSeccion : {},
			index_subSeccionPadre : {},
			setSubSecciones : function(subSecciones){
				for(var i = 0;i < subSecciones.length; i++){
					var subSeccionItem = subSecciones[i];
					if(!$scope.editor.seccion.index_subSeccion[subSeccionItem.cod_seccion]){
						$scope.editor.seccion.index_subSeccion[subSeccionItem.cod_seccion] = {};
					}
					
					var index_seccion = $scope.editor.seccion.index_seccion[subSeccionItem.cod_seccion];
					$scope.editor.seccion.index_subSeccion[subSeccionItem.cod_seccion][subSeccionItem.cod_sub_seccion] = $scope.seccion.lista[index_seccion].subSeccion.lista.length;
					$scope.seccion.lista[index_seccion].subSeccion.lista.push({
						nombre : subSeccionItem.nombre,
						atributo : {
							selected : null,
							lista : []
						}
					});
				}
			},
			index_subSeccionPadre : {},
			setSubSeccionesPlantillas : function(subSecciones){
				for(var e = 0; e < subSecciones.length ; e++){
					var subSeccionItem = subSecciones[e];
					var indexSeccion = $scope.editor.seccion.index_seccionPadre[subSeccionItem.cod_seccion];
					
					if(!$scope.editor.seccion.index_subSeccionPadre[subSeccionItem.cod_seccion]){
						$scope.editor.seccion.index_subSeccionPadre[subSeccionItem.cod_seccion] = {};
					}
					$scope.editor.seccion.index_subSeccionPadre[subSeccionItem.cod_seccion][subSeccionItem.cod_sub_seccion] = $scope.seccion.lista[indexSeccion].subSeccion.lista.length;
					$scope.seccion.lista[indexSeccion].subSeccion.lista.push({
						nombre : subSeccionItem.nombre,
						atributo : {
							selected : null,
							lista : []
						}
					});
				}
			},
			getAtributos : function(atributos){
				
				for(var e = 0;e < $scope.seccion.lista.length;e++){
					
					var seccionItem = $scope.seccion.lista[e];
					
					if(!((seccionItem.tipo && seccionItem.tipo=="W") || seccionItem.cod_seccion_padre)){
						
						for(var i = 0;i<$scope.seccion.lista[e].subSeccion.lista.length;i++){
							
							var atributosdeSubseccion = $scope.seccion.lista[e].subSeccion.lista[i].atributo.lista;
							
							for(var o = 0;o<atributosdeSubseccion.length;o++){
								var atributoItem = atributosdeSubseccion[o];
			
								if(!(atributoItem.web_etiqueta && atributoItem.web_etiqueta.length!=0)){
									atributoItem.inf_error = "Falta registrar etiqueta";
									$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
									return false;
								}
								
								if($scope.editor.atributo_tipo!="sololectura"){
									if(!(atributoItem.web_tipo_campo && atributoItem.web_tipo_campo.length!=0)){
										atributoItem.inf_error = "Falta seleccionar tipo de campo";
										$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
										return false;
									}
									
									if(atributoItem.web_tipo_campo=="E" && !(atributoItem.web_tipo_lista && atributoItem.web_tipo_lista.length!=0)){
										atributoItem.inf_error = "Falta seleccionar el tipo de lista desplegable";
										$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
										return false;
									}
									
									if(atributoItem.web_tipo_campo=="E" && atributoItem.web_tipo_lista=="C" && !(atributoItem.web_catalogo && atributoItem.web_catalogo.length!=0)){
										atributoItem.inf_error = "Falta seleccionar el tipo de catálogo";
										$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
										return false;
									}
									
									if(atributoItem.web_requerido=="1" && !(atributoItem.web_mensaje_validacion && atributoItem.web_mensaje_validacion.length!=0)){
										atributoItem.inf_error = "Falta mensaje de validación";
										$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
										return false;
									}
								}
								
								atributoItem.inf_error = "";
								
								var nuevoAtributo = {
									plantilla 				: seccionItem.plantilla,
									cod_seccion 			: (seccionItem.plantilla && seccionItem.plantilla=="1")?$scope.editor.seccion.index_codSeccion[e]:(e+1),
									cod_sub_seccion 		: (i+1),
									cod_atributo 			: atributoItem.cod_atributo,
									web_etiqueta 			: atributoItem.web_etiqueta
								};
								
								if($scope.editor.atributo_tipo!="sololectura"){
									nuevoAtributo.cod_tipo_accion 		= 'C';
									nuevoAtributo.web_tipo 		 		= atributoItem.web_tipo;
									nuevoAtributo.web_tipo_campo 		= atributoItem.web_tipo_campo;
									nuevoAtributo.web_tipo_lista 		= atributoItem.web_tipo_lista;
									nuevoAtributo.web_catalogo 			= atributoItem.web_catalogo;
									nuevoAtributo.web_requerido 		= atributoItem.web_requerido;
									nuevoAtributo.web_mensaje_validacion= atributoItem.web_mensaje_validacion;
								}
								
								nuevoAtributo[$scope.editor.atributo_codElemento] = (o+1);
								
								atributos.push(nuevoAtributo);
								
							}
			
						}
						
					}
					
				}
				
				return true;
				
			},
			setAtributos : function(atributos){
				for(var i = 0;i < atributos.length; i++){
					var atributoItem = atributos[i];
					if(atributoItem.cod_seccion!='0'){
						var indexSeccion = $scope.editor.seccion.index_seccion[atributoItem.cod_seccion];
						if($scope.editor.seccion.index_subSeccion[atributoItem.cod_seccion]){
							var indexSubseccion = $scope.editor.seccion.index_subSeccion[atributoItem.cod_seccion][atributoItem.cod_sub_seccion];
						}
						var atributoConsulta = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
						
						$scope.seccion.lista[indexSeccion].subSeccion.lista[indexSubseccion].atributo.lista.push(angular.extend({},atributoItem,{
							cla_nombre : atributoConsulta.cla_nombre,
							atr_nombre : atributoConsulta.nombre,
							sql_longitud : atributoConsulta.sql_longitud,
							sql_precision : atributoConsulta.sql_precision
						}));
					}
				}
			},
			setAtributosPlantillas : function(atributos){
				for(var i = 0;i < atributos.length; i++){
					var atributoItem = atributos[i];
					var indexSeccion = $scope.editor.seccion.index_seccionPadre[atributoItem.cod_seccion];
					if($scope.editor.seccion.index_subSeccionPadre[atributoItem.cod_seccion]){
						var indexSubseccion = $scope.editor.seccion.index_subSeccionPadre[atributoItem.cod_seccion][atributoItem.cod_sub_seccion];
					}
					var atributoConsulta = $scope.consulta.atributos[$scope.consulta.atributosId[atributoItem.cod_atributo]];
					
					$scope.seccion.lista[indexSeccion].subSeccion.lista[indexSubseccion].atributo.lista.push(angular.extend({},atributoItem,{
						cla_nombre : atributoConsulta.cla_nombre,
						atr_nombre : atributoConsulta.nombre,
						sql_longitud : atributoConsulta.sql_longitud,
						sql_precision : atributoConsulta.sql_precision
					}));
				}
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
		cargar : function(lista){
			
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
			if($scope.accion && $scope.accion.plantilla && $scope.accion.plantilla.cargarModal){
				$scope.accion.plantilla.cargarModal(function(){
					$scope.seccion.cargarModalPlantilla();
				})
			} else {
				$scope.seccion.cargarModalPlantilla();
			}
		},
		cargarModalPlantilla : function(){
			if($scope.seccion.lista.length>0){
				$scope.seccion.lista[0].activo = true;
			}
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'plantilla/inc_editor_seccion_agregarplantilla.html',
				controller: 'inc_editor_seccion_agregarplantilla',
				resolve: {
					config : function(){
						return {
							accionAgregar : function(idPlantilla,call){
								for(var i = 0;i<$scope.seccion.lista.length;i++){
									if($scope.seccion.lista[i].cod_seccion_padre == idPlantilla){
										$scope.agregarAlerta("danger","Ya se tiene asociado la plantilla seleccionada");
										call();
										return;
									}
								}
								$scope.accion.plantilla.agregar(idPlantilla,call,$scope.seccion.lista.length+1);
							},
							accionEliminar : function(idPlantilla){
								for(var i = 0;i<$scope.seccion.lista.length;i++){
									if($scope.seccion.lista[i].cod_seccion_padre == idPlantilla){
										$scope.seccion.lista.splice(i,1);
										break;
									}
								}
								$scope.agregarAlerta("success","Atributo eliminado correctamente");
							},
							consultaPlantillas : $scope.accion.plantilla.consulta,
							eliminarPlantilla : $scope.accion.plantilla.eliminar,
							vistaPrevia : $scope.accion.plantilla.vistaPrevia
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
			if(!$scope.editor.procesoInstanciar){
				 if(newValue){
					 $scope.seccion.agregarWidget("HIS");
				 } else {
					 $scope.seccion.quitarWidget("HIS");
				 }
			}
		}
	});
	$scope.$watch('editor.seccionDocumentos', function(newValue, oldValue) {
		if(newValue != oldValue){
			if(!$scope.editor.procesoInstanciar){
				 if(newValue){
					 $scope.seccion.agregarWidget("DOC");
				 } else {
					 $scope.seccion.quitarWidget("DOC");
				 }
			}
		}
	});
	$scope.$watch('editor.seccionObservaciones', function(newValue, oldValue) {
		if(newValue != oldValue){
			if(!$scope.editor.procesoInstanciar){
				 if(newValue){
					 $scope.seccion.agregarWidget("OBS");
				 } else {
					 $scope.seccion.quitarWidget("OBS");
				 }
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

mapeo.registerCtrl('inc_editor_seccion_agregarplantilla', function ($scope, $modalInstance, ajax, config) {

	$scope.listaPlantillas = [];
	
	$scope.Agregar = function(){
		config.accionAgregar($scope.idPlantilla,function(){
			$modalInstance.close();
		});
	};
	
	$scope.Cancelar = function(){
		$modalInstance.close();
	};
	
	var cargarPlantillas = function(){
		ajax.jpo(config.consultaPlantillas,function(respuesta){
			$scope.listaPlantillas = respuesta.SECCION;
		});
	};
	
	cargarPlantillas();

	$scope.vistaPrevia = function(){
		ajax.jpo(angular.extend({},config.vistaPrevia,{
			ATR_W_COD_SECCION : $scope.idPlantilla,
			SUB_W_COD_SECCION : $scope.idPlantilla,
			SEC_W_COD_SECCION : $scope.idPlantilla
		}),function(respuesta){
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
			ajax.jpo(angular.extend({},config.eliminarPlantilla,{
				ATR_W_COD_SECCION : $scope.idPlantilla,
				SUB_W_COD_SECCION : $scope.idPlantilla,
				SEC_W_COD_SECCION : $scope.idPlantilla
			}),function(respuesta){
				if(respuesta){
					config.accionEliminar($scope.idPlantilla);
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
	
	$scope.atributosConsulta = angular.copy(config.cargarAtributosConsulta());
	
	$scope.listaAtributos = angular.copy(config.cargarAtributos());
	$scope.agregarAtributo = function(){
		$scope.mensaje = "";
		if(typeof($scope.atributo)!="object"){
			$scope.mensaje = "Seleccione el atributo corréctamente";
			return;
		}
		for(var i = 0;i < $scope.listaAtributos.length; i++){
			if($scope.listaAtributos[i].cod_atributo == $scope.atributo.cod_atributo){
				$scope.mensaje = "Atributo repetido";
				return;
			}
		}
		$scope.listaAtributos.push({
			cod_atributo : $scope.atributo.cod_atributo,
			web_etiqueta : $scope.atributo.web_etiqueta,
			cla_nombre : $scope.atributo.cla_nombre
		});
		delete $scope.atributo;
	};
	$scope.eliminarAtributo = function(index){
		$scope.listaAtributos.splice(index,1);
	};
	
	$scope.guardar = function(){
		if($scope.listaObservacion.length==0){
			$scope.mensaje = "Debe ingresar por lo menos una observación";
			return;
		}
		config.guardar($scope.listaObservacion,$scope.listaAtributos);
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