package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.jpa.ProcesoJPA;
import pe.com.soul.core.dao.jpa.TareaJPA;
import pe.com.soul.core.dao.jpa.TareaPlantillaJPA;
import pe.com.soul.core.dao.jpa.UsuarioJPA;
import pe.com.soul.core.modelo.Tarea;

/**
 * Session Bean implementation class TareaDao
 */
@Stateless
@LocalBean
public class TareaDao extends BaseDao<TareaJPA> implements TareaDaoLocal {

	public TareaDao() {
		super(TareaJPA.class);
	}

	@Override
	public Tarea guardar(Tarea tarea) throws Exception {
		
		TareaJPA tareaJPA = new TareaJPA();
		tareaJPA.setVersion(tarea.getVersion());
		tareaJPA.setEstado(Tarea.ESTADO_PENDIENTE);
		tareaJPA.setNombre(tarea.getNombre());
		tareaJPA.setAleas(tarea.getAleas());
		tareaJPA.setPrioridad(tarea.getPrioridad());
		tareaJPA.setFechaCreacion(tarea.getFechaCreacion());
		tareaJPA.setFechaReclamo(tarea.getFechaReclamo());
		tareaJPA.setFechaTermino(tarea.getFechaTermino());
		tareaJPA.setFechaUltimaModificacion(tarea.getFechaUltimaModificacion());
		
		ProcesoJPA procesoJPA = new ProcesoJPA();
		procesoJPA.setCodigoProceso(tarea.getProceso().getCodigoProceso());
		
		TareaPlantillaJPA tareaPlantillaJPA = new TareaPlantillaJPA();
		tareaPlantillaJPA.setCodigoTareaPlantilla(tarea.getTareaPlantilla().getCodigoTareaPlantilla());
		
		UsuarioJPA usuarioJPA = new UsuarioJPA();
		if(tarea.getDueno()!=null){
			usuarioJPA.setCodigoUsuario(tarea.getDueno().getCodigo());
		}
		
		tareaJPA.setProceso(procesoJPA);
		tareaJPA.setTareaPlantilla(tareaPlantillaJPA);
		tareaJPA.setUsuario(usuarioJPA);
		
		tareaJPA = guardar(tareaJPA);
		tarea.setCodigoTarea(tareaJPA.getCodigoTarea());
		
		return tarea;
	}

}
