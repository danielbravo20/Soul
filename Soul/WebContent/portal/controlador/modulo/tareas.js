portal.registerCtrl('tare', function($scope, $modal, $timeout, $filter, $window, ajax, semaforo, hostname, texto, cargador) {

	$scope.grillaTarea = {};
	$scope.tiposTarea = [
		{
			id: "PorHacer",
			descripcion: "Por hacer"
		},
		{
			id: "MisTareas",
			descripcion: "Mis Tareas"
		},
		{
			id: "Disponibles",
			descripcion: "Disponibles"
		},
		{
			id: "Editables",
			descripcion: "Editables"
		},
		{
			id: "Realizadas",
			descripcion: "Realizadas"
		},
		{
			id: "Administradas",
			descripcion: "Administradas"
		}
	];
	$scope.filtro = {
		esOculto : false,
		data : {
			accion : "getTareas",//
			tipoTarea : "PorHacer",
			buscar : "",
			codigoUnidadNegocio : texto.defecto.codigoUnidadNegocio,
			codigoProducto : texto.defecto.codigoProducto,
			fechaDesde : "",
			fechaHasta : "",
			ordenFecha : "DESC",//
			rnd : Math.random(),
			paginacionNro : 1,//
			cargarTotal : "1"//			
		}
	};
	$scope.codigoTareaSeleccionada = -1;
	$scope.urlResumenTarea = "";
	$scope.verTareasPendientes = false;
	$scope.verHistorialTareas = false;
	
	$scope.listar = function(tkiid){
		$scope.InstanciarResumen();
		ajax.get({
			url: "portal",
			data: $scope.filtro.data,
			getRespuesta : function(grillaTarea){
				for(var i in grillaTarea.items){
					var tarea = grillaTarea.items[i];
					
					grillaTarea.items[i].fechaCreacion = new Date(tarea.fechaCreacion);
					grillaTarea.items[i].proceso.fechaCreacion = new Date(tarea.proceso.fechaCreacion);
					
					var data = $scope.getUN("UN,PROD,PROC,TARE",[tarea.codigoUnidadNegocio,tarea.codigoProducto,tarea.codigoProceso,tarea.codigoTarea]);
					grillaTarea.items[i]._data = data;

					if(data.error){
						grillaTarea.items[i].error = true;
					} else {
						grillaTarea.items[i].puntero = "pointer";
						var sem = semaforo.calcular(data.tarea.tiempoRojo,data.tarea.tiempoAmarillo,grillaTarea.fechaActual,tarea.fechaCreacion);
						grillaTarea.items[i].creacionColor = sem.color;
						grillaTarea.items[i].creacionDescripcion = sem.descripcion;
					}
					
					// /*DUMMY*/ CARGAR TAREA
					if(parametro.codigo && Number(parametro.codigo) == tarea.codigo){
						var cargarTarea = i;
					}
					// ----------------------
					
				}
				
				$scope.grillaTarea = grillaTarea;
				$scope.filtro.esOculto = true;
				
				if(tkiid){
					$scope.cargarTarea(tkiid);
				}
				
				// /*DUMMY*/ CARGAR TAREA
				if(cargarTarea){
					$scope.verResumen(cargarTarea);
					$scope.accion.trabajar.ejecutar();
				}
				// ----------------------

			},
			mostrarCargador : true
		});
	};
	
	$scope.cargarTarea = function(tkiid){
		for(var i in $scope.grillaTarea.items){
			if($scope.grillaTarea.items[i].tkiid == tkiid){
				$scope.verResumen(i);
				$scope.accion.trabajar.ejecutar();
			}
		}
	};
	
	$scope.filtrar = function(){
		$scope.filtro.esOculto = true;
		$scope.listar();
	};
	
	$scope.limpiar = function(){
		$scope.filtro.data.buscar = "";
		$scope.filtro.data.codigoUnidadNegocio = texto.defecto.codigoUnidadNegocio;
		$scope.filtro.data.codigoProducto = texto.defecto.codigoProducto;
		$scope.filtro.data.fechaDesde = "";
		$scope.filtro.data.fechaHasta = "";
		$scope.filtro.data.ordenFecha = "DESC";
		$scope.filtro.data.paginacionNro = 1;
		$scope.filtrar();
	};
	
	$scope.cambiarOrden = function(){
		if($scope.filtro.data.ordenFecha=="DESC") { 
			$scope.filtro.data.ordenFecha = "ASC"; 
		} else if($scope.filtro.data.ordenFecha=="ASC") {
			$scope.filtro.data.ordenFecha = "DESC";
		}
		$scope.listar();
	};
	
	$scope.actualizar = function(){
		$scope.actualizarListado.instanciar(true);
	};
	
	$scope.restaurarFiltro = function(){
		$scope.filtro.esOculto = true;
	};
	
	$scope.seleccionarTarea = function(){
		return $scope.tareaSeleccionada;
	};

	// RESUMEN
	
		// ACCIONES
		
		$scope.accion = {};
		
		$scope.accion.transferir = {
			esActivo : false,
			validarPerfil : function(callback){
				ajax.get({
					url: "portal",
					data: {
						accion 			: "buscarPerfilTAREAS",
		    			codigoUsuario 	: $scope.$parent.data.Usuario.codigo,
		    			codigoTarea 	: $scope.tareaSeleccionada.codigoTarea
					},
					getRespuesta : function(respuesta){
						if(respuesta.conforme){
							callback(true);
						} else {
							cargador.ocultar();
							$scope.alertaPortal.mostrarError(respuesta.mensaje);
							callback(false);
						}
						$scope.usuarios = respuesta;
					},
					mostrarCargadorInicial : true
				});
			},
			transferir : function(usuario,callback){
				ajax.getAjax({
					url: 	$scope.tareaSeleccionada._data.urlTarea,
					data: {
						accion 					: "transferir",
						codigoTarea				: $scope.tareaSeleccionada.codigoTarea,
						codigoUsuario			: usuario.codigo,
						tkiid				 	: $scope.tareaSeleccionada.tkiid,
		        		usuarioAdministrador 	: $scope.data.Usuario.codigo
					},
					getRespuesta : function(respuesta){
						$scope.$parent.alertaPortal.mostrar("Tarea transferida corréctamente");
	        			$scope.listar();
						callback();
					},
					mostrarCargadorFinal : true
				});
			},
			cargarTransferir : function(usuario,callback){
				$scope.accion.transferir.validarPerfil(function(respuesta){
					if(respuesta){
						$scope.accion.transferir.transferir(usuario,callback);
					} else {
						//callback();
					}
				});
			},
			mostrarBusqueda : function(){
				var modalInstance = $modal.open({
					animation: true,
					templateUrl: 'buscarUsuario.html',
					controller: 'Modal_buscarUsuario',
					resolve: {
						config : function(){
							return {
								nombreBoton : "Transferir",
								ejecutarAccion : function(usuario,callback){
									$scope.accion.transferir.cargarTransferir(usuario,callback);
								}
							};
						}
					}
				});
				modalInstance.result.then(function(){
					$scope.instanciar();
			    });
			}
		};
		
		$scope.accion.liberar = {
			esActivo : false,
			ejecutar : function(){
				ajax.getAjax({
					url: 	$scope.tareaSeleccionada._data.urlTarea,
					data: {
						accion 					: "liberar",
						codigoTarea				: $scope.tareaSeleccionada.codigoTarea,
		        		tkiid				 	: $scope.tareaSeleccionada.tkiid
		        	},
					getRespuesta : function(respuesta){
		        		if(respuesta.conforme){
		        			$scope.$parent.alertaPortal.mostrar("Tarea liberada corréctamente");
		        			$scope.listar();
		        		} else {
		        			cargador.mostrarError(respuesta.mensaje + " | " + respuesta.parametro);
		        		}
					},
					mostrarCargadorInicial : true
				});
			}
		};
		
		$scope.accion.editar = {
			esActivo : false,
			ejecutar : function(){
				$scope.accion.trabajar.ejecutar();
			}
		};
			
		$scope.accion.reclamar = {
			esActivo : false,
			ejecutar : function(callback){
				ajax.getAjax({
					url: 	$scope.tareaSeleccionada._data.urlTarea,
					data: {
						accion 					: "reclamar",
						codigoTarea				: $scope.tareaSeleccionada.codigoTarea,
						tkiid				 	: $scope.tareaSeleccionada.tkiid
		        	},
					getRespuesta : function(respuesta){
		        		if(respuesta.conforme){
		        			$scope.$parent.alertaPortal.mostrar("Tarea reclamada corréctamente");
		        			if(callback){
								callback();
							} else {
								$scope.listar();
							}
		        		} else {
		        			cargador.mostrarError(respuesta.mensaje + " | " + respuesta.parametro);
		        		}
					},
					mostrarCargadorInicial : true
				});
			}
		};
		
		var cargarDuenoUsuario = function(tkiid){
			for(var i in $scope.grillaTarea.items){
				if($scope.grillaTarea.items[i].tkiid == tkiid){
					$scope.grillaTarea.items[i].duenoUsuario = $scope.data.Usuario.codigo;
					break;
				}
			}
		};
		
		$scope.accion.trabajar = {
			esActivo : false,
			ejecutar : function(){
				if($scope.tareaSeleccionada.duenoUsuario){
					this._ejecutar();
				} else {
					$scope.accion.reclamar.ejecutar(function(){
						cargarDuenoUsuario($scope.tareaSeleccionada.tkiid);
						$scope.accion.trabajar._ejecutar();
					});
				}
			},
			_ejecutar : function(){
				var tarea = $scope.tareaSeleccionada.codigoTarea;
				var piid = $scope.tareaSeleccionada.proceso.piid;
				var tkiid = $scope.tareaSeleccionada.tkiid;
				$scope.$parent.verSeccion("Detalle",$scope.texto.url.plantillaNexo+"tareaContenido.html");		
			}
		};
		
		$scope.restaurarBotonesAccion = function(){
			$scope.accion.trabajar.esActivo = false;
			$scope.accion.liberar.esActivo = false;
			$scope.accion.reclamar.esActivo = false;
			$scope.accion.editar.esActivo = false;
			$scope.accion.transferir.esActivo = false;
		};
		
		// RESUMEN
		$scope.verResumen = function(index){
			$scope.historiales = [];
			$scope.tareasPendientes = [];
			$scope.verTareasPendientes = false;
			$scope.verHistorialTareas = false;
			
			var tarea = $scope.grillaTarea.items[index];
			if(!tarea.error){
				
				$scope.ocultaListaTarea = true;
				$scope.restaurarFiltro();
				_calc();
				
				tarea._data.url = "/"+tarea._data.producto.url+"/";
				tarea._data.urlCP = tarea._data.url+tarea._data.producto.codigo+"/";
				tarea._data.urlTarea = tarea._data.urlCP+"tareaController";
				tarea._data.urlProceso = tarea._data.urlCP+"procesoController";
				$scope.codigoTareaSeleccionada = index;
				$scope.tareaSeleccionada = tarea;
				
				$scope.restaurarBotonesAccion();
				var usuarioLog = $scope.$parent.data.Usuario.codigo;
				if(usuarioLog == tarea.duenoUsuario){
					$scope.accion.trabajar.esActivo = true;
				} 
				if(tarea.duenoUsuario){
					$scope.accion.liberar.esActivo = true;
				} else {
					$scope.accion.trabajar.esActivo = true;
					$scope.accion.reclamar.esActivo = true;
				}
				
				if($scope.filtro.data.tipoTarea=="Editables"){
					$scope.accion.editar.esActivo = true;
				}
				
				if($scope.filtro.data.tipoTarea=="Administradas"){
					$scope.accion.transferir.esActivo = true;
				}
			
				/* Nueva Variable Plantilla */
				$scope.texto.url.plantillaNexo = "/"+tarea._data.producto.url+"/nexofinanciero/";
				$scope.urlResumenTarea = "/"+tarea._data.producto.url+"/"+tarea._data.unidadNegocio.url+"/"+tarea._data.proceso.url+"/vista/resumentarea.html?rnd="+Math.random();
				$scope.$parent.texto.url.plantilla = "/"+tarea._data.producto.url+"/plantillas/";				
			}
		};
		$scope.InstanciarResumen = function(){
			$scope.codigoTareaSeleccionada = -1;
			$scope.tareaSeleccionada = {};
			$scope.urlResumenTarea = "";
			$scope.$parent.texto.url.plantilla = "";
		};
	
		$scope.cargarResumenTarea = function(callback){
			var piid = $scope.tareaSeleccionada.proceso.piid;
			var codigoProceso = $scope.tareaSeleccionada._data.proceso.nombreProceso;
			ajax.getAjax({
				url		: $scope.tareaSeleccionada._data.urlProceso,
				data	: {accion:"verResumen",codigoProceso:codigoProceso, piid:piid},
				getRespuesta : function(respuesta){
					callback(respuesta);
				},
				mostrarCargador : true
			});
		};
		
		// HISTORIAL
		$scope.cargarHistorialTareas = function(){
			$scope.verHistorialTareas = true;
			$scope.verTareasPendientes = false;
			var piid = $scope.tareaSeleccionada.proceso.piid;
			var codigoProceso = $scope.tareaSeleccionada._data.proceso.nombreProceso;
			ajax.getAjax({
				url		: $scope.tareaSeleccionada._data.urlProceso,
				data	: {accion:"historial",codigoProceso:codigoProceso, piid:piid},
				getRespuesta : function(tareaHistoriales){
					$scope.historiales = tareaHistoriales;
				},
				mostrarCargador : true
			});
		};
		
		// TAREAS PENDIENTES
		$scope.cargarTareasPendientes = function(){
			$scope.verTareasPendientes = true;
			$scope.verHistorialTareas = false;
			var piid = $scope.tareaSeleccionada.proceso.piid;
			var codigoProceso = $scope.tareaSeleccionada._data.proceso.nombreProceso;
			ajax.getAjax({
				url		: "/NexoFinanciero/portal/portalController",
				data	: {accion:"obtenerTareasPendientes",codigoProceso:codigoProceso, piid:piid},
				getRespuesta : function(respuesta){
					for(var i in respuesta){
						var objTarea = $scope.getUN("TARE",[respuesta[i].codigoUnidadNegocio,respuesta[i].codigoProducto, respuesta[i].codigoProceso,respuesta[i].codigoTarea]);
						respuesta[i].nombreTarea = objTarea.nombre;
						respuesta[i].fechaCreacion = new Date(respuesta[i].fechaCreacion);
					}
					$scope.tareasPendientes = respuesta;
				},
				mostrarCargador : true
			});
		};
		
		
	// INSTANCIAR 
	$scope.esInstanciado = false;
	$scope.instanciar = function(){
		$scope.InstanciarResumen();
		if($scope.$parent.$parent.modulo.activo && !$scope.esInstanciado){
			$scope.listar();
			$scope.esInstanciado = true;
		}
		$scope.ocultaListaTarea = false;
	};
	$scope.$watch('filtro.data.paginacionNro', function(newVal, oldVal){
    	if(newVal!=oldVal){
    		$scope.listar();
    	}
    });
	
	$scope.instanciar();
	
	/* MEJORA ACTUALIZACION AUTOMATICA */
	$scope.actualizarListado = {
		instanciar : function(esSoloLimpiar){
			if(esSoloLimpiar){
	    		this.reTie_contador = 0;
	    		this.reTie_segundo = 0;
	    		this.meGetMensaje();
	    		$scope.listar();
			} else {
				this.reTie_progreso(true);
				this.meTie_progreso(true);
			}
		},
		reTie_maximo : 2*60,
		reTie_progreso : function(esInstanciado){
			var _this = this;
	    	if(this.reTie_segundo<this.reTie_maximo && !esInstanciado){
	    		this.reTie_contador = Math.floor((this.reTie_segundo/this.reTie_maximo)*100);
	    		this.reTie_segundo++;
	    		$timeout( function(){ _this.reTie_progreso(); }, 1000);
	    	} else {
	    		var modActivo = $filter('filter')($scope.data.modulos, {activo: true});
	    		this.reTie_contador = 0;
	    		this.reTie_segundo = 0;
	    		this.reTie_progreso();
	    		if($scope.seccion == "Modulos" && modActivo.length>0 && modActivo[0].codigo == "tare" && $scope.codigoTareaSeleccionada == -1){
	    			$scope.listar();
	    			this.meGetMensaje();
	    		}
	    		
	    	}
		},
		meTie_maximo : 0.75*60,
		mensaje : "",
		meTie_progreso : function(esInstanciado){
			var _this = this;
	    	if(this.meTie_segundo<this.meTie_maximo && !esInstanciado){
	    		this.meTie_contador = Math.floor((this.meTie_segundo/this.meTie_maximo)*100);
	    		this.meTie_segundo++;
	    		$timeout( function(){ _this.meTie_progreso(); }, 1000);
	    	} else {
	    		this.meTie_contador = 0;
	    		this.meTie_segundo = 0;
	    		this.meTie_progreso();
	    		this.meGetMensaje();
	    	}
		},
		meGetMensaje : function(){
			var segundoActual = this.reTie_segundo;
			var segundos = segundoActual%60;
			var minutos = (segundoActual-segundos)/60;
			if(minutos>0){
				textoExt = "Minutos";
			} else {
				textoExt = "Segundos";
			}
			$scope.actualizarListado.mensaje = minutos+":"+(segundos<10?"0"+segundos:segundos)+" "+textoExt;
		}
	};

	$scope.actualizarListado.instanciar();

	$scope.cerrarResumen = function(){
		$scope.ocultaListaTarea = false;
		$scope.codigoTareaSeleccionada = -1;
		$scope.urlResumenTarea = "";
	};
	
	var w = angular.element($window);
	w.bind('resize', function () {
		if($scope.codigoTareaSeleccionada != -1){
			_calc();
		}
    });
	
	var _calc = function(){
		if($window.innerWidth >= 992){
        	$scope.ocultaListaTarea = false;
        } else {
        	$scope.ocultaListaTarea = true;
        }
	};
	
});