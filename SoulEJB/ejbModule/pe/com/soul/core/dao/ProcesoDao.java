package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.jpa.ProcesoJPA;
import pe.com.soul.core.dao.jpa.ProcesoPlantillaJPA;
import pe.com.soul.core.dao.jpa.UsuarioJPA;
import pe.com.soul.core.modelo.Proceso;

/**
 * Session Bean implementation class ProcesoDao
 */
@Stateless
@LocalBean
public class ProcesoDao extends BaseDao<ProcesoJPA> implements ProcesoDaoLocal {

	public ProcesoDao() {
		super(ProcesoJPA.class);
	}

	@Override
	public Proceso guardar(Proceso proceso) {
		
		ProcesoPlantillaJPA procesoPlantillaJPA = new ProcesoPlantillaJPA();
		procesoPlantillaJPA.setCodigoProcesoPlantilla(proceso.getCodigoProcesoPlantilla());
		
		UsuarioJPA usuarioJPA = new UsuarioJPA();
		usuarioJPA.setCodigoUsuario(proceso.getUsuario().getCodigo());
		
		ProcesoJPA procesoJPA = new ProcesoJPA();
		procesoJPA.setProcesoPlantilla(procesoPlantillaJPA);
		procesoJPA.setEstado(proceso.getEstado());
		procesoJPA.setAleas(proceso.getAleas());
		procesoJPA.setNombre(proceso.getNombre());
		procesoJPA.setVersion(proceso.getVersion());
		procesoJPA.setFechaCreacion(proceso.getFechaCreacion());
		procesoJPA.setFechaTermino(proceso.getFechaTermino());
		procesoJPA.setUsuario(usuarioJPA);
		/*
		Tarea tarea = proceso.getTareaInicial();
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
		
		TareaPlantillaJPA tareaPlantillaJPA = new TareaPlantillaJPA();
		tareaPlantillaJPA.setCodigoTareaPlantilla(tarea.getTareaPlantilla().getCodigoTareaPlantilla());
		
		if(tarea.getDueno()!=null){
			usuarioJPA.setCodigoUsuario(tarea.getDueno().getCodigo());
		}
		
		tareaJPA.setProceso(procesoJPA);
		tareaJPA.setTareaPlantilla(tareaPlantillaJPA);
		tareaJPA.setUsuario(usuarioJPA);
		
		Set<TareaJPA> tareaJPAs = new HashSet<TareaJPA>();
		tareaJPAs.add(tareaJPA);
		procesoJPA.setTareas(tareaJPAs);
		*/
		procesoJPA = this.guardar(procesoJPA);
		proceso.setCodigoProceso(procesoJPA.getCodigoProceso()); 
		
		return proceso;
		
	}


}
