package pe.com.captiva.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.dao.entity.Proyecto;

/**
 * Session Bean implementation class ClaseDao
 */
@Stateless
@LocalBean
public class ProyectoDao extends BaseDao<Proyecto> implements ProyectoDaoLocal {

    /**
     * Default constructor. 
     */
    public ProyectoDao() {
        super(Proyecto.class);
    }

	@Override
	public ProyectoBean obtenerProyecto(Integer codigo) {
		return parsearProyecto(obtenerEntity(codigo));
	}
    
    private ProyectoBean parsearProyecto(Proyecto proyecto){
    	ProyectoBean proyectoBean = null;
    	if(proyecto !=null){
	    	proyectoBean = new ProyectoBean();
	    	proyectoBean.setCodProyecto(proyecto.getCodProyecto());
	    	proyectoBean.setNombre(proyecto.getNombre());
	    	proyectoBean.setJavPaquete(proyecto.getJavPaquete());
    	}
    	return proyectoBean;
    }

}
