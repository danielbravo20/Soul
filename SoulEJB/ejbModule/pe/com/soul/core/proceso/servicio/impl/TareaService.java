package pe.com.soul.core.proceso.servicio.impl;

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
import pe.com.soul.core.service.portal.TareaServiceLocal;

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
	
	public void liberar(Tarea tarea, Usuario usuario) throws Exception{
		tarea = tareaDaoLocal.obtener(tarea.getCodigoTarea());
		if (tarea.getProceso().getEstado()==Proceso.ESTADO_EJECUTANDO) {
			if(tarea.getEstado()==Tarea.ESTADO_RECLAMADO){
				tarea.setDueno(null);
				tarea.setEstado(Tarea.ESTADO_PENDIENTE);
				tarea.setFechaReclamo(null);
				tarea.setFechaUltimaModificacion(new Date());
				tareaDaoLocal.actualizar(tarea);
			}else{
				throw new Exception("La tarea no esta reclamada...");
			}
				
		}else{
			throw new Exception("El proceso no esta en ejecución...");
		}
		
	}

}
