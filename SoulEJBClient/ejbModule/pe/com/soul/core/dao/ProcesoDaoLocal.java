package pe.com.soul.core.dao;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;

@Local
public interface ProcesoDaoLocal {
	
	Proceso guardar(Proceso proceso) throws Exception;
	Proceso actualizar(Proceso proceso) throws Exception;
	
}
