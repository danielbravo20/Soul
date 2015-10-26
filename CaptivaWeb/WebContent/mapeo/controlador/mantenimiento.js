mapeo.registerCtrl('mantenimiento', function($scope, ajax, util) {

	$scope.atributos = [];
	$scope.vista = "mantener"; // lista - mantener
	
	$scope.tipoDatos = [
	    {	id : "long"			, valor : "long - BIGINT"},
		{	id : "String"		, valor : "String - VARCHAR"},
		{	id : "BigDecimal"	, valor : "java.math.BigDecimal - DECIMAL"},
		{	id : "double"		, valor : "double - DECIMAL"},
		{	id : "Date"			, valor : "Date - DATE"},
		{	id : "boolean"		, valor : "boolean - CHAR"},
		{	id : "Timestamp"	, valor : "java.sql.Timestamp - TIMESTAMP"},
		{	id : "int"			, valor : "int - INTEGER"}
	];
	
	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.esEdicion = false;
		$scope.cargado = { paquete : "modulo", clase : "Mantenimiento"};
		$scope.cargado["MAN_W_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado["MRO_W_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado["AMA_W_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado["MAN_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado["MRO_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado["AMA_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
		$scope.cargado.MRO_COD_ROLES = [];
		$scope.atributos = [];
		$scope.listar();
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.MANTENIMIENTO = respuesta.MANTENIMIENTO;
			$scope.MANTENIMIENTO_ROL = respuesta.MANTENIMIENTO_ROL;
			
			for(var i = 0; i<$scope.data.MANTENIMIENTO.length; i++){
				for(var e = 0; e<$scope.MANTENIMIENTO_ROL.length; e++){
					if($scope.MANTENIMIENTO_ROL[e].COD_MANTENIMIENTO == $scope.data.MANTENIMIENTO[i].COD_MANTENIMIENTO){
						if(!$scope.data.MANTENIMIENTO[i].ROLES){
							$scope.data.MANTENIMIENTO[i].ROLES = [];
						}
						$scope.data.MANTENIMIENTO[i].ROLES.push($scope.MANTENIMIENTO_ROL[e].COD_ROL);
					}
				}
			}
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.getRolxCodMan = function(COD_MANTENIMIENTO){
		var roles = "";
		for(var i = 0; i<$scope.MANTENIMIENTO_ROL.length; i++){
			if($scope.MANTENIMIENTO_ROL[i].COD_MANTENIMIENTO == COD_MANTENIMIENTO){
				roles += $scope.MANTENIMIENTO_ROL[i].COD_ROL+"<br>";
			}
		}
		return roles;
	};
	
	$scope.eliminar = function(COD_MANTENIMIENTO){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado["MAN_W_COD_MANTENIMIENTO"] = COD_MANTENIMIENTO;
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.nuevo = function(index){
		$scope.instanciar();
		$scope.vista = "mantener";
	};
	
	$scope.editarCargar = function(COD_MANTENIMIENTO){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		
		$scope.cargado.metodo = "editarCargar";
		
		$scope.cargado["MAN_W_COD_MANTENIMIENTO"] = COD_MANTENIMIENTO;
		$scope.cargado["MRO_W_COD_MANTENIMIENTO"] = COD_MANTENIMIENTO;
		$scope.cargado["AMA_W_COD_MANTENIMIENTO"] = COD_MANTENIMIENTO;
		
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.cargado.MAN_COD_MANTENIMIENTO = COD_MANTENIMIENTO;
			$scope.cargado.MAN_NOMBRE = respuesta.MANTENIMIENTO.NOMBRE;
			$scope.cargado.MAN_DESCRIPCION = respuesta.MANTENIMIENTO.DESCRIPCION;
			$scope.cargado.MAN_COD_ESQUEMA = respuesta.MANTENIMIENTO.COD_ESQUEMA;
			$scope.cargado.MAN_COD_DATASOURCE =  respuesta.MANTENIMIENTO.COD_DATASOURCE;
			
			for(var i = 0; i<respuesta.MANTENIMIENTO_ROL.length; i++){
				$scope.cargado.MRO_COD_ROLES.push(respuesta.MANTENIMIENTO_ROL[i].COD_ROL);
			}
			
			$scope.atributos = respuesta.MANTENIMIENTO_ATRIBUTO;
		});
		
	};
	
	var validar = function(){
		
		if($scope.cargado.MRO_COD_ROLES.length==0){
			$scope.agregarAlerta("danger","Debe ingresar uno o más roles");
			return false;
		}
		
		if($scope.atributos.length==0){
			$scope.agregarAlerta("danger","Debe ingresar uno o más atributos");
			return false;
		}
		
		var reglasAtributos = [];
		var tieneLLave = 0;
		for(var i = 0; i<$scope.atributos.length; i++){
			var tipoDato = $scope.atributos[i].TIPO_DATO;
			if(tipoDato == 'long' || tipoDato == 'Date' || tipoDato == 'int' || tipoDato == 'Timestamp'){
			} else {
				if(!$scope.atributos[i].LONGITUD>0){
					$scope.agregarAlerta("danger","Ingrese una longitud correcta a la fila "+(Number(i)+1));
					return false;
				}
				if(tipoDato == 'BigDecimal' || tipoDato == 'double'){
					if(!$scope.atributos[i].PRECISION>0){
						$scope.agregarAlerta("danger","Ingrese una precision correcta a la fila "+(Number(i)+1));
						return false;
					}
				}
			}
			if($scope.atributos[i].ES_LLAVE_PRIMARIA == true){
				tieneLLave++;
			}
		}
		if(tieneLLave == 0){
			$scope.agregarAlerta("danger","Debe agregar por lo menos una llave primaria");
			return false;
		}

		return true;
	};
	
	var cargarEnvio = function(){
		for(var i = 0; i<$scope.atributos.length; i++){
			var atri = $scope.atributos[i];
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_COD_MANTENIMIENTO"] = $scope.cargado.MAN_COD_MANTENIMIENTO;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_COD_ATRIBUTO"] = (Number(i)+1);
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_NOMBRE"] = atri.NOMBRE;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_DESCRIPCION"] = atri.DESCRIPCION;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_TIPO_DATO"] = atri.TIPO_DATO;
			if(atri.LONGITUD){
				$scope.cargado["AMA_M_"+(Number(i)+1)+"_LONGITUD"] = atri.LONGITUD;
			}
			if(atri.PRECISION){
				$scope.cargado["AMA_M_"+(Number(i)+1)+"_PRECISION"] = atri.PRECISION;
			}
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_ES_LLAVE_PRIMARIA"] = atri.ES_LLAVE_PRIMARIA;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_ES_LISTADO"] = atri.ES_LISTADO;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_ES_BUSQUEDA"] = atri.ES_BUSQUEDA;
			$scope.cargado["AMA_M_"+(Number(i)+1)+"_ES_OBLIGATORIO"] = atri.ES_OBLIGATORIO;
		}
		for(var i = 0; i<$scope.cargado.MRO_COD_ROLES.length; i++){
			var rol = $scope.cargado.MRO_COD_ROLES[i];
			$scope.cargado["MRO_M_"+(Number(i)+1)+"_COD_PROYECTO"] = $scope.data.PROYECTO.COD_PROYECTO;
			$scope.cargado["MRO_M_"+(Number(i)+1)+"_COD_MANTENIMIENTO"] = $scope.cargado.MAN_COD_MANTENIMIENTO;
			$scope.cargado["MRO_M_"+(Number(i)+1)+"_COD_ROL"] = rol;
		}
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_MANTENIMIENTO.$invalid) { return; }
		if(validar()){
			cargarEnvio();
			if($scope.esEdicion){
				$scope.cargado.metodo = "editar";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Editado corréctamente");
					$scope.instanciar(true);
				});
			} else {
				$scope.cargado.metodo = "registrar";
				ajax.jpo($scope.cargado,function(respuesta){
					$scope.agregarAlerta("success","Creado corréctamente");
					$scope.instanciar(true);
				});
			}
		}
	};
	
	/* ATRIBUTOS */
	$scope.atributoAgregar = function(){
		$scope.atributos.push({});
	};
	
	$scope.atributoEliminar = function($index){
		$scope.atributos.splice($index,1);
	};
	
	$scope.atributoSubir = function($index){
		if($index>0){
			$scope.atributos.move($index-1,$index);
		}
	};
	
	$scope.atributoBajar = function($index){
		if($index<$scope.atributos.length-1){
			$scope.atributos.move($index,$index+1);
		}
	};
	
	$scope.atributoCopiar = function($index){
		$scope.atributos.push(angular.copy($scope.atributos[$index]));
	};
	
	$scope.atributoValidarLLave = function($index){
		if($scope.atributos[$index].esLlavePrimaria == true){
			$scope.atributos[$index].esObligatorio = true;
		}
	};
	
	$scope.atributoValidarBusqueda = function($index){
		if($scope.atributos[$index].esBusqueda == true){
			$scope.atributos[$index].esListado = true;
		}
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.formatearCodigo = function(){
		var formatos = util.getClases($scope.cargado.MAN_NOMBRE);
		$scope.cargado.MAN_COD_MANTENIMIENTO = formatos[0];
	};
	
});