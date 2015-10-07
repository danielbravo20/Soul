package pe.com.soul.core.proceso.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
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
	
	@Resource
    private SessionContext sessionContext;
	
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
	
	public Tarea liberar(long tkiid) throws Exception{
		Tarea tarea = tareaDaoLocal.obtener(tkiid);
		if(tarea!=null){
			if (tarea.getProceso().getEstado()==Proceso.ESTADO_EJECUTANDO) {
				if(tarea.getEstado()==Tarea.ESTADO_RECLAMADO){
					tarea.setDueno(null);
					tarea.setEstado(Tarea.ESTADO_PENDIENTE);
					tarea.setFechaReclamo(null);
					tarea.setFechaUltimaModificacion(new Date());
					tarea = tareaDaoLocal.actualizar(tarea);
				}else{
					throw new Exception("La tarea no esta reclamada...");
				}
					
			}else{
				throw new Exception("El proceso no esta en ejecución...");
			}
		}else{
			throw new Exception("La tarea no existe...");
		}
		return tarea;
	}
	
	public Tarea reclamar(long tkiid) throws Exception{
		Tarea tarea = tareaDaoLocal.obtener(tkiid);
		if(tarea!=null){
			if (tarea.getProceso().getEstado()==Proceso.ESTADO_EJECUTANDO) {
				Date fecha = new Date();
				if(tarea.getEstado()==Tarea.ESTADO_PENDIENTE){
					tarea.setDueno(sessionContext.getCallerPrincipal().getName());
					tarea.setEstado(Tarea.ESTADO_RECLAMADO);
					tarea.setFechaReclamo(fecha);
					tarea.setFechaUltimaModificacion(fecha);
					tarea = tareaDaoLocal.actualizar(tarea);
				}else{
					throw new Exception("La tarea no esta disponible...");
				}
					
			}else{
				throw new Exception("El proceso no esta en ejecución...");
			}
		}else{
			throw new Exception("La tarea no existe...");
		}
		return tarea;
	}

	@Override
	public Tarea trabajar(long tkiid) throws Exception {
		Tarea tarea = tareaDaoLocal.obtener(tkiid);
		if(tarea!=null){
			if (tarea.getProceso().getEstado()==Proceso.ESTADO_EJECUTANDO) {
				if(tarea.getEstado()==Tarea.ESTADO_PENDIENTE){
					tarea = reclamar(tkiid);
				}
				if(tarea.getEstado()==Tarea.ESTADO_RECLAMADO && tarea.getDueno().equalsIgnoreCase(sessionContext.getCallerPrincipal().getName())){
					return tarea;
				}else{
					throw new Exception("La tarea no esta reclamada...");
				}
					
			}else{
				throw new Exception("El proceso no esta en ejecución...");
			}
		}else{
			throw new Exception("La tarea no existe...");
		}
	}

	@Override
	public Tarea completar(long tkiid) throws Exception {
		Tarea tarea = tareaDaoLocal.obtener(tkiid);
		if(tarea!=null){
			if (tarea.getProceso().getEstado()==Proceso.ESTADO_EJECUTANDO) {
				if(tarea.getEstado()==Tarea.ESTADO_RECLAMADO && tarea.getDueno().equalsIgnoreCase(sessionContext.getCallerPrincipal().getName())){
					Date fecha = new Date();
					tarea.setEstado(Tarea.ESTADO_TERMINADO);
					tarea.setFechaTermino(fecha);
					tarea.setFechaUltimaModificacion(fecha);
					tarea = tareaDaoLocal.actualizar(tarea);
				}else{
					throw new Exception("La tarea no esta reclamada...");
				}
			}else{
				throw new Exception("El proceso no esta en ejecución...");
			}
		}else{
			throw new Exception("La tarea no existe...");
		}
		return tarea;
	}

}
