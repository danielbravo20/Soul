package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.entities.ProcesoEntity;
import pe.com.soul.core.dao.entities.ProcesoPlantillaEntity;
import pe.com.soul.core.dao.entities.UsuarioEntity;
import pe.com.soul.core.modelo.Proceso;

/**
 * Session Bean implementation class ProcesoDao
 */
@Stateless
@LocalBean
public class ProcesoDao extends BaseDao<ProcesoEntity> implements ProcesoDaoLocal {

	public ProcesoDao() {
		super(ProcesoEntity.class);
	}

	@Override
	public Proceso guardar(Proceso proceso) {
		
		ProcesoPlantillaEntity procesoPlantillaEntity = new ProcesoPlantillaEntity();
		procesoPlantillaEntity.setCodigoProcesoPlantilla(proceso.getCodigoProcesoPlantilla());
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setCodigoUsuario(proceso.getUsuario().getCodigo());
		
		ProcesoEntity procesoEntity = new ProcesoEntity();
		procesoEntity.setProcesoPlantilla(procesoPlantillaEntity);
		procesoEntity.setEstado(proceso.getEstado());
		procesoEntity.setAleas(proceso.getAleas());
		procesoEntity.setNombre(proceso.getNombre());
		procesoEntity.setVersion(proceso.getVersion());
		procesoEntity.setFechaCreacion(proceso.getFechaCreacion());
		procesoEntity.setFechaTermino(proceso.getFechaTermino());
		procesoEntity.setUsuario(usuarioEntity);
		
		try {
			procesoEntity = this.guardar(procesoEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		proceso.setCodigoProceso(procesoEntity.getCodigoProceso()); 
		
		return proceso;
		
	}


}
