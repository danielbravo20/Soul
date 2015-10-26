﻿var getParametro = getQueryString();

var mapeo = angular.module('mapeo', ['core','ui.bootstrap', "dndLists"]);

	mapeo.controller("principal", function($scope, $timeout, $modal, ajax) {
		
		// SCOPE
		
		$scope.data = {};
		$scope.modulo = 'proyectogestionar'; // proyectogestionar - proyectocargado  - usuariogestionar - usuarioperfil
		$scope.subModulo = ''; // trabajarProyecto : { modulosProyecto 0 , generarProyecto 1, esquema 2, dataSource 3, rol};
		
		$scope.getControladorScope = function(controlador){
			return angular.element(document.querySelector('[ng-controller='+controlador+']')).scope();
		};
		
		$scope.cargarTrabajarProyecto = function(objetoProyecto){
			
     		$scope.data.PROYECTO = objetoProyecto;
			$scope.data.PROYECTO.esCargado = true;
			$scope.modulo = "proyectocargado";
			
			if(getParametro.submodulo){
				$scope.subModulo = getParametro.submodulo;
			} else {
				$scope.subModulo = "proyectodetalle";
			}
			
			var dataReq = { paquete: "gestion", clase: "Principal", metodo: "listarRegistros"};
			ajax.get(dataReq,{
				LIB_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIS_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIS_W_COD_VERSION 	: "1",
				LIC_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIC_W_COD_USUARIO 	: $scope.data.USUARIO.cod_usuario
			},function(respuesta){
				angular.extend($scope.data, respuesta);
				$scope.getControladorScope("proyectocargado").instanciar();
			});
			
		};
		
		// Variables
		
		var cargarUsuario = function(usuario,callback){
			var dataReq = { paquete: "gestion", clase: "Usuario", metodo: "cargarUsuario"};
			ajax.get(dataReq,{USU_W_COD_USUARIO : usuario},function(respuesta){
				$scope.data.USUARIO = respuesta;
				if(callback){ callback();}
			});
		};
		
		var cargarProyecto = function(){
			var dataReq = { paquete: "gestion", clase: "Principal", metodo: "listarProyectos"};
			ajax.get(dataReq,{EQU_W_COD_USUARIO : $scope.data.USUARIO.cod_usuario},function(respuesta){
			
				var proyectoId = [];
				for(var i in respuesta.PROYECTOS){
					proyectoId[respuesta.PROYECTOS[i].cod_proyecto] = i;
					respuesta.PROYECTOS[i].equipo = [];
					respuesta.PROYECTOS[i].versiones = [];
				}
				for(var i in respuesta.USUARIOxProyectos){
					if(respuesta.USUARIOxProyectos[i].esResponsable && respuesta.USUARIOxProyectos[i].cod_usuario == $scope.data.USUARIO.cod_usuario){
						$scope.data.USUARIO.esResponsable = true;
					}
					respuesta.PROYECTOS[proyectoId[respuesta.USUARIOxProyectos[i].cod_proyecto]].equipo.push(respuesta.USUARIOxProyectos[i]);
				}
				for(var i in respuesta.Versiones){
					respuesta.PROYECTOS[proyectoId[respuesta.Versiones[i].cod_proyecto]].versiones.push(respuesta.Versiones[i]);
				}
				angular.extend($scope.data, respuesta);
				
				if(getParametro.proyecto){
					$scope.cargarTrabajarProyecto(respuesta.PROYECTOS[proyectoId[getParametro.proyecto]]);
				} else {
					$scope.getControladorScope("proyectogestionar").instanciar();
				}
				
			});
		};
				
		var instanciar = function(){
			ajax.cargarConfiguracion(function(respuesta){
				document.title = respuesta.config.proyecto + " v"+respuesta.config.version;
				$scope.data.config = respuesta.config;
				if(getParametro.usuario){
					cargarUsuario(getParametro.usuario,cargarProyecto);
				} else {
					cargarProyecto();
				};
			});
		};
		
		instanciar();
				
	});
	
	mapeo.controller('Modal_generar', function ($scope, $modalInstance, config) {

		$scope.data = config.data;
		
		$scope.cancelar = function(){
			$modalInstance.close();
		};
		
		$scope.salir = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});
	
	mapeo.run(function(pagerConfig, paginationConfig, datepickerPopupConfig){
	   pagerConfig.previousText = '<< Anterior';
	   pagerConfig.nextText = 'Siguiente >>';
	   
	   paginationConfig.previousText = '<< Anterior';
	   paginationConfig.nextText = 'Siguiente >>';
	   paginationConfig.firstText = 'Primero';
	   paginationConfig.lastText = 'Ultimo';
	   
	   datepickerPopupConfig.closeText = 'Cerrar';
	   datepickerPopupConfig.currentText = 'Hoy';
	   datepickerPopupConfig.clearText = 'Limpiar';
	});
	
	mapeo.config(function($controllerProvider) {
		mapeo.registerCtrl = $controllerProvider.register;
	});