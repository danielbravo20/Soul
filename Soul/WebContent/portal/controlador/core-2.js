var core = angular.module('core', ['coreComun','ngFileUpload']);

	core.value("hostname","/Soul"); 
	
	core.factory("texto",function(){
		return {
			url : {
				desconectar : "/portal/preDesconexion.jsp"
			},
			conf : {
				codAtriUNPre : 6,
				codAtriProdPre : 7,
				mensajesAJAX : {
					errorUsuario : "Error usuario no correcto"
				}
			},
			defecto : {
				
			}
		};
	});
	
	core.factory("ajax", function($http, hostname, cargador, texto, Upload) {
		var tipoUrl = {
			"portal" : "/portal/portalController",
			"perfil" : "/portal/perfilController"
		};
		return {
			_get : function(opc){
				
				var opcDefecto = {
					url : null,
					data : null,
					getRespuesta : function(){},
					mostrarCargadorInicial : null, 	// Si solo se muestra el cargador de inicio
					mostrarCargadorFinal : null, 	// Si solo se oculta el cargador al final
					mostrarCargador : null 			// Se muestra y se oculta el cargador
				};

				var opciones = $.extend({},opcDefecto,opc);
			
				var jsonResultado = function(objRespuesta){
					var mensajeError = "";
					if(typeof(objRespuesta)!="undefined"){
						if(objRespuesta.resultado){
							var respuesta = objRespuesta.respuesta;
							if(typeof(respuesta)!="undefined"){
								if(typeof(opciones.getRespuesta)!="undefined"){
									opciones.getRespuesta(respuesta);
								}
							} else {
								mensajeError = "No se ha cargado ninguna respuesta";
							}
						} else {
							if(typeof(objRespuesta.mensajeError)!="undefined"){
								mensajeError = objRespuesta.mensajeError;
							} else {
								mensajeError = "Ha ocurrido un error";
							}
						}
					} else {
						mensajeError = "Respuesta Incorrecta";
					}
					if(mensajeError.length>0){
						cargador.mostrarError(mensajeError);
						if(texto.conf.mensajesAJAX.errorUsuario==mensajeError){
							location.href = "ibm_security_logout?logoutExitPage=../index.jsp";
						}
					}
				};
				var jsonCarga = function(){
					angular.element(document.querySelector('[ng-controller=principal]')).scope().tiempo.segundo = 0;
					return $http({
						url		: opciones.url,
						method	: "GET",
						params	: angular.extend({},{rnd : Math.random()},opciones.data),
						error 	: function( jqXHR, textStatus, errorThrown ){
							location.href = hostname+texto.url.desconectar;
						}
					});
				};
				
				var blnMostrarInicio = false;
				if(opciones.mostrarCargadorInicial != null && opciones.mostrarCargadorInicial){
					blnMostrarInicio = true;
				}
				var blnMostrarFinal = false;
				if(opciones.mostrarCargadorFinal != null && opciones.mostrarCargadorFinal){
					blnMostrarFinal = true;
				}
				
				if(opciones.mostrarCargador != null && opciones.mostrarCargador){
					blnMostrarInicio = true;
					blnMostrarFinal = true;
				}

				if(blnMostrarInicio){
					cargador.mostrar(function(){
						jsonCarga().success(function(data){
							if(blnMostrarFinal){
								cargador.ocultar(function(){
									jsonResultado(data);
								});
							} else {
								jsonResultado(data);
							}
						});
					});
				} else {
					jsonCarga().success(function(data){
						if(blnMostrarFinal){
							cargador.ocultar(function(){
								jsonResultado(data);
							});
						} else {
							jsonResultado(data);
						}
					});
				}
			},
			getAjaxArchivo : function(objeto){
				cargador.mostrar(function(){
					angular.element(document.querySelector('[ng-controller=principal]')).scope().tiempo.segundo = 0;
					Upload.upload({
	                    url		: objeto.url,
	                    fields	: objeto.data,
	                    file	: typeof(objeto.archivo)=="object"?objeto.archivo:{"files[]": objeto.archivo},
	                    fileFormDataName: objeto.nombreFile || 'file'
	                }).progress(function (evt) {
	                	var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
	                }).success(function (respuesta, status, headers, config) {
	                	cargador.ocultar(function(){
	                		if(respuesta.resultado){
	                			if(objeto.getRespuesta){
	                				objeto.getRespuesta(respuesta.respuesta);
			                	}
	                		} else {
	                			cargador.mostrarError(respuesta.mensajeError);
	                		}
						});
	                });
				});
				
			},
			getAjax : function(url,data,callback){
				if(typeof(url)=="object"){
					this._get(url);
				} else {
					if(!callback && typeof(data)=="function"){
						callback = data;
						data = {};
					}
					this._get({
						url : url,
						data : data,
						getRespuesta : callback
					});
				}
			},
			get : function(tipo,data,callback){debugger;
				if(typeof(tipo)=="object"){
					tipo.url = hostname+tipoUrl[tipo.url];
					this._get(tipo);
				} else {
					if(!callback && typeof(data)=="function"){
						callback = data;
						data = {};
					}
					this._get({
						url : hostname+tipoUrl[tipo],
						data : data,
						getRespuesta : callback
					});
				}
			},
			getConversionCatalogo : function(listaCatalogo){
				var catalogo = {};
				for(var i in listaCatalogo){
					catalogo[i] = {};
					var cont = 1;
					for(var e in listaCatalogo[i]){
						catalogo[i][cont] = { id : e, name: listaCatalogo[i][e] };
						cont++;
					}
				}
				return catalogo;
			},
			getCatalogos : function(data,callback){
				var _this = this;
				this.get("productoGC",data,function(respuesta){
					callback(_this.getConversionCatalogo(respuesta));
				});
			}
		};
	});
	
	core.factory("unidadNegocio", function() {
		return {
			parsear : function(unidadesNegocio){
				var UN = new Object();
				for (var IdunidadNegocio in unidadesNegocio) {
					var unidadNegocio = unidadesNegocio[IdunidadNegocio];
					if(typeof(UN[unidadNegocio.codigo])=="undefined"){
						UN[unidadNegocio.codigo] = new Object();
					}
					UN[unidadNegocio.codigo] = unidadNegocio;
					UN[unidadNegocio.codigo].producto = new Object();
					
					for (var Idproducto in unidadNegocio.productos){
						var producto = unidadNegocio.productos[Idproducto];
						if(typeof(UN[unidadNegocio.codigo].producto[producto.codigo])=="undefined"){
							UN[unidadNegocio.codigo].producto[producto.codigo] = new Object();
						}
						UN[unidadNegocio.codigo].producto[producto.codigo] = producto;
						UN[unidadNegocio.codigo].producto[producto.codigo].proceso = new Object();
						
						for (var Idproceso in producto.procesoPlantillas){
							var proceso = producto.procesoPlantillas[Idproceso];
							if(typeof(UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo])=="undefined"){
								UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo] = new Object();
							}
							UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo] = proceso;
							UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo].tarea = new Object();
							
							for (var Idtarea in proceso.tareaPlantillas){
								var tarea = proceso.tareaPlantillas[Idtarea];
								if(typeof(UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo].tarea[tarea.codigo])=="undefined"){
									UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo].tarea[tarea.codigo] = new Object();
								}
								UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo].tarea[tarea.codigo] = tarea;
								
							}
							
							delete UN[unidadNegocio.codigo].producto[producto.codigo].proceso[proceso.codigo].tareaPlantillas;
						}
						
						delete UN[unidadNegocio.codigo].producto[producto.codigo].procesoPlantillas;
					}
					
					delete UN[unidadNegocio.codigo].productos;
				}
				
				return UN;
			}
		};
	});
	
	core.factory("semaforo", function() {
		return {
			calcular : function(tiempoAmarillo,tiempoRojo,fechaActual,fechaCreacion){
				var objVencimiento = {
					"descripcion" : "",
					"color" : ""
				};
				var dateFechaAct, dateFechaCre;
				if(isValidDate(fechaActual)){
					dateFechaAct = fechaActual;
				} else {
					dateFechaAct = new Date(fechaActual);
				}
				if(isValidDate(fechaCreacion)){
					dateFechaCre = fechaCreacion;
				} else {
					dateFechaCre = new Date(fechaCreacion);
				}

				if (!isValidDate(dateFechaAct) || !isValidDate(dateFechaCre)) {
					return objVencimiento;
				}

				if (dateFechaAct.getTime() < dateFechaCre.getTime()) {
					return objVencimiento;
				}

				var totalSegundos = Math.round((dateFechaAct.getTime() - dateFechaCre.getTime()) / 1000);
				var totalMinutos = Math.round((totalSegundos / 60) * 100) / 100;
				var totalHoras = Math.round((totalSegundos / 3600) * 100) / 100;

				if (totalHoras > 24) {
					objVencimiento.descripcion = Math.round((totalHoras / 24) * 100) / 100 + " Días";
				}
				if (totalHoras <= 24) {
					objVencimiento.descripcion = totalHoras + " Hora(s)";
				}
				if (totalMinutos < 60) {
					objVencimiento.descripcion = totalMinutos + " Minuto(s)";
				}
				if (totalSegundos < 60) {
					objVencimiento.descripcion = totalSegundos + " Segundo(s)";
				}

				if (totalSegundos >= tiempoAmarillo) { // MAYOR IGUAL A 5 HORAS
					objVencimiento.color = "btn btn-danger btn-sm";
				}
				if (totalSegundos >= tiempoRojo && totalSegundos < tiempoAmarillo) { // MAYOR IGUAL A 2 // HORAS Y MENOR A 5 // HORAS
					objVencimiento.color = "btn btn-warning btn-sm";
				}
				if (totalSegundos < tiempoRojo) { // MENOR A 2 HORAS
					objVencimiento.color = "btn btn-success btn-sm";
				}
				if (totalSegundos <= 0) {
					objVencimiento.descripcion = "0 Segundos";
				}
				return objVencimiento;
			}
		};
	});
	
	core.factory("tarea", function() {
		return {
			adjunto : function(){
				return angular.element(document.querySelector('[ng-controller=tarea]')).scope().adjunto;
			}
		};
	});
	
	core.factory("validar", function() {
		
		var validacion = function(nombreObjPadre, solicitud, mensajeRespuesta, Lista){

			this.nombreObjPadre = nombreObjPadre;
			this.mensajeRespuesta = mensajeRespuesta;
			this.Lista = Lista || null;
			this.campos = [];
			this.modulos = {};
			this._solicitud = solicitud;
			
			this.setSolicitud = function(solicitud){
				if(solicitud){
					this._solicitud = solicitud;
				}
			};
			
			this.getcampoxId = function(objeto){
				for(var i in this.campos){
					if(this.campos[i].objeto == objeto){
						return i;
					};
				}
				return false;
			};
			
			this.agregar = function(objeto,mensaje,validacion,modulo,estado){
				var item = {
					objeto : objeto,
					mensaje : mensaje,
					validacion : validacion,
					modulo : modulo,
					estado : (estado)?estado:1,
					contadorError : 0
				};
				var eid = this.getcampoxId(objeto);
				if(!eid){
					this.campos.push(item);
				}
				if(!this.modulos[modulo]){
					this.modulos[modulo] = 0;
				};
				// InstanciarValidacion
				if(typeof(item.validacion)=="string"){
					this.instanciarValidacion(item,item.validacion);
				} else {
					for(var i in item.validacion){
						this.instanciarValidacion(item,item.validacion[i]);
					}
				}
			};
			
			this.cargar = function(listaObjeto){
				for(var i in listaObjeto){
					if(typeof(listaObjeto[i].tab)=="undefined"){
						listaObjeto[i].tab = 0;
					}
					this.agregar(i,listaObjeto[i].nombre,listaObjeto[i].validacion,listaObjeto[i].tab);
				}
			};
			
			this.mensaje = "";
			
			this.instanciarValidacion = function(item,validacion){
				
				var validacionDinamica = validacion.split(":");
				if(validacionDinamica.length==2 && validacionDinamica[0]=="LONGITUD"){
					var long = Number(validacionDinamica[1]);
					//document.querySelector("ng-model='"+this.solicitud[item.objeto]+"'");
					$("[ng-model='"+item.objeto+"']").attr("maxlength",long);
				}
				/*
				if(validacionDinamica.length==2 && validacionDinamica[0]=="DECIMAL"){
					var deci = Number(validacionDinamica[1]);
					if(this.solicitud[item.objeto].length != deci){
						mensajeTemp = "El campo '"+item.mensaje+"' es un campo debe tener "+deci+" decimales";
					}
				}*/
			};
			
			this.validar = function(){
				this.solicitud = getMapperValor(this.nombreObjPadre,this._solicitud);
				if(this.Lista!=null){
					for(var i in this.modulos){
						this.Lista[i].alertas = 0;
					}
				}
				this.mensaje = "";
				for(var i in this.campos){
					var item = this.campos[i];
					if(item.estado != 0){
						if(typeof(item.validacion)=="string"){
							this.ejecutarValidacion(item,item.validacion);
						} else {
							for(var i in item.validacion){
								this.ejecutarValidacion(item,item.validacion[i]);
							}
						}
						item.contadorError = 0;
					}
				}
				
				var seEncontroActivo = false;
				if(this.Lista!=null){
					for(var i in this.modulos){
						if(!seEncontroActivo){
							if(this.modulos[i]>0){
								this.Lista[i].activo = true;
								seEncontroActivo = true;
							}
						} else {
							this.Lista[i].activo = false;
						}
						this.Lista[i].alertas = this.modulos[i];
					}
				}
				if(this.mensaje.length==0){
					this.mensajeRespuesta.mensaje = "";
					return true;
				} else {
					this.mensajeRespuesta.mensaje = this.mensaje;
					for(var i in this.modulos){
						this.modulos[i] = 0;
					}
					return false;
				}
			};
			
			this.ejecutarValidacion = function(item,validacion){
				var mensajeTemp = "";
				if(validacion=="REQUERIDO"){
					if(typeof(this.solicitud[item.objeto])=="undefined" || this.solicitud[item.objeto].toString().trim()==""){					
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser obligatorio";
					}
				}
				if(validacion=="ENTERO"){
					var patron = /^[0-9]*$/;
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !patron.test(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un Número Entero válido";
					}	
				}
				if(validacion=="TELEFONO"){
					var patron = /^[0-9]{6,12}$/;
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !patron.test(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un Número Telefónico válido";
					}
				}
				if(validacion=="PORCENTAJE"){
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !isPorcentaje(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un Porcentaje válido";
					}
				}
				if(validacion=="EMAIL"){
					var patron = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !patron.test(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un Email válido";
					}	
				}
				if(validacion=="DNI"){
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !isDNI(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un DNI válido";
					}	
				}
				if(validacion=="RUC"){
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !isRUC(this.solicitud[item.objeto])){
						mensajeTemp = "Ingrese un Nro de RUC válido para el campo '"+item.mensaje+"'";
					}
				}
				if(validacion=="CE"){
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !isCE(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un Carnet de extrangería válido";
					}	
				}
				if(validacion=="CADENA"){
					var patron = /^[.´ñÑáéíóúÁÉÍÓÚa-zA-Z0-9\-\_\s]+$/;
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !patron.test(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un texto válido";
					}	
				}
				if(validacion=="ESPECIALES"){
					var patron = /^[´@¿?%$¬#&()"{}¡!°|'=\\]+$/;
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && patron.test(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser un texto válido";
					}	
				}
				if(validacion=="FECHA"){
					if(typeof(this.solicitud[item.objeto])!="undefined" && this.solicitud[item.objeto] !="" && !isValidDate(this.solicitud[item.objeto])){
						mensajeTemp = "El campo '"+item.mensaje+"' debe ser una fecha válida";
					}
				}
				
				// DINAMICOS
				var validacionDinamica = validacion.split(":");
				if(validacionDinamica.length==2 && validacionDinamica[0]=="LONGITUD"){
					var long = Number(validacionDinamica[1]);
					if(this.solicitud[item.objeto] && this.solicitud[item.objeto].length != long){
						mensajeTemp = "El campo '"+item.mensaje+"' debe tener longitud "+long;
					}
				}
				if(validacionDinamica.length==2 && validacionDinamica[0]=="LONGITUD_MIN"){
					var long = Number(validacionDinamica[1]);
					if(this.solicitud[item.objeto] && this.solicitud[item.objeto].length < long){
						mensajeTemp = "El campo '"+item.mensaje+"' debe tener longitud mínima de "+long;
					}
				}
				if(validacionDinamica.length==2 && validacionDinamica[0]=="LONGITUD_MAX"){
					var long = Number(validacionDinamica[1]);
					if(angular.isObject(this.solicitud[item.objeto]) && this.solicitud[item.objeto].length > long){
						mensajeTemp = "El campo '"+item.mensaje+"' debe tener longitud máxima de "+long;
					}
				}
				if(validacionDinamica.length==2 && validacionDinamica[0]=="DECIMAL"){
					var deci = Number(validacionDinamica[1]);
					if(this.solicitud[item.objeto] && (this.solicitud[item.objeto].length - (this.solicitud[item.objeto].indexOf(".")+1)) != deci){
						mensajeTemp = "El campo '"+item.mensaje+"' debe tener "+deci+" decimales";
					}
				}
				
				if(mensajeTemp.length>0){
					if(this.mensaje.length==0){
						this.mensaje = mensajeTemp;
					}
					item.contadorError++;
					if(item.contadorError == 1){
						this.modulos[item.modulo]++;
					}
				}			
			};
			
			this.cambiar = function(objeto, nuevaValidacion){
				var eid = this.getcampoxId(objeto);
				if(eid){
					this.campos[eid].validacion = nuevaValidacion;
				}                                                               
			};
			
			this.habilitar = function(objeto){
				var eid = this.getcampoxId(objeto);
				if(eid){
					this.campos[eid].estado = 1;
				}
			};
			
			this.deshabilitar = function(objeto){
				var eid = this.getcampoxId(objeto);
				if(eid){
					this.campos[eid].estado = 0;
				}
			};
			
			this.quitar = function(objeto){
				var eid = this.getcampoxId(objeto);
				if (eid) {
					this.campos.splice(eid, 1);
				}
			};
			
		};
		
		return {
			instanciar : function(nombreObjPadre, solicitud, mensajeRespuesta, Lista){
				return new validacion(nombreObjPadre, solicitud, mensajeRespuesta, Lista);
			}
		};
	});
	
	core.factory("tarea", function() {
		
		var f_adjunto = function (){
			this.tarea;
			this.documentos = null;
			this.historialDocumento = [];
			this.historialDocumentoId = {};
			
			this.habilitarDocumento = function(tipo,codigoTipoDocumento){
				var eid = this.historialDocumentoId[codigoTipoDocumento];
				if(eid){
					var doc = this.historialDocumento[eid];
					if(doc){
						doc.esVisible = tipo;
					} else {
						doc.esVisible = tipo;
					}
				}
			};
			
			this.instanciar = function(config){
				this.tarea = config.tarea;
				
				var documentosId = {};
				if(config.documentos){
					for(var i in config.documentos){
						documentosId[config.documentos[i].codigoTipoDocumento] = config.documentos[i];
					}
				}
				
				for(var i in config.historialDocumento){
					this.historialDocumentoId[config.historialDocumento[i].codigoTipoDocumento] = i;
					var itemDoc = documentosId[config.historialDocumento[i].codigoTipoDocumento];
					if(itemDoc){
						config.historialDocumento[i]["isObligatorio"] = itemDoc.isObligatorio;
						config.historialDocumento[i]["formatos"] = itemDoc.formatos;
						config.historialDocumento[i]["isSoloLectura"] = false;
					} else {
						config.historialDocumento[i]["isSoloLectura"] = true;
					}
					config.historialDocumento[i]["esVisible"] = true;
					config.historialDocumento[i]["index"] = i;
				}
				
				if(config.documentos){
					for(var i in config.documentos){
						if(!this.historialDocumentoId[config.documentos[i].codigoTipoDocumento]){
							var ind = config.historialDocumento.length;
							config.historialDocumento.push({
								codigoTipoDocumento : config.documentos[i].codigoTipoDocumento,
								nombreTipoDocumento : config.documentos[i].nombreDocumento,
								isObligatorio 		: config.documentos[i].isObligatorio,
								formatos 			: config.documentos[i].formatos,
								esVisible			: true,
								index				: ind
							});
							this.historialDocumentoId[config.documentos[i].codigoTipoDocumento] = ind;
						};
					}
					this.documentos = config.documentos;
				}
				this.historialDocumento = config.historialDocumento;
			};
			
			this.descargarArchivo = function(index){
				var historialAdj = this.historialDocumento[index];
				window.open(this.tarea._data.urlTarea+"?generarDescarga=true&accion=obtenerNombreRutaDocumento&codigoTarea="+this.tarea.codigoTarea+"&nombreDocumento="+historialAdj.nombreDocumento);
			};
			
			this.eliminarArchivo = function(index){
				var historialAdj = this.historialDocumento[index];
				ajax.getAjax({
					url: 	this.tarea._data.urlTarea,
					data: angular.extend({
						accion 			: "eliminarDocumentoAdjunto",
						tkiid			: "",
						piid			: this.tarea.proceso.piid,
						codigoTarea		: this.tarea.codigoTarea, 
						codigoDocumento	: historialAdj.codigoDocumento, 
						nombreDocumento	: historialAdj.nombreDocumento
					}),
					getRespuesta : function(respuesta){
						if(respuesta.conforme || respuesta){
							$scope.$parent.$parent.alertaPortal.mostrar("Documento eliminado satisfactoriamente");
	    					delete this.historialDocumento[index].nombreDocumento;
	    					delete this.historialDocumento[index].codigoDocumento;
	    					this.historialDocumento[index].comentario = "";
						}
					},
					mostrarCargador : true
				});
			};
			
			this.cargarArchivoNuevo = function(index){
				var historialAdj = this.historialDocumento[index];
				var files = historialAdj.adjunto;
		        if (files && files.length) {
		            for (var i = 0; i < files.length; i++) {
		                var file = files[i];
		    			ajax.getAjaxArchivo({
		    				url 			: this.tarea._data.urlTarea,
		    				archivo			: file,
		    				data 			: {
								accion 				: "adjuntarDocumentos",
								codigoTarea 		: this.tarea.codigoTarea,
								piid				: this.tarea.proceso.piid,
								descripcionTarea	: this.tarea._data.tarea.nombre,
								codigoTipoDocumento : historialAdj.codigoTipoDocumento,
								comentario			: historialAdj.comentario,
								adicional			: false,
								soloLectura			: false,
								nombreObjeto		: "file",
								nombreOriginal		: file.name,
								rnd					: Math.random()
		    				},
		    				getRespuesta 	: function(respuesta){
		    					$scope.$parent.$parent.alertaPortal.mostrar("Documento Adjuntado satisfactoriamente");
		    					this.historialDocumento[index].nombreDocumento = respuesta.nombreDocumento;
		    					this.historialDocumento[index].codigoDocumento = respuesta.codigoDocumento;
			    			}
		    			});
		            }
		        } else {
		        	$scope.$parent.$parent.alertaPortal.mostrarAlerta("Se debe adjuntar un archivo de los formatos: "+historialAdj.formatos);
		        }
			};
			
			this.mostrarCargarNuevo = function(){
				this.lista.push({
					tipo : "NUEVO",
					esObligatorio : false,
					esSoloVisible : true,
					esCargado : false,
					descripcionTipoDocumento : "Documento Adicional"
				});
			};
			
			this.quitarNuevo = function(index){
				this.lista.splice(index, 1);
			};
			
			this.validar = function(tabs){
				var alertas = 0;
				for(var i in this.historialDocumento){
					var doc = this.historialDocumento[i];
					if(typeof(doc.codigoDocumento)=="undefined" && doc.esVisible){
						alertas++;
					}
				}
				if(alertas>0){
					tabs.alertas = alertas;
					tabs.activo = true;
					return false;
				} else {
					return true;
				}
			};
		};
		
		return {
			adjunto : function(){
				return new f_adjunto();
			}
		};
	});
	
var getMapperObjId = function(objeto,id){
	var mapeo = {};
	for(var i in objeto){
		mapeo[objeto[i][id]] = objeto[i];
	}
	return mapeo;
};

var getMapperValor = function(variable,objeto,castearFechas,castearBooleanos){
	if(typeof(castearFechas)=="undefined"){
		castearFechas = false;
	}
	if(typeof(castearBooleanos)=="undefined"){
		castearBooleanos = true;
	}
	var mapeo = {};
	if(typeof(variable)=="object"){
		objeto = variable;
		variable = "";
	}
	if(variable.length!=0){
		variable = variable+".";
	}
	for(var i in objeto){
		if(typeof(objeto[i])=="object" && !isValidDate(objeto[i])){
			mapeo = angular.extend({},mapeo,getMapperValor(variable+i,objeto[i],castearFechas));
		} else {
			
			if(castearFechas && (isValidDate(objeto[i]) || esStringDate(objeto[i]))){
				if(!isValidDate(objeto[i])){
					objeto[i] = new Date(objeto[i]);
				}
				var fecha = objeto[i];
				var dia = (fecha.getDate()<10)?"0"+fecha.getDate():fecha.getDate();
				var mes = (fecha.getMonth()<9)?"0"+(fecha.getMonth()+1):(fecha.getMonth()+1);
				var ano = fecha.getFullYear();
				var valor = dia+"/"+mes+"/"+ano;
			} else if (typeof(objeto[i]) == "boolean") {
				if(castearBooleanos){
					if(objeto[i]){
						var valor = 1;
					} else {
						var valor = 0;
					}
				} else {
					var valor = objeto[i];
				}
			} else {
				var valor = objeto[i];
			}
			mapeo[variable+i] = valor;
			
		}
	}
	return mapeo;
};