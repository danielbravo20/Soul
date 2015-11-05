mapeo.registerCtrl('procesogestionarinicio', function($scope, ajax, util) {
	
	$scope.tipoClase = {
		"Integer" : "I",
		"Long" : "L",
		"long" : "l",
		"String" : "S",
		"boolean" : "b",
		"java.math.BigDecimal" : "B",
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
	
	var cargarAtributos = function(call){
		var consulta = {
			paquete : "modulo", 
			clase : "Atributo",
			metodo : "listarDetalle"
		};
		ajax.jpo(consulta,function(respuesta){
			for(var i = 0;i<respuesta.length;i++){
				if(!$scope.atributo.listaId[respuesta[i].cod_atributo]){
					$scope.atributo.listaId[respuesta[i].cod_atributo] = i;
				}
				respuesta[i].busqueda = respuesta[i].nombre+" ("+respuesta[i].cla_nombre+")";
			}
			$scope.atributo.lista = respuesta;
			if(call){
				call();
			}
		});
	};
	
	$scope.config = {
		esEdicion : true,
		fechaInicio : new Date()
	};
	
	$scope.instanciar = function(vaAListar){
		$scope.cargado = { paquete : "modulo", clase : "Proceso"};
		$scope.cargado["PIS_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
		$scope.cargado["PIN_W_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;

		cargarAtributos(function(){
			if(!(typeof(vaAListar)=="boolean" && vaAListar==false)){
				listar();
			}
		});
	};
	
	var listar = function(){
		$scope.subSeccion.lista = [];
		$scope.cargado.metodo = "listarInicio";
		ajax.jpo($scope.cargado,function(respuesta){
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
				if(respuesta.INICIO && respuesta.INICIO.length>0){
					for(var i = 0;i< respuesta.INICIO.length;i++){
						
						var atributoItem = respuesta.INICIO[i];

						if($scope.subSeccion.lista[atributoItem.cod_sub_seccion-1]){
							
							var objAtributo = $scope.atributo.lista[$scope.atributo.listaId[atributoItem.cod_atributo]];
						
							$scope.subSeccion.lista[atributoItem.cod_sub_seccion-1].atributo.lista.push({
								cod_atributo : atributoItem.cod_atributo,
								web_etiqueta : atributoItem.web_etiqueta,
								web_tipo : atributoItem.web_tipo,
								web_tipo_campo : atributoItem.web_tipo_campo,
								web_tipo_lista : atributoItem.web_tipo_lista,
								web_catalogo : atributoItem.web_catalogo,
								web_requerido : atributoItem.web_requerido,
								web_mensaje_validacion : atributoItem.web_mensaje_validacion,
								// Adicionales
								cla_nombre : objAtributo.cla_nombre,
								atr_nombre : objAtributo.nombre,
								sql_longitud : objAtributo.sql_longitud,
								sql_precision : objAtributo.sql_precision
							});
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
		eliminar : function($index){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			$scope.subSeccion.lista.splice(index,1);
		},
		bajar : function($index){
			var index = $scope.subSeccion.actual;
			$("i[eid='subSeccionEditar_"+index+"']").next().hide();
			if(index<$scope.subSeccion.lista.length-1){
				$scope.subSeccion.lista.move(index,index+1);
			}
		},
		subir : function($index){
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
					// a registrar
					cod_atributo : item.cod_atributo,
					web_tipo : item.tipo,
					web_etiqueta : item.inf_nombre,
					// apoyo
					cla_nombre : item.cla_nombre,
					atr_nombre : item.nombre,
					sql_longitud : item.sql_longitud,
					sql_precision : item.sql_precision,
				});
				delete $scope.subSeccion.lista[indice].nuevoAtributo;
			}
		},
		nuevo : function(){
			
		},
		eliminar : function($index){
			
		},
		bajar : function($index){
			
		},
		subir : function($index){
			
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
	}
	
	var validarRegistro = function(){
		
		var contaAtri = 1;
		
		if($scope.subSeccion.lista.length==0){
			$scope.agregarAlerta("danger","Debes registrar por lo menos una Sub sección");
			return false;
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
				
				if(!(atributoItem.web_tipo_campo && atributoItem.web_tipo_campo.length!=0)){
					atributoItem.inf_error = "Falta seleccionar tipo de campo";
					$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1));
					return false;
				}
				
				if(atributoItem.web_tipo_campo=="E" && !(atributoItem.web_tipo_lista && atributoItem.web_tipo_lista.length!=0)){
					atributoItem.inf_error = "Falta seleccionar el tipo de lista desplegable";
					$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1));
					return false;
				}
				
				if(atributoItem.web_tipo_campo=="E" && atributoItem.web_tipo_lista=="C" && !(atributoItem.web_catalogo && atributoItem.web_catalogo.length!=0)){
					atributoItem.inf_error = "Falta seleccionar el tipo de catálogo";
					$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1));
					return false;
				}
				
				if(atributoItem.web_requerido=="1" && !(atributoItem.web_mensaje_validacion && atributoItem.web_mensaje_validacion.length!=0)){
					atributoItem.inf_error = "Falta mensaje de validación";
					$scope.agregarAlerta("danger","Corregir los atributos pendientes en la Sub sección Nro "+(i+1));
					return false;
				}
				
				atributoItem.inf_error = "";
				
				$scope.cargado["PIN_M_"+(contaAtri)+"_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
				$scope.cargado["PIN_M_"+(contaAtri)+"_cod_sub_seccion"] = (i+1);
				$scope.cargado["PIN_M_"+(contaAtri)+"_cod_proceso_inicio"] = (e+1);
				$scope.cargado["PIN_M_"+(contaAtri)+"_cod_atributo"] = atributoItem.cod_atributo;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_etiqueta"] = atributoItem.web_etiqueta;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_tipo"] = atributoItem.web_tipo;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_tipo_campo"] = atributoItem.web_tipo_campo;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_tipo_lista"] = atributoItem.web_tipo_lista;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_catalogo"] = atributoItem.web_catalogo;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_requerido"] = atributoItem.web_requerido;
				$scope.cargado["PIN_M_"+(contaAtri)+"_web_mensaje_validacion"] = atributoItem.web_mensaje_validacion;
				
				contaAtri++;				
				
			}

			// CARGAR DATOS DE SUB SECCION
			$scope.cargado["PIS_M_"+(i+1)+"_cod_proceso"] = $scope.data.PROCESO_CARGADO.cod_proceso;
			$scope.cargado["PIS_M_"+(i+1)+"_cod_sub_seccion"] = (i+1);
			$scope.cargado["PIS_M_"+(i+1)+"_nombre"] = subSeccionItem.nombre;

		}
		
		return true;
	};
	
	$scope.registrarInicio = function(){
		if(validarRegistro()){
			$scope.cargado.metodo = "registrarInicio";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Registrado corréctamente");
				$scope.instanciar(false);
			});
		}
	};

});