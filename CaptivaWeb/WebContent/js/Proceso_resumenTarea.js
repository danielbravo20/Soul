	
var getColumnaAncho = {
		"2" : "350px",
		"3" : "231px"
};

Mapeo.proceso.resumenTarea = {
	listaAtributos : {},
	agregarAtributo : function(id,nombre,descripcion){

		this.listaAtributos[id] = id;
		var long = $("input[name='columns']:checked").val();
		var campo = '';
			campo +='<li class="ui-state-highlight ui-sortable-handle" style="width: '+getColumnaAncho[long]+'">';
			campo +='	<div class="contenido">';
			campo +='		<div class="borrar">x</div>';
			campo +='		<div class="valor"><b>'+nombre+'</b><br><span style="font-style: italic;">('+descripcion+')</span></div>';
			campo +='	</div>';
			campo +='</li>';
		$("#ul_proc_resTar_listaCampo").append(campo);
		
		$("#ul_proc_resTar_listaCampo li").last().find(".borrar").click(function(){
			$(this).parent().parent().remove();
		});
	},
	eventoEntidad : function(_this){
	
		//$(".proc_cls_atributo").remove();
		
		var eEstado = _this.attr("eEstado");
		if(eEstado=="0"){
			_this.attr("eEstado","1");
			_this.find(".ui-icon").removeClass("ui-icon-plus").addClass("ui-icon-minus");
			
			Mapeo.atributo.getListado(_this.attr("eVal"),function(listaAtributos){
				
				var nivel = Number(_this.attr("eNivel"));
				
			    var listaAtributo = "";
			    for(var i in listaAtributos){
			    	var item = listaAtributos[i];
			    	
			    	if(item.esTipoDatoEntidad){
			    		console.log("tst");
			    		listaAtributo += '<li class="ui-widget-content cls_proc_entidad" style="cursor: pointer; background: #FFF8DC;" eNivel="'+(nivel+1)+'" eEstado="0" eVal="'+item.codigoClase+'" eEntidadPadre="'+_this.attr("eVal")+'">';
			    		listaAtributo += '  <div class="listaAtributosDesabilitadoA" style="padding-left: '+((nivel+1)*16)+'px;"><span class="ui-icon ui-icon-plus"></span></div>';
			    		listaAtributo += '  <div class="listaAtributosDesabilitadoB">'+item.codigoAtributo+'</div>';
			    		listaAtributo += '  <div class="listaAtributosDesabilitadoC"><a title="Quitar" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"><span class="ui-button-text"><span class="ui-icon ui-icon-minusthick"></span></span></a></div>';
			    		listaAtributo += '</li>';
			    	} else {
			    		if(Mapeo.proceso.resumenTarea.listaAtributos[item.codigoAtributo]){
					    	listaAtributo += '<li class="ui-widget-content cls_proc_atributoDisable" eEntidad="'+_this.attr("eVal")+'">';
			    	    	listaAtributo += '  <div style="padding-left: '+((nivel+1)*16)+'px; width: 16px; display: inline-block;"><span class="ui-icon ui-icon-carat-1-e"></span></div>';
			    	    	listaAtributo += '  <div style=" display: inline-block; color:#B2B2B2; ">'+item.java_atributo+'</div>';
			    	    	listaAtributo += '</li>';
			    		} else {
					    	listaAtributo += '<li class="ui-widget-content cls_proc_atributo" style="cursor: pointer;" eEstado="0" eVal="'+item.codigoAtributo+'" eAtributo="'+item.java_atributo+'" eEntidad="'+_this.attr("eVal")+'" eNivel="'+nivel+'">';
			    	    	listaAtributo += '  <div style="padding-left: '+((nivel+1)*16)+'px; width: 16px; display: inline-block;"><span class="ui-icon ui-icon-triangle-1-e"></span></div>';
			    	    	listaAtributo += '  <div style=" display: inline-block; ">'+item.java_atributo+'</div>';
			    	    	listaAtributo += '</li>';
			    		}
			    		
			    	}
		
			    }
			    _this.after(listaAtributo);
			    
			    _this.nextAll(".cls_proc_atributo[eEntidad='"+_this.attr("eVal")+"']").click(function(){
			    	
			    	var nivel = Number($(this).attr("eNivel"));
			    	
			    	Mapeo.proceso.resumenTarea.agregarAtributo($(this).attr("eVal"),$(this).attr("eAtributo"),$(this).attr("eEntidad"));
			    	
			    	var listaAtributo  = '<li class="ui-widget-content cls_proc_atributoDisable" eEntidad="'+_this.attr("eVal")+'">';
		    	    	listaAtributo += '  <div class="listaAtributosDesabilitadoA" style="padding-left: '+((nivel+1)*16)+'px;"><span class="ui-icon ui-icon-carat-1-e"></span></div>';
		    	    	listaAtributo += '  <div class="listaAtributosDesabilitadoB">'+$(this).attr("eAtributo")+'</div>';
		    	    	listaAtributo += '  <div class="listaAtributosDesabilitadoC"><a title="Quitar" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"><span class="ui-button-text"><span class="ui-icon ui-icon-minusthick"></span></span></a></div>';
		    	    	listaAtributo += '</li>';
		    	    	
		    	    $(this).after(listaAtributo).remove();
		    	    
		    	    
			    	
			    });
			    
			    _this.nextAll(".cls_proc_entidad[eEntidadPadre='"+_this.attr("eVal")+"']").click(function(){
			    	Mapeo.proceso.resumenTarea.eventoEntidad($(this));
			    });
			    
			});
		}
		
		if(eEstado=="1"){
			_this.attr("eEstado","0");
			_this.find(".ui-icon").removeClass("ui-icon-minus").addClass("ui-icon-plus");
			_this.nextAll(".cls_proc_atributoDisable[eEntidad='"+_this.attr("eVal")+"']").remove();
			_this.nextAll(".cls_proc_atributo[eEntidad='"+_this.attr("eVal")+"']").remove();
			_this.nextAll(".cls_proc_entidad[eEntidadPadre='"+_this.attr("eVal")+"']").remove();
		}
		
	}
};

$(document).ready(function(){
	
    $("#ul_proc_resTar_listaCampo").sortable({
      dropOnEmpty: false
    }).disableSelection();
    
    $("input[name='columns']").click(function(){
    	$("#ul_proc_resTar_listaCampo li").css("width",getColumnaAncho[$(this).val()]);
    });
    
    $("#btnAgf").click(function(){
    	
    	var total = $("#ul_proc_resTar_listaCampo li").size();
    	
    	var long = $("input[name='columns']:checked").val();
    	
    	var campo = '';
    		campo +='<li class="ui-state-highlight ui-sortable-handle" style="width: '+getColumnaAncho[long]+'">';
    		campo +='	<div class="contenido">';
    		campo +='		<div class="borrar">x</div>';
    		campo +='		<div class="valor"><b>Código de Solicitud '+(total+1)+'</b><br><span style="font-style: italic;">(Solicitud)</span></div>';
    		campo +='	</div>';
    		campo +='</li>';
    	$("#ul_proc_resTar_listaCampo").append(campo);
    	
    	$("#ul_proc_resTar_listaCampo li").last().find(".borrar").click(function(){
    		$(this).parent().parent().remove();
    	});
    	
    });
    
    var lista = "";
    for(var i in Mapeo.clase.data.listado){
    	var item = Mapeo.clase.data.listado[i];
    	lista += '<li class="ui-widget-content cls_proc_entidad" style="cursor: pointer; background: #FFF8DC;" eNivel="0" eEstado="0" eVal="'+item.codigoClase+'">';
    	lista += '  <div style=" width: 16px; display: inline-block;"><span class="ui-icon ui-icon-plus"></span></div>';
    	lista += '  <div style=" display: inline-block; ">'+item.java_entidad+'</div>';
    	lista += '</li>';
    }
    $("#ol_proc_resTar_listaObjeto").append(lista);
    
    $("#ol_proc_resTar_listaObjeto .cls_proc_entidad").click(function(){
    	Mapeo.proceso.resumenTarea.eventoEntidad($(this));
    });
    
	$("#div_proc_resTar_Acordeon").accordion({
		collapsible: false,
		heightStyle: "content",
		active: false,
		icons : {"header":"ui-icon-clipboard","activeHeader":"ui-icon-clipboard"}
	});
    
	$("#a_proc_resTar_btnGrabar").button().click(function(e){
		e.preventDefault();
		Mapeo.mantenimiento.editar();
	});
	
	$("#a_proc_resTar_btnVolver").button().click(function(e){
		e.preventDefault();
		$("#div_mant_editar").remove();
		$("#div_mapeo_principal").show();
	});
    
});