var getParametro = getQueryString();

var mapeo = angular.module('mapeo', ['core','ui.bootstrap', "dndLists"]);

	mapeo.controller("principal", function($scope, $timeout, $modal, ajax, util) {
		
		// SCOPE
		
		$scope.data = {};
		
		$scope.modulo = ''; // proyectogestionar - proyectocargado  - usuariogestionar - usuarioperfil
		$scope.subModulo = ''; // trabajarProyecto : { modulosProyecto 0 , generarProyecto 1, esquema 2, dataSource 3, rol};
		
		$scope.$watch('modulo', function(newValue, oldValue) {
			if(newValue != oldValue){
				var scope = $scope.getControladorScope(newValue);
				if(scope != null){
					scope.instanciar();
				}
			}
		});
		
		$scope.getControladorScope = function(controlador){
			var obj = angular.element(document.querySelector('[ng-controller='+controlador+']'));
			if(obj.length>0){
				return obj.scope();
			} else {
				return null;
			}
		};
		
		$scope.cargarModulo = function(modulo,functionEjec){
			$scope.modulo = modulo;
			if(functionEjec){
				functionEjec();
			}
		};
		
		$scope.getPerfil = function(perfil){
			var obs = util.getObjeto($scope.data.config.perfiles,{id : perfil});
			if(obs){
				return obs.valor;
			} else {
				return "";
			}
		};
		
		$scope.cargarTrabajarProyecto = function(objetoProyecto){
			
     		$scope.data.PROYECTO = objetoProyecto;
			$scope.data.PROYECTO.esCargado = true;
			
			if(getParametro.submodulo){
				$scope.subModulo = getParametro.submodulo;
			} else {
				$scope.subModulo = "proyectodetalle";
			}
			
			var dataReq = { paquete: "gestion", clase: "Principal", metodo: "listarRegistros"};
			ajax.get(dataReq,{
				LIB_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIS_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIC_W_COD_PROYECTO 	: $scope.data.PROYECTO.cod_proyecto,
				LIC_W_COD_USUARIO 	: $scope.data.USUARIO.cod_usuario
			},function(respuesta){
				angular.extend($scope.data, respuesta);
				$scope.modulo = "proyectocargado";
			});
			
		};
		
		$scope.cerrarTrabajarProyecto = function(){
			$scope.data.PROYECTO = {esCargado : false};
			$scope.modulo = "proyectogestionar";
		};
		
		$scope.cargarProyecto = function(){
			var dataReq = { paquete: "gestion", clase: "Principal", metodo: "listarProyectos"};
			ajax.get(dataReq,{EQU_W_COD_USUARIO : $scope.data.USUARIO.cod_usuario},function(respuesta){
			
				var proyectoId = [];
				for(var i in respuesta.PROYECTOS){
					proyectoId[respuesta.PROYECTOS[i].cod_proyecto] = i;
					respuesta.PROYECTOS[i].equipo = [];
				}
				for(var i in respuesta.USUARIOS_PROYECTOS){
					if(respuesta.USUARIOS_PROYECTOS[i].esResponsable && respuesta.USUARIOS_PROYECTOS[i].cod_usuario == $scope.data.USUARIO.cod_usuario){
						$scope.data.USUARIO.es_responsable = true;
					}
					respuesta.PROYECTOS[proyectoId[respuesta.USUARIOS_PROYECTOS[i].cod_proyecto]].equipo.push(respuesta.USUARIOS_PROYECTOS[i]);
				}
				angular.extend($scope.data, respuesta);
				
				if(getParametro.proyecto){
					$scope.cargarTrabajarProyecto(respuesta.PROYECTOS[proyectoId[getParametro.proyecto]]);
				} else {
					$scope.modulo = 'proyectogestionar';
					//$scope.getControladorScope("proyectogestionar").instanciar();
				}
				
			});
		};
		
		$scope.alertas = [];
		$scope.agregarAlerta = function(tipo,mensaje){
			$scope.alertas.push({tipo: tipo,mensaje: mensaje});
			var pos = $scope.alertas.length-1;
			$timeout(function(){
				$scope.alertas.splice(pos,1);
			},3000);
		};
		$scope.cerrarAlerta = function(index) {
			$scope.alertas.splice(index, 1);
		};
		
		// UTIL
		
		$scope.getUltimoCodigo = function(objeto,nombreCodigo){
			var contador = 0;
			for(var i = 0; i<$scope.data[objeto].length; i++){
				var nro = Number($scope.data[objeto][i][nombreCodigo]);
				if(nro>contador){
					contador = nro;
				}
			}
			return contador+1;
		};
		
		// Variables
		
		var cargarUsuario = function(usuario,callback){
			var dataReq = { paquete: "gestion", clase: "Usuario", metodo: "cargarUsuario"};
			ajax.get(dataReq,{USU_W_COD_USUARIO : usuario},function(respuesta){
				$scope.data.USUARIO = respuesta;
				if(callback){ callback();}
			});
		};
						
		var instanciar = function(){

			ajax.cargarConfiguracion(function(respuesta){
				document.title = respuesta.config.proyecto + " v"+respuesta.config.version;
				$scope.data.config = respuesta.config;				
				if(getParametro.usuario){
					cargarUsuario(getParametro.usuario,$scope.cargarProyecto);
				} else {
					$scope.cargarProyecto();
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
	
	mapeo.controller('rol_modal_entidad', function ($scope, $modalInstance, ajax, config) {

		$scope.config = config;
		
		var cargarAsociacion = function(){
			var consulta = {
				paquete : "modulo", 
				clase : "Rol",
				metodo : "listarRolxEntidad",
				tabla : config.tabla
			};
				consulta["ROL_W_"+config.cod_entidad] = config.atributo;
				
			ajax.jpo(consulta,function(respuesta){
				var misRojes = {};
				for(var i = 0;i< respuesta.length; i++){
					if(typeof(misRojes[respuesta[i].cod_rol]=="undefined")){
						misRojes[respuesta[i].cod_rol] = true;
					}
				}
				
				for(var i = 0;i< $scope.config.rol.length; i++){
					if(misRojes[$scope.config.rol[i].cod_rol]==true){
						$scope.config.rol[i].esSeleccionado = true;
					} else {
						$scope.config.rol[i].esSeleccionado = false;
					}
				}
			});
		};
		
		cargarAsociacion();
		
		$scope.guardar = function(){
			var consulta = {
				paquete : "modulo", 
				clase : "Rol",
				metodo : "registrarRolxEntidad",
				tabla : config.tabla
			};
				consulta["ROL_W_"+config.cod_entidad] = config.atributo;
			
			var cont = 1;
			for(var i = 0;i< $scope.config.rol.length; i++){
				if($scope.config.rol[i].esSeleccionado==true){
					consulta["ROL_M_"+cont+"_cod_rol"] = $scope.config.rol[i].cod_rol;
					consulta["ROL_M_"+cont+"_"+config.cod_entidad] = $scope.config.atributo;
					cont++;
				}
			}

			ajax.jpo(consulta,function(respuesta){
				if(respuesta==true){
					alert("Agregado");
					//$scope.agregarAlerta("success","Proceso editado corréctamente");
					$modalInstance.close();
				}
			});
		};
		
		$scope.cancelar = function(){
			$modalInstance.close();
		};
		
	});
