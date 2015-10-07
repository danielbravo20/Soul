portal.registerCtrl('procesos', function($scope, $modal) {
	
	$scope.tareas = [];
	
	var listarTareas = function(){
		var lista = [];
			
			lista.push({
				codigoProceso : 1,
				nombreProceso : "Emision",
				colorProceso : "#66FF66",
				codigoTarea : 1,
				nombreTarea : "Completar Solicitud",
				descripcion : "DNI 2231232",
				codigoUsuarioDueno : 1,
				dueno : "Miguel Grau Seminario",
				duenoRol : "Almirante",
				tieneFoto : true,
				sexo : "M",
				fechaCreacion : new Date(),
				fechaCreacionEstilo : "btn-warning",
				fechaCreacionResumen : "2 Días"
			});
			
			lista.push({
				codigoProceso : 2,
				nombreProceso : "Modificación",
				colorProceso : "#0099FF",
				codigoTarea : 2,
				nombreTarea : "Completar Solicitud",
				descripcion : "DNI 3424332",
				codigoUsuarioDueno : 2,
				dueno : "Sarita Colonia",
				duenoRol : "Santa",
				tieneFoto : true,
				sexo : "M",
				fechaCreacion : new Date(),
				fechaCreacionEstilo : "btn-success",
				fechaCreacionResumen : "1 Hora"
			});
			
			lista.push({
				codigoProceso : 1,
				nombreProceso : "Renovacion",
				colorProceso : "#CC66FF",
				codigoTarea : 3,
				nombreTarea : "Visar Solicitud",
				descripcion : "DNI 2231232",
				codigoUsuarioDueno : 3,
				dueno : "Tupac Amaru",
				duenoRol : "Cacique",
				tieneFoto : true,
				sexo : "F",
				fechaCreacion : new Date(),
				fechaCreacionEstilo : "btn-success",
				fechaCreacionResumen : "3 Horas"
			});
		
		for(var i in lista){
			if(lista[i].tieneFoto){
				lista[i].urlFoto = "/Soul/imagenes/fotos/"+lista[i].codigoUsuarioDueno+".png";
			} else if(!lista[i].tieneFoto && lista[i].sexo=='M'){
				lista[i].urlFoto = "/Soul/imagenes/icono_varon.png";
			} else if(!lista[i].tieneFoto && lista[i].sexo=='F'){
				lista[i].urlFoto = "/Soul/imagenes/icono_mujer.png";
			}
		}
		
		$scope.tareas = lista;
		
	};
	
	listarTareas();
	
	
	$scope.verDetalle = function(){
		var modalInstance = $modal.open({
			animation: true,
			templateUrl: 'modulo/procesos_detalleTarea.html',
			controller: 'Modal_procesos_detalleTarea',
			resolve: {
				config : function(){
					return {
						accionEliminar : function(idPlantilla){
						}
					};
				}
			}
		});
			modalInstance.result.then(function(){	
			});
	}
	
	// FILTRO
	
	$scope.filtrar = {
		abrir : function(){
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'modulo/procesos_filtrartareas.html',
				controller: 'Modal_procesos_perfilDueno',
				resolve: {
					config : function(){
						return {
							accionEliminar : function(idPlantilla){
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

portal.registerCtrl('Modal_procesos_perfilDueno', function ($scope, ajax, $modalInstance, config) {

	$scope.listaPlantillas = [];
	
	$scope.filtrar = function(){
		$modalInstance.close();
	};
	
	$scope.limpiar = function(){
		$modalInstance.close();
	};
	
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});

portal.registerCtrl('Modal_procesos_detalleTarea', function ($scope, ajax, $modalInstance, config) {

	$scope.listaPlantillas = [];
	
	$scope.filtrar = function(){
		$modalInstance.close();
	};
	
	$scope.limpiar = function(){
		$modalInstance.close();
	};
	
	$scope.cancelar = function(){
		$modalInstance.close();
	};
	
});