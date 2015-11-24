package pe.com.captiva.servicio;

import java.io.File;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.EquipoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.dao.EquipoDaoLocal;
import pe.com.captiva.dao.ProyectoDaoLocal;
import pe.com.captiva.servicio.util.general.GeneralClaseBean;
import pe.com.captiva.servicio.util.general.GeneralClaseEntity;
import pe.com.captiva.servicio.util.general.GeneralSQLInsertSoul;
import pe.com.captiva.servicio.util.general.GeneralSQLCreateSoul;
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
import pe.com.captiva.servicio.util.proceso.ProcesoHtmlInicio;
import pe.com.captiva.servicio.util.proceso.ProcesoJavaScriptInicio;
import pe.com.captiva.servicio.util.proceso.ProcesoJavaScriptPreInicio;
import pe.com.captiva.servicio.util.tarea.TareaClaseControlador;
import pe.com.captiva.servicio.util.tarea.TareaClaseInterfaceServicio;
import pe.com.captiva.servicio.util.tarea.TareaClasePreControlador;
import pe.com.captiva.servicio.util.tarea.TareaClasePreServicio;
import pe.com.captiva.servicio.util.tarea.TareaClasePreUtil;
import pe.com.captiva.servicio.util.tarea.TareaClaseServicio;
import pe.com.captiva.servicio.util.tarea.TareaClaseUtil;

/**
 * Session Bean implementation class FabricaSoulService
 */
@Stateless
@LocalBean
public class FabricaSoulService implements FabricaSoulServiceLocal {

	@EJB
	private ProyectoDaoLocal proyectoDaoLocal;
	
	@EJB
	private EquipoDaoLocal equipoDaoLocal;
	
	@Override
	public void crearProyecto(String usuario, Integer codigoProyecto) throws Exception {
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
			new ProcesoHtmlInicio().construir(proyectoBean);
			
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
