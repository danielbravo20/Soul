package pe.com.soul.core.servicio;

import pe.com.soul.core.modelo.Tarea;

public interface BaseTareaServicio {

	Tarea accionReclamar(long tkiid) throws Exception;
	Tarea accionLiberar(long tkiid) throws Exception;
	Tarea accionTrabajar(long tkiid) throws Exception;
	Tarea accionCompletar(long tkiid, Object object) throws Exception;
	void accionCancelar(Tarea tarea) throws Exception;
	void accionRechazar(Tarea tarea) throws Exception;
	void accionObservar(Tarea tarea) throws Exception;
	
}
