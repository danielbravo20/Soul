package pe.com.soul.core.service.portal;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.TareaDaoLocal;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;

/**
 * Session Bean implementation class TareaService
 */
@Stateless
@LocalBean
public class TareaService implements TareaServiceLocal {

	@EJB
	TareaDaoLocal tareaDaoLocal;
	
    public Tarea crearTarea(TareaPlantilla tareaPlantilla, Proceso proceso, Usuario dueno) throws Exception{
    	
    	Date fechaCreacion = new Date();
    	
    	Tarea tarea = new Tarea();
		tarea.setVersion(tareaPlantilla.getVersion());
		tarea.setEstado(Tarea.ESTADO_PENDIENTE);
		tarea.setNombre(tareaPlantilla.getNombre());
		tarea.setAleas(tareaPlantilla.getAleas());
		tarea.setPrioridad(tareaPlantilla.getPrioridad());
		tarea.setFechaCreacion(fechaCreacion);
		tarea.setFechaUltimaModificacion(fechaCreacion);
		tarea.setProceso(proceso);
		tarea.setTareaPlantilla(tareaPlantilla);
		
		if(dueno!=null){
			tarea.setDueno(dueno.getUsuario());
		}
		
    	return tareaDaoLocal.guardar(tarea);
    }

	@Override
	public List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception {
		return tareaDaoLocal.obtenerReclamadas(usuario);
	}

	@Override
	public List<Tarea> obtenerDisponibles(Usuario usuario) throws Exception {
		return tareaDaoLocal.obtenerDisponibles(usuario);
	}

}
