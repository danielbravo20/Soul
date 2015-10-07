portal.registerCtrl('tareas', function($scope, $modal, ajax) {
	
	$scope.tareasConfig = {
		vista : "lista"
	};
	
	$scope.tareas = [];
	
	var listarTareas = function(){
		
		ajax.get({
			url : "tarea",
			data : {accion:"disponibles"},
			getRespuesta : function(lista){
				
				for(var i in lista){
					
					lista[i].codigoUsuarioDueno = 1;
					lista[i].duenoRol = "Almirante";
					lista[i].tieneFoto = true;
					lista[i].sexo = "M";
					lista[i].fechaCreacionEstilo = "btn-warning";
					lista[i].fechaCreacionResumen = "2 Días";
					
					if(lista[i].tieneFoto){
						lista[i].urlFoto = "/Soul/imagenes/fotos/"+lista[i].codigoUsuarioDueno+".png";
					} else if(!lista[i].tieneFoto && lista[i].sexo=='M'){
						lista[i].urlFoto = "/Soul/imagenes/icono_varon.png";
					} else if(!lista[i].tieneFoto && lista[i].sexo=='F'){
						lista[i].urlFoto = "/Soul/imagenes/icono_mujer.png";
					}
				}
				
				$scope.tareas = lista;
				
			}/*,
			mostrarCargador : true*/
		});
		
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
	
	$scope.iniciarProceso = function(codigoProcesoPlantilla){
		$scope.getControlador("base_iniciarproceso").cargarIniciarProceso(codigoProcesoPlantilla);
		$scope.tareasConfig.vista = "inicio";
	}
	
	$scope.instanciarTareas = function(){
		$scope.vistaTarea = "lista";
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