var acceder = angular.module('acceder', ['ui.bootstrap','coreComun']);

	acceder.controller('Logeo', function ($scope, $modal, cargador) {
		
		$scope.mensaje = "";
		
		$scope.anho = new Date().getFullYear();
		
		$scope.open = function(){
			cargador.ocultar();
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'confirmaracceso.html',
				controller: 'ModalInstanceCtrl'
			});
		};
		
		$scope.mostrarError = function(error){
			cargador.ocultar();
			$scope.mensaje = error;
			$scope.$apply();
		};
		
		$scope.acceder = function(){
			
			if($("#usuario").val().length==0){
				$("#usuario").focus();
				return false;
			}
			
			if($("#clave").val().length==0){
				$("#clave").focus();
				return false;
			}
			
			cargador.mostrarAcceder();
			if($("#acceder").data('esDetenido')=='1'){
				return ;
			} else {
				$("#acceder").data('esDetenido','1');
				$("#clave").val($.trim($("#clave").val()));
				logeo_acceder("false");
			}
		};
		
	});
	
	acceder.controller('ModalInstanceCtrl', function ($scope, $modalInstance, cargador) {

		$scope.Si = function(){
			cargador.mostrar();
			logeo_acceder("true");
			$modalInstance.close();
		};

		$scope.Cancelar = function(){
			$modalInstance.dismiss('cancel');
		};
		
	});


if(typeof console === "undefined") {
	    var console = {
	        log		: function() { },
	        debug	: function() { },
	        info 	: function() { },
	        warn 	: function() { },
	        error 	: function() { }
	    };
	}

var logeo_acceder = function(borraSession){
	 debugger;
	var ruta = 'portal/postAutenticacion.html?txt_log_acceder='+borraSession+'&rnd='+Math.random();
	
	if($("#nroIframe").size()!=0){
		$("#nroIframe").remove();
	}
	
	$("body").append('<iframe id="nroIframe" src="'+ruta+'"></iframe>');
	//console.log("1. AJAX Cargamos en un iframe la ruta '"+ruta+"'");
	$("#nroIframe").hide().load(function() {
		
		if($("#nroIframe").contents().find("pre").text()=="USUARIO_EXISTE_SESSION_LOCAL" || $("#nroIframe").contents()[0].childNodes[0].innerText=="USUARIO_EXISTE_SESSION_LOCAL"){
			location.href = "index.jsp";
		};
		
		$('#nroIframe').unbind("load");
		//console.log("2. luego que cargo el iframe validamos accediendo al 'j_security_check'");
		
		$.ajax({
			url: "j_security_check",
			data: {j_username : $("#usuario").val(),j_password : $("#clave").val(),rnd:Math.random()},
			success: function(data, textStatus, jqXHR ){
				//console.log("3. respuesta del 'j_security_check'");
				
				$('#nroIframe').bind("load");
				var dataJSON = {"estado" : false};
				try {
					dataJSON = jQuery.parseJSON( data );
				} catch(e){};
				
				//console.log(dataJSON);
				
				if(dataJSON.estado){
					//console.log("3. accedimos corréctamente al 'j_security_check' por tanto la página del iframe 'postAutenticacion.html' deberá retornar su objeto en JSON");
					//$("#div_loge_avisos").jnexoAviso({mensaje:"Redireccionando..."});
					setTimeout(function(){location.href = dataJSON.url; },1500);
				} else {
					//console.log("3.1 No se pudo acceder nos devuelve :");
					//console.log(data);
					
					if(data=="USUARIO_EXISTE_SESSION"){
						//console.log("3.2 El filter nos avisa que ya existe una session creada, por tanto nos desconectamos de la session ingresada 'portal/preDesconexion.jsp'");
						//$("#div_loge_avisos").jnexoAviso({cerrar:"true"});
						angular.element(document.querySelector('[ng-controller=Logeo]')).scope().open();
						$("body").append('<iframe id="cerrarSes" src="portal/preDesconexion.jsp"></iframe>');
						$('#cerrarSes').load(function() {
							//console.log("3.3 luego de desconectarnos eliminamos el iframe de 'portal/preDesconexion.html' ");
							$('#cerrarSes').remove();
						});
						$("#acceder").data('esDetenido','0');
					} else {
						//console.log("3. Si no nos devuelve ninguna informacion es por que no se ha podido acceder por un problema de session de usuario y contraseña.");
						//$("#div_loge_avisos").jnexoAviso({error:"Usuario o Contraseña Incorrecto", esCerrable : true});
						$("#usuario").select();
						$("#acceder").data('esDetenido','0');
						angular.element(document.querySelector('[ng-controller=Logeo]')).scope().mostrarError("Usuario o Contraseña Incorrecto");
						
					}
				}
			}
		});

	});
};

$(document).ready(function(){
	
	$("#usuario").keypress(function(event){ // Filtrar x buscar
		if (event.keyCode == 10 || event.keyCode == 13){
			if($.trim($(this).val())==""){
				$(this).val("");
				$("#usuario").focus();
			} else {
				$(this).val($.trim($(this).val()));
				$("#clave").focus();
			}
			return false;
		}
	});
	
	$("#clave").keypress(function(event){ // Filtrar x buscar
		if (event.keyCode == 10 || event.keyCode == 13){
			if($.trim($(this).val())==""){
				$(this).val("");
				$("#clave").focus();
			} else {
				$(this).val($.trim($(this).val()));
				$("#acceder").click();
			}
			return false;
		}
	});
	
	$("#acceder").data('esDetenido','0');
	$("#usuario").focus();
	
	var centrar = function(){
		var objLog = $("#LogeoContenido");
		objLog.css("left",Math.floor(($(window).width()-objLog.width())/2)+"px");
		objLog.css("top",(Math.floor(($(window).height()-objLog.height())/2)+23)+"px");
	};
	
	$(window).resize(function() {
		centrar();
	});
	
	centrar();
	
});