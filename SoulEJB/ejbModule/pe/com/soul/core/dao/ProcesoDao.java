package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.entities.ProcesoEntity;
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
	public Proceso guardar(Proceso proceso) throws Exception {
		
		ProcesoEntity procesoEntity = new ProcesoEntity();
		procesoEntity.setCodigoProcesoPlantilla(proceso.getCodigoProcesoPlantilla());
		procesoEntity.setEstadoProceso(proceso.getEstado());
		procesoEntity.setAleasProceso(proceso.getAleas());
		procesoEntity.setNombreProceso(proceso.getNombre());
		procesoEntity.setVersionProceso(proceso.getVersion());
		procesoEntity.setFechaCreacionProceso(proceso.getFechaCreacion());
		procesoEntity.setFechaTerminoProceso(proceso.getFechaTermino());
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUsuario(proceso.getCreador());
		
		procesoEntity.setUsuario(usuarioEntity);
		
		procesoEntity = this.guardarEntity(procesoEntity);
		proceso.setCodigoProceso(procesoEntity.getCodigoProceso()); 
		
		return proceso;
		
	}
	
	@Override
	public Proceso actualizar(Proceso proceso) throws Exception {
		String consulta = "select p from ProcesoEntity p where p.codigoProceso =:parametro ";
    	
		ProcesoEntity procesoEntity = buscarRegistro(consulta, "parametro", proceso.getCodigoProceso());
		proceso.setEstado(proceso.getEstado());
		proceso.setFechaTermino(proceso.getFechaTermino());
		procesoEntity = this.actualizarEntity(procesoEntity);
		
		return proceso;
		
	}


}
