package pe.com.captiva.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.RolBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.dao.entity.Atributo;
import pe.com.captiva.dao.entity.Clase;
import pe.com.captiva.dao.entity.Proceso;
import pe.com.captiva.dao.entity.ProcesoInicio;
import pe.com.captiva.dao.entity.Proyecto;
import pe.com.captiva.dao.entity.Rol;
import pe.com.captiva.dao.entity.Tarea;

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
		    		ClaseBean claseBean = parseClaseBean((Clase) claseIterator.next());
					clases.add(claseBean);
					if(claseBean.getNivel()!=null && claseBean.getNivel()==1){
						proyectoBean.setClasePadre(claseBean);
					}
				}
		    	proyectoBean.setClases(clases);
	    	}
	    	
	    	Set<Proceso> procesoSet = proyecto.getProcesos();
	    	if (procesoSet!=null) {
				List<ProcesoBean> procesos = new ArrayList<ProcesoBean>();
				Iterator<Proceso> procesoIterator = procesoSet.iterator();
				while (procesoIterator.hasNext()) {
					procesos.add(parseProcesoBean((Proceso)procesoIterator.next()));
				}
				proyectoBean.setProcesos(procesos);
			}
	    	
    	}
    	return proyectoBean;
    }
    
    private ProcesoBean parseProcesoBean(Proceso proceso){
    	ProcesoBean procesoBean = null;
    	if(proceso!=null){
    		procesoBean = new ProcesoBean();
    		procesoBean.setCodigo(proceso.getCodProceso());
    		procesoBean.setNombre(proceso.getInfNombre());
    		procesoBean.setClase(proceso.getJavClase());
    		
    		Set<Rol> rolSet = proceso.getRols();
    		Iterator<Rol> rolIterator = rolSet.iterator();
    		List<RolBean> rolesPotencial = new ArrayList<RolBean>();
    		while (rolIterator.hasNext()) {
				rolesPotencial.add(parseRolBean((Rol) rolIterator.next()));
			}
    		procesoBean.setRolPotencial(rolesPotencial);
    		
    		Set<Tarea> tareaSet = proceso.getTareas();
    		Iterator<Tarea> tareaIterator = tareaSet.iterator();
    		List<TareaBean> tareasBeans = new ArrayList<TareaBean>();
    		while (tareaIterator.hasNext()) {
				tareasBeans.add(parseTareaBean((Tarea) tareaIterator.next()));
			}
    		procesoBean.setTareas(tareasBeans);
    		
    		Set<ProcesoInicio> procesoInicioSet = proceso.getProcesoInicios();
    		Iterator<ProcesoInicio> procesoInicioIterator = procesoInicioSet.iterator();
    		List<AtributoProceso> atributoProcesoBeans = new ArrayList<AtributoProceso>();
    		while (procesoInicioIterator.hasNext()) {
    			atributoProcesoBeans.add(parseAtributoProcesoBean((ProcesoInicio) procesoInicioIterator.next()));
			}
    		procesoBean.setAtributosEntrada(atributoProcesoBeans);
    	}
    	return procesoBean;
    }
    
    private AtributoProceso parseAtributoProcesoBean(ProcesoInicio procesoInicio){
    	AtributoProceso atributoProceso = null;
		if(procesoInicio!=null){
			atributoProceso = new AtributoProceso();
			atributoProceso.setWebFlgValidacion('1'==procesoInicio.getWebFlgValidacion());
			atributoProceso.setNombre(procesoInicio.getAtributo().getNombre());
			atributoProceso.setTipo(procesoInicio.getAtributo().getTipo());
			atributoProceso.setWebNombre(procesoInicio.getAtributo().getWebNombre());
			atributoProceso.setWebValorOmision(procesoInicio.getWebValOmision());
			atributoProceso.setClase(parseClaseBeanSimple(procesoInicio.getAtributo().getClase()));
		}
		return atributoProceso;
    }
    
    private ClaseBean parseClaseBeanSimple(Clase clase){
    	ClaseBean claseBean = null;
    	if(clase!=null){
    		claseBean = new ClaseBean();
    		claseBean.setCodigoClase(clase.getCodClase());
    		claseBean.setNombre(clase.getNombre());
    		claseBean.setNivel(clase.getNivel());
    	}
    	return claseBean;
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
    
    private TareaBean parseTareaBean(Tarea tarea){
    	TareaBean tareaBean = null;
    	if(tarea!=null){
    		tareaBean = new TareaBean();
    		tareaBean.setCodigo(tarea.getCodTarea());
    		tareaBean.setNombre(tarea.getNombre());
    		tareaBean.setVersion(tarea.getVersionTarea());
    		tareaBean.setClase(tarea.getJavClase());
    		
    		Set<Rol> rolPotencialSet = tarea.getRols();
    		Iterator<Rol> rolPotencialIterator = rolPotencialSet.iterator();
    		List<RolBean> rolesPotencial = new ArrayList<RolBean>();
    		while (rolPotencialIterator.hasNext()) {
				rolesPotencial.add(parseRolBean((Rol) rolPotencialIterator.next()));
			}
    		tareaBean.setRolesPotencial(rolesPotencial);
    		
    		Set<Rol> rolAdministradorSet = tarea.getRols();
    		Iterator<Rol> rolAdministradorIterator = rolAdministradorSet.iterator();
    		List<RolBean> rolesAdministrador = new ArrayList<RolBean>();
    		while (rolAdministradorIterator.hasNext()) {
				rolesAdministrador.add(parseRolBean((Rol) rolAdministradorIterator.next()));
			}
    		tareaBean.setRolesAdministrador(rolesAdministrador);
    		tareaBean.setTareaSiguiente(parseTareaBeanSimple(tarea.getTareaByCodTareaSiguiente()));
    		tareaBean.setTareaObservado(parseTareaBeanSimple(tarea.getTareaByCodTareaObservado()));
    	}
    	return tareaBean;
    }
    
    private TareaBean parseTareaBeanSimple(Tarea tarea){
    	TareaBean tareaBean = null;
    	if(tarea!=null){
    		tareaBean = new TareaBean();
    		tareaBean.setCodigo(tarea.getCodTarea());
    		tareaBean.setNombre(tarea.getNombre());
    		tareaBean.setVersion(tarea.getVersionTarea());
    		tareaBean.setClase(tarea.getJavClase());
    	}
    	return tareaBean;
    }
 
    private RolBean parseRolBean(Rol rol){
    	RolBean rolBean = null;
    	if(rol!=null){
    		rolBean = new RolBean();
    		rolBean.setRol(rol.getCodRol());
    		rolBean.setNombre(rol.getDescripcion());
    	}
    	return rolBean;
    }
}
