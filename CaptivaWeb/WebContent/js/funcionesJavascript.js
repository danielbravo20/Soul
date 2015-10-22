	
  var dateFormat = function () {
        var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
            timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
            timezoneClip = /[^-+\dA-Z]/g,
            pad = function (val, len) {
                val = String(val);
                len = len || 2;
                while (val.length < len) val = "0" + val;
                return val;
            };
    
        // Regexes and supporting functions are cached through closure
        return function (date, mask, utc) {
            var dF = dateFormat;
    
            // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
            if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
                mask = date;
                date = undefined;
            }
    
            // Passing date through Date applies Date.parse, if necessary
            date = date ? new Date(date) : new Date;
            if (isNaN(date)) throw SyntaxError("invalid date");
    
            mask = String(dF.masks[mask] || mask || dF.masks["default"]);
    
            // Allow setting the utc argument via the mask
            if (mask.slice(0, 4) == "UTC:") {
                mask = mask.slice(4);
                utc = true;
            }
    
            var _ = utc ? "getUTC" : "get",
                d = date[_ + "Date"](),
                D = date[_ + "Day"](),
                m = date[_ + "Month"](),
                y = date[_ + "FullYear"](),
                H = date[_ + "Hours"](),
                M = date[_ + "Minutes"](),
                s = date[_ + "Seconds"](),
                L = date[_ + "Milliseconds"](),
                o = utc ? 0 : date.getTimezoneOffset(),
                flags = {
                    d:    d,
                    dd:   pad(d),
                    ddd:  dF.i18n.dayNames[D],
                    dddd: dF.i18n.dayNames[D + 7],
                    m:    m + 1,
                    mm:   pad(m + 1),
                    mmm:  dF.i18n.monthNames[m],
                    mmmm: dF.i18n.monthNames[m + 12],
                    yy:   String(y).slice(2),
                    yyyy: y,
                    h:    H % 12 || 12,
                    hh:   pad(H % 12 || 12),
                    H:    H,
                    HH:   pad(H),
                    M:    M,
                    MM:   pad(M),
                    s:    s,
                    ss:   pad(s),
                    l:    pad(L, 3),
                    L:    pad(L > 99 ? Math.round(L / 10) : L),
                    t:    H < 12 ? "a"  : "p",
                    tt:   H < 12 ? "am" : "pm",
                    T:    H < 12 ? "A"  : "P",
                    TT:   H < 12 ? "AM" : "PM",
                    Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                    o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                    S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
                };
    
            return mask.replace(token, function ($0) {
                return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
            });
        };
    }();
    
    // Some common format strings
    dateFormat.masks = {
        "default":      "ddd mmm dd yyyy HH:MM:ss",
        shortDate:      "m/d/yy",
        mediumDate:     "mmm d, yyyy",
        longDate:       "mmmm d, yyyy",
        fullDate:       "dddd, mmmm d, yyyy",
        shortTime:      "h:MM TT",
        mediumTime:     "h:MM:ss TT",
        longTime:       "h:MM:ss TT Z",
        isoDate:        "yyyy-mm-dd",
        isoTime:        "HH:MM:ss",
        isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
        isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
    };
    
    // Internationalization strings
    dateFormat.i18n = {
        dayNames: [
            "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab",
            "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"
        ],    
        monthNames: [
            "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic",
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        ]
    };
    
    // For convenience...
    Date.prototype.format = function (mask, utc) {
        return dateFormat(this, mask, utc);
    };

	// Validamos si existe el objeto console en caso de IE
	if(typeof console === "undefined") {
	    var console = {
	        log		: function() { },
	        debug	: function() { },
	        info 	: function() { },
	        warn 	: function() { },
	        error 	: function() { }
	    };
	}

	// Funcion para validar si un objeto date es correctamente creado
	/*
	 * var obj = new Date("Jun 25, 2014 7:00:00 A");
	 * console.log(isValidDate(obj)); // false
	 * 
	 * var obj = new Date("Jun 25, 2014 7:00:00 AM");
	 * console.log(isValidDate(obj)); // true
	 * */
	function isValidDate(d) {
		if ( Object.prototype.toString.call(d) !== "[object Date]" )
			return false;
		return !isNaN(d.getTime());
	}
	
	// Contar la cantidad de elemento que tiene un objeto
	Object.size = function(obj) {
	    var size = 0, key;
	    for (key in obj) {
	        if (obj.hasOwnProperty(key)) size++;
	    }
	    return size;
	};
	
	var formatoTexto =function(opciones){
		
		this.opciones = {
			delimitador : " | ", 
			complemento	: " ",
			cortarExcedente : true
			//,longitudes : {2,3,11,3,10}
			//,tipoFormato : {'C','D','C'}
		};
		
		if(typeof(opciones)!="undefined"){
			this.opciones = $.extend({},this.opciones,opciones);
		}

		this.formatear_interno = function(texto,longitud,tipo){ // tipo: I,D,C
			if(texto.length<longitud){
				var resto = longitud-texto.length;
				// DEFINIENDO SEPARADORES 
				var separadorDerecha = 0;
				var separadorIzquierda = 0;
				if(tipo=="D"){
					separadorDerecha = resto;
				}
				if(tipo=="C"){
					if(resto%2==0){ // par
						separadorDerecha = resto/2;
						separadorIzquierda = resto/2;
					} else {
						separadorDerecha = (resto-1)/2;
						separadorIzquierda = (resto+1)/2;
					}
				}
				if(tipo=="I"){
					separadorIzquierda = resto;
				}
				// GENERANDO EL TEXTO
				var textoDerecha = "";
				var textoIzquierda = "";
				
				for(var a = 0;a<separadorDerecha;a++){
					textoDerecha += this.opciones.complemento;
				}
				for(var a = 0;a<separadorIzquierda;a++){
					textoIzquierda += this.opciones.complemento;
				}
				return textoIzquierda+texto+textoDerecha;
			}
			if(texto.length==longitud){
				return texto;
			}
			if(texto.length>longitud){
				if(this.opciones.cortarExcedence){
					return texto.substring(0,longitud);
				}
			}
		};
		
		this.formatear = function(campos){

			var textoFinal = "";
			for(var i in campos){
				if(typeof(campos[i])=="number"){
					campos[i] = ""+campos[i];
				}
				if(typeof(campos[i])=="string"){
					var longSoportada = campos[i].length;
					var tipoFormato = "C";
					if(this.opciones.longitudes){
						if(this.opciones.longitudes[i]){
							longSoportada = this.opciones.longitudes[i];
						}
					}
					if(this.opciones.tipoFormato){
						if(this.opciones.tipoFormato[i]){
							tipoFormato = this.opciones.tipoFormato[i];
						}
					}
					textoFinal += this.formatear_interno(campos[i],longSoportada,tipoFormato) + this.opciones.delimitador;
				}
			}
			return textoFinal.substring(0,textoFinal.length-(this.opciones.delimitador.length));
		};

	};
	
	function nombreFormatoBD(msj){
	    var letter, word = "";
	    for (var count = 0; count < msj.length; count = count +1){
	        letter = msj.charAt(count);
	        if (letter >= 'A' && letter <= 'Z' && count>0){ 
	            word +="_"+letter;       
	        } else {
	            word +=letter;
	        }
	    }
	    return word.toUpperCase();
	};
	
	function controlador(nombreControlador){
		return angular.element(document.querySelector('[ng-controller='+nombreControlador+']'));
	}
	function controladorScope(nombreControlador){
		return angular.element(document.querySelector('[ng-controller='+nombreControlador+']')).scope();
	}
	
	Array.prototype.move = function (old_index, new_index) {
		while (old_index < 0) {
			old_index += this.length;
		}
		while (new_index < 0) {
			new_index += this.length;
		}
		if (new_index >= this.length) {
			var k = new_index - this.length;
			while ((k--) + 1) {
				this.push(undefined);
			}
		}
		this.splice(new_index, 0, this.splice(old_index, 1)[0]);
		return this; // for testing purposes
	};
	
	getQueryString = function(){
		var query_string = {};
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i=0;i<vars.length;i++) {
			var pair = vars[i].split("=");
			if (typeof query_string[pair[0]] === "undefined") {
				query_string[pair[0]] = pair[1];
			} else if (typeof query_string[pair[0]] === "string") {
			var arr = [ query_string[pair[0]], pair[1] ];
			query_string[pair[0]] = arr;
			} else {
				query_string[pair[0]].push(pair[1]);
			}
		}
		return query_string;
	};