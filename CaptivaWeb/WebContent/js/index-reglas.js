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

//v1.0
mapeo.directive('fecha', function($compile) {
    // directive factory creates a link function
    return {
    	restrict: 'E',
    	link: function(scope, element, attrs) {
    		this.getId = function(){
    		    var text = "";
    		    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    		    for( var i=0; i < 10; i++ )
    		        text += possible.charAt(Math.floor(Math.random() * possible.length));

    		    return text;
    		};
    		var fecha = "",fechaMin = "",fechaMax = "",botonFecha = '',id='',ancho="200",titulo='',mostrarError='',name='',requerido='';
		  	if(attrs.fechaMin){
		  		fechaMin = 'min-date="'+attrs.fechaMin+'"';
		  	}
		  	if(attrs.fechaMax){
		  		fechaMax = 'max-date="'+attrs.fechaMax+'"';
		  	}
		  	if(attrs.id){
		  		id = attrs.id;
		  	} else {
		  		id = this.getId();
		  	}
		  	if(attrs.ancho){
		  		ancho = attrs.ancho;
		  	}
		  	if(attrs.mostrarError=="true"){
		  		mostrarError = "show-errors";
		  		requerido = "required";
		  		name = 'name="'+id+'"';
		  	}
		  	if(attrs.requerido){
		  		requerido = 'ng-required="'+attrs.requerido+'"';
		  	}
		  	if(attrs.titulo){
		  		titulo = 'tooltip="'+attrs.titulo+'"';
		  	}
		  	if(isIE || isFirefox){
		  		fecha = '<input '+name+' type="text" '+titulo+' class="form-control input-sm" ng-model="'+attrs.fecha+'" is-open="$parent.fecha.abierto.'+id+'" style="width: 150px;" '+fechaMin+' '+fechaMax+' datepicker-popup="dd/MM/yyyy" '+requerido+'/>';
			} else {
		  		fecha = '<input '+name+' type="date" '+titulo+' class="form-control input-sm" ng-model="'+attrs.fecha+'" is-open="$parent.fecha.abierto.'+id+'" style="width: 150px;" '+fechaMin+' '+fechaMax+' datepicker-popup '+requerido+'/>';
	        }
		  	
		  	botonFecha = '<span class="input-group-btn" style="display:inline-block"><button type="button" class="btn btn-default btn-sm" ng-click="$parent.fecha.abrir($event,\''+id+'\')"><i class="glyphicon glyphicon-calendar"></i>&nbsp;</button></span>';
        	element.html('<p class="input-group" style="width: '+ancho+'px; display: inline-block" '+mostrarError+'>'+fecha+botonFecha+'</p>');
          	$compile(element.contents())(scope);
    	}
    }
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

// v1.0
mapeo.directive('nxRegla', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
    	    	
            if (!ctrl) return;
            
            var reglas = {};
            
                reglas.decimal = function(valor){
	                	
					var obj = {};
						obj.valorEntero = (""+valor).replace(/[^\d|\-+|\.+]/g, '');
	                	obj.valorExterno = "";
	
	                var decimal = obj.valorEntero.split(".");
	                if(attrs.nxMaxEntero){
	                    var maxEnt = Number(attrs.nxMaxEntero);
	                    if(decimal[0].length > maxEnt){
	                        decimal[0] = decimal[0].substr(0,maxEnt);
	                        obj.valorEntero = decimal[0]+obj.valorEntero.substring(maxEnt+1);
	                    }
	                }
	                if(decimal.length==1){
	                    if(obj.valorEntero.length==0){
	                    	obj.valorExterno = ""; 
	                    } else {
	                    	//obj.valorExterno = $filter('number')(decimal[0]);  
	                    	obj.valorExterno = $filter('number')(decimal[0])+".00";  
	                    }
	                } else if(decimal.length==2){
	                   if(attrs.nxMaxDecimal){
	                          var dec = Number(attrs.nxMaxDecimal);
	                          if(decimal[1].length > dec){
	                            decimal[1] = decimal[1].substr(0,dec); 
	                          }
	                    }
	                    obj.valorExterno = $filter('number')(decimal[0])+"."+decimal[1];
	                    obj.valorEntero = decimal[0]+"."+decimal[1];
	                    if(decimal[1].length==0){
	                        obj.valorEntero = decimal[0];
	                    }
	                } else {
	                    obj.valorExterno = $filter('number')(decimal[0])+"."+decimal[1];
	                    if(decimal[1].length==0){
	                        obj.valorEntero = decimal[0];
	                    } else {
	                        obj.valorEntero = decimal[0]+"."+decimal[1];   
	                    }
	                    
	                }
					return obj;
	            };
	            
	            reglas.validarPosicion = function(miObj){
	            	var decimal = miObj.valorExterno.split(".");
	            	if(decimal.length==2 && decimal[1]=="00"){
                        setCaretPosition(elem[0],miObj.valorExterno.length-3);
	                }
	            };

            ctrl.$formatters.unshift(function (a) {
                if(attrs.nxRegla){
                    if(attrs.nxRegla=="decimal"){
                        var miObj = reglas.decimal(ctrl.$modelValue);
                        
                        return miObj.valorExterno;
                    }
                }
            });
            
            function setCaretPosition(elem, caretPos) {
                if (elem != null) {
                    if (elem.createTextRange) {
                        var range = elem.createTextRange();
                        range.move('character', caretPos);
                        range.select();
                    } else {
                        if (elem.selectionStart) {
                            elem.focus();
                            elem.setSelectionRange(caretPos, caretPos);
                        } else
                            elem.focus();
                    }
                }
            }
            
            ctrl.$parsers.unshift(function (viewValue) {
                if(attrs.nxRegla){
                    if(attrs.nxRegla=="decimal"){
                        var miObj = reglas.decimal(viewValue);
                        elem.val(miObj.valorExterno);
                        reglas.validarPosicion(miObj);
                        return miObj.valorEntero;
                    }
                }
            });
        }
    };
}]);

mapeo.directive('maximaLongitud', function() { // maxima-longitud
  return {
    require: 'ngModel',
    link: function (scope, element, attrs, ngModelCtrl) {
      
      var validarLongitud = function(cadena,longitudMax){
    	  if(typeof(cadena) == "number" && isNaN(cadena)){
    		  cadena = "";
    	  }
    	  if(cadena==undefined){
    		  cadena = "";
    	  }
    	var cadena = ""+cadena;
        if(cadena){
          if (longitudMax.length>0 && cadena.length>Number(longitudMax)) {
            return cadena.substring(0, Number(longitudMax));
          } else {
            return cadena;
          }
        } else {
          return "";
        }
      };
      
      function fromUser(text) {
          if(attrs.maximaLongitud){
              var transformedInput = validarLongitud(text,attrs.maximaLongitud);
              if(attrs.type=="number"){
            	  transformedInput = Number(transformedInput);
              }
              ngModelCtrl.$setViewValue(transformedInput);
              ngModelCtrl.$render();
              return transformedInput;
          }
          return text;
      };
      
      attrs.$observe('maximaLongitud', function(passedId) {
          ngModelCtrl.$setViewValue(validarLongitud(ngModelCtrl.$modelValue,attrs.maximaLongitud));
          ngModelCtrl.$render();
      });
      
      ngModelCtrl.$parsers.push(fromUser);
      
    }
  }; 
});