package pe.com.captiva.servicio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pe.com.captiva.bean.EquipoBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.dao.EquipoDaoLocal;
import pe.com.captiva.dao.ProyectoDaoLocal;
import pe.com.captiva.servicio.util.general.GeneralClaseBean;
import pe.com.captiva.servicio.util.general.GeneralClaseEntity;
import pe.com.captiva.servicio.util.general.GeneralSQLCreateSoul;
import pe.com.captiva.servicio.util.general.GeneralSQLInsertSoul;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseControlador;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseDao;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseInterfaceDao;
//import pe.com.captiva.servicio.util.proceso.ProcesoClaseDao;
//import pe.com.captiva.servicio.util.proceso.ProcesoClaseInterfaceDao;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseInterfaceServicio;
import pe.com.captiva.servicio.util.proceso.ProcesoClasePreControlador;
import pe.com.captiva.servicio.util.proceso.ProcesoClasePreDao;
import pe.com.captiva.servicio.util.proceso.ProcesoClasePreServicio;
import pe.com.captiva.servicio.util.proceso.ProcesoClasePreUtil;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseServicio;
import pe.com.captiva.servicio.util.proceso.ProcesoClaseUtil;
import pe.com.captiva.servicio.util.proceso.ProcesoHtml;
import pe.com.captiva.servicio.util.proceso.ProcesoJavaScriptInicio;
import pe.com.captiva.servicio.util.proceso.ProcesoJavaScriptPreInicio;
import pe.com.captiva.servicio.util.proceso.TareaHtml;
import pe.com.captiva.servicio.util.tarea.TareaClaseControlador;
import pe.com.captiva.servicio.util.tarea.TareaClaseInterfaceServicio;
import pe.com.captiva.servicio.util.tarea.TareaClasePreControlador;
import pe.com.captiva.servicio.util.tarea.TareaClasePreServicio;
import pe.com.captiva.servicio.util.tarea.TareaClasePreUtil;
import pe.com.captiva.servicio.util.tarea.TareaClaseServicio;
import pe.com.captiva.servicio.util.tarea.TareaClaseUtil;
import pe.com.mapeo.dao.Jpo;

/**
 * Session Bean implementation class FabricaSoulService
 */
@Stateless
@LocalBean
public class FabricaSoulService implements FabricaSoulServiceLocal {

	private Jpo jpo;
	
	@EJB
	private ProyectoDaoLocal proyectoDaoLocal;
	
	@EJB
	private EquipoDaoLocal equipoDaoLocal;
	
	@Override
	public void crearProyecto(String usuario, Integer codigoProyecto,HttpServletRequest request) throws Exception {
		EquipoBean equipoBean = equipoDaoLocal.obtenerEquipoBean(usuario, codigoProyecto);
		ProyectoBean proyectoBean = proyectoDaoLocal.obtenerProyecto(codigoProyecto);
		
		if(validacionConfiguracion(equipoBean, proyectoBean)){
			
			proyectoBean.setEquipoBean(equipoBean);
			new GeneralClaseBean().construir(proyectoBean);
			new GeneralClaseEntity().construir(proyectoBean);
			new GeneralSQLCreateSoul().construir(proyectoBean);
			new GeneralSQLInsertSoul().construir(proyectoBean);
			
			new ProcesoClasePreUtil().construir(proyectoBean);
			new ProcesoClaseUtil().construir(proyectoBean);
			new ProcesoClaseInterfaceServicio().construir(proyectoBean);
			new ProcesoClasePreControlador().construir(proyectoBean);
			new ProcesoClaseControlador().construir(proyectoBean);
			new ProcesoClasePreServicio().construir(proyectoBean);
			new ProcesoClaseServicio().construir(proyectoBean);
			new ProcesoClaseInterfaceDao().construir(proyectoBean);
			new ProcesoClasePreDao().construir(proyectoBean);
			new ProcesoClaseDao().construir(proyectoBean);
			new ProcesoJavaScriptPreInicio().construir(proyectoBean);
			new ProcesoJavaScriptInicio().construir(proyectoBean);
			
			jpo = new Jpo(request,true);
			
			ProyectoBean proyecto = mapearJPO(request);

			new ProcesoHtml().construir(proyecto);
			new TareaHtml().construir(proyecto);
			
			new TareaClasePreUtil().construir(proyectoBean);
			new TareaClaseUtil().construir(proyectoBean);
			new TareaClaseInterfaceServicio().construir(proyectoBean);
			new TareaClasePreControlador().construir(proyectoBean);
			new TareaClaseControlador().construir(proyectoBean);
			new TareaClasePreServicio().construir(proyectoBean);
			new TareaClaseServicio().construir(proyectoBean);
		
			System.out.println("---> Termino");
		}
	}
	
	@SuppressWarnings("unchecked")
	private ProyectoBean mapearJPO(HttpServletRequest request) throws Exception {
		
		String usuario 		= request.getParameter("usuario");
		int codigoProyecto 	= new Integer(request.getParameter("codigoProyecto"));
		String condicion 	= "cod_proyecto = '"+codigoProyecto+"'";

		Map<String, Object> objProyecto = (Map<String, Object>) jpo.tabla("proyecto").donde(condicion).obtener("*");
		Map<String, Object> objEquipo = (Map<String, Object>) jpo.tabla("equipo").donde("cod_proyecto = '"+codigoProyecto+"' AND cod_usuario = '"+usuario+"'").obtener("*");
		List<Map<String, Object>> objProcesos = (List<Map<String, Object>>) jpo.tabla("proceso").donde(condicion).seleccionar("*");
		
		ProyectoBean proyecto = new ProyectoBean();
		
		proyecto.setCodProyecto(codigoProyecto);
		proyecto.setNombre((String) objProyecto.get("nombre"));
		proyecto.setProyecto((String) objProyecto.get("proyecto"));
		proyecto.setPaquete((String) objProyecto.get("paquete"));
		
			EquipoBean equipo = new EquipoBean();
			equipo.setDirectorioParcial((String) objEquipo.get("carpeta_destino_parcial"));
			equipo.setDirectorioWorkspace((String) objEquipo.get("carpeta_destino_workspace"));
		
		proyecto.setEquipoBean(equipo);
		
			List<ProcesoBean> procesos = new ArrayList<ProcesoBean>();
			for(Map<String, Object> objProceso : objProcesos){
				ProcesoBean proceso = new ProcesoBean();
				proceso.setCodigo((Integer) objProceso.get("cod_proceso"));
				proceso.setNombre((String) objProceso.get("nombre"));
				proceso.setClase((String) objProceso.get("jav_clase"));
					proceso.setAleas(proceso.getClase().toLowerCase()); // Adicional
				proceso.setDatasource((String) objProceso.get("jav_datasource"));
				proceso.setCodigoResumen((Integer) objProceso.get("cod_con_resumen"));
				proceso.setCodigoDetalle((Integer) objProceso.get("cod_con_detalle"));
				proceso.setWebDetalleTipoVista((String) objProceso.get("web_detalle_tipovista"));
				if(objProceso.get("cod_tarea")!=null){
					proceso.setCodTareaInicial((Integer) objProceso.get("cod_tarea"));
				}
				procesos.add(proceso);
			}
		
		proyecto.setProcesos(procesos);
		
		return proyecto;
		
	}

	/**
	 * @param configuracionBean
	 * @return
	 * @throws Exception
	 * 
	 * Se valida que la configuraci�n del usuario:
	 * 	- existe el directorio del workspace
	 *  - existe el directorio temporal/parcial
	 *  - existe el proyecto EAR
	 *  - existe el proyecto Web
	 *  - existe el proyecto Service
	 *  - existe el proyecto ServiceLib 
	 */
	private boolean validacionConfiguracion(EquipoBean equipoBean, ProyectoBean proyectoBean) throws Exception{
		
		validaDirectorio(equipoBean.getDirectorioWorkspace());
		validaDirectorio(equipoBean.getDirectorioParcial());
		validaDirectorio(equipoBean.getDirectorioWorkspace()+File.separator+proyectoBean.getProyecto()+ProyectoBean.SUFIJO_PROYECTO_EAR);
		validaDirectorio(equipoBean.getDirectorioWorkspace()+File.separator+proyectoBean.getProyecto()+ProyectoBean.SUFIJO_PROYECTO_SERVICE);
		validaDirectorio(equipoBean.getDirectorioWorkspace()+File.separator+proyectoBean.getProyecto()+ProyectoBean.SUFIJO_PROYECTO_SERVICELIB);
		validaDirectorio(equipoBean.getDirectorioWorkspace()+File.separator+proyectoBean.getProyecto()+ProyectoBean.SUFIJO_PROYECTO_WEB);
		validaDirectorio(equipoBean.getDirectorioWorkspace()+File.separator+proyectoBean.getProyecto()+ProyectoBean.SUFIJO_PROYECTO_DAO);
		
		return true;
	}
	
	private void validaDirectorio(String directorio) throws Exception{
		File file = new File(directorio);
		
		if(file.exists()==false || file.isDirectory()==false){
			throw new Exception("La ruta "+directorio+", no existe o no es un directorio...");
		}
	}

}
