﻿mapeo.registerCtrl('maestro_documento', function($scope, ajax, util, $modal) {

	$scope.pag = {
		actual : 1,
		itemxPagina : 10,
		get : function(ind){
			return Math.floor(ind/this.itemxPagina) + 1;
		}
	};
	
	$scope.instanciar = function(listar){
		$scope.cargado = { paquete : "modulo", clase : "MaestroDocumento"};
		$scope.cargado.DOC_W_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.DOC_cod_proyecto = $scope.data.PROYECTO.cod_proyecto;
		$scope.cargado.DOC_estado = "0";
		$scope.esEdicion = false;
		
		if(typeof(listar)!="undefined" && listar == true){
			$scope.listar(function(){
				$scope.cargado.DOC_cod_mae_documento = util.getUltimoCodigo($scope.data.MAE_DOCUMENTO,"cod_mae_documento");
			});
		} else {
			$scope.cargado.DOC_cod_mae_documento = util.getUltimoCodigo($scope.data.MAE_DOCUMENTO,"cod_mae_documento");
			$scope.pag.total = $scope.data.MAE_DOCUMENTO.length;
		}
	};
	
	$scope.listar = function(call){
		$scope.cargado.metodo = "listar";
		ajax.jpo($scope.cargado,function(respuesta){
			$scope.data.MAE_DOCUMENTO = respuesta;
			$scope.pag.total = respuesta.length;
			if(call){
				call();
			}
		});
	};
	
	$scope.nuevo = function(){
		$scope.instanciar();
	};
	
	$scope.eliminar = function(cod_mae_documento){
		if(confirm("Desea eliminar el elemento seleccionado?")){
			$scope.cargado.metodo = "eliminar";
			$scope.cargado.DOC_W_cod_mae_documento = cod_mae_documento;		// WHERE-------
			ajax.jpo($scope.cargado,function(respuesta){
				$scope.agregarAlerta("success","Eliminado corréctamente");
				$scope.instanciar(true);
			});
		}
	};
	
	$scope.editarCargar = function(cod_mae_documento){
		$scope.esEdicion = true;
		var objDoc = util.getObjeto($scope.data.MAE_DOCUMENTO,{cod_mae_documento : cod_mae_documento});
		util.jpoCargar($scope.cargado,objDoc,"DOC");
		$scope.formatos = objDoc.formatos.split(",");
	};
	
	$scope.guardar = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.FRM_DOC.$invalid) { return; }
		if($scope.formatos.length==0){
			$scope.agregarAlerta("danger","Debe agregar por lo menos 1 formato");
			return;
		} else {
			$scope.cargado.DOC_formatos = $scope.formatos.join(",");
		}
		if($scope.esEdicion){
			$scope.cargado.metodo = "editar";
			$scope.cargado.DOC_W_cod_mae_documento = $scope.cargado.DOC_cod_mae_documento;			// WHERE-------
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
	
	$scope.instanciar(true);
	
	$scope.formatos = [];
	
	$scope.formatoAgregar = function(){
		for(var i = 0 ; i < $scope.formatos.length ; i++){
			if($scope.formatos[i] == "."+$scope.formatoNuevo){
				return;
			};
		}
		$scope.formatos.push("."+$scope.formatoNuevo);
		$scope.formatoNuevo = "";
	};
	
	$scope.quitarFormato = function(index){
		$scope.formatos.splice(index,1);
	};
	
});