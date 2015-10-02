package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
		tareaEntity.setVersion(tarea.getVersion());
		tareaEntity.setEstado(Tarea.ESTADO_PENDIENTE);
		tareaEntity.setNombre(tarea.getNombre());
		tareaEntity.setAleas(tarea.getAleas());
		tareaEntity.setPrioridad(tarea.getPrioridad());
		tareaEntity.setFechaCreacion(tarea.getFechaCreacion());
		tareaEntity.setFechaReclamo(tarea.getFechaReclamo());
		tareaEntity.setFechaTermino(tarea.getFechaTermino());
		tareaEntity.setFechaUltimaModificacion(tarea.getFechaUltimaModificacion());
		
		ProcesoEntity procesoEntity = new ProcesoEntity();
		procesoEntity.setCodigoProceso(tarea.getProceso().getCodigoProceso());
		
		TareaPlantillaEntity tareaPlantillaEntity = new TareaPlantillaEntity();
		tareaPlantillaEntity.setCodigoTareaPlantilla(tarea.getTareaPlantilla().getCodigoTareaPlantilla());
		
		if(tarea.getDueno()!=null){
			tareaEntity.setDueno(tarea.getDueno().getUsuario());
		}
		
		tareaEntity.setProceso(procesoEntity);
		tareaEntity.setTareaPlantilla(tareaPlantillaEntity);
		
		tareaEntity = guardar(tareaEntity);
		
		return parseTarea(tareaEntity);
	}

	@Override
	public List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception {
		
		String consulta = "select t from TareaEntity t where t.dueno =:parametro ";
    	
    	List<TareaEntity> tareasEntity = buscarRegistros(consulta, "parametro", usuario.getUsuario());
		List<Tarea> tareas = new ArrayList<Tarea>();
    	
		if(tareasEntity!=null){
			for (int x = 0; x < tareasEntity.size(); x++) {
				tareas.add(parseTarea(tareasEntity.get(x)));
			}
		}
		
		return tareas;
	}
	
	private Tarea parseTarea(TareaEntity tareaEntity){
		Tarea tarea = new Tarea();
		tarea.setCodigoTarea(tareaEntity.getCodigoTarea());
		tarea.setVersion(tareaEntity.getVersion());
		tarea.setEstado(tareaEntity.getEstado());
		tarea.setNombre(tareaEntity.getNombre());
		tarea.setAleas(tareaEntity.getAleas());
		tarea.setPrioridad(tareaEntity.getPrioridad());
		tarea.setFechaCreacion(tareaEntity.getFechaCreacion());
		tarea.setFechaReclamo(tareaEntity.getFechaReclamo());
		tarea.setFechaTermino(tareaEntity.getFechaTermino());
		tarea.setFechaUltimaModificacion(tareaEntity.getFechaUltimaModificacion());
		return tarea;
	}

}
