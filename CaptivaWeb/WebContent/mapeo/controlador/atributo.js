mapeo.registerCtrl('atributo', function($scope, ajax, util) {

	$scope.dataType = {
		"JAVA" : {
			"Integer" : "Integer", 
			"Long" : "Long", 
			"long" : "long", 
			"String" : "String", 
			"boolean" : "boolean", 
			"java.math.BigDecimal" : "java.math.BigDecimal", 
			"java.sql.Date" : "java.sql.Date", 
			"java.sql.Timestamp" : "java.sql.Timestamp"
		},
		"DO" : {
			"Integer" : "int",
			"Long" : "long",
			"long" : "long",
			"String" : "String",
			"boolean" : "boolean", 
			"java.math.BigDecimal" : "BigDecimal",
			"java.sql.Date" : "date"
		},
		"SQL" : {
			"Integer" : "INTEGER",
			"Long" : "BIGINT",
			"long" : "BIGINT",
			"String" : "VARCHAR",
			"boolean" : "CHAR",
			"java.math.BigDecimal" : "DECIMAL",
			"java.sql.Date" : "DATE",
			"java.sql.Timestamp" : "TIMESTAMP"
		}
	};

	$scope.tipoDatos = [];
	
	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar,clasePadre){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Atributo"};
		$scope.cargado.esDetallado = true;
		$scope.esEdicion = false;
		if(clasePadre){
			$scope.clasePadre = clasePadre;
		}
		if($scope.clasePadre){
			$scope.cargado.ATR_W_cod_clase = $scope.clasePadre.cod_clase;
			$scope.cargado.ATR_cod_clase = $scope.clasePadre.cod_clase;
		}
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.ATRIBUTO.length;
		}
		
		// CARGAR TIPO DE DATOS
		$scope.tipoDatos = [];
		for(var i in $scope.data.CLASE){
			$scope.tipoDatos.push($scope.data.CLASE[i].nombre);
		}
		for(var i in $scope.dataType.JAVA){
			$scope.tipoDatos.push($scope.dataType.JAVA[i]);
		}
		
		$scope.cargado.FLG_sqlDesabilitado = true;
		
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.ATRIBUTO = respuesta;
			$scope.pag.total = respuesta.length;
			if($scope.postCargamosAtributo){
				$scope.editarCargar($scope.postAtributo);
				delete $scope.postCargamosAtributo;
				delete $scope.postAtributo;
			}
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
		var ultimo = 0;
		for(var i in $scope.data.ATRIBUTO){
			if($scope.data.ATRIBUTO[i].cod_atributo>ultimo){
				ultimo = $scope.data.ATRIBUTO[i].cod_atributo;
			}
		}
		$scope.cargado.ATR_cod_atributo = ultimo+1;
	};
	
	$scope.eliminar = function(cod_atributo){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.ATR_W_cod_atributo = cod_atributo;		// WHERE-------
			$scope.cargado.ADB_W_cod_atributo = cod_atributo;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_atributo){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		$scope.cargado.metodo = "editarCargar";
		$scope.cargado.ATR_W_cod_atributo = cod_atributo;	// WHERE-------
		$scope.cargado.ADB_W_cod_atributo = cod_atributo;	// WHERE-------
		$scope.cargado.ADB_cod_atributo = cod_atributo;		// WHERE-------
		ajax.jpo($scope.cargado,function(respuesta){
			util.jpoCargar($scope.cargado,respuesta.ATRIBUTO,"ATR");
			$scope.cargado.FLG_sql = false;
			$scope.cargado.FLG_sqlEsEdicion = false;
			if(Object.size(respuesta.ATRIBUTO_SQL)>0){
				util.jpoCargar($scope.cargado,respuesta.ATRIBUTO_SQL,"ADB");
				$scope.cargado.FLG_sql = true;
				$scope.cargado.FLG_sqlEsEdicion = true;
			}
		});
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_ATRIBUTO.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Editado corréctamente");
				$scope.instanciar(true);
			});
		} else {
			$scope.cargado.metodo = "registrar";
			if($scope.cargado.FLG_sql){
				$scope.cargado.ADB_cod_atributo = $scope.cargado.ATR_cod_atributo;
			}
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Creado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.formatearNombres = function(){
		var formatos = util.getClases($scope.cargado.ATR_inf_nombre);
		$scope.cargado.ATR_nombre = formatos[0];
		$scope.cargado.ADB_campo = formatos[2];
	};
	
	$scope.$watch("cargado.ATR_tipo",function(oldVal,newVal){
		if(oldVal != newVal){
			var tipo = oldVal;
			if($scope.dataType.JAVA[tipo]){
				$scope.cargado.FLG_sqlDesabilitado = false;
			} else {
				$scope.cargado.FLG_sqlDesabilitado = true;
				$scope.cargado.FLG_sql = false;
			}
			if($scope.dataType.SQL[tipo]){
				$scope.cargado.ADB_tipo = $scope.dataType.SQL[tipo];
			} else {
				$scope.cargado.ADB_tipo = "";
			}
		}
	});
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.getDO = function(COD_OBJ_BPM){
		if(COD_OBJ_BPM){
			return util.getObjeto($scope.data.OBJ_BPM,{COD_OBJ_BPM : COD_OBJ_BPM}).nombre;
		} else {
			return "";
		}
	};
		
	$scope.cargarAtributos = function(){
		var consulta = {
			paquete : "modulo", 
			clase : "Atributo",
			metodo : "listarSQL",
			ATR_W_cod_tabla : $scope.cargado.ADB_fk_tabla,
			ATR_W_pk		: "1"
		};
		ajax.jpo(consulta,function(respuesta){
			$scope.atributosFK = respuesta;
		});
	};
	
});