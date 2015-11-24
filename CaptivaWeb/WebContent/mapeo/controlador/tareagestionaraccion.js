mapeo.registerCtrl('tareagestionaraccion', function($scope, $modal, $filter, ajax, util) {

	// EDITOR ----------------------------------
	$scope.editor = {
		atributo_tipo : "detallado"
	};
	// EDITOR ----------------------------------
	
	var condicion = {
		cod_proyecto : $scope.data.PROYECTO.cod_proyecto,
		cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
	};
	
	// JPO
	var Ttarea = new jsJPO("TAR");
		Ttarea.donde({
			cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
		});
	
	var Tcancelar = new jsJPO("MMC");
		Tcancelar.donde(condicion);
		
	var Trechazar = new jsJPO("MMR");
		Trechazar.donde(condicion);
		
	var Tdocumento = new jsJPO("MDT");
		Tdocumento.donde(condicion);
		
	var Tobservar = new jsJPO("MOB");
		Tobservar.donde(condicion);
		
	var Tsubsanar = new jsJPO("MSU");
		Tsubsanar.donde(condicion);
		
	var Tseccion = new jsJPO("SEC");
		Tseccion.donde({
			cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
		});
		
	var TsubSeccion = new jsJPO("SUB");
		TsubSeccion.donde({
			cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
		});
		
	var Tatributos = new jsJPO("ATR");
		Tatributos.donde({
			cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
		});

	func.consulta($scope, ajax, condicion.cod_proyecto, $scope.data.TAREA_CARGADA.cod_con_trabajar);
	
	$scope.accion = {
		plantilla : {
			cargarModal : function(call){
				$scope.registrarInicio(call);
			},
			agregar : function(idPlantilla,call,cod_seccion){
				ajax.jpo({
					paquete : "modulo",
					clase : "Tarea",
					metodo : "agregarSeccion",
					SEC_cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea,
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
				clase : "Tarea",
				metodo : "listarSeccion",
				SEC_W_tipo : 'P'
			},
			eliminar : {
				paquete : "modulo",
				clase : "Tarea",
				metodo : 'eliminarSeccion'
			},
			vistaPrevia : {
				paquete : "modulo",
				clase : "Tarea",
				metodo : "listarAccion"
			}
		},
		tarea : {
			listaAtributos: [],
			agregarAtributo : function(){
				$scope.mensaje = "";
				if(typeof($scope.accion.tarea.atributo)!="object"){
					$scope.agregarAlerta("danger","Seleccione el atributo corréctamente");
					return;
				}
				for(var i = 0;i < $scope.accion.tarea.listaAtributos.length; i++){
					if($scope.accion.tarea.listaAtributos[i].cod_atributo == $scope.accion.tarea.atributo.cod_atributo){
						$scope.agregarAlerta("danger","Atributo repetido");
						return;
					}
				}
				$scope.accion.tarea.listaAtributos.push({
					cod_atributo : $scope.accion.tarea.atributo.cod_atributo,
					web_etiqueta : $scope.accion.tarea.atributo.web_etiqueta,
					cla_nombre : $scope.accion.tarea.atributo.cla_nombre
				});
				delete $scope.accion.tarea.atributo;
			},
			eliminarAtributo : function(index){
				$scope.accion.tarea.listaAtributos.splice(index,1);
			}
		},
		rechazo : {
			activo : false,
			listaMotivos : [],
			listaAtributos : [],
			abrirModal : function(){
				$scope.accion.gestionarAccionDenegar("rechazo","Cancelación");
			}
		},
		cancelar : {
			activo : false,
			listaMotivos : [],
			listaAtributos : [],
			abrirModal : function(){
				$scope.accion.gestionarAccionDenegar("cancelar","Rechazo");
			}
		},
		gestionarAccionDenegar : function(motivo,titulo){
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'tareagestionaraccion_modal_denegar.html',
				controller: 'tareagestionaraccion_modal_denegar',
				resolve: {
					config : function(){
						return {
							guardar : function(listaMotivos,listaAtributos){
								$scope.accion[motivo].listaMotivos = listaMotivos;
								$scope.accion[motivo].listaAtributos = listaAtributos;
							},
							cargarAtributosConsulta : function(){
								return $scope.consulta.atributos;
							},
							cargarAtributos : function(){
								return $scope.accion[motivo].listaAtributos;
							},
							cargar : function(){
								return $scope.accion[motivo].listaMotivos;
							},
							titulo : titulo
						};
					}
				}
			});
			modalInstance.result.then(function(){
			});
		}
	};
	
	var cargarInstanciar = function(){
		var tarea =  $scope.data.TAREA_CARGADA;
		$scope.accion.cancelar.activo = tarea.web_acc_cancelar == "1"?true : false;
		$scope.accion.rechazo.activo = tarea.web_acc_rechazar == "1"?true : false;
		$scope.accion.grabar = tarea.web_acc_grabar == "1"?true : false;
		$scope.editor.tipoVista = tarea.tipo_vista;
		$scope.editor.seccionHistorial = tarea.web_wid_historial == "1"?true : false;
		$scope.editor.seccionDocumentos = tarea.web_wid_documento == "1"?true : false;
		$scope.editor.observacion.tipoSubsanar = tarea.web_acc_subsanar == "1"?true : false;
	};
	
	$scope.instanciar = function(vaAListar){
		$scope.cargado = { paquete : "modulo", clase : "Tarea"};
		$scope.consulta.cargar(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				$scope.listar();
			} else {
				cargarInstanciar();
			}
		});
	};
	
	var cargarAtributoAdicional = function(objeto,filtro){
		var filtrado = $filter('filter')(objeto, filtro);
		if(filtrado && filtrado.length>0){
			for(var i = 0 ; i < filtrado.length; i++){
				var atributo = $scope.consulta.atributos[$scope.consulta.atributosId[filtrado[i].cod_atributo]];
				filtrado[i].web_etiqueta = atributo.web_etiqueta;
				filtrado[i].cla_nombre = atributo.cla_nombre;
			}
			return filtrado;
		}
		return [];
	};
	
	var enviarAtributos = function(objAtributo,obj,lista,identificador){
		if(obj && obj[lista]){
			for(var i = 0; i < obj[lista].length ; i++){
				var item = obj[lista][i];
				objAtributo.push({
					cod_seccion : "0",
					cod_tarea_accion : (i+1),
					cod_tipo_accion : identificador,
					cod_atributo : item.cod_atributo,
					val_omision : item.val_omision
				});
			}
		}
	};
	
	$scope.listar = function(){
		Tcancelar.agregar($scope.cargado);
		Trechazar.agregar($scope.cargado);
		Tdocumento.agregar($scope.cargado);
		Tobservar.agregar($scope.cargado);
		Tsubsanar.agregar($scope.cargado);
		Tseccion.agregar($scope.cargado);
		TsubSeccion.agregar($scope.cargado);
		Tatributos.agregar($scope.cargado);
		$scope.cargado.metodo = "listarAccion";
		ajax.jpo($scope.cargado,function(respuesta){
			
			$scope.data.TAREA = respuesta.TAREA;
			$scope.data.TAREA_CARGADA = $filter('filter')(respuesta.TAREA, {cod_tarea : condicion.cod_tarea})[0];
			
			cargarInstanciar();
			
			$scope.accion.cancelar.listaMotivos = respuesta.CANCELAR;
			$scope.accion.rechazo.listaMotivos = respuesta.RECHAZAR;
			if(respuesta.DOCUMENTO.length>0){
				$scope.editor.seccionDocumentos = true;
				for(var i = 0; i < respuesta.DOCUMENTO.length; i++){
					respuesta.DOCUMENTO[i].maeDocumento = $filter('filter')($scope.data.MAE_DOCUMENTO, {cod_mae_documento : respuesta.DOCUMENTO[i].cod_mae_documento})[0];
				}
				$scope.editor.documento.listaDocumentos = respuesta.DOCUMENTO;
			}
			if($scope.data.TAREA_CARGADA.web_acc_observar == "1"){
				
				$scope.editor.seccionObservaciones = true;
				$scope.editor.observacion.tipoObservar = true;
				$scope.editor.observacion.cod_tarea = $scope.data.TAREA_CARGADA.cod_tarea_observado;
		
				for(var i = 0; i < respuesta.OBSERVACION.length; i++){
					var obs = respuesta.OBSERVACION[i];
					obs.cod_mae_observacion
					
					if(!obs.listaSubsanaciones){
						obs.listaSubsanaciones = [];
					}
					
					for(var e = 0; e < respuesta.SUBSANACION.length; e++){
						var sub = respuesta.SUBSANACION[e];
						
						if(obs.cod_mae_observacion == sub.cod_mae_observacion){
							obs.listaSubsanaciones.push(sub);
						}
						
					}
				}
				$scope.editor.observacion.listaObservacion = respuesta.OBSERVACION;
				$scope.editor.observacion.contarSubsanaciones();
			}
			
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
				$scope.editor.seccion.setAtributos($filter('filter')(respuesta.ATRIBUTO, {cod_tipo_accion : 'C'}));
				
				$scope.accion.cancelar.listaAtributos = cargarAtributoAdicional(respuesta.ATRIBUTO, {cod_tipo_accion : 'A'});
				$scope.accion.rechazo.listaAtributos = cargarAtributoAdicional(respuesta.ATRIBUTO, {cod_tipo_accion : 'R'});
				$scope.editor.observacion.listaAtributos = cargarAtributoAdicional(respuesta.ATRIBUTO, {cod_tipo_accion : 'O'});
				$scope.accion.tarea.listaAtributos = cargarAtributoAdicional(respuesta.ATRIBUTO, {cod_tipo_accion : 'C', cod_seccion : '0'});
				
			} 
			if(respuesta.ATRIBUTO_PLANTILLA && respuesta.ATRIBUTO_PLANTILLA.length>0){
				$scope.editor.seccion.setAtributosPlantillas(respuesta.ATRIBUTO_PLANTILLA);
			}
		});
	};
	
	var cargarRegistro = function(){
				
		Ttarea.registrar({
			cod_tarea_observado : ($scope.editor.seccionObservaciones && $scope.editor.observacion.tipoObservar && $scope.editor.observacion.cod_tarea)?$scope.editor.observacion.cod_tarea:"IS_NULL",
			web_acc_grabar : $scope.accion.grabar?"1":"0",
			web_acc_cancelar : $scope.accion.cancelar.activo?"1":"0",
			web_acc_rechazar : $scope.accion.rechazo.activo?"1":"0",
			web_acc_observar : ($scope.editor.seccionObservaciones && $scope.editor.observacion.tipoObservar)?"1":"0",
			web_acc_subsanar : ($scope.editor.seccionObservaciones && $scope.editor.observacion.tipoSubsanar)?"1":"0",
			web_wid_historial : $scope.editor.seccionHistorial?"1":"0",
			web_wid_documento : $scope.editor.seccionDocumentos?"1":"0",
			tipo_vista : $scope.editor.tipoVista
		});
		Ttarea.agregar($scope.cargado);
		
		Tcancelar.correlativo("cod_mae_motivo_cancelar");
		Tcancelar.registrarMultiple($scope.accion.cancelar.listaMotivos);
		Tcancelar.registrarMultipleAdicional(condicion);
		Tcancelar.agregar($scope.cargado);
		
		Trechazar.correlativo("cod_mae_motivo_rechazar");
		Trechazar.registrarMultiple($scope.accion.rechazo.listaMotivos);
		Trechazar.registrarMultipleAdicional(condicion);
		Trechazar.agregar($scope.cargado);
		
		Tdocumento.registrarMultiple(($scope.editor.seccionDocumentos)?$scope.editor.documento.listaDocumentos:[]);
		Tdocumento.registrarMultipleAdicional(condicion);
		Tdocumento.registrarMultiplePersonalizado(function(item){
			if(item.maeDocumento){
				item.cod_mae_documento = item.maeDocumento.cod_mae_documento;
			}
			if(item.tipo == 'A'){
				item.es_obligatorio="0";
			}
			delete item.maeDocumento;
			return item;
		});
		Tdocumento.agregar($scope.cargado);

		Tobservar.correlativo("cod_mae_observacion");
		Tobservar.registrarMultiple(($scope.editor.seccionObservaciones)?$scope.editor.observacion.listaObservacion:[]);
		Tobservar.registrarMultipleAdicional(condicion);
		Tobservar.registrarMultiplePersonalizado(function(item){
			delete item.listaSubsanaciones;
			return item;
		});
		Tobservar.agregar($scope.cargado);

		var subsanaciones = [], cont = 0;
		for(var i = 0; i < $scope.editor.observacion.listaObservacion.length; i++){
			var obs = $scope.editor.observacion.listaObservacion[i];
			if(obs.listaSubsanaciones){
				for(var e = 0; e < obs.listaSubsanaciones.length; e++){
					var sub = obs.listaSubsanaciones[e];
					subsanaciones[cont++] = {
						cod_mae_observacion 	: i+1,
						cod_mae_subsanacion 	: e+1,
						nombre 					: sub.nombre,
						descripcion				: sub.descripcion,
						estado 					: sub.estado,
						tipo_sustento 			: sub.tipo_sustento,
						cod_mae_documento_tarea : sub.cod_mae_documento_tarea
					};
				}
			}
		}
		Tsubsanar.registrarMultiple(($scope.editor.seccionObservaciones)?subsanaciones:[]);
		Tsubsanar.registrarMultipleAdicional(condicion);
		Tsubsanar.agregar($scope.cargado);

		var secciones = $scope.editor.seccion.getSecciones();
		if(secciones){
			Tseccion.correlativo("cod_seccion");
			Tseccion.registrarMultiple(secciones.lista);
			Tseccion.registrarMultipleAdicional({
				cod_tarea : $scope.data.TAREA_CARGADA.cod_tarea
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
					item.cod_tarea = $scope.data.TAREA_CARGADA.cod_tarea;
				}
				delete item.plantilla;
				return item;
			});
			TsubSeccion.agregar($scope.cargado);
		} else {
			return false;
		}
		
		var atributos = [];

			enviarAtributos(atributos,$scope.accion.cancelar,"listaAtributos","A");
			enviarAtributos(atributos,$scope.accion.rechazo,"listaAtributos","R");
			enviarAtributos(atributos,$scope.editor.observacion,"listaAtributos","O");
			enviarAtributos(atributos,$scope.accion.tarea,"listaAtributos","C");
			
			if(!$scope.editor.seccion.getAtributos(atributos)){
				return false;
			}
			
			Tatributos.registrarMultiple(atributos);
			Tatributos.registrarMultiplePersonalizado(function(item){
				if(!(item.plantilla && item.plantilla=="1")){
					item.cod_tarea = $scope.data.TAREA_CARGADA.cod_tarea;
				}
				delete item.plantilla;
				return item;
			});
			Tatributos.agregar($scope.cargado);
		
		return true;
		
	};
	
	$scope.registrarInicio = function(call){
		if(cargarRegistro()){
			$scope.cargado.metodo = "registrarAccion";
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

mapeo.registerCtrl('tareagestionaraccion_modal_denegar', function ($scope, $modalInstance, config) {

	$scope.titulo = config.titulo;
	
	
	$scope.listaMotivos = angular.copy(config.cargar());
	$scope.motivo = "";
	$scope.agregar = function(){
		$scope.mensaje = "";
		for(var i = 0;i < $scope.listaMotivos.length; i++){
			if($scope.listaMotivos[i].nombre == $scope.motivo){
				$scope.mensaje = "Motivo repetido";
				return;
			}
		}
		$scope.listaMotivos.push({
			nombre : $scope.motivo,
			estado : "1"
		});
		delete $scope.motivo;
	};
	$scope.eliminar = function(index){
		$scope.listaMotivos.splice(index,1);
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
		if($scope.listaMotivos.length==0){
			$scope.mensaje = "Debe ingresar por lo menos un motivo";
			return;
		}
		config.guardar($scope.listaMotivos,$scope.listaAtributos);
		$modalInstance.close();
	};
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});