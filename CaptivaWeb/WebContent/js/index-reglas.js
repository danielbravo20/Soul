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