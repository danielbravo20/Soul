﻿mapeo.registerCtrl('rol', function($scope, ajax, util, $modal) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", clase : "Rol"};
		$scope.cargado.ROL_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.ROL_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.ROL_estado = "0";
		$scope.esEdicion = false;
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar(function(){
				$scope.cargado.ROL_cod_rol = util.getUltimoCodigo($scope.data.ROL,"cod_rol");
			});
		} else {
			$scope.cargado.ROL_cod_rol = util.getUltimoCodigo($scope.data.ROL,"cod_rol");
			$scope.pag.total = $scope.data.ROL.length;
		}
	};
	
	$scope.listar = function(call){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.ROL = respuesta;
			$scope.pag.total = respuesta.length;
			if(call){
				call();
			}
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
	};
	
	$scope.eliminar = function(cod_rol){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.ROL_W_cod_rol = cod_rol;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_rol){
		$scope.esEdicion = true;
		util.jpoCargar($scope.cargado,util.getObjeto($scope.data.ROL,{cod_rol : cod_rol}),"ROL");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_ROL.$invalid) { return; }
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.ROL_W_cod_rol = $scope.cargado.ROL_cod_rol;			// WHERE-------
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
	
	$scope.$watch("buscar",function(oldVal,newVal){
		if(oldVal != newVal){
			$scope.pag.actual = 1;
		}
	});
	
});