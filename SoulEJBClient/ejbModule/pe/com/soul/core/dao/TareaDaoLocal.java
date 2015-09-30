package pe.com.soul.core.dao;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Tarea;

@Local
public interface TareaDaoLocal {

	Tarea guardar(Tarea tarea) throws Exception;
	
}
