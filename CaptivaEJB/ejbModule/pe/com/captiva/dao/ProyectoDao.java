package pe.com.captiva.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ConsultaBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProcesoDetalleBean;
import pe.com.captiva.bean.ProcesoDetalleSeccionBean;
import pe.com.captiva.bean.ProcesoDetalleSubSeccionBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.RolBean;
import pe.com.captiva.bean.SubseccionProceso;
import pe.com.captiva.bean.TablaBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.dao.entity.Atributo;
import pe.com.captiva.dao.entity.AtributoSql;
import pe.com.captiva.dao.entity.Clase;
import pe.com.captiva.dao.entity.Consulta;
import pe.com.captiva.dao.entity.ConsultaAtributo;
import pe.com.captiva.dao.entity.Proceso;
import pe.com.captiva.dao.entity.ProcesoDetalle;
import pe.com.captiva.dao.entity.ProcesoDetalleSeccion;
import pe.com.captiva.dao.entity.ProcesoDetalleSubSeccion;
import pe.com.captiva.dao.entity.ProcesoInicio;
import pe.com.captiva.dao.entity.ProcesoInicioSubSeccion;
import pe.com.captiva.dao.entity.Proyecto;
import pe.com.captiva.dao.entity.Rol;
import pe.com.captiva.dao.entity.Tabla;
import pe.com.captiva.dao.entity.Tarea;

/**
 * Session Bean implementation class ClaseDao
 */
@Stateless
@LocalBean
public class ProyectoDao extends BaseDao<Proyecto> implements ProyectoDaoLocal {

	@EJB
	private AtributoSQLDaoLocal atributoSQLDaoLocal;
	
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
	    	
	    	Set<Tabla> tablaSet = proyecto.getTablas();
	    	if(tablaSet!=null){
	    		List<TablaBean> tablas = new ArrayList<TablaBean>();
	    		Iterator<Tabla> tablaIterator = tablaSet.iterator();
	    		while (tablaIterator.hasNext()) {
	    			tablas.add(parseTablaBean((Tabla) tablaIterator.next()));
					
				}
	    		proyectoBean.setTablas(tablas);
	    	}
    	}
    	return proyectoBean;
    }
    
    private ProcesoBean parseProcesoBean(Proceso proceso){
    	ProcesoBean procesoBean = null;
    	if(proceso!=null){
    		
    		procesoBean = new ProcesoBean();
    		procesoBean.setCodigo(proceso.getCodProceso());
    		procesoBean.setNombre(proceso.getNombre());
    		procesoBean.setClase(proceso.getJavClase());
    		procesoBean.setTareaInicial(parseTareaBeanSimple(proceso.getTarea()));
    		procesoBean.setAleas(proceso.getJavClase().toLowerCase());
    		
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
    		
    		Set<ProcesoInicioSubSeccion> procesoInicioSubSeccions = proceso.getProcesoInicioSubSeccions();
    		Iterator<ProcesoInicioSubSeccion> procesoInicioSubSeccionsIterator = procesoInicioSubSeccions.iterator();
    		List<SubseccionProceso> procesoInicioSubSeccionsBeans = new ArrayList<SubseccionProceso>();
    		while (procesoInicioSubSeccionsIterator.hasNext()) {
    			procesoInicioSubSeccionsBeans.add(parseSubseccionProcesoBean((ProcesoInicioSubSeccion) procesoInicioSubSeccionsIterator.next()));
			}
    		procesoBean.setSubseccionEntrada(procesoInicioSubSeccionsBeans);
    		
    		//consulta detalle del proceso
    		ConsultaBean consultaDetalleBean = new ConsultaBean();
    		List<AtributoBean> consultaDetalleAtributosBean = new ArrayList<AtributoBean>();
    		Consulta consultaDetalle = proceso.getConsultaByCodConDetalle();
    		
    		Set<ConsultaAtributo> consultaDetalleAtributoSet = consultaDetalle.getConsultaAtributos();
    		Iterator<ConsultaAtributo> consultaDetalleAtributoIterator = consultaDetalleAtributoSet.iterator();
    		while (consultaDetalleAtributoIterator.hasNext()) {
				ConsultaAtributo consultaAtributo = (ConsultaAtributo) consultaDetalleAtributoIterator.next();
				consultaDetalleAtributosBean.add(parseAtributoBean(consultaAtributo.getAtributo()));
			}
    		consultaDetalleBean.setAtributosBean(consultaDetalleAtributosBean);
    		procesoBean.setConsultaDetalle(consultaDetalleBean);
    		
    		//consulta resumen del proceso
    		ConsultaBean consultaResumenBean = new ConsultaBean();
    		List<AtributoBean> consultaResumenAtributosBean = new ArrayList<AtributoBean>();
    		Consulta consultaResumen = proceso.getConsultaByCodConResumen();
    		
    		Set<ConsultaAtributo> consultaAtributoResumenSet = consultaResumen.getConsultaAtributos();
    		Iterator<ConsultaAtributo> consultaAtributoResumenIterator = consultaAtributoResumenSet.iterator();
    		while (consultaAtributoResumenIterator.hasNext()) {
				ConsultaAtributo consultaAtributo = (ConsultaAtributo) consultaAtributoResumenIterator.next();
				consultaResumenAtributosBean.add(parseAtributoBean(consultaAtributo.getAtributo()));
			}
    		consultaResumenBean.setAtributosBean(consultaResumenAtributosBean);
    		procesoBean.setConsultaResumen(consultaResumenBean);
    		
    		// PROCESO DETALLE
    		List<ProcesoDetalleBean> procesoDetallesBean = new ArrayList<ProcesoDetalleBean>();
    		Set<ProcesoDetalle> procesoDetalle = proceso.getProcesoDetalles();
    		Iterator<ProcesoDetalle> procesoDetalleIterator = procesoDetalle.iterator();
    		while (procesoDetalleIterator.hasNext()) {
    			procesoDetallesBean.add(parseProcesoDetalle((ProcesoDetalle) procesoDetalleIterator.next()));
			}
    		procesoBean.setProcesoDetallesBean(procesoDetallesBean);
    		
    		System.out.println("TOTAL : "+procesoDetalle.size());
    		// PROCESO DETALLE SECCION
    		List<ProcesoDetalleSeccionBean> procesoDetalleSeccionBean = new ArrayList<ProcesoDetalleSeccionBean>();
    		Set<ProcesoDetalleSeccion> procesoDetalleSeccion = proceso.getProcesoDetalleSeccions();
    		Iterator<ProcesoDetalleSeccion> procesoDetalleSeccionIterator = procesoDetalleSeccion.iterator();
    		while (procesoDetalleSeccionIterator.hasNext()) {
    			procesoDetalleSeccionBean.add(parseProcesoDetalleSeccion((ProcesoDetalleSeccion) procesoDetalleSeccionIterator.next()));
			}
    		procesoBean.setProcesoDetallesSeccionBean(procesoDetalleSeccionBean);
    		
    		System.out.println("TOTAL : "+procesoDetalleSeccion.size());
    		// PROCESO DETALLE SUB SECCION
    		List<ProcesoDetalleSubSeccionBean> procesoDetalleSubSeccionBean = new ArrayList<ProcesoDetalleSubSeccionBean>();
    		Set<ProcesoDetalleSubSeccion> procesoDetalleSubSeccion = proceso.getProcesoDetalleSubSeccions();
    		Iterator<ProcesoDetalleSubSeccion> procesoDetalleSubSeccionIterator = procesoDetalleSubSeccion.iterator();
    		while (procesoDetalleSubSeccionIterator.hasNext()) {
    			procesoDetalleSubSeccionBean.add(parseProcesoDetalleSubSeccion((ProcesoDetalleSubSeccion) procesoDetalleSubSeccionIterator.next()));
			}
    		procesoBean.setProcesoDetallesSubSeccionBean(procesoDetalleSubSeccionBean);
    		
    		
    		System.out.println("TOTAL : "+procesoDetalleSubSeccion.size());
    		
    	}
    	return procesoBean;
    }
    
    private ProcesoDetalleSubSeccionBean parseProcesoDetalleSubSeccion(ProcesoDetalleSubSeccion procesoDetalleSubSeccion){
    	ProcesoDetalleSubSeccionBean procesoDetalleSubSeccionBean = null;
		if(procesoDetalleSubSeccion!=null){
			procesoDetalleSubSeccionBean = new ProcesoDetalleSubSeccionBean();
			
			procesoDetalleSubSeccionBean.setCodigoProceso(procesoDetalleSubSeccion.getId().getCodProceso());
			procesoDetalleSubSeccionBean.setCodSeccion(procesoDetalleSubSeccion.getId().getCodSeccion());
			procesoDetalleSubSeccionBean.setCodSubSeccion(procesoDetalleSubSeccion.getId().getCodSubSeccion());
			procesoDetalleSubSeccionBean.setNombre(procesoDetalleSubSeccion.getId().getNombre());
			
		}
		return procesoDetalleSubSeccionBean;
    }
    
    private ProcesoDetalleSeccionBean parseProcesoDetalleSeccion(ProcesoDetalleSeccion procesoDetalleSeccion){
    	ProcesoDetalleSeccionBean procesoDetalleSeccionBean = null;
		if(procesoDetalleSeccion!=null){
			procesoDetalleSeccionBean = new ProcesoDetalleSeccionBean();
			
			procesoDetalleSeccionBean.setCodigoProceso(procesoDetalleSeccion.getId().getCodProceso());
			procesoDetalleSeccionBean.setCodSeccion(procesoDetalleSeccion.getId().getCodSeccion());
			procesoDetalleSeccionBean.setTipoSeccion(procesoDetalleSeccion.getId().getTipo());
			procesoDetalleSeccionBean.setTipoWidget(procesoDetalleSeccion.getId().getTipoWidget());
			procesoDetalleSeccionBean.setNombre(procesoDetalleSeccion.getId().getNombre());
			procesoDetalleSeccionBean.setCodSeccionPadre(procesoDetalleSeccion.getId().getCodSeccionPadre());
			
		}
		return procesoDetalleSeccionBean;
    }
    
    private ProcesoDetalleBean parseProcesoDetalle(ProcesoDetalle procesoDetalle){
    	ProcesoDetalleBean procesoDetalleBean = null;
		if(procesoDetalle!=null){
			procesoDetalleBean = new ProcesoDetalleBean();
			
			procesoDetalleBean.setCodigoProceso(procesoDetalle.getId().getCodProceso());
			procesoDetalleBean.setCodSeccion(procesoDetalle.getId().getCodSeccion());
			procesoDetalleBean.setCodSubSeccion(procesoDetalle.getId().getCodSubSeccion());
			procesoDetalleBean.setCodProcesoDetalle(procesoDetalle.getId().getCodProcesoDetalle());
			procesoDetalleBean.setCodAtributo(procesoDetalle.getId().getCodAtributo());
			procesoDetalleBean.setWebEtiqueta(procesoDetalle.getId().getWebEtiqueta());
			
			procesoDetalleBean.setWebNombre(procesoDetalle.getAtributo().getWebNombre());
			
		}
		return procesoDetalleBean;
    }
    
    private AtributoProceso parseAtributoProcesoBean(ProcesoInicio procesoInicio){
    	AtributoProceso atributoProceso = null;
		if(procesoInicio!=null){
			atributoProceso = new AtributoProceso();
			
			atributoProceso.setCodSubSeccion(procesoInicio.getId().getCodSubSeccion());
			atributoProceso.setCodProcesoInicio(procesoInicio.getId().getCodProcesoInicio());
			atributoProceso.setCodigo(procesoInicio.getAtributo().getCodAtributo());
			atributoProceso.setNombre(procesoInicio.getAtributo().getNombre());
			atributoProceso.setTipo(procesoInicio.getAtributo().getTipo());
			
			atributoProceso.setWebEtiqueta(procesoInicio.getWebEtiqueta());
			atributoProceso.setWebTipo(procesoInicio.getWebTipo());
			atributoProceso.setWebNombre(procesoInicio.getAtributo().getWebNombre());
			atributoProceso.setWebTipoCampo(procesoInicio.getWebTipoCampo());
			atributoProceso.setWebTipoLista(procesoInicio.getWebTipoLista());
			atributoProceso.setWebCatalogo(procesoInicio.getWebCatalogo());
			if(procesoInicio.getWebRequerido()=='1'){
				atributoProceso.setFlgWebRequerido(true);
			}
			atributoProceso.setWebMensajeValidacion(procesoInicio.getWebMensajeValidacion());
			atributoProceso.setValorOmision(procesoInicio.getValOmision());
			atributoProceso.setClase(parseClaseBeanSimple(procesoInicio.getAtributo().getClase()));
			atributoProceso.setCampoSQLBean(parseCampoSQLBeanSimple(procesoInicio.getAtributo().getAtributoSql()));
			
		}
		return atributoProceso;
    }
    
    private SubseccionProceso parseSubseccionProcesoBean(ProcesoInicioSubSeccion subseccion){
    	SubseccionProceso subseccionProceso = null;
		if(subseccion!=null){
			subseccionProceso = new SubseccionProceso();
			subseccionProceso.setCodigoProceso(subseccion.getId().getCodProceso());
			subseccionProceso.setCodigoSubseccion(subseccion.getId().getCodSubSeccion());
			subseccionProceso.setNombre(subseccion.getNombre());
		}
		return subseccionProceso;
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
    		claseBean.setTablaBean(parseTablaBeanSimple(clase.getTabla()));
    		
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
    		
    		atributoBean.setCampoSQLBean(parseCampoSQLBean(atributo.getAtributoSql()));
    	}
    	return atributoBean;
    }
    
    private TareaBean parseTareaBean(Tarea tarea){
    	TareaBean tareaBean = null;
    	if(tarea!=null){
    		tareaBean = new TareaBean();
    		tareaBean.setCodigo(tarea.getCodTarea());
    		tareaBean.setNombre(tarea.getNombre());
    		tareaBean.setClase(tarea.getClase());
    		
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
    		tareaBean.setClase(tarea.getClase());
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
    
    private TablaBean parseTablaBean(Tabla tabla){
    	TablaBean tablaBean = null;
    	if(tabla!=null){
    		tablaBean = new TablaBean();
    		tablaBean.setEsquema(tabla.getEsquema());
    		tablaBean.setNombre(tabla.getNombre());
    		tablaBean.setCodigo(tabla.getCodTabla());
    		
    		System.out.println(tabla.getNombre()+" - "+tabla.getClases().size()); 
    		
    		List<CampoSQLBean> camposSQL = new ArrayList<CampoSQLBean>();
    		List<CampoSQLBean> camposPK = new ArrayList<CampoSQLBean>();
    		List<CampoSQLBean> camposFK = new ArrayList<CampoSQLBean>();
    		
    		Set<AtributoSql> atributoSQLset = tabla.getAtributoSqls();
    		Iterator<AtributoSql> atributoSQLiterator = atributoSQLset.iterator();
    		
    		while (atributoSQLiterator!=null && atributoSQLiterator.hasNext()) {
				AtributoSql atributoSql = (AtributoSql) atributoSQLiterator.next();
				CampoSQLBean campoSQLBean = parseCampoSQLBean(atributoSql);
				camposSQL.add(campoSQLBean);
				if(campoSQLBean.isFlgPK()){
					camposPK.add(campoSQLBean);
				}
				if(campoSQLBean.isFlgTieneFK()){
					camposFK.add(campoSQLBean);
				}
			}
    		
    		tablaBean.setCamposSQL(camposSQL);
    		tablaBean.setCamposPK(camposPK);
    		tablaBean.setCamposFK(camposFK);
    		
    		Set<Clase> clasesSet = tabla.getClases();
    		Iterator<Clase> clasesIterator = clasesSet.iterator();
    		
    		while (clasesIterator.hasNext()) {
				tablaBean.setClaseBean(parseClaseBeanSimple((Clase) clasesIterator.next()));
			}
    		
    	}
    	return tablaBean;
    }
    
    private TablaBean parseTablaBeanSimple(Tabla tabla){
    	TablaBean tablaBean = null;
    	if(tabla!=null){
    		tablaBean = new TablaBean();
    		tablaBean.setEsquema(tabla.getEsquema());
    		tablaBean.setNombre(tabla.getNombre());
    		tablaBean.setCodigo(tabla.getCodTabla());
    	}
    	return tablaBean;
    }
    
    private CampoSQLBean parseCampoSQLBean(AtributoSql atributoSql){
    	CampoSQLBean campoSQLBean = null;
    	if(atributoSql!=null){
    		campoSQLBean = new CampoSQLBean();
    		
    		campoSQLBean.setNombre(atributoSql.getCampo()); 
    		campoSQLBean.setTipo(atributoSql.getTipo());
    		campoSQLBean.setFuncionBusqueda(atributoSql.getFnBusNombre());
    		campoSQLBean.setFuncionBusquedaCatalogo(atributoSql.getFnBusCatalogo());
    		campoSQLBean.setLongitud(atributoSql.getLongitud()!=null?atributoSql.getLongitud():0);
    		campoSQLBean.setPrecision(atributoSql.getPrecision()!=null?atributoSql.getPrecision():0);
    		campoSQLBean.setFlgObligatorio(atributoSql.getObligatorio()=='1'?true:false);
    		campoSQLBean.setFlgPK(atributoSql.getPk()=='1'?true:false);
    		campoSQLBean.setSequence(atributoSql.getSequencial());
    		
    		if(atributoSql.getFkUnoMucho() != null){
    			campoSQLBean.setFkUnoMuchos(atributoSql.getFkUnoMucho()=='1'?true:false);
    		}
    		campoSQLBean.setValorDefecto(atributoSql.getValDefecto());
    		if(atributoSql.getFkTabla()!=null && atributoSql.getFkCampo()!=null && atributoSql.getFkCampo()>0){
    			campoSQLBean.setFk(atributoSQLDaoLocal.obtenerCampoSQLBean(atributoSql.getFkTabla(), atributoSql.getFkCampo()));
    		}
    	}
    	return campoSQLBean;
    }
    
    private CampoSQLBean parseCampoSQLBeanSimple(AtributoSql atributoSql){
    	CampoSQLBean campoSQLBean = null;
    	if(atributoSql!=null){
    		campoSQLBean = new CampoSQLBean();
    		
    		campoSQLBean.setNombre(atributoSql.getCampo()); 
    		campoSQLBean.setTipo(atributoSql.getTipo());
    		campoSQLBean.setFuncionBusqueda(atributoSql.getFnBusNombre());
    		campoSQLBean.setFuncionBusquedaCatalogo(atributoSql.getFnBusCatalogo());
    		campoSQLBean.setLongitud(atributoSql.getLongitud()!=null?atributoSql.getLongitud():0);
    		campoSQLBean.setPrecision(atributoSql.getPrecision()!=null?atributoSql.getPrecision():0);
    		campoSQLBean.setFlgObligatorio(atributoSql.getObligatorio()=='1'?true:false);
    		campoSQLBean.setFlgPK(atributoSql.getPk()=='1'?true:false);
    		campoSQLBean.setSequence(atributoSql.getSequencial());
    		
    		if(atributoSql.getFkUnoMucho() != null){
    			campoSQLBean.setFkUnoMuchos(atributoSql.getFkUnoMucho()=='1'?true:false);
    		}
    		campoSQLBean.setValorDefecto(atributoSql.getValDefecto());
    		
    	}
    	return campoSQLBean;
    }
}
