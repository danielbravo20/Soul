package pe.com.soul.core.dao;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;

@Local
public interface TareaPlantillaDaoLocal {
	
	TareaPlantilla obtenerPrimeraTarea(Proceso proceso) throws Exception;
	
}
