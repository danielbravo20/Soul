package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.jpa.ProcesoJPA;
import pe.com.soul.core.dao.jpa.TareaJPA;
import pe.com.soul.core.dao.jpa.TareaPlantillaJPA;
import pe.com.soul.core.dao.jpa.UsuarioJPA;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.Usuario;

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
		//tareaJPA.setUsuario(usuarioJPA);
		
		tareaJPA = guardar(tareaJPA);
		
		return parseTarea(tareaJPA);
	}

	@Override
	public List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception {
		
		String consulta = "select t from TareaJPA t where t.usuario.codigoUsuario =:parametro ";
    	
    	List<TareaJPA> tareasJPA = buscarRegistros(consulta, "parametro", usuario.getCodigo());
		List<Tarea> tareas = new ArrayList<Tarea>();
    	
		if(tareasJPA!=null){
			for (int x = 0; x < tareasJPA.size(); x++) {
				tareas.add(parseTarea(tareasJPA.get(x)));
			}
		}
		
		return tareas;
	}
	
	private Tarea parseTarea(TareaJPA tareaJPA){
		Tarea tarea = new Tarea();
		tarea.setCodigoTarea(tareaJPA.getCodigoTarea());
		tarea.setVersion(tareaJPA.getVersion());
		tarea.setEstado(tareaJPA.getEstado());
		tarea.setNombre(tareaJPA.getNombre());
		tarea.setAleas(tareaJPA.getAleas());
		tarea.setPrioridad(tareaJPA.getPrioridad());
		tarea.setFechaCreacion(tareaJPA.getFechaCreacion());
		tarea.setFechaReclamo(tareaJPA.getFechaReclamo());
		tarea.setFechaTermino(tareaJPA.getFechaTermino());
		tarea.setFechaUltimaModificacion(tareaJPA.getFechaUltimaModificacion());
		return tarea;
	}

}
