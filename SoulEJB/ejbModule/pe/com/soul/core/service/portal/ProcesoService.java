package pe.com.soul.core.service.portal;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.ProcesoDaoLocal;
import pe.com.soul.core.dao.TareaDaoLocal;
import pe.com.soul.core.dao.TareaPlantillaDaoLocal;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;

/**
 * Session Bean implementation class ProcesoService
 */
@Stateless
@LocalBean
public class ProcesoService implements ProcesoServiceLocal {

	@EJB
	ProcesoDaoLocal procesoDaoLocal;
	
	@EJB
	TareaPlantillaDaoLocal tareaPlantillaDaoLocal;
	
	@EJB
	TareaDaoLocal tareaDaoLocal;  
	
	@Override
	public Proceso crear(Proceso proceso) throws Exception {
		
		Date fecha = new Date();
		
		proceso.setFechaCreacion(fecha);
		proceso.setEstado(Proceso.ESTADO_EJECUTANDO);
		
		TareaPlantilla tareaPlantilla = tareaPlantillaDaoLocal.obtenerPrimeraTarea(proceso);
		Tarea tarea = new Tarea();
		tarea.setVersion(tareaPlantilla.getVersion());
		tarea.setEstado(Tarea.ESTADO_PENDIENTE);
		tarea.setNombre(tareaPlantilla.getNombre());
		tarea.setAleas(tareaPlantilla.getAleas());
		tarea.setPrioridad(tareaPlantilla.getPrioridad());
		tarea.setFechaCreacion(fecha);
		tarea.setFechaUltimaModificacion(fecha);
		tarea.setProceso(proceso);
		tarea.setTareaPlantilla(tareaPlantilla);
		proceso.setTareaInicial(tarea);
		
		proceso = procesoDaoLocal.guardar(proceso);
		//tarea = tareaDaoLocal.guardar(tarea);
		//proceso.setPrimeraTarea(tarea);
		
		return proceso;
	}

}
