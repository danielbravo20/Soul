var jsJPO = function(pre){
	
	this.pre = pre;
	this.condicion;
	this.data;
	this.dataMultiple;
	this.dataMultipleAdicional;
	this.dataMultiplePersonalizado;
	this.contador;
	
	this.donde = function(condicion){
		this.condicion = condicion;
	};
	this.data = function(data){
		this.data = data;
	};
	this.correlativo = function(contador){
		this.contador = contador;
	};
	
	this.registrar = function(registrar){
		this.data = angular.copy(registrar);
	};
	this.registrarMultiple = function(dataMultiple){
		this.dataMultiple = angular.copy(dataMultiple);
	};
	this.registrarMultipleAdicional = function(dataMultipleAdicional){
		this.dataMultipleAdicional = dataMultipleAdicional;
	};
	this.registrarMultiplePersonalizado = function(dataMultiplePersonalizado){
		this.dataMultiplePersonalizado = dataMultiplePersonalizado;
	};
	
	this.agregar = function(objetoSalida){
		if(this.condicion){
			for(var campo in this.condicion){
				objetoSalida[this.pre+"_W_"+campo] = this.condicion[campo];
			}
		}
		if(this.dataMultiple){debugger;
			for(var i = 0; i < this.dataMultiple.length; i++){
				if(this.dataMultiplePersonalizado){
					this.dataMultiple[i] = this.dataMultiplePersonalizado(this.dataMultiple[i], i);
				}
				if(this.contador){
					objetoSalida[this.pre+"_M_"+(i+1)+"_"+this.contador] = (i+1);
				}
				if(this.dataMultipleAdicional){
					for(var campoMA in this.dataMultipleAdicional){
						objetoSalida[this.pre+"_M_"+(i+1)+"_"+campoMA] = this.dataMultipleAdicional[campoMA];
					}
				}
				for(var campoMU in this.dataMultiple[i]){
					if(campoMU!="$$hashKey"){
						objetoSalida[this.pre+"_M_"+(i+1)+"_"+campoMU] = this.dataMultiple[i][campoMU];
					}
				}
			}
		}
		if(this.data){
			for(var campo in this.data){
				if(campo!="$$hashKey"){
					objetoSalida[this.pre+"_"+campo] = this.data[campo];
				}
			}
		}
	};
};