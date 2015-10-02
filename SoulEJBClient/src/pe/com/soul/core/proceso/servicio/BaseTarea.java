package pe.com.soul.core.proceso.servicio;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;

public interface BaseTarea {

	TareaPlantilla obtenerTareaPlantilla();
	void reclamar(Tarea tarea) throws Exception;
	void liberar(Tarea tarea) throws Exception;
	
}
