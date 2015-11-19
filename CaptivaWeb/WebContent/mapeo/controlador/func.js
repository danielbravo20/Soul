var func = {};

	func.consulta = function($scope,ajax,codProyecto,codConsulta){
		
		$scope.consulta = {
			tablas : [],
			atributos : [],
			atributosId : {},
			cargar : function(callback){
				ajax.jpo({
					paquete : "modulo",
					clase : "Consulta",
					metodo : "cargarConsulta",
					CON_COD_PROYECTO : codProyecto,
					CON_W_COD_CONSULTA : codConsulta,
					CON_W_COD_PROYECTO : codProyecto,
					CAT_W_COD_CONSULTA : codConsulta,
					CTA_W_COD_CONSULTA : codConsulta
				},function(respuesta){
					
					for(var i = 0; i<respuesta.CONSULTA_TABLA.length ; i++){
						if(!$scope.consulta.tablas[respuesta.CONSULTA_TABLA[i].cod_tabla]){
							$scope.consulta.tablas[respuesta.CONSULTA_TABLA[i].cod_tabla] = respuesta.CONSULTA_TABLA[i];
						}
					}
					
					for(var i = 0; i<respuesta.CONSULTA_ATRIBUTO.length ; i++){
						respuesta.CONSULTA_ATRIBUTO[i].web_tipo = respuesta.CONSULTA_ATRIBUTO[i].tipo;
						respuesta.CONSULTA_ATRIBUTO[i].web_etiqueta = respuesta.CONSULTA_ATRIBUTO[i].etiqueta;
						respuesta.CONSULTA_ATRIBUTO[i].atr_nombre = respuesta.CONSULTA_ATRIBUTO[i].nombre;
						if(!$scope.consulta.atributosId[respuesta.CONSULTA_ATRIBUTO[i].cod_atributo]){
							$scope.consulta.atributosId[respuesta.CONSULTA_ATRIBUTO[i].cod_atributo] = i;
						}
					}
					
					$scope.consulta.atributos = respuesta.CONSULTA_ATRIBUTO;
					
					if(callback){
						callback();
					}
					
				});
			}
		};

	};