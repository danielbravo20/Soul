mapeo.registerCtrl('tareagestionar', function($scope, $filter, $timeout, $modal, ajax, util) {

	$scope.mensajeError = "";
	
	$scope.config = {
	};
	
	$scope._config = {
		esEdicion : true,
		tipoVista : "V"
	};
	
	$scope.esEdicion = true;
	
	$scope.titulo = {};
	
	$scope.postInstanciar = function(){
		$scope.cargado = { paquete : "modulo", clase : "TareaGestionar"};
		$scope.cargado.TAR_W_COD_PROYECTO = $scope.config.COD_PROYECTO;
		$scope.cargado.TAR_W_COD_PROCESO = $scope.config.COD_PROCESO;
		$scope.cargado.TAR_W_COD_TAREA = $scope.config.COD_TAREA;
	};
	
	$scope.cargarSubSecciones = function(seccionIndex,seccionId){
		$scope.cargado["TSS_I_TAREA_SECCION_ID"] = seccionId.join(",");
		$scope.cargado.metodo = "listarSubSeccion";
		ajax.jpo($scope.cargado,function(respuesta){
			for(var i = 0 ; i<respuesta.length;i++){
				var indiceId = seccionIndex[respuesta[i].TAREA_SECCION_ID];
				$scope.objSeccion.lista[indiceId].subSeccion.lista.push({
					nombre : respuesta[i].NOMBRE,
					seccionId : indiceId+1,
					atributo : {
						selected : null,
						lista : []
					}
				});
			}
		});
	};
	
	$scope.cargarSeccionPlantillas = function(plantillasIndex,plantillasId,seccionIndex,seccionId,callback){
		delete $scope.cargado["TSB_W_COD_PROYECTO"];
		delete $scope.cargado["TSB_W_COD_PROCESO"];
		delete $scope.cargado["TSB_W_COD_TAREA"];
    	$scope.cargado["TSB_I_ID"] = plantillasId.join(",");
		$scope.cargado.metodo = "listarSeccion";
		ajax.jpo($scope.cargado,function(respuesta){
			for(var i = 0 ; i<respuesta.length;i++){
				$scope.objSeccion.lista[plantillasIndex[respuesta[i].ID]].nombre = respuesta[i].NOMBRE;
			}
			if(callback){
				callback(seccionIndex,seccionId);
			}
		});
	};
	
	$scope.cargarSeccion = function(callback){
    	$scope.cargado["TSB_W_COD_PROYECTO"] = $scope.config.COD_PROYECTO;
    	$scope.cargado["TSB_W_COD_PROCESO"] = $scope.config.COD_PROCESO;
    	$scope.cargado["TSB_W_COD_TAREA"] = $scope.config.COD_TAREA;
		$scope.cargado.metodo = "listarSeccion";
		ajax.jpo($scope.cargado,function(respuesta){
			if(respuesta.length>0){
				var plantillasIndex = {},plantillasId = [];
				var seccionIndex = {},seccionId = [];
				for(var i = 0 ; i<respuesta.length;i++){
					var esPlantilla = respuesta[i].ID_PADRE?true:false;
					if(esPlantilla){
						seccionIndex[respuesta[i].ID_PADRE] = $scope.objSeccion.lista.length;
						seccionId.push(respuesta[i].ID_PADRE);
						// -------
						plantillasIndex[respuesta[i].ID_PADRE] = $scope.objSeccion.lista.length;
						plantillasId.push(respuesta[i].ID_PADRE);
					} else {
						seccionIndex[respuesta[i].ID] = $scope.objSeccion.lista.length;
						seccionId.push(respuesta[i].ID);
					}
					$scope.objSeccion.lista.push({
						ID_PADRE : respuesta[i].ID_PADRE,
						EDITOR_PLANTILLA : esPlantilla==true?"1":"0",
						nombre : respuesta[i].NOMBRE,
						activo : (respuesta[i].ACTIVO=="1")?true:false,
						subSeccion : {
							actual : 1,
							lista : []
						}
					});
				}
				if(plantillasId.length>0){
					$scope.cargarSeccionPlantillas(plantillasIndex,plantillasId,seccionIndex,seccionId,callback);
				} else {
					if(callback){
						callback(seccionIndex,seccionId);
					}
				}
			}
		});
	};
	
	$scope.cargarData = function(){
		$scope.cargarSeccion(function(seccionIndex,seccionId){
			$scope.cargarSubSecciones(seccionIndex,seccionId);
		});
	};
	
	$scope.instanciar = function(COD_PROCESO,objTarea){
		var objProceso = util.getObjeto($scope.data.PROCESO,{COD_PROCESO : COD_PROCESO});
		var objProducto = util.getObjeto($scope.data.MAE_PRODUCTO,{COD_PRODUCTO : objProceso.COD_PRODUCTO, COD_UNI_NEGOCIO : objProceso.COD_UNI_NEGOCIO});
		var objUniNeg = util.getObjeto($scope.data.MAE_UNI_NEGOCIO,{COD_UNI_NEGOCIO : objProceso.COD_UNI_NEGOCIO});
		
		$scope.titulo.unidadNegocio = objUniNeg.NOMBRE;
		$scope.titulo.producto = objProducto.NOMBRE;
		$scope.titulo.proceso = objProceso.INF_NOMBRE;
		$scope.titulo.tarea = objTarea.NOMBRE;
		
		$scope.titulo.codigo = "0000";
		$scope.titulo.fecha = new Date();
		
		$scope.config = angular.extend({},objTarea,$scope._config);

		$scope.postInstanciar();
		$scope.cargarData();
	};
	
	// SECCION
	
	$scope.objSeccion = {
		actual : 0,
		lista : []
	};
	
	$scope.seccionAgregar = function(){
		var eid = $scope.objSeccion.lista.length+1;
		$scope.objSeccion.lista.push({
			nombre : "Seccion Nro "+eid,
			activo : true,
			subSeccion : {
				actual : 1,
				lista : []
			}
		});
		$scope.objSeccion.lista[eid-1].activo = true;
	};
	
	$scope.seccionEliminar = function(){
		index = $scope.objSeccion.actual-1;
		$("i[eid='seccionEditar_"+index+"']").next().hide();
		$scope.objSeccion.lista.splice(index,1);
	};
	
	$scope.seccionBajar = function(){
		index = $scope.objSeccion.actual-1;
		$("i[eid='seccionEditar_"+index+"']").next().hide();
		if(index>0){
			$scope.objSeccion.lista.move(index-1,index);
		}
	};
	
	$scope.seccionSubir = function($index){
		index = $scope.objSeccion.actual-1;
		$("i[eid='seccionEditar_"+index+"']").next().hide();
		if(index<$scope.objSeccion.lista.length-1){
			$scope.objSeccion.lista.move(index,index+1);
		}
	};
	
	$scope.seccionCopiar = function(){
		index = $scope.objSeccion.actual-1;
		$("i[eid='seccionEditar_"+index+"']").next().hide();
		var newTab = angular.copy($scope.objSeccion.lista[index]);
			newTab.nombre += " Copia";
		$scope.objSeccion.lista.push(newTab);
	};
	
	$scope.asignarSelectTab = function(nroIndex){
		$("i").next(".popover").hide();
		$scope.objSeccion.actual = nroIndex + 1;
	};
	
	// SUBSECCION
	
	$scope.subSeccionAgregar = function(seccionId){
		var eid = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.length;
		$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.push({
			nombre : "Sub Seccion Nro "+(eid+1),
			seccionId : $scope.objSeccion.actual,
			atributo : {
				selected : null,
				lista : []
			}
		});
	};
	
	$scope.subSeccionEliminar = function(){
		var index = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual;
		$("i[eid='subSeccionEditar_"+index+"']").next().hide();
		$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.splice(index,1);
	};
	
	$scope.subSeccionBajar = function($index){
		var index = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual;
		$("i[eid='subSeccionEditar_"+index+"']").next().hide();
		if(index<$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.length-1){
			$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.move(index,index+1);
		}
	};
	
	$scope.subSeccionSubir = function($index){
		var index = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual;
		$("i[eid='subSeccionEditar_"+index+"']").next().hide();
		if(index>0){
			$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.move(index-1,index);
		}
	};
	
	$scope.subSeccionCopiar = function(){
		var index = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual;
		$("i[eid='subSeccionEditar_"+index+"']").next().hide();
		var newTab = angular.copy($scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista[index]);
			newTab.nombre += " Copia";
		$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista.push(newTab);
	};
	
	// ATRIBUTO
	
	$scope.atributoAgregar = function($index){
		/*
		var eid = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion[$index].atributos.length+1;
		$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion[$index].atributos.push({
			id 		: "atributo_"+eid,
			etiqueta 	: "atributo "+eid,
			valor 		: "atributo "+eid,
			activo 	: true,
			tipo	: 1
		});*/
	};
	
	$scope.tablas = {};
	
	$scope.objAtributo = {
		lista : [],
		seleccionado : null,
		cargar : function(){
			ajax.jpo({
				paquete : "modulo",
				clase : "Consulta",
				metodo : "cargarConsulta",
				CON_COD_PROYECTO : 1,
				CON_W_COD_CONSULTA : 2,
				CON_W_COD_PROYECTO : 1,
				CAT_W_COD_CONSULTA : 2,
				CTA_W_COD_CONSULTA : 2
			},function(respuesta){
				
				for(var i = 0; i<respuesta.CONSULTA_TABLA.length ; i++){
					if(!$scope.tablas[respuesta.CONSULTA_TABLA[i].COD_TABLA]){
						$scope.tablas[respuesta.CONSULTA_TABLA[i].COD_TABLA] = respuesta.CONSULTA_TABLA[i];
					}
				}
				/**/var ca = [];
				for(var i = 0; i<respuesta.CONSULTA_ATRIBUTO.length ; i++){
					if(i<5){
						ca.push(respuesta.CONSULTA_ATRIBUTO[i]);
					} else {
						break;
					}
				}
				$scope.objAtributo.lista = ca;
				
				//$scope.objAtributo.lista = respuesta.CONSULTA_ATRIBUTO;
				
			});
		},
		eliminar : function(index){
			var indexSubSeccion = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual-1;
			$("i[eid='atributo_"+index+"']").next().hide();
			$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista[indexSubSeccion].atributo.lista.splice(index,1);
		},
		bajar : function(index){
			var indexSubSeccion = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual-1;
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index>0){
				$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista[indexSubSeccion].atributo.lista.move(index-1,index);
			}
		},
		subir : function(index){
			var indexSubSeccion = $scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.actual-1;
			$("i[eid='atributo_"+index+"']").next().hide();
			if(index<$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista[indexSubSeccion].atributo.lista.length-1){
				$scope.objSeccion.lista[$scope.objSeccion.actual-1].subSeccion.lista[indexSubSeccion].atributo.lista.move(index,index+1);
			}
		},
		abrirListado : function(){
			
		}
	};
	
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

    $scope.$watch('config.WEB_ACC_SUBOBS', function(newVal,oldVal) {
    	if(newVal!==oldVal){
    		if(newVal == "O"){
    			$scope.config.WEB_ACC_OBSERVAR = "1";
    			$scope.config.WEB_ACC_SUBSANAR = "0";
    		}
    		if(newVal == "S"){
    			$scope.config.WEB_ACC_SUBSANAR = "1";
    			$scope.config.WEB_ACC_OBSERVAR = "0";
    		}
    	}
    }, true);
    
    $scope.$watch('config.WEB_ACC_SUBOBS_SHOW', function(newVal,oldVal) {
    	if(newVal!==oldVal){
    		if(newVal == true){
    			$scope.config.WEB_ACC_OBSERVAR = "1";
    			$scope.config.WEB_ACC_SUBSANAR = "0";
    		}
    		if(newVal == false){
    			$scope.config.WEB_ACC_SUBSANAR = "0";
    			$scope.config.WEB_ACC_OBSERVAR = "0";
    		}
    	}
    }, true);
    
    var validarRegistro = function(){
    	if($scope.objSeccion.lista.length==0){
    		$scope.mensajeError = "Debe registrar por lo menos una seccion";
    		return false;
    	}
    	if($scope.objSeccion.lista.length==1){
    		if($scope.objSeccion.lista[0].subSeccion.lista==0){
        		$scope.mensajeError = "Debe registrar por lo menos una sub sección";
        		return false;
    		}
    	}
    	if($scope.objSeccion.lista.length==1){
    		if($scope.objSeccion.lista[0].subSeccion.lista.length==1){
    			if($scope.objSeccion.lista[0].subSeccion.lista[0].atributo.length==0){
            		$scope.mensajeError = "Debe registrar por lo menos un atributo";
            		return false;
        		}
    		}
    	}
    	return true;
    };
    
    var cargarEnvio = function(){
    	
    	// TAREA
    	$scope.cargado["TAR_WEB_ACC_CANCELAR"] = $scope.config.WEB_ACC_CANCELAR;
    	$scope.cargado["TAR_WEB_ACC_COMPLETAR"] = $scope.config.WEB_ACC_COMPLETAR;
    	$scope.cargado["TAR_WEB_ACC_GRABAR"] = $scope.config.WEB_ACC_GRABAR;
    	$scope.cargado["TAR_WEB_ACC_OBSERVAR"] = $scope.config.WEB_ACC_OBSERVAR;
    	$scope.cargado["TAR_WEB_ACC_RECHAZAR"] = $scope.config.WEB_ACC_RECHAZAR;
    	$scope.cargado["TAR_WEB_ACC_SALIR"] = $scope.config.WEB_ACC_SALIR;
    	$scope.cargado["TAR_WEB_ACC_SUBSANAR"] = $scope.config.WEB_ACC_SUBSANAR;
    	$scope.cargado["TAR_TIPO_VISTA"] = $scope.config.tipoVista;
    	
    	// SECCION
    	$scope.cargado["TSE_W_COD_PROYECTO"] = $scope.config.COD_PROYECTO;
    	$scope.cargado["TSE_W_COD_PROCESO"] = $scope.config.COD_PROCESO;
    	$scope.cargado["TSE_W_COD_TAREA"] = $scope.config.COD_TAREA;
    	
    	var plantillas = [];
    	
    	var i;
    	
    	// Sub Seccion
		var tareaSeccion = [];
    	var ss_i = 0;
		
		for(i = 0; i< $scope.objSeccion.lista.length; i++){
			
			var seccion = $scope.objSeccion.lista[i];
			var id = i+1;
			var seccionId = $scope.config.COD_PROYECTO+"_"+$scope.config.COD_PROCESO+"_"+$scope.config.COD_TAREA+"_"+id;
			
			$scope.cargado["TSE_M_"+id+"_COD_PROYECTO"] = $scope.config.COD_PROYECTO;
			$scope.cargado["TSE_M_"+id+"_COD_PROCESO"] = $scope.config.COD_PROCESO;
			$scope.cargado["TSE_M_"+id+"_COD_TAREA"] = $scope.config.COD_TAREA;
			$scope.cargado["TSE_M_"+id+"_ID"] = seccionId;
			$scope.cargado["TSE_M_"+id+"_TAREA_SECCION_ID"] = id;
			$scope.cargado["TSE_M_"+id+"_ACTIVO"] = (seccion.activo==true)?"1":"0";
			
			var idPlantilla;
			
			if(seccion.EDITOR_PLANTILLA=="1"){
				if(seccion.ID_PADRE && seccion.ID_PADRE.length>0){
					idPlantilla = seccion.ID_PADRE;
				} else {
					idPlantilla = new Date().getTime();
					plantillas.push({
						ID : idPlantilla,
						NOMBRE : seccion.nombre
					});
				}
				$scope.cargado["TSE_M_"+id+"_ID_PADRE"] = idPlantilla;	// SECCION
				tareaSeccion.push(idPlantilla);	// SUBSECCION
			} else {
				$scope.cargado["TSE_M_"+id+"_NOMBRE"] = seccion.nombre;	// SECCION
				tareaSeccion.push(seccionId);	// SUBSECCION
			}
			
			for(var ii = 0; ii< seccion.subSeccion.lista.length; ii++){
				var subSeccion = seccion.subSeccion.lista[ii];
				var id = ii+1;
				ss_i++;
				$scope.cargado["TSS_M_"+ss_i+"_TAREA_SECCION_ID"] = seccion.EDITOR_PLANTILLA=="1"?idPlantilla:seccionId;
				$scope.cargado["TSS_M_"+ss_i+"_TAREA_SUB_SECCION_ID"] = id;
				$scope.cargado["TSS_M_"+ss_i+"_NOMBRE"] = subSeccion.nombre;
				$scope.cargado["TSS_M_"+ss_i+"_ACTIVO"] = seccion.actual==id?"1":"0";
			}
			
		}
		
		for(var e = 0; e< plantillas.length; e++){
			var id = i+1;
			$scope.cargado["TSE_M_"+id+"_ID"] = plantillas[e].ID;
			$scope.cargado["TSE_M_"+id+"_NOMBRE"] = plantillas[e].NOMBRE;
			i++;
		}
		
		// REGISTRANDO SUBSECCIONES
		$scope.cargado["TSS_I_TAREA_SECCION_ID"] = tareaSeccion.join(",");
		
	};
	
	$scope.guardar = function(){
		if(validarRegistro()){
			$scope.cargado.metodo = "registrar";
			cargarEnvio();
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Editado corréctamente");
				$scope.postInstanciar();
			});
		}
	};
	
	$scope.buscarPlantilla = function(){
		if($scope.objSeccion.lista.length>0){
			$scope.objSeccion.lista[$scope.objSeccion.lista.length-1].activo = true;
		}
		var modalInstance = $modal.open({
			animation: true,
			templateUrl: 'tareagestionar_modal_agregarPlantilla.html',
			controller: 'Modal_tareagestionar_agregarPlantilla',
			resolve: {
				config : function(){
					return {
						accionEliminar : function(idPlantilla){
							for(var i = 0;i<$scope.objSeccion.lista.length;i++){
								if($scope.objSeccion.lista[i].ID_PADRE == idPlantilla){
									$scope.objSeccion.lista.splice(i,1);
								}
							}
						}
					};
				}
			}
		});
		modalInstance.result.then(function(){	
		});
	};
	
	$scope.objAtributo.cargar();
	  
});

mapeo.registerCtrl('Modal_tareagestionar_agregarPlantilla', function ($scope, ajax, $modalInstance, config) {

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
			clase : "TareaGestionar",
			metodo : 'listarSeccion',
			"TSB_W_TAREA_SECCION_ID" : 'is_null'
		},function(respuesta){
			$scope.listaPlantillas = respuesta;
		});
	};
	
	cargarPlantillas();
	
	$scope.eliminar = function(){
		if(confirm("Desea eliminar la plantilla seleccionada?")){
			ajax.jpo({
				paquete : "modulo", 
				clase : "TareaGestionar",
				metodo : 'eliminarSeccion',
				"TSB_W_ID" : $scope.idPlantilla,
				"TSS_W_TAREA_SECCION_ID" : $scope.idPlantilla
			},function(respuesta){
				if(respuesta){
					config.accionEliminar($scope.idPlantilla);
					alert("atributo eliminado correctamente");
					$modalInstance.close();
				}
			});
		};
	};
	
	
});