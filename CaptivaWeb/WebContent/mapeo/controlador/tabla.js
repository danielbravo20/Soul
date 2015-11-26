mapeo.registerCtrl('tabla', function($scope, ajax, util, $modal) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.vista = "lista";
		$scope.cargado = { paquete : "modulo", clase : "Tabla"};
		$scope.cargado.TAB_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.TAB_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.esEdicion = false;
		
		$scope.esquemas = [];
		var miEsquema = {};
		for(var i =0;i< $scope.data.TABLA.length; i++){
			if(!miEsquema[$scope.data.TABLA[i].esquema]){
				miEsquema[$scope.data.TABLA[i].esquema] = $scope.data.TABLA[i].esquema;
				$scope.esquemas.push($scope.data.TABLA[i].esquema);
			}
		}
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar();
		} else {
			$scope.pag.total = $scope.data.TABLA.length;
		}
	};
	
	$scope.listar = function(){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.TABLA = respuesta;
			$scope.pag.total = respuesta.length;
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
		$scope.vista = "mantener";
		// Datos por defecto
			$scope.cargado.TAB_cod_tabla = util.getUltimoCodigo($scope.data.TABLA,"cod_tabla");
	};
	
	$scope.eliminar = function(cod_tabla){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.ATR_W_cod_tabla = cod_tabla;		// WHERE-------
			$scope.cargado.TAB_W_cod_tabla = cod_tabla;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_tabla){
		$scope.vista = "mantener";
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.TABLA,{cod_tabla : cod_tabla}),"TAB");
	};
		
	var _guardar = function(){
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.TAB_W_cod_tabla = $scope.cargado.TAB_cod_tabla;			// WHERE-------
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
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_TABLA.$invalid) { return; }
		if($scope.cargado.TAB_es_mantenimiento=="0"){
			$scope.cargado.TAB_flg_mantenimiento_eliminar = '0';
			$scope.borrarRoles($scope.cargado.TAB_cod_tabla,"tabla_mantenimiento_rol","cod_tabla",_guardar);
		} else {
			_guardar();
		}
	};
	
	$scope.pagAtributo = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.listarAtributos = function(cod_tabla){
		$scope.vista = "atributos";
		$scope.nombreTabla = util.getObjeto($scope.data.TABLA,{cod_tabla : cod_tabla}).nombre;
		var consulta = {
			paquete : "modulo", 
			clase : "Atributo",
			metodo : "listarSQL",
			ATR_W_cod_tabla : cod_tabla
		};
		ajax.jpo(consulta,function(respuesta){
			$scope.data.TABLA_ATRIBUTO = respuesta;
			$scope.pagAtributo.total = respuesta.length;
		});
	};
	
	$scope.cargarAtributo = function(cod_clase,cod_atributo){
		$scope.cargarTab("clase");
		$scope.getControladorScope("clase").gestionarAtributos(cod_clase);
		$scope.getControladorScope("atributo").postCargamosAtributo = true;
		$scope.getControladorScope("atributo").postAtributo = cod_atributo;
	};
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
	$scope.$watch("buscarAtributo",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pagAtributo.actual = 1;
		}
	});
	
	$scope.gestionarRoles = function(){
		$scope.asociarRoles($scope.cargado.TAB_cod_tabla,"ROLES DE MANTENIMIENTO DE LA TABLA '"+$scope.cargado.TAB_nombre+"'","tabla_mantenimiento_rol","cod_tabla");
	};
	
});