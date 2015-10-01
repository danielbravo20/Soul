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
		
		try {
			procesoJPA = this.guardar(procesoJPA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proceso.setCodigoProceso(procesoJPA.getCodigoProceso()); 
		
		return proceso;
		
	}


}
