mapeo.registerCtrl('tareagestionaraccion', function($scope, $modal, ajax, util) {

	// EDITOR ----------------------------------
	$scope.editor = {
		atributo_tipo : "detallado"
	}
	// EDITOR ----------------------------------
	
	func.consulta($scope, ajax, $scope.data.TAREA_CARGADA.cod_proyecto, $scope.data.TAREA_CARGADA.cod_con_trabajar);
	
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