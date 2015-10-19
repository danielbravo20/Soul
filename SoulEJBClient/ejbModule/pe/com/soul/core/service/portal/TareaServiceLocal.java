package pe.com.soul.core.service.portal;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;

@Local
public interface TareaServiceLocal {

	Tarea crearTarea(TareaPlantilla tareaPlantilla, Proceso proceso, String dueno) throws Exception;
	
	Tarea liberar(long tkiid) throws Exception;
	Tarea reclamar(long tkiid) throws Exception;
	Tarea transferir(long tkiid, String nuevoUsuario) throws Exception;
	Tarea trabajar(long tkiid) throws Exception;
	Tarea completar(long tkiid) throws Exception;
	Tarea terminar(long tkiid) throws Exception;
	Tarea finalizar(long tkiid) throws Exception;
	
	List<Tarea> consultarTarea(long codigoProceso) throws Exception;
	List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception;
	List<Tarea> obtenerDisponibles(Usuario usuario) throws Exception;
	
}
