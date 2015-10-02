package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.soul.core.dao.entities.ProcesoEntity;
import pe.com.soul.core.dao.entities.TareaEntity;
import pe.com.soul.core.dao.entities.TareaPlantillaEntity;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.Usuario;

/**
 * Session Bean implementation class TareaDao
 */
@Stateless
@LocalBean
public class TareaDao extends BaseDao<TareaEntity> implements TareaDaoLocal {

	public TareaDao() {
		super(TareaEntity.class);
	}

	@Override
	public Tarea guardar(Tarea tarea) throws Exception {
		
		TareaEntity tareaEntity = new TareaEntity();
		tareaEntity.setVersionTarea(tarea.getVersion());
		tareaEntity.setNombreTarea(tarea.getNombre());
		tareaEntity.setAleasTarea(tarea.getAleas());
		tareaEntity.setPrioridadTarea(tarea.getPrioridad());
		tareaEntity.setFechaCreacionTarea(tarea.getFechaCreacion());
		tareaEntity.setFechaReclamoTarea(tarea.getFechaReclamo());
		tareaEntity.setFechaTerminoTarea(tarea.getFechaTermino());
		tareaEntity.setFechaUltimaModificacionTarea(tarea.getFechaUltimaModificacion());
		
		ProcesoEntity procesoEntity = new ProcesoEntity();
		procesoEntity.setCodigoProceso(tarea.getProceso().getCodigoProceso());
		
		TareaPlantillaEntity tareaPlantillaEntity = new TareaPlantillaEntity();
		tareaPlantillaEntity.setCodigoTareaPlantilla(tarea.getTareaPlantilla().getCodigoTareaPlantilla());
		
		if(tarea.getDueno()!=null){
			tareaEntity.setDuenoTarea(tarea.getDueno());
			tareaEntity.setEstadoTarea(Tarea.ESTADO_RECLAMADO);
			tareaEntity.setFechaReclamoTarea(tarea.getFechaUltimaModificacion());
		}else{
			tareaEntity.setEstadoTarea(Tarea.ESTADO_PENDIENTE);
		}
		
		tareaEntity.setProceso(procesoEntity);
		tareaEntity.setTareaPlantilla(tareaPlantillaEntity);
		
		tareaEntity = guardar(tareaEntity);
		
		return parseTarea(tareaEntity);
	}

	@Override
	public List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception {
		
		String consulta = "select t from TareaEntity t where t.duenoTarea =:parametro ";
    	
    	List<TareaEntity> tareasEntity = buscarRegistros(consulta, "parametro", usuario.getUsuario());
		List<Tarea> tareas = new ArrayList<Tarea>();
    	
		if(tareasEntity!=null){
			for (int x = 0; x < tareasEntity.size(); x++) {
				tareas.add(parseTarea(tareasEntity.get(x)));
			}
		}
		
		return tareas;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Tarea> obtenerDisponibles(Usuario usuario) throws Exception {
		
		String consulta = "select new pe.com.soul.core.modelo.Tarea(t.id.codigoTarea, t.id.codigoProceso, t.id.codigoTareaPlantilla, t.id.estadoTarea, t.id.nombreTarea, t.id.aleasTarea, t.id.versionTarea, t.id.prioridadTarea, t.id.fechaCreacionTarea, t.id.fechaReclamoTarea, t.id.fechaTerminoTarea, t.id.fechaUltimaModificacionTarea, t.id.duenoTarea, t.id.codigoProcesoPlantilla, t.id.estadoProceso, t.id.nombreProceso, t.id.aleasProceso, t.id.versionProceso, t.id.fechaCreacionProceso, t.id.fechaTerminoProceso, t.id.usuarioCreacionProceso) from pe.com.soul.core.dao.entities.TareaPotencialDuenoEntity t where t.id.duenoTarea =:parametro ";
		
		Query query = em.createQuery(consulta);
		query.setParameter("parametro", usuario.getUsuario());
		
		return query.getResultList();
		
	}
	
	private Tarea parseTarea(TareaEntity tareaEntity){
		Tarea tarea = new Tarea();
		tarea.setCodigoTarea(tareaEntity.getCodigoTarea());
		tarea.setVersion(tareaEntity.getVersionTarea());
		tarea.setEstado(tareaEntity.getEstadoTarea());
		tarea.setNombre(tareaEntity.getNombreTarea());
		tarea.setAleas(tareaEntity.getAleasTarea());
		tarea.setDueno(tareaEntity.getDuenoTarea());
		tarea.setPrioridad(tareaEntity.getPrioridadTarea());
		tarea.setFechaCreacion(tareaEntity.getFechaCreacionTarea());
		tarea.setFechaReclamo(tareaEntity.getFechaReclamoTarea());
		tarea.setFechaTermino(tareaEntity.getFechaTerminoTarea());
		tarea.setFechaUltimaModificacion(tareaEntity.getFechaUltimaModificacionTarea());
		return tarea;
	}

}
