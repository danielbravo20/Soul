package pe.com.soul.core.servicio;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;

public interface BaseTareaServicio {

	TareaPlantilla obtenerTareaPlantilla();
	void accionReclamar(Tarea tarea, Usuario usuario) throws Exception;
	void accionLiberar(Tarea tarea, Usuario usuario) throws Exception;
	Object accionTrabajar(Tarea tarea) throws Exception;
	void accionCompletar(Tarea tarea) throws Exception;
	void accionCancelar(Tarea tarea) throws Exception;
	void accionRechazar(Tarea tarea) throws Exception;
	void accionObservar(Tarea tarea) throws Exception;
	
}
