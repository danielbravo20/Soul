var ui_DatePicker = function(ID_DESDE,ID_HASTA,opciones){
	
	var Opcdefecto = {
		f_desde_onClose : function(){},
		autoCambiarDesdeHasta : true, // Si luego de seleccionar el Desde se posicione x defecto en el hasta
		f_hasta_onClose : function(){}
	};
	
		Opcdefecto = $.extend({},Opcdefecto,opciones);
	
	var jDesde = null;
	if(typeof(ID_DESDE)=="string"){
		jDesde = $(ID_DESDE);
	} else {
		jDesde = ID_DESDE;
	}
	
	if(typeof(ID_HASTA)!="undefined"){
		
		var jHasta = null;
		if(typeof(ID_HASTA)=="string"){
			jHasta = $(ID_HASTA);
		} else {
			jHasta = ID_HASTA;
		}

		jDesde.datepicker({
		  defaultDate: "+1w",
		  changeMonth: true,
		  numberOfMonths: 3,
		  onClose: function( selectedDate ) {
			jHasta.datepicker( "option", "minDate", selectedDate );
			if(Opcdefecto.autoCambiarDesdeHasta){
				jHasta.focus();
			}
			Opcdefecto.f_desde_onClose();
		  }
		});
		jHasta.datepicker({
		  defaultDate: "+1w",
		  changeMonth: true,
		  numberOfMonths: 3,
		  onClose: function( selectedDate ) {
			jDesde.datepicker( "option", "maxDate", selectedDate );
			Opcdefecto.f_hasta_onClose();
		  }
		});
		
	} else {
		jDesde.datepicker({
		  defaultDate: "+1w",
		  changeMonth: true
		});
	}

};

$.fn.error = function(opciones){
	return this.each(function() {
		if(typeof(opciones)=="string"){
			if($(this).next('label').size()==0){
				$(this).after("<label class=\"error\"></label>");
			};
			$(this).next('label').empty().append(opciones);
		}
		if(typeof(opciones)=="boolean"){
			$(this).next('label').remove();
		}
    });
};

$.fn.cargarModulo = function(){
	return this.each(function() {
		$(this).button().click(function(e){
			e.preventDefault();
			$("#div_mapeo_principal").after("<div id='"+$(this).attr("ediv")+"'></div>");
			$("#"+$(this).attr("ediv")).load($(this).attr("href")+"?rnd="+Math.random());
		});
		if(typeof($(this).attr("eicon"))!="undefined"){
			$(this).button("option","icons",{primary:"ui-icon-"+$(this).attr("eicon")});
		}
    });
};

$.widget( "custom.catcomplete", $.ui.autocomplete, {
    _create: function() {
      this._super();
      this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
    },
    _renderMenu: function( ul, items ) {
      var that = this,
        currentCategory = "";
      $.each( items, function( index, item ) {
        var li;
        if ( item.category != currentCategory ) {
          ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
          currentCategory = item.category;
        }
        li = that._renderItemData( ul, item );
        if ( item.category ) {
          li.attr( "aria-label", item.category + " : " + item.label );
        }
      });
    }
  });

$.extend($.ui.dialog.prototype.options, {
	autoOpen	: false,
	draggable	: false,
	modal		: true
});

$.datepicker.setDefaults(
	$.extend({
		'dateFormat':'yy-mm-dd'
	})
);

var generarAutoComplete = function(){
	var autoCompletar = new Array();
	var e = 0;
	for(var i in Mapeo.data.tipoDatos){
		autoCompletar[e] = { value: i, label: Mapeo.data.tipoDatos[i], category: "Java" };
		e++;
	}
	for(var i in Mapeo.clase.data.lista){
		var item = Mapeo.clase.data.lista[i];
		autoCompletar[e] = { value: item["codigoClase"], label: item["codigoClase"], category: "Entidad" };
		e++;
	}
	return autoCompletar;
};

var generarAutoCompletePK = function(claseSubstraida,lista){
	var autoCompletar = new Array();
	var e = 0;
	for(var i in lista){
		var item = lista[i];
		var bds_entidad = Mapeo.clase.data.listaxCodigo[item.codigoClase].bds_entidad;
		if(item.codigoClase != claseSubstraida){
			autoCompletar[e] = { value: item["bds_atributo"]+" - "+bds_entidad, label: item["bds_atributo"]+" - "+bds_entidad };
			e++;
		}
	}
	return autoCompletar;
};