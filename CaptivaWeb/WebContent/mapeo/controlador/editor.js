mapeo.registerCtrl('editor', function($scope, $modal, ajax, util) {
	
	angular.extend($scope.$parent.$parent.editor,{
		esEdicion : true,
		fechaInicio : new Date(),
		url_atributo_tipo : "plantilla/inc_editor_atributo_"+$scope.editor.atributo_tipo+".html" // detallado | sololectura
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