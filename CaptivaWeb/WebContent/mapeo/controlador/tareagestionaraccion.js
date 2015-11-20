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

	func.consulta($scope, ajax, condicion.cod_proyecto, $scope.data.TAREA_CARGADA.cod_con_trabajar);

	$scope.accion = {
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
	
	$scope.instanciar = function(vaAListar){ //s
		
		$scope.cargado = { paquete : "modulo", clase : "Tarea"};
		
		var tarea =  $scope.data.TAREA_CARGADA;
		
		$scope.accion.cancelar.activo = tarea.web_acc_cancelar == "1"?true : false;
		$scope.accion.rechazo.activo = tarea.web_acc_rechazar == "1"?true : false;
		$scope.accion.grabar = tarea.web_acc_grabar == "1"?true : false;
		$scope.editor.tipoVista = tarea.tipo_vista;
		
		$scope.consulta.cargar(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				$scope.listar();
			}
		});
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listarAccion";
		ajax.jpo($scope.cargado,function(respuesta){
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
		});
	};
	
	var cargarRegistro = function(){
		
		Ttarea.registrar({
			cod_tarea_observado : $scope.editor.observacion.cod_tarea?$scope.editor.observacion.cod_tarea:"IS_NULL",
			web_acc_grabar : $scope.accion.grabar?"1":"0",
			web_acc_cancelar : $scope.accion.cancelar.activo?"1":"0",
			web_acc_rechazar : $scope.accion.rechazo.activo?"1":"0",
			web_acc_observar : $scope.editor.observacion.tipoObservar?"1":"0",
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
		
		Tdocumento.registrarMultiple($scope.editor.documento.listaDocumentos);
		Tdocumento.registrarMultipleAdicional(condicion);
		Tdocumento.registrarMultiplePersonalizado(function(item){
			item.cod_mae_documento = item.maeDocumento.cod_mae_documento;
			if(item.tipo == 'A'){
				item.es_obligatorio="0";
			}
			delete item.maeDocumento;
			return item;
		});
		Tdocumento.agregar($scope.cargado);

		Tobservar.correlativo("cod_mae_observacion");
		Tobservar.registrarMultiple($scope.editor.observacion.listaObservacion);
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
		Tsubsanar.registrarMultiple(subsanaciones);
		Tsubsanar.registrarMultipleAdicional(condicion);
		Tsubsanar.agregar($scope.cargado);

		return true;
		
	};
	
	$scope.registrarInicio = function(){
		if(cargarRegistro()){
			$scope.cargado.metodo = "registrarAccion";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Registrado corréctamente");
				$scope.instanciar(false);
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
			if($scope.listaAtributos[i].cod_clase == $scope.atributo.cod_clase && $scope.listaAtributos[i].cod_atributo == $scope.atributo.cod_atributo){
				$scope.mensaje = "Atributo repetido";
				return;
			}
		}
		$scope.listaAtributos.push($scope.atributo);
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