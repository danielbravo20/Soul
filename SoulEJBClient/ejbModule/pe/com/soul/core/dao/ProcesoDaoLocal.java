package pe.com.soul.core.dao;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;

@Local
public interface ProcesoDaoLocal {
	Proceso actualizar(Proceso proceso);
	Proceso guardar(Proceso proceso);
}
