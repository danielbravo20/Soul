mapeo.registerCtrl('procesogestionardetalle', function($scope, $modal, ajax, util) {

	// EDITOR ----------------------------------
	$scope.editor = {
		atributo_tipo : "sololectura"
	}
	// EDITOR ----------------------------------
	
	func.consulta($scope, ajax, $scope.data.PROCESO_CARGADO.cod_proyecto, $scope.data.PROCESO_CARGADO.cod_con_detalle);
	
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
	
	$scope.instanciar = function(vaAListar){
		$scope.cargado = { paquete : "modulo", clase : "Proceso"};

		$scope.cargado["PRO_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
		$scope.cargado["PDS_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
		$scope.cargado["PDB_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
		$scope.cargado["PDA_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;

		if(!(typeof(vaAListar)!="undefined" && vaAListar==false)){
			$scope.consulta.tablas = [];
			$scope.consulta.atributos = [];
			$scope.consulta.atributosId = [];

			if($scope.data.PROCESO_CARGADO.web_detalle_tipovista){
				$scope.editor.tipoVista = $scope.data.PROCESO_CARGADO.web_detalle_tipovista;
			}
			
			$scope.consulta.cargar(function(){
				//detalle.cargar();
			});
		}
	};
	
	$scope.$watch('editor.seccionHistorial', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue=="1"){
				 $scope.seccion.agregarWidget("HIS");
			 } else {
				 $scope.seccion.quitarWidget("HIS");
			 }
		}
	});
	$scope.$watch('editor.seccionDocumentos', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue=="1"){
				 $scope.seccion.agregarWidget("DOC");
			 } else {
				 $scope.seccion.quitarWidget("DOC");
			 }
		}
	});
	$scope.$watch('editor.seccionObservaciones', function(newValue, oldValue) {
		if(newValue != oldValue){
			 if(newValue=="1"){
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
			"HIS" : "plantilla/inc_modelo_historialtareas.html",
			"DOC" : "plantilla/inc_modelo_documentos.html",
			"OBS" : "plantilla/inc_modelo_observaciones.html"
		}
	};
	
	$scope.interno = {
		lista : [],
		agregar : function(){
			$scope.interno.lista.push({
				cla_nombre : $scope.interno.nuevoAtributo.cla_nombre,
				atr_nombre : $scope.interno.nuevoAtributo.nombre
			});
			delete $scope.interno.nuevoAtributo;
		},
		nuevo : function(){
			
		},
		eliminar : function(index){
			$scope.interno.lista.splice(index,1);
		}
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
					$scope.cargado["PDS_M_"+(eAd)+"_cod_seccion"] = codSeccion;
					$scope.cargado["PDS_M_"+(eAd)+"_tipo"] = "P"; // Plantilla
					$scope.cargado["PDS_M_"+(eAd)+"_nombre"] = seccionItem.nombre;
					eAd++;
				}

				$scope.cargado["PDS_M_"+(e+1)+"_cod_seccion_padre"] = codSeccion;
			}
			
			$scope.cargado["PDS_M_"+(e+1)+"_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
			$scope.cargado["PDS_M_"+(e+1)+"_cod_seccion"] = (e+1);
			$scope.cargado["PDS_M_"+(e+1)+"_tipo"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?"A":seccionItem.tipo;
			if(seccionItem.tipo_widget){
				$scope.cargado["PDS_M_"+(e+1)+"_tipo_widget"] = seccionItem.tipo_widget;
			}
			
			if(!((seccionItem.tipo && seccionItem.tipo=="W") || (seccionItem.plantilla && seccionItem.plantilla=="1"))){
				$scope.cargado["PDS_M_"+(e+1)+"_nombre"] = seccionItem.nombre;
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
							$scope.cargado["PDA_M_"+(o_cont)+"_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
						}
						$scope.cargado["PDA_M_"+(o_cont)+"_cod_seccion"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?codSeccion:(e+1);
						$scope.cargado["PDA_M_"+(o_cont)+"_cod_sub_seccion"] = (i+1);
						$scope.cargado["PDA_M_"+(o_cont)+"_cod_proceso_detalle"] = (o+1);
						$scope.cargado["PDA_M_"+(o_cont)+"_cod_atributo"] = atributoItem.cod_atributo;
						$scope.cargado["PDA_M_"+(o_cont)+"_web_etiqueta"] = atributoItem.web_etiqueta;
						
						o_cont++;
						
					}
	
					// CARGAR DATOS DE SUB SECCION
					if(!(seccionItem.plantilla && seccionItem.plantilla=="1")){
						$scope.cargado["PDB_M_"+(i_cont)+"_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
					}
					$scope.cargado["PDB_M_"+(i_cont)+"_cod_seccion"] = (seccionItem.plantilla && seccionItem.plantilla=="1")?codSeccion:(e+1);
					$scope.cargado["PDB_M_"+(i_cont)+"_cod_sub_seccion"] = (i+1);
					$scope.cargado["PDB_M_"+(i_cont)+"_nombre"] = subSeccionItem.nombre;
					
					i_cont++;
	
				}
				
			}
			
		}
		
		return true;
	};
	
	$scope.registrarInicio = function(){
		if(validarRegistro()){
			$scope.cargado["PRO_web_detalle_tipovista"] = $scope.editor.tipoVista;
			$scope.cargado.metodo = "registrarDetalle";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Registrado corréctamente");
				$scope.instanciar(false);
			});
		}
	};
	
	$scope.instanciar();

});