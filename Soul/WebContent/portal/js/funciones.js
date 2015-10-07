var util = {
	getObjeto : function(objeto, filtro, enviarTodo){
		if(typeof(enviarTodo)=="undefined"){
			enviarTodo = false;
		}
		var misObjetos = null;
		for(var i in objeto){
			var v_totales = 0;
			var v_conformes = 0;
			for(var e in filtro){
				v_totales++;
				if(objeto[i][e] && objeto[i][e] == filtro[e]){
					v_conformes++;
				}
			}
			if(v_totales == v_conformes){
				if(misObjetos == null){
					misObjetos = [];
				}
				misObjetos.push(objeto[i]);
			}
		}
		if(enviarTodo){
			return misObjetos;
		} else {
			if(misObjetos != null){
				return misObjetos[0];
			}
		}
	}
};