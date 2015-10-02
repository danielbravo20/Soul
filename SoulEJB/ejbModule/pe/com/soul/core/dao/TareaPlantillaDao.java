package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.entities.TareaPlantillaEntity;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;

/**
 * Session Bean implementation class ProcesoDao
 */
@Stateless
@LocalBean
public class TareaPlantillaDao extends BaseDao<TareaPlantillaEntity> implements TareaPlantillaDaoLocal {

	public TareaPlantillaDao() {
		super(TareaPlantillaEntity.class);
	}

	@Override
	public TareaPlantilla obtenerPrimeraTarea(Proceso proceso) throws Exception {
		
		String consulta = "select tp from TareaPlantillaEntity tp INNER JOIN tp.procesoPlantilla pp where pp.codigoProcesoPlantilla =:parametro ORDER BY tp.orden";
    	
    	List<TareaPlantillaEntity> tareaPlantillaEntitys = buscarRegistros(consulta, "parametro", proceso.getCodigoProcesoPlantilla());
    	
    	TareaPlantilla tareaPlantilla = null;
    	
    	if(tareaPlantillaEntitys!=null){
    		TareaPlantillaEntity tareaPlantillaEntity = tareaPlantillaEntitys.get(0);
    		tareaPlantilla = new TareaPlantilla();
	    	tareaPlantilla.setAleas(tareaPlantillaEntity.getAleas());
	    	tareaPlantilla.setCodigoTareaPlantilla(tareaPlantillaEntity.getCodigoTareaPlantilla());
	    	tareaPlantilla.setEstado(tareaPlantillaEntity.getEstado());
	    	tareaPlantilla.setNombre(tareaPlantillaEntity.getNombre());
	    	tareaPlantilla.setPrioridad(tareaPlantillaEntity.getPrioridad());
	    	tareaPlantilla.setVersion(tareaPlantillaEntity.getVersion());
	    	tareaPlantilla.setOrden(tareaPlantillaEntity.getOrden());
    	}
    	
		return tareaPlantilla;
		
	}

	
	
}
