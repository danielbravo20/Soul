package pe.com.soul.core.service.portal;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;

@Local
public interface TareaServiceLocal {

	Tarea crearTarea(TareaPlantilla tareaPlantilla, Proceso proceso, Usuario dueno) throws Exception;
	List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception;
	
}
