package pe.com.captiva.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.dao.entity.Atributo;
import pe.com.captiva.dao.entity.Clase;
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
	    	proyectoBean.setProyecto(proyecto.getProyecto());
	    	proyectoBean.setPaquete(proyecto.getPaquete());
	    	
	    	Set<Clase> claseSet = proyecto.getClases();
	    	if(claseSet!=null){
	    		List<ClaseBean> clases = new ArrayList<ClaseBean>();
		    	Iterator<Clase> claseIterator = claseSet.iterator();
		    	while (claseIterator.hasNext()) {
					clases.add(parseClaseBean((Clase) claseIterator.next()));
				}
		    	proyectoBean.setClases(clases);
	    	}
	    	
    	}
    	return proyectoBean;
    }
    
    private ClaseBean parseClaseBean(Clase clase){
    	ClaseBean claseBean = null;
    	if(clase!=null){
    		claseBean = new ClaseBean();
    		claseBean.setCodigoClase(clase.getCodClase());
    		claseBean.setNombre(clase.getNombre());
    		claseBean.setNivel(clase.getNivel());
    		
    		Set<Atributo> atributoSet = clase.getAtributos();
    		if(atributoSet!=null){
    			List<AtributoBean> atributos = new ArrayList<AtributoBean>();
    			Iterator<Atributo> atributoIterator = atributoSet.iterator();
    			while (atributoIterator.hasNext()) {
					atributos.add(parseAtributoBean( (Atributo) atributoIterator.next()));
				}
    			claseBean.setAtributos(atributos);
    		}
    	}
    	return claseBean;
    }
    
    private AtributoBean parseAtributoBean(Atributo atributo){
    	AtributoBean atributoBean = null;
    	if(atributo!=null){
    		atributoBean = new AtributoBean();
    		atributoBean.setCodigo(atributo.getCodAtributo());
    		atributoBean.setNombre(atributo.getNombre());
    		if(atributo.getFlgLista()=='1'){
    			atributoBean.setFlgLista(true);
    		}
    		atributoBean.setTipo(atributo.getTipo());
    		atributoBean.setWebNombre(atributo.getWebNombre());
    	}
    	return atributoBean;
    }

}
