var getParametro = getQueryString();

var reglaMapeo = function(datos){
	var incorrectas = 0;
	var respuesta = {
		resultado : false,
		mensaje : ""
	};
	for(var i in datos){
		var campo = datos[i];
		if(campo.regla=="REQUERIDO"){
			if(typeof(campo.valor)=="undefined"){
				respuesta.mensaje = campo.mensaje;
				incorrectas++;
				break;
			} else {
				if(typeof(campo.valor)=="object"){
					if(Object.size(respuesta)==0){
						respuesta.mensaje = campo.mensaje;
						incorrectas++;
						break;
					}
				} else {
					if(campo.valor.length==0){
						respuesta.mensaje = campo.mensaje;
						incorrectas++;
						break;
					}
				}
			}
		}
		if(campo.regla=="ENTERO"){
			if (!/^\+?([1-9]\d*)$/.test(campo.valor)){
				respuesta.mensaje = campo.mensaje;
				incorrectas++;
				break;
			}
		}
	}
	if(incorrectas==0){
		respuesta.resultado = true;
	}
	return respuesta;
};

var Mapeo = {};

	Mapeo.data = {
		PERFIL : [
			{ id: "PRO_MAN", valor: "GESTOR DE PROYECTOS"},
			{ id: "LID_TEC", valor: "LIDER TÉCNICO"},
			{ id: "ANA_PRO", valor: "ANALISTA PROGRAMADOR"}
		]
	};

var mapeo = angular.module('mapeo', ['core','ui.bootstrap', "dndLists"]);

	mapeo.controller("principal", function($scope, $timeout, $modal, ajax) {

		$scope.fecha = {
			formato : "yyyy-MM-dd",
			abierto : {},
			abrir : function($event,tipo) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.fecha.abierto[tipo] = true;
			}
		};

		$scope.modulo = 'proyectogestionar'; // proyectogestionar - proyectocargado  - usuariogestionar - usuarioperfil
		$scope.subModulo = ''; // trabajarProyecto : { modulosProyecto 0 , generarProyecto 1, esquema 2, dataSource 3, rol};
		$scope.data = {
			PROYECTO : {esCargado : false}
		};
		$scope.combo = {
			Perfil : Mapeo.data.PERFIL
		};
		
		$scope.getControladorScope = function(controlador){
			return angular.element(document.querySelector('[ng-controller='+controlador+']')).scope();
		};
		
		$scope.getControlador = function(controlador){
			return angular.element(document.querySelector('[ng-controller='+controlador+']'));
		};
		
		$scope.getComboxId = function(nombreCombo,buscar,id,valor){
			if(!id){id = "id";} if(!valor){valor = "valor";}
			for(var i in $scope.combo[nombreCombo]){
				if($scope.combo[nombreCombo][i][id] == buscar){
					return $scope.combo[nombreCombo][i][valor];
				}
			}
		};
		
		$scope.cargarUsuario = function(usuario,callback){
			var dataReq = { paquete: "gestion", clase: "Usuario", metodo: "cargarUsuario"};
			ajax.get(dataReq,{USU_W_COD_USUARIO : usuario},function(respuesta){
				$scope.data.USUARIO = respuesta;
				if(callback){ callback();}
			});
		};
		
		$scope.cargarProyecto = function(){
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
		
		$scope.cerrarTrabajarProyecto = function(){
			delete $scope.data.PROYECTO;
			$scope.data.PROYECTO = {esCargado : false};
			$scope.modulo = "proyectogestionar";
			$scope.getControladorScope("proyectogestionar").instanciar();
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
		
		if(getParametro.usuario){
			$scope.cargarUsuario(getParametro.usuario,$scope.cargarProyecto);
		} else {
			$scope.cargarProyecto();
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
		
		$scope.abrirGenerador = function(){
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'Generar.html',
				controller: 'Modal_generar',
				resolve: {
					config : function(){
						return {
							data : $scope.data
						};
					}
				}
			});
			modalInstance.result.then(function(){
				$scope.instanciar();
			});
		};
		
		$scope.generar = function(){
			
		};
		
		$scope.cargarModulo = function(modulo,subModulo,id){
			$scope.getControladorScope("principal").modulo = modulo;
			var tabs = $scope.getControladorScope(modulo).tabs;
			for(var i in tabs.lista){
				if(tabs.lista[i].id == subModulo){
					tabs.lista[i].activo = true;
				}
			}
			var elemScope = $scope.getControladorScope(subModulo);
				elemScope.editarCargar(id);
		};
		
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
	
	
	mapeo.directive('showErrors', function ($timeout, showErrorsConfig) {
      var getShowSuccess, linkFn;
      getShowSuccess = function (options) {
        var showSuccess;
        showSuccess = showErrorsConfig.showSuccess;
        if (options && options.showSuccess != null) {
          showSuccess = options.showSuccess;
        }
        return showSuccess;
      };
      linkFn = function (scope, el, attrs, formCtrl) {
        var blurred, inputEl, inputName, inputNgEl, options, showSuccess, toggleClasses;
        blurred = false;
        options = scope.$eval(attrs.showErrors);
        showSuccess = getShowSuccess(options);
        inputEl = el[0].querySelector('[name]');
        inputNgEl = angular.element(inputEl);
        inputName = inputNgEl.attr('name');
        if (!inputName) {
          throw 'show-errors element has no child input elements with a \'name\' attribute';
        }
        inputNgEl.bind('blur', function () {
          blurred = true;
          return toggleClasses(formCtrl[inputName].$invalid);
        });
        scope.$watch(function () {
			//console.log($(inputEl).is(":visible")); && $(inputEl).is(":visible")
			return formCtrl[inputName] && formCtrl[inputName].$invalid ;
        }, function (invalid) {
          if (!blurred) {
            return;
          }
          return toggleClasses(invalid);
        });
        scope.$on('show-errors-check-validity', function () {
          return toggleClasses(formCtrl[inputName].$invalid);
        });
        scope.$on('show-errors-reset', function () {
          return $timeout(function () {
            el.removeClass('has-error');
            el.removeClass('has-success');
            return blurred = false;
          }, 0, false);
        });
        return toggleClasses = function (invalid) {
          el.toggleClass('has-error', invalid);
          if (showSuccess) {
            return el.toggleClass('has-success', !invalid);
          }
        };
      };
      return {
        restrict: 'A',
        require: '^form',
        compile: function (elem, attrs) {
          if (!elem.hasClass('form-group')) {
            throw 'show-errors element does not have the \'form-group\' class';
          }
          return linkFn;
        }
      };
    });
	
	mapeo.provider('showErrorsConfig', function () {
		var _showSuccess;
		_showSuccess = false;
		this.showSuccess = function (showSuccess) {
		  return _showSuccess = showSuccess;
		};
		this.$get = function () {
		  return { showSuccess: _showSuccess };
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
	/*
	mapeo.directive('popover2',['$parse', '$compile','$log','$templateCache','$position',
       function ($parse,$compile,$log,$templateCache,$position ) {
            function link(scope, element, attrs) {
                var popupScope = scope.$new(false);   
                var html = $templateCache.get('views/popover/popover-html-unsafe-popup.html').trim();
                var template = angular.element(html)
                
                var popupLinkFn = $compile(template);
                
                var popup = popupLinkFn(popupScope);
                
                popupScope.isOpen = false;             
                popupScope.content = attrs['popover2'];
                var position = function(){
                    var ttPosition = $position.positionElements(element, popup, scope.placement, false);
                    ttPosition.top += 'px';
                    ttPosition.left +='px';
                    popup.css(ttPosition);
                };
                popupScope.setOpen = function(val) {                 
                    
                    popupScope.isOpen = val;
                    popup.css({display: popupScope.isOpen ? 'block' :'none' });       
                    position();
                    // call twice, same as tooltip.js
                    position();
                    
                };            
               
                var cleanup = element.on('click',function(event){   
                   popupScope.setOpen(!popupScope.isOpen);
                });
                
                element.after(popup);
                element.on('$destroy',cleanup);
                
            }
           return {
               replace:false,            
               link:link,
               scope: {title: "@", placement: "@",},
           }; 
       }
    ]);*/

