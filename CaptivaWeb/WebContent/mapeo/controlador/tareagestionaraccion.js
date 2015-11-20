mapeo.registerCtrl('tareagestionaraccion', function($scope, $modal, ajax, util) {

	// EDITOR ----------------------------------
	$scope.editor = {
		atributo_tipo : "detallado"
	};
	// EDITOR ----------------------------------
	
	func.consulta($scope, ajax, $scope.data.TAREA_CARGADA.cod_proyecto, $scope.data.TAREA_CARGADA.cod_con_trabajar);

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
		$scope.cargado["TRS_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		$scope.cargado["TAR_W_cod_tarea"] = $scope.data.TAREA_CARGADA.cod_tarea;
		
		$scope.consulta.cargar(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				$scope.listar();
			}
		});
	};
	
	$scope.listar = function(){
		
	};
	
	var validarRegistro = function(){
		
		var i_cont = 1;
		var o_cont = 1;
		
		if($scope.seccion.lista.length==0){
			$scope.agregarAlerta("danger","Debes registrar por lo menos una sección");
			return false;
		}
			
		var eAd = $scope.seccion.lista.length+1;
		for(var e = 0;e < $scope.seccion.lista.length;e++){
			
			var seccionItem = $scope.seccion.lista[e];
			
			var codSeccion = "";
			if(seccionItem.plantilla && seccionItem.plantilla=="1"){
				
				if(seccionItem.cod_seccion_padre){
					codSeccion = seccionItem.cod_seccion_padre;
				} else {
					codSeccion = new Date().getTime();
					$scope.cargado["TSE_M_"+(eAd)+"_cod_seccion"] = codSeccion;
					$scope.cargado["TSE_M_"+(eAd)+"_tipo"] = "P"; // Plantilla
					$scope.cargado["TSE_M_"+(eAd)+"_nombre"] = seccionItem.nombre;
					eAd++;
				}

				$scope.cargado["TSE_M_"+(e+1)+"_cod_seccion_padre"] = codSeccion;
			}
			
			$scope.cargado["TSE_M_"+(e+1)+"_cod_tarea"] = $scope.data.PROCESO_CARGADO.cod_proceso;
			$scope.cargado["TSE_M_"+(e+1)+"_cod_seccion"] = (e+1);
			$scope.cargado["TSE_M_"+(e+1)+"_tipo"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?"A":seccionItem.tipo;
			if(seccionItem.tipo_widget){
				$scope.cargado["TSE_M_"+(e+1)+"_tipo_widget"] = seccionItem.tipo_widget;
			}
			
			if(!((seccionItem.tipo && seccionItem.tipo=="W") || (seccionItem.plantilla && seccionItem.plantilla=="1"))){
				$scope.cargado["TSE_M_"+(e+1)+"_nombre"] = seccionItem.nombre;
			}
			
			if(!((seccionItem.tipo && seccionItem.tipo=="W") || seccionItem.cod_seccion_padre)){
				
				if(seccionItem.subSeccion.lista.length==0){
					$scope.agregarAlerta("danger","Debes registrar por lo menos una Sub sección en la sección Nro "+(e+1));
					return false;
				}
				
				for(var i = 0;i<$scope.seccion.lista[e].subSeccion.lista.length;i++){
					
					var subSeccionItem = $scope.seccion.lista[e].subSeccion.lista[i];
					var atributosdeSubseccion = $scope.seccion.lista[e].subSeccion.lista[i].atributo.lista;
					
					if(atributosdeSubseccion.length==0){
						$scope.agregarAlerta("danger","Debes registrar por lo menos un atributo en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
						return false;
					}
					for(var o = 0;o<atributosdeSubseccion.length;o++){
						var atributoItem = atributosdeSubseccion[o];
	
						if(!(atributoItem.web_etiqueta && atributoItem.web_etiqueta.length!=0)){
							atributoItem.inf_error = "Falta registrar etiqueta";
							$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1)+", en la sección Nro "+(e+1));
							return false;
						}
						atributoItem.inf_error = "";
						
						if(!(seccionItem.plantilla && seccionItem.plantilla=="1")){
							$scope.cargado["TAC_M_"+(o_cont)+"_cod_tarea"] = $scope.data.PROCESO_CARGADO.cod_proceso;
						}
						$scope.cargado["TAC_M_"+(o_cont)+"_cod_seccion"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?codSeccion:(e+1);
						$scope.cargado["TAC_M_"+(o_cont)+"_cod_sub_seccion"] = (i+1);
						$scope.cargado["TAC_M_"+(o_cont)+"_cod_tarea_detalle"] = (o+1);
						$scope.cargado["TAC_M_"+(o_cont)+"_cod_atributo"] = atributoItem.cod_atributo;
						$scope.cargado["TAC_M_"+(o_cont)+"_web_etiqueta"] = atributoItem.web_etiqueta;
						
						o_cont++;
						
					}
	
					// CARGAR DATOS DE SUB SECCION
					if(!(seccionItem.plantilla && seccionItem.plantilla=="1")){
						$scope.cargado["TSU_M_"+(i_cont)+"_cod_tarea"] = $scope.data.PROCESO_CARGADO.cod_proceso;
					}
					$scope.cargado["TSU_M_"+(i_cont)+"_cod_seccion"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?codSeccion:(e+1);
					$scope.cargado["TSU_M_"+(i_cont)+"_cod_sub_seccion"] = (i+1);
					$scope.cargado["TSU_M_"+(i_cont)+"_nombre"] = subSeccionItem.nombre;
					
					i_cont++;
	
				}
				
			}
			
		}
		
		return true;
	};
	
	$scope.registrarInicio = function(){
		if(validarRegistro()){
			$scope.cargado["TAR_web_detalle_tipovista"] = $scope.config.tipoVista;
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