package pe.com.soul.core.servicio;

import pe.com.soul.core.modelo.Tarea;

public interface BaseTareaServicio {

	Tarea accionReclamar(long tkiid) throws Exception;
	Tarea accionLiberar(long tkiid) throws Exception;
	Tarea accionTrabajar(long tkiid) throws Exception;
	Tarea accionCompletar(long tkiid, Object object) throws Exception;
	Tarea accionCancelar(long tkiid, Object object) throws Exception;
	Tarea accionRechazar(long tkiid, Object object) throws Exception;
	Tarea accionObservar(long tkiid, Object object) throws Exception;
	Tarea accionTransferir(long tkiid, String nuevoUsuario) throws Exception;
}
