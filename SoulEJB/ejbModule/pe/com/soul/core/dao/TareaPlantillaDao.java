package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.jpa.TareaPlantillaJPA;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;

/**
 * Session Bean implementation class ProcesoDao
 */
@Stateless
@LocalBean
public class TareaPlantillaDao extends BaseDao<TareaPlantillaJPA> implements TareaPlantillaDaoLocal {

	public TareaPlantillaDao() {
		super(TareaPlantillaJPA.class);
	}

	@Override
	public TareaPlantilla obtenerPrimeraTarea(Proceso proceso) throws Exception {
		
		String consulta = "select tp from TareaPlantillaJPA tp INNER JOIN tp.procesoPlantilla pp where pp.codigoProcesoPlantilla =:parametro ORDER BY tp.orden";
    	
    	List<TareaPlantillaJPA> tareaPlantillaJPAs = buscarRegistros(consulta, "parametro", proceso.getCodigoProcesoPlantilla());
    	
    	TareaPlantilla tareaPlantilla = null;
    	
    	if(tareaPlantillaJPAs!=null){
    		TareaPlantillaJPA tareaPlantillaJPA = tareaPlantillaJPAs.get(0);
    		tareaPlantilla = new TareaPlantilla();
	    	tareaPlantilla.setAleas(tareaPlantillaJPA.getAleas());
	    	tareaPlantilla.setCodigoTareaPlantilla(tareaPlantillaJPA.getCodigoTareaPlantilla());
	    	tareaPlantilla.setEstado(tareaPlantillaJPA.getEstado());
	    	tareaPlantilla.setNombre(tareaPlantillaJPA.getNombre());
	    	tareaPlantilla.setPrioridad(tareaPlantillaJPA.getPrioridad());
	    	tareaPlantilla.setVersion(tareaPlantillaJPA.getVersion());
	    	tareaPlantilla.setOrden(tareaPlantillaJPA.getOrden());
    	}
    	
		return tareaPlantilla;
		
	}

	
	
}
