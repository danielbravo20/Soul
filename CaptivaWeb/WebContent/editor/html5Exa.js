	var figuras = {
		color : {
			verdeFondo : "#33ffad",
			verdeBorde : "#00e689",
			celesteFondo : "#66d9ff",
			celesteBorde : "#00ace6",
			naranjaFondo : "#ffcc99",
			naranjaBorde : "#ff8000"
		},
		crearCirculo : function(idCanvas,confAdd){
			var config = {
				espacio : 22,
				borde : 2
			};
			$.extend( config, confAdd );
			var canvas = document.getElementById(idCanvas);
			var valMax = (canvas.height < canvas.width)?canvas.height:canvas.width;

			var context = canvas.getContext('2d');
			var centerX = canvas.width / 2;
			var centerY = canvas.height / 2;
			var radius = (valMax - ((config.espacio+config.borde)*2)) / 2;
			if(radius>0){
				context.beginPath();
				context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
				context.fillStyle = this.color.naranjaFondo;
				context.fill();
				context.lineWidth = config.borde;
				context.strokeStyle = this.color.naranjaBorde;
				context.stroke();
			}

		},
		crearRombo : function(idCanvas,confAdd){
			var config = {
				espacio : 5,
				borde : 2,
				tipo : "proporcion" // proporcion // margen
			};
			$.extend( config, confAdd );
			var canvas = document.getElementById(idCanvas);
			var valMax = (canvas.height < canvas.width)?canvas.height:canvas.width;
			var context = canvas.getContext("2d");

			var centerX = canvas.width / 2;
			var centerY = canvas.height / 2;
			
				context.beginPath();
			if(config.tipo == "margen"){
				var margen = config.espacio+config.borde;
				context.moveTo(centerX,margen);
				context.lineTo(canvas.width-margen,centerY);
				context.lineTo(centerX, canvas.height-margen);
				context.lineTo(margen,centerY);
			} else {
				var largoMax = (valMax - ((config.espacio+config.borde)*2)) / 2;
				context.moveTo(centerX,centerY-largoMax);
				context.lineTo(centerX+largoMax,centerY);
				context.lineTo(centerX,centerY+largoMax);
				context.lineTo(centerX-largoMax,centerY);
			}
			
			context.fillStyle = this.color.verdeFondo;
			context.fill();
			context.closePath();
			
			context.lineWidth = config.borde;
			context.strokeStyle = this.color.verdeBorde;
			context.stroke();

		},
		crearCuadrado : function(idCanvas,confAdd){
			var config = {
				espacio : 10,
				bordeRadio : 5	
			};
			$.extend( config, confAdd );
			var canvas = document.getElementById(idCanvas);
			var context = canvas.getContext('2d');
			// Set rectangle and corner values
			var rectX = config.espacio;
			var rectY = config.espacio;
			var rectWidth = canvas.width-(config.espacio*2);
			var rectHeight = canvas.height-(config.espacio*2);
			var cornerRadius = config.bordeRadio;

			// Set faux rounded corners
			context.lineJoin = "round";
			context.lineWidth = cornerRadius;
			context.fillStyle = this.color.celesteFondo;
			context.fill();
			context.strokeStyle = this.color.celesteBorde;
			
			// Change origin and dimensions to match true size (a stroke makes the shape a bit larger)
			context.strokeRect(rectX+(cornerRadius/2), rectY+(cornerRadius/2), rectWidth-cornerRadius, rectHeight-cornerRadius);
			context.fillRect(rectX+(cornerRadius/2), rectY+(cornerRadius/2), rectWidth-cornerRadius, rectHeight-cornerRadius);

			context.stroke();
		},
		crearFlecha : function(idCanvas,confAdd){
			var canvas = document.getElementById(idCanvas);
			var context = canvas.getContext("2d");
			var config = {
				deX : 0,
				deY : 0,
				aX : canvas.width,
				aY : canvas.height
			};       
			$.extend( config, confAdd );			
			var headlen = 10;   // length of head in pixels
			var angle = Math.atan2(config.aY-config.deY,config.aX-config.deX);
			context.beginPath()
			context.moveTo(config.deX, config.deY);
			context.lineTo(config.aX, config.aY);
			context.lineWidth = 2;
			//context.lineTo(config.aX-headlen*Math.cos(angle-Math.PI/6),config.aY-headlen*Math.sin(angle-Math.PI/6));
			//context.moveTo(config.aX, config.aY);
			//context.lineTo(config.aX-headlen*Math.cos(angle+Math.PI/6),config.aY-headlen*Math.sin(angle+Math.PI/6));
			context.stroke();
			
            var endRadians = Math.atan((config.aY-config.deY)/(config.aX-config.deX));
				endRadians+= ((config.aX>config.deX)?90:-90)*Math.PI/180;
			
			context.save();
            context.beginPath();
            context.translate(config.aX,config.aY);
            context.rotate(endRadians);
            context.moveTo(0,0);
            context.lineTo(5,20);
            context.lineTo(-5,20);
            context.closePath();
            context.restore();
            context.fill();
			
		},
		crearFlechaFija : function(idCanvas, confAdd){
			
			var canvas = document.getElementById(idCanvas);
			var context = canvas.getContext("2d");
				context.setTransform(1, 0, 0, 1, 0, 0);
				context.clearRect(0, 0, canvas.width, canvas.height);
				
			var config = {
				deX : 0,
				deY : 0,
				aX : canvas.width,
				aY : canvas.height,
				grosor : 2
			};       
			$.extend( config, confAdd );	
			
			this.pintarLinea = function(deX,deY,aX,aY){
				context.beginPath()
				context.moveTo(deX, deY);
				context.lineTo(aX, aY);
				context.lineWidth = config.grosor;
				context.stroke();
			};
			
			this.crearPunta = function(deX,deY,aX,aY){
				var endRadians = Math.atan((aY-deY)/(aX-deX));
					endRadians+= ((aX>deX)?90:-90)*Math.PI/180;
				context.beginPath();
				context.translate(aX,aY);
				context.rotate(endRadians);
				context.moveTo(0,0);
				context.lineTo(5,20);
				context.lineTo(-5,20);
				context.stroke();
				context.closePath();
				context.restore();
				context.fill();
			};

			
			
			
			if(config.aY==20){
				this.pintarLinea(config.deX, 10, config.aX, 10);
				this.crearPunta(config.deX, 10, config.aX, 10);
			} else if(config.deTIPO == 'D' && config.aTIPO == 'I' && config.aX > config.deX){
				var puntoB_X = ((config.aX-config.deX)/2+10);
				var puntoB_Y = config.deY;
				var puntoC_X = puntoB_X;
				var puntoC_Y = config.aY;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, puntoC_X, puntoC_Y);
				this.pintarLinea(puntoC_X, puntoC_Y, config.aX, config.aY);
				this.crearPunta(puntoC_X, puntoC_Y, config.aX, config.aY);
			} else if(config.deTIPO == 'I' && config.aTIPO == 'D' && config.deX > config.aX){ // 
				var puntoB_X = ((config.deX-config.aX)/2+10);
				var puntoB_Y = config.deY;
				var puntoC_X = puntoB_X;
				var puntoC_Y = config.aY;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, puntoC_X, puntoC_Y);
				this.pintarLinea(puntoC_X, puntoC_Y, config.aX, config.aY);
				this.crearPunta(puntoC_X, puntoC_Y, config.aX, config.aY);
			} else if(config.deTIPO == 'R' && config.aTIPO == 'B' && config.deY > config.aY){ // 
				var puntoB_X = config.deX;
				var puntoB_Y = ((config.deY-config.aY)/2+10);
				var puntoC_X = config.aX;
				var puntoC_Y = puntoB_Y;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, puntoC_X, puntoC_Y);
				this.pintarLinea(puntoC_X, puntoC_Y, config.aX, config.aY);
				this.crearPunta(config.aX, config.aY, config.aX, config.aY+0.01);
			} else if(config.deTIPO == 'B' && config.aTIPO == 'R' && config.aY > config.deY){ // 
				var puntoB_X = config.deX;
				var puntoB_Y = ((config.aY-config.deY)/2+10);
				var puntoC_X = config.aX;
				var puntoC_Y = puntoB_Y;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, puntoC_X, puntoC_Y);
				this.pintarLinea(puntoC_X, puntoC_Y, config.aX, config.aY);
				this.crearPunta(config.aX, config.aY, puntoC_X, config.aY-0.01);
			} else if(config.deTIPO == 'R' && config.aTIPO == 'I' && config.aX > config.deX && config.aY < config.deY){
				var puntoB_X = config.deX;
				var puntoB_Y = config.aY;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, config.aX, config.aY);
				this.crearPunta(puntoB_X, puntoB_Y, config.aX, config.aY);
			} else if(config.deTIPO == 'B' && config.aTIPO == 'I' && config.aX > config.deX && config.aY > config.deY){
				var puntoB_X = config.deX;
				var puntoB_Y = config.aY;
				this.pintarLinea(config.deX, config.deY, puntoB_X, puntoB_Y);
				this.pintarLinea(puntoB_X, puntoB_Y, config.aX, config.aY);
				this.crearPunta(puntoB_X, puntoB_Y, config.aX, config.aY);
			}
			
			context.save();
		},
		crearFondo : function(idCanvas){
			var canvas = document.getElementById(idCanvas);
			var context = canvas.getContext('2d');
			
			var margen = 50;
			
			var ancho = $(".EF_areaTrabajoBase").width();
			var alto = $(".EF_areaTrabajoBase").height();
			
			canvas.width = ancho;
			canvas.height = alto;
			
			for(var i = 0;i < (Math.floor(ancho/margen)+1); i++){
				context.beginPath();
				context.moveTo(i*margen,0);
				context.lineTo(i*margen,alto);
				context.lineWidth = 0.1;
				context.stroke();
			}
			
			for(var i = 0;i < (Math.floor(alto/margen)+1); i++){
				context.beginPath();
				context.moveTo(0,i*margen);
				context.lineTo(ancho,i*margen);
				context.lineWidth = 0.1;
				context.stroke();
			}
			
		},
		clonar : function(canvasAnterior,elementoNuevo){
			var canvasAnterior = document.getElementById(canvasAnterior);
			var elementoNuevo = document.getElementById(elementoNuevo);
			var context = elementoNuevo.getContext('2d');
			context.drawImage(canvasAnterior, 0, 0);
		}
	};
	
	var utilEditor = {
		getJContPos : function(){
			var jContenido = $(".EF_contenido");
			var contenido = {
				ancho : jContenido.width(),
				alto : jContenido.height(),
				izquierda : jContenido.position().left,
				arriba : jContenido.position().top
			};
			return contenido;
		}
	};
	
var editor = angular.module("editor", []);

	editor.controller("areaTrabajo",function($scope, $compile){

		$scope.tabIF = {
			unoALaVes : true,
			actual : 1,
			lista : [
				{
					nombre : "Información",
					activo : true,
					url : 'tab_IF_informacion.html'
				},
				{
					nombre : "Propiedades",
					activo : false,
					url : 'tab_IF_propiedades.html'
				}
			]
		};
	
		$scope.EF = {
			area : {
				cargarItem : function(id){
					$scope.EF.area.itemCargado = $scope.EF.area.items[id];
				},
				itemCargado : {},
				items : []
			},
			flecha : {
				inicio : false,
				puntoX : {},
				puntoY : {},
				items : [],
				itemId : {}
			},
			dibujarLinea : function(itemX,itemY,nuevoIndex){

				var jPuntoInicio = $(itemX);
					jPuntoInicio.show();
				var inicioAlto = jPuntoInicio.position().top+jPuntoInicio.parent().position().top+$(".EF_areaTrabajo").scrollTop();
				var inicioAncho = jPuntoInicio.position().left+jPuntoInicio.parent().position().left+$(".EF_areaTrabajo").scrollLeft();
					jPuntoInicio.hide();

				var jPuntoFin = $(itemY);
					jPuntoFin.show();
				var finAlto = jPuntoFin.position().top+jPuntoFin.parent().position().top+$(".EF_areaTrabajo").scrollTop();
				var finAncho = jPuntoFin.position().left+jPuntoFin.parent().position().left+$(".EF_areaTrabajo").scrollLeft();
					jPuntoFin.hide();
			
				var ancho = Math.abs(inicioAncho-finAncho)+20;
				var alto = Math.abs(inicioAlto-finAlto)+20;
				
				var pInicioAlto,pInicioAncho,pFinAlto,pFinAncho;
				
				var puntoAlto;
				
				if(inicioAlto < finAlto){
					puntoAlto = inicioAlto;
					pInicioAlto = 10;
					pFinAlto = alto-10;
				} else if(finAlto < inicioAlto){
					puntoAlto = finAlto;
					pInicioAlto = alto-10;
					pFinAlto = 10;
				} else {
					puntoAlto = inicioAlto;
				}
				
				var puntoAncho;
				
				if(inicioAncho < finAncho){
					puntoAncho = inicioAncho;
					pInicioAncho = 10;
					pFinAncho = ancho-10;
				} else if(finAncho < inicioAncho){
					puntoAncho = finAncho;
					pInicioAncho = ancho-10;
					pFinAncho = 10;
				}
				
				var margenX = 3;
				var margenY = 3;
				
				var index = $scope.EF.flecha.items.length;
				if(typeof(nuevoIndex)!="undefined"){
					index = nuevoIndex;
					$("#flecha_"+index).remove();
				} else {
					var indx = $scope.EF.flecha.items.length;
					$scope.EF.flecha.items.push({
						itemX : $scope.EF.flecha.puntoX.item,
						itemY : $scope.EF.flecha.puntoY.item
					});
					if(typeof($scope.EF.flecha.itemId[jPuntoInicio.attr("id")])=="undefined"){
						$scope.EF.flecha.itemId[jPuntoInicio.attr("id")] = [];
					}
						$scope.EF.flecha.itemId[jPuntoInicio.attr("id")].push(indx);
					if(typeof($scope.EF.flecha.itemId[jPuntoFin.attr("id")])=="undefined"){
						$scope.EF.flecha.itemId[jPuntoFin.attr("id")] = [];
					}
						$scope.EF.flecha.itemId[jPuntoFin.attr("id")].push(indx);
					
				}
				$(".EF_areaTrabajoBase").prepend('<canvas id="flecha_'+index+'" width="'+ancho+'" height="'+alto+'" style="position: absolute;left: '+((puntoAncho-10)+margenX)+'px; top: '+((puntoAlto-10)+margenY)+'px;"></canvas>'); // 
					
				figuras.crearFlechaFija('flecha_'+index,{
					deX : pInicioAncho,
					deY : pInicioAlto,
					aX : pFinAncho,
					aY : pFinAlto,
					deTIPO : jPuntoInicio.attr("eTipo"),
					aTIPO : jPuntoFin.attr("eTipo")
				});
				
			},
			crearLinea : function(){
				this.dibujarLinea($scope.EF.flecha.puntoX.item,$scope.EF.flecha.puntoY.item);
			},
			crearFlechaTemporal : function(){
				$(".EF_areaTrabajoBase").mousemove(function(event){
					if($scope.EF.flecha.inicio == true){
						var contenido = utilEditor.getJContPos();
						var posiActualX = (contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft();
						
						var posiActualX = event.pageX-(contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft();
						var posiActualY = event.pageY-(contenido.arriba)+$(".EF_areaTrabajo").scrollTop();
						
						var opci = {};
						
						var ancho = Math.abs($scope.EF.flecha.puntoX.posX - posiActualX);
						var alto = Math.abs($scope.EF.flecha.puntoY.posY - posiActualY);
						
						var indRang = 80;
						
						var difAncho = 0;
						if(ancho<indRang){
							difAncho = (indRang-ancho);
							$("#flechaEnlace").attr("width",(indRang*2));
							$("#flechaEnlace").css("left", $scope.EF.flecha.puntoX.posX-indRang);
						} else {
							$("#flechaEnlace").attr("width",ancho);
							if(posiActualX < $scope.EF.flecha.puntoX.posX){
								$("#flechaEnlace").css("left", posiActualX);
							} else {
								$("#flechaEnlace").css("left", $scope.EF.flecha.puntoX.posX);
							}
						}
						
						var difAlto = 0;
						if(alto<indRang){
							difAlto = (indRang-alto);
							$("#flechaEnlace").attr("height",(indRang*2));
							$("#flechaEnlace").css("top", $scope.EF.flecha.puntoY.posY-indRang);
						} else {
							$("#flechaEnlace").attr("height",alto);
							if(posiActualY < $scope.EF.flecha.puntoY.posY){
								$("#flechaEnlace").css("top", posiActualY);
							} else {
								$("#flechaEnlace").css("top", $scope.EF.flecha.puntoY.posY);
							}
						}
						
						if(posiActualX > $scope.EF.flecha.puntoX.posX && posiActualY > $scope.EF.flecha.puntoY.posY){
							opci = {
								deX : difAncho==0?0:indRang,
								deY : difAlto==0?0:indRang,
								aX : difAncho==0?ancho:((indRang*2)-difAncho),
								aY : difAlto==0?alto:((indRang*2)-difAlto)
							};
						} else if(posiActualX > $scope.EF.flecha.puntoX.posX && posiActualY < $scope.EF.flecha.puntoY.posY){
							opci = {
								deX : difAncho==0?0:indRang,
								deY : difAlto==0?alto:indRang,
								aX : difAncho==0?ancho:((indRang*2)-difAncho),
								aY : 0+difAlto
							};
						} else if(posiActualX < $scope.EF.flecha.puntoX.posX && posiActualY > $scope.EF.flecha.puntoY.posY){
							opci = {
								deX : difAncho==0?ancho:indRang,
								deY : difAlto==0?0:indRang,
								aX : difAncho==0?0:(difAncho),
								aY : difAlto==0?alto:((indRang*2)-difAlto)
							};
						} else if(posiActualX < $scope.EF.flecha.puntoX.posX && posiActualY < $scope.EF.flecha.puntoY.posY){
							opci = {
								deX : difAncho==0?ancho:indRang,
								deY : difAlto==0?alto:indRang,
								aX : 0+difAncho,
								aY : 0+difAlto
							};
						} else {
							opci = {
								deX : difAncho==0?0:indRang,
								deY : difAlto==0?0:indRang,
								aX : difAncho==0?ancho:((indRang*2)-difAncho),
								aY : difAlto==0?alto:((indRang*2)-difAlto)
							};
						}
						figuras.crearFlecha("flechaEnlace",opci);
						
					}
				});
			}
		};
		
		$(".EF_paletaItem").draggable({ 
			opacity: 0.7, 
			helper: function(event) {
				return $('<div class="EF_paletaItem" title="Punto de Enlace"><canvas id="canvasSombra" width="100" height="80"></canvas></div>');
			},
			start: function(event) {
				figuras.clonar(event.toElement.id,"canvasSombra");
				$("#canvasSombra").attr("idPadre",event.toElement.id);
			},
			stop: function(event) {

				var contenido = utilEditor.getJContPos();
				
				var jPaleta = $(".EF_paleta");
				
				var paleta = {
					ancho : jPaleta.width(),
					alto : jPaleta.height(),
					izquierda : jPaleta.position().left + contenido.izquierda,
					arriba : jPaleta.position().top + contenido.arriba
				};
				
				var jPropiedades = $(".EF_propiedades");
				
				var propiedades = {
					ancho : jPropiedades.width(),
					alto : jPropiedades.height(),
					izquierda : jPropiedades.position().left + contenido.izquierda,
					arriba : jPropiedades.position().top + contenido.arriba
				};
				
				var cursorX = window.event.clientX;
				var cursorY = window.event.clientY;
				
				var dentroContenido = false,dentroPaleta = false, dentroPropiedades = false;
				
				if(
					cursorX>contenido.izquierda && cursorX<contenido.ancho + contenido.izquierda &&
					cursorY>contenido.arriba && cursorY<contenido.alto + contenido.arriba 
				) {
					dentroContenido = true;
				}
				if(
					cursorX>paleta.izquierda && cursorX<paleta.ancho + paleta.izquierda &&
					cursorY>paleta.arriba && cursorY<paleta.alto + paleta.arriba 
				) {
					dentroPaleta = true;
				}
				if(
					cursorX>propiedades.izquierda && cursorX<propiedades.ancho + propiedades.izquierda &&
					cursorY>propiedades.arriba && cursorY<propiedades.alto + propiedades.arriba 
				) {
					dentroPropiedades = true;
				}
				
				if(dentroContenido && !dentroPaleta && !dentroPropiedades){
					var numId = ($scope.EF.area.items.length);
					var nuevoDivId = "itemDiv_"+(numId+1);
					var nuevoCanId = "item_"+(numId+1);
					
					var tipo = $(event.toElement).attr("idPadre");
					
					if(tipo=="inicioFin"){
						var n_width = 36;
						var n_height = 36;
						var htmlCont = '<div id="'+nuevoDivId+'" numId="'+numId+'" class="EF_areaItem" ng-click="test.flechaEnlace = 1">';
							htmlCont+= '	<canvas id="'+nuevoCanId+'" width="'+n_width+'" height="'+n_height+'"></canvas>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_R" eTipo="R" style="top: -1px; left: 14px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_B" eTipo="B" style="bottom: 2px; left: 14px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_I" eTipo="I" style="top: 15px; left: -2px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_D" eTipo="D" style="top: 15px; right: -2px;"></div>';
							htmlCont+= '</div>';
						
						$(".EF_areaTrabajoBase").append(htmlCont);
						$("#"+nuevoDivId).css("left", cursorX-(contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft()-(n_width/2));
						$("#"+nuevoDivId).css("top", cursorY-(contenido.arriba)+$(".EF_areaTrabajo").scrollTop()-(n_height/2));
						figuras.crearCirculo(nuevoCanId,{
							espacio : 0
						});
					}
					if(tipo=="decision"){
						var n_width = 70;
						var n_ancho = 70;
						var htmlCont = '<div id="'+nuevoDivId+'" numId="'+numId+'" class="EF_areaItem">';
							htmlCont+= '	<canvas id="'+nuevoCanId+'" width="'+n_width+'" height="'+n_ancho+'"></canvas>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_R" eTipo="R" style="top: -1px; left: 31px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_B" eTipo="B" style="bottom: 2px; left: 31px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_I" eTipo="I" style="top: 32px; left: -2px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_D" eTipo="D" style="top: 32px; right: -2px;"></div>';
							htmlCont+= '</div>';
						
						$(".EF_areaTrabajoBase").append(htmlCont);
						$("#"+nuevoDivId).css("left", cursorX-(contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft()-(n_width/2));
						$("#"+nuevoDivId).css("top", cursorY-(contenido.arriba)+$(".EF_areaTrabajo").scrollTop()-(n_ancho/2));
						figuras.crearRombo(nuevoCanId,{
							espacio : 0,
							tipo : "proporcion"
						});
					}
					if(tipo=="actividad"){
						var n_width = 80;
						var n_ancho = 60;
						var htmlCont = '<div id="'+nuevoDivId+'" numId="'+numId+'" class="EF_areaItem">';
							htmlCont+= '	<canvas id="'+nuevoCanId+'" width="'+n_width+'" height="'+n_ancho+'"></canvas>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_R" eTipo="R" style="top: -1px; left: 36px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_B" eTipo="B" style="bottom: 2px; left: 36px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_I" eTipo="I" style="top: 27px; left: -2px;"></div>';
							htmlCont+= '	<div class="EF_areaItemPunto" id="'+nuevoDivId+'_punto_D" eTipo="D" style="top: 27px; right: -2px;"></div>';
							htmlCont+= '</div>';
						
						$(".EF_areaTrabajoBase").append(htmlCont);
						$("#"+nuevoDivId).css("left", cursorX-(contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft()-(n_width/2));
						$("#"+nuevoDivId).css("top", cursorY-(contenido.arriba)+$(".EF_areaTrabajo").scrollTop()-(n_ancho/2));
						figuras.crearCuadrado(nuevoCanId,{
							espacio : 0
						});
					}
					
					$scope.EF.area.items.push({
						id : nuevoDivId,
						idCan : nuevoCanId,
						tipo : tipo
					});
					
					$("#"+nuevoDivId).draggable({ stack: ".EF_areaItem" }).click(function(){
						$scope.EF.area.cargarItem($(this).attr("numId"));
						$scope.$apply();
					}).mouseover(function(){
						$(this).find("div").show();
					}).mouseout(function(){
						$(this).find("div").hide();
					});
					
					$("#"+nuevoDivId).on("drag", function( event, ui ) {
					
						var puntos = $(this).find("div");
							for(var i = 0; i < puntos.size(); i++){
								if(typeof($scope.EF.flecha.itemId[puntos.get(i).id])!="undefined"){
									var ind = $scope.EF.flecha.itemId[puntos.get(i).id];
									for(var e = 0;e < ind.length; e++){
										var lin = $scope.EF.flecha.items[ind[e]];
										$scope.EF.dibujarLinea(lin.itemX,lin.itemY,ind[e]);
									}
								}
							}
					});
				}
			}
		}); 
	
		$(".EF_areaTrabajoBase").click(function(event){
			if(event.target.nodeName=="DIV"){
				if($scope.EF.flecha.inicio){
					$scope.EF.flecha.inicio = false;
					$(".EF_areaTrabajoBase").unbind("mousemove");
					$("#flechaEnlace").remove();
					$scope.EF.flecha.puntoY.item = event.target;
					$scope.EF.crearLinea();
				} else {
					var contenido = utilEditor.getJContPos();
					$scope.EF.flecha.inicio = true;
					$scope.EF.flecha.puntoX.item = event.target;
					$scope.EF.flecha.puntoX.posX = event.pageX-(contenido.izquierda)+$(".EF_areaTrabajo").scrollLeft();
					$scope.EF.flecha.puntoY.posY = event.pageY-(contenido.arriba)+$(".EF_areaTrabajo").scrollTop();
					$scope.EF.crearFlechaTemporal();
					$(".EF_areaTrabajoBase").prepend('<canvas id="flechaEnlace" width="8" height="8" style="position: absolute;left: '+($scope.EF.flecha.puntoX.posX-4)+'px; top: '+($scope.EF.flecha.puntoY.posY-4)+'px;"></canvas>'); // background-color: beige;
					figuras.crearCirculo("flechaEnlace",{
						espacio : 0
					});
				}
			} else {
				$scope.EF.flecha.inicio = false;
				$(".EF_areaTrabajoBase").unbind("mousemove");
				$("#flechaEnlace").remove();
			}
		
		});
		
	});

var puntosEnlace = {};
	
$(document).ready(function(){

	figuras.crearCirculo("inicioFin");
	figuras.crearRombo("decision");
	figuras.crearCuadrado("actividad");
	figuras.crearFondo("fondoAreaTrabajo");

	$(".EF_areaTrabajo").animate({scrollTop:($(".EF_areaTrabajoBase").height()-350)/2}, 700);
	$(".EF_areaTrabajo").animate({scrollLeft:($(".EF_areaTrabajoBase").width()-350)/2}, 700);
	
});