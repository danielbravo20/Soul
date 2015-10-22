var coreVar = {
	urls : {
		hostname : "/Mapeo_v4Web/"
	}
};

var Core = {};

	Core.aviso = function(Mensaje,opciones) {
		
		this.getMensajeFormato = function(Mensaje,tieneBotonCerrar,esCargable,colorTexto,callback){
			
			msje_carg = '';
			if(esCargable){
				msje_carg = '<img src="'+coreVar.urls.hostname+'js/librerias/core/img/CARGADOR.gif" width="20" height="20" />';
			}
			
			msje_cerr = '';
			if(tieneBotonCerrar){
				msje_cerr = '<a href="#" class="AVISO_COLUMNA_3_CERRAR"><div></div></a>';
			}
						
			msje  = '<div style="display:block; text-align:center">';
			msje += '<div class="AVISO_COLUMNA_1"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="40" height="43" align="center" valign="middle">'+msje_carg+'</td></tr></table></div>';
			msje += '<div class="AVISO_COLUMNA_2"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="42" align="center" valign="middle" class="AVISO_COLUMNA_2_'+colorTexto+'">'+Mensaje+'</td></tr></table></div>';
			msje += '<div class="AVISO_COLUMNA_3"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="40" height="43" align="center" valign="middle">'+msje_cerr+'</td></tr></table></div>';
			msje += '</div>';
			
			$("#core_aviso").fadeOut(function(){
				$(this).empty().append(msje).fadeIn();
				callback();
				if(tieneBotonCerrar){
					$(this).find("a.AVISO_COLUMNA_3_CERRAR").click(function(e){
						e.preventDefault();	
						$("#core_aviso").fadeOut(function(){
							$(this).empty();
						});
					});
				};
			});
			
		};
		
		if($("#core_aviso").size()==0){
			$("body").prepend('<div id="core_aviso" class="AVISO_CONTENIDO"></div>');
		}
		
		if(typeof(Mensaje)=="object"){
			var opciones = Mensaje;
			var Mensaje = "";
		}
		
		var conf_tieneBotonCerrar = false;
		var conf_mostrarCargador = false;
		var conf_color = "VERDE";
		var conf_callback = function(){};
		
		if(!opciones){
			opciones = {tipo : "mensaje"};
		}
		
		if(Mensaje.length>0){
			if(opciones.tipo=="cargador"){
				conf_mostrarCargador = true;	conf_color = "AMARILLO";
				Mensaje += ", Por favor espere ...";
			} else if(opciones.tipo=="alerta"){
				conf_tieneBotonCerrar = true;	conf_color = "AMARILLO";
			} else if(opciones.tipo=="error"){
				conf_tieneBotonCerrar = true;	conf_color = "ROJO";
				setTimeout(function(){$("#core_aviso").fadeOut(function(){$("#core_aviso").empty();});},2000);
			} else if(opciones.tipo=="errorExcepcion" && opciones.detalle){
				conf_tieneBotonCerrar = true;	conf_color = "ROJO";
				Mensaje += ", para ver más detalle <a href='#' class='AVISO_COLUMNA_4_CERRAR'>Click aquí</a>";
				conf_callback = function(){

					if(opciones.detalle.length!=0){
						$("#core_aviso").find("a.AVISO_COLUMNA_4_CERRAR").click(function(e){
							
							e.preventDefault();	
							$("#core_aviso").fadeOut(function(){
								$(this).empty();
								
								var msje  = '';
									msje += '<div style="display:block; text-align:center"><table width="100%"><tr><td align="center">';
									msje += '<div class="AVISO_ERROR_DETALLE_CONTENIDO">';
									msje += '	<div class="AVISO_ERROR_DETALLE_BTN_CERRAR"><a href="#" class="AVISO_COLUMNA_3_CERRAR"><div></div></a></div>';
									msje += '	<div class="AVISO_ERROR_DETALLE_DETALLE" contenteditable="true">'+opciones.detalle+'</div>';
									msje += '</div>';
									msje += '</td></tr></table></div>';
															
									$("#core_aviso").append(msje).show();
								
								$("#core_aviso").find("a.AVISO_COLUMNA_3_CERRAR").click(function(e){
									e.preventDefault();	
									$("#core_aviso").fadeOut(function(){
										$(this).empty();
									});
								});
								
							});
						});
					
					}
					
				};
			} else { // Mensaje normal
				conf_color = "VERDE";
				conf_tieneBotonCerrar = true;
				setTimeout(function(){$("#core_aviso").fadeOut(function(){$("#core_aviso").empty();});},3500);
			}
			
			this.getMensajeFormato(Mensaje,conf_tieneBotonCerrar,conf_mostrarCargador,conf_color,conf_callback);
		}
		
		if(opciones.autoOcultar){
			var mensajeTiempo = 5000; // Defecto 5 segundos
			if(opciones.segundos){
				mensajeTiempo = Number(opciones.segundos)*1000;
			}
			setTimeout(function(){$("#core_aviso").fadeOut(function(){$("#core_aviso").empty();});},mensajeTiempo);
		};
				
		if(opciones.cerrar){
			$("#core_aviso").fadeOut(function(){
				$("#core_aviso").hide();
			});
		};
		
	};

	Core.cargador = function(accion,mensajeCall,call){ // opciones : true, false, "siEstaVisible", "siEstaOculto"
		
		if($("#core_cargadorFondo").size()==0){
			cargador  = '<div id="core_cargadorFondo" class="cls_core_cargadorFondo"></div>';
			cargador += '<div id="core_cargadorMensaje" class="cls_core_cargadorMensaje"></div>';
			$("body").append(cargador);
		}
		
		var tipoAccion = typeof(accion);
		var mensaje = "";
		var callback = function(){};
		if(typeof(mensajeCall)=="string"){
			mensaje = mensajeCall;
		} else if(typeof(mensajeCall)=="function"){
			callback = mensajeCall;
		}
		if(typeof(call)=="function"){
			callback = call;
		}
		
		if(tipoAccion=="string"){
			if(accion=="siEstaVisible"){
				if($("#core_cargadorFondo").is(":visible")){
					return true;
				} else {
					return false;
				}
			}
			if(accion=="siEstaOculto"){
				if(!$("#core_cargadorFondo").is(":visible")){
					return true;
				} else {
					return false;
				}
			}
		}
		
		if(tipoAccion=="boolean"){
			
			if(accion){

				if(Core.cargador("siEstaOculto")){

					if(mensaje==""){
						msje_nuevo = "Cargando...";	
					} else {
						msje_nuevo = opciones.mensaje;
					}
					
					var msje  = '	<div class="cls_core_cargadorMensaje_CONTENEDOR" id="msg">';
					    msje += '		<span class="cls_core_cargadorMensaje_IMG"></span>';
					    msje += '		<span class="cls_core_cargadorMensaje_TEXTO">'+msje_nuevo+'</span>';
					   msje += '	</div>';
						
					$("#core_cargadorFondo").fadeIn("fast");
					$("#core_cargadorMensaje").append(msje).fadeIn("fast",function(){
						callback();
					});
					$("#core_cargadorMensaje").css("top",Math.ceil(($(window).height()-241)/2)+"px");
					$("#core_cargadorMensaje").css("left",Math.ceil(($(window).width()-422)/2)+"px");
					
				} else {
					callback();
				}
				
			} else {
				
				if(Core.cargador("siEstaVisible")){
					$("#core_cargadorFondo").fadeOut("fast");
					$("#core_cargadorMensaje").fadeOut("fast",function(){
						$("#core_cargadorMensaje .cls_core_cargadorMensaje_CONTENEDOR").remove();
						callback();
					});	
					
				} else {
					callback();
				}
				
			}
			
		}
		
	};
	
	Core.getJSON = function(opc){
	
		var opcDefecto = {
			url : null,
			data : null,
			getRespuesta : function(){},
			excepcionID : "",
			mostrarCargadorInicial : null, 	// Si solo se muestra el cargador de inicio
			mostrarCargadorFinal : null, 	// Si solo se oculta el cargador al final
			mostrarCargador : null 			// Se muestra y se oculta el cargador
		};
	
		var opciones = $.extend({},opcDefecto,opc);
		
		if(opciones.url != null){
			
			console.log("ID LLAMADA : "+opciones.excepcionID+" - URL : "+opciones.url);
			
			var jsonResultado = function(objRespuesta){
				var mensaje = "";
				if(typeof(objRespuesta)!="undefined"){
					if(objRespuesta.resultado){
						var respuesta = objRespuesta.objeto;
						if(typeof(respuesta)!="undefined"){
							if(typeof(opciones.getRespuesta)!="undefined"){
								opciones.getRespuesta(respuesta);
							}
						} else {
							mensaje = "No se ha cargado ninguna respuesta";
						}
					} else {
						if(typeof(objRespuesta.mensaje)!="undefined"){
							mensaje = objRespuesta.mensaje;
						} else {
							mensaje = "Ha ocurrido un error";
						}
					}
				} else {
					mensaje = "Respuesta Incorrecta";
				}
				if(mensaje.length>0){
					Core.aviso("[MAPEO]["+opciones.excepcionID+"]",{tipo:"errorExcepcion",detalle:mensaje});
					Core.cargador(false);
				}
			};
			var jsonCarga = function(){
				return $.ajax({
					dataType	: "json",
					type		: "GET",
				  	url			: opciones.url,
				  	data		: opciones.data,
				  	error 		: function( jqXHR, textStatus, errorThrown ){
						//location.href = coreVar.urls.hostname+"index.jsp";
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
				Core.cargador(true,function(){
					jsonCarga().done(function(data){
						if(blnMostrarFinal){
							Core.cargador(false,function(){
								jsonResultado(data);
							});
						} else {
							jsonResultado(data);
						}
					});
				});
			} else {
				return jsonCarga().done(function(data){
					if(blnMostrarFinal){
						Core.cargador(false,function(){
							jsonResultado(data);
						});
					} else {
						jsonResultado(data);
					}
				});
			}
			
		} else {
			console.log("[Core.getJSON][NoData]");
		}
		
	};
	
	Core.cargarCombo = function(campo,objetoCombo,idSeleccionado){
		var jCombo = null;
		if(typeof(campo)=="string"){
			jCombo = $("#"+campo);
		} else {
			jCombo = campo;
		}
		if(jCombo!=null){
			if(jCombo.size()>0 && jCombo.prop('tagName')=="SELECT"){
				jCombo.empty();
				var opciones = '<option value="">SELECCIONE</option>';
				for(var idCampo in objetoCombo){
					opciones += '<option value="'+idCampo+'">'+objetoCombo[idCampo]+'</option>';
				}
				jCombo.append(opciones);
				if(typeof(idSeleccionado)!="undefined"){
					jCombo.val(idSeleccionado);
				} else {
					jCombo.val("");
				}
			}
		}
	};
	
	Core.getQueryString = function(){
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
	
	Core.tablaDinamica = function(opciones){
		
		this.construirTabla = function(){
			
			var _this = this;
			var miTabla = $("#"+this.opciones.idTabla);
			var acciones = this.opciones.agregarAcciones;
			
			if(typeof(acciones)!="undefined"){
				if(acciones["Agregar"]){
					miTabla.after('<a href="#" id="'+this.opciones.idTabla+'_btnAgregar"><span class="ui-icon ui-icon-plusthick"></span></a>');
					$("#"+this.opciones.idTabla+"_btnAgregar").button().click(function(e){
						e.preventDefault();
						var indice = $("#"+_this.opciones.idTabla).find(".cls_AtributoFila").size();
						var filaHTML = _this.opciones.formatoFilas(indice);
						_this.agregarFila(indice, filaHTML);
					});
				}
				if(acciones["Eliminar"] || acciones["Subir"] || acciones["Bajar"] || acciones["Duplicar"]){
					miTabla.find(".ui-widget-header").append("<td>&nbsp;</td>");
				}
			}
			
			if(typeof(this.opciones.agregarInformacion)!="undefined"){
				this.agregarInformacion(this.opciones.agregarInformacion());
			}
			
		};
		
		this.agregarInformacion = function(listadoArray,callback){
			var indice = $("#"+this.opciones.idTabla).find(".cls_AtributoFila").size();
			if(listadoArray){
				for(var i = 0; i < listadoArray.length;i++){
					this.agregarFila(indice,this.opciones.formatoFilas(indice),listadoArray[i]);
					indice++;
				}
			}
			if(typeof(callback)!="undefined"){
				callback();
			}
		};
		
		this.agregarFila = function(indice,filaHTML,objetoNuevo){
			
			var jMiTabla = $("#"+this.opciones.idTabla);
				jMiTabla.append(filaHTML);
			
			var jFilaNueva = jMiTabla.find("tr:last-child");
				jFilaNueva.attr("eid",indice);
				jFilaNueva.addClass("cls_AtributoFila");
				this.nuevaFilaAgregarOpciones(jFilaNueva);
			
			if(typeof(this.opciones.luegoNuevaFila)!="undefined"){
				if(typeof(objetoNuevo)!="undefined"){
					this.opciones.luegoNuevaFila(jFilaNueva,indice,":esCargado",objetoNuevo);
				} else {
					this.opciones.luegoNuevaFila(jFilaNueva,indice,":esNuevo",null);
				}
			};
			
			this.inicializarEvento(indice);
			
		};
		
		this.nuevaFilaAgregarOpciones = function(filaNueva){
			if(typeof(this.opciones.agregarAcciones)!="undefined"){
				var Opciones = '   <td>';
					if(this.opciones.agregarAcciones["Eliminar"]){
						Opciones+= '		<a class="cls_TablaFila_opciones" title="Eliminar"><span class="ui-icon ui-icon-minusthick"></span></a>';
					}
					if(this.opciones.agregarAcciones["Subir"]){
						Opciones+= '		<a class="cls_TablaFila_opciones" title="Subir"><span class="ui-icon ui-icon-arrowthick-1-n"></span></a>';
					}
					if(this.opciones.agregarAcciones["Bajar"]){
						Opciones+= '       <a class="cls_TablaFila_opciones" title="Bajar"><span class="ui-icon ui-icon-arrowthick-1-s"></span></a>';
					}
					if(this.opciones.agregarAcciones["Duplicar"]){
						Opciones+= '       <a class="cls_TablaFila_opciones" title="Duplicar"><span class="ui-icon ui-icon-copy"></span></a>';
					}
					Opciones+= '	</td>';
				filaNueva.append(Opciones);
			}
		};
		
		this.inicializarEvento = function(indice){
			
			var _this = this;
			
			$(".cls_AtributoFila[eid='"+indice+"']").find(".cls_TablaFila_opciones").button().click(function(e){
				e.preventDefault();
				
				var tipo = $(this).attr("title");
				var id = Number($(this).parent().parent().attr("eid"));
				
				if(tipo=="Eliminar"){
					$(".cls_AtributoFila[eid='"+id+"']").remove();
					$(".cls_AtributoFila").each(function(){
						if($(this).attr("eid")>id){
							$(this).attr("eid",Number($(this).attr("eid"))-1);
						};
					});
				};
				
				if(tipo=="Subir"){
					if(id>0){
						var objetoActual = $(".cls_AtributoFila[eid='"+id+"']");
						var objetoSiguiente = $(".cls_AtributoFila[eid='"+(id-1)+"']");
							objetoSiguiente.attr("eid",id);
						objetoActual.after(objetoSiguiente);
						objetoActual.attr("eid",(id-1));
					}
				};
				
				if(tipo=="Bajar"){
					if(id<$("#table_mod_mant_Atributos .cls_AtributoFila").size()-1){
						var objetoActual = $(".cls_AtributoFila[eid='"+id+"']");
						var objetoAnterior = $(".cls_AtributoFila[eid='"+(id+1)+"']");
							objetoAnterior.attr("eid",id);
						objetoActual.before(objetoAnterior);
						objetoActual.attr("eid",(id+1));
					}
				};
				
				if(tipo=="Duplicar"){
					var jObjetoActual = $(".cls_AtributoFila[eid='"+id+"']");
					var jObjetoClonado = $(".cls_AtributoFila[eid='"+id+"']").clone();
					$(".cls_AtributoFila").each(function(){
						if($(this).attr("eid")>id){
							$(this).attr("eid",Number($(this).attr("eid"))+1);
						};
					});
					jObjetoActual.after(jObjetoClonado);
					jObjetoClonado.attr("eid",(id+1));
					jObjetoClonado.find("td:last-child").remove();
					_this.nuevaFilaAgregarOpciones(jObjetoClonado);
					_this.opciones.luegoNuevaFila(jObjetoClonado,(id+1),":esClonado",jObjetoActual);
					_this.inicializarEvento((id+1));
				};
				
			});
			
		};
		
		if(typeof(opciones)!=undefined){
			this.opciones = $.extend({},{idTabla : null},opciones);
			if(opciones.idTabla != null){
				this.construirTabla();
			}
		}
		
	};
	
	Core.serializar = function(jObjeto){
	    var objeto = {};
	    jObjeto.find("input,textarea,select").each(function(){
	        var element = $(this);
	        if(element.is("input")){
	            if((element.attr("type")=="text" || element.attr("type")=="password") && element.is(":visible") && element.val()!=""){
	                objeto[element.attr("name")] = element.val();
	            }
	            if(element.attr("type")=="hidden" && element.val()!=""){
	                objeto[element.attr("name")] = element.val();
	            }
	            if(element.attr("type")=="radio" && element.is(":checked") && element.is(":visible")){
	                objeto[element.attr("name")] = element.val();
	            }
	            if(element.attr("type")=="checkbox" && element.is(":checked") && element.is(":visible")){
	                objeto[element.attr("name")] = element.val();
	            }
	        }
	        if(element.is("select") && element.is(":visible") && element.val()!=""){
	            objeto[element.attr("name")] = element.val();
	        }
	        if(element.is("textarea") && element.is(":visible") && element.val()!=""){
	            objeto[element.attr("name")] = element.val();
	        }
	    });
	    return objeto;
	};
	
	Core.jpo = {
		urlGestor : "/Mapeo_v4Web/gestorController",
		enviar : function(idFormulario,metodo,callback){
		
			if(typeof(metodo)=="string"){
				$("#"+idFormulario).find("input[name='metodo']").val(metodo);
			}
			if(typeof(metodo)=="function"){
				callback = metodo;
			}
		
			return Core.getJSON({
				excepcionID 	: "MAPEO_"+idFormulario,
				url 			: this.urlGestor,
				data 			: $("#"+idFormulario).serialize(),
				getRespuesta 	: function(respuesta){
					if(typeof(respuesta)=="undefined"){
						Core.aviso("Ha ocurrido un problema",{tipo: "error"});
					} else {
						if(typeof(callback)!="undefined"){
							callback(respuesta);
						}
					}
				}
			});
		}
	};
	