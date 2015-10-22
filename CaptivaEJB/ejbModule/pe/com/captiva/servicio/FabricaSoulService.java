package pe.com.captiva.servicio;

import java.io.File;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.ConfiguracionBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.dao.ConfiguracionDaoLocal;
import pe.com.captiva.dao.ProyectoDaoLocal;

/**
 * Session Bean implementation class FabricaSoulService
 */
@Stateless
@LocalBean
public class FabricaSoulService implements FabricaSoulServiceLocal {

	@EJB
	private ProyectoDaoLocal proyectoDaoLocal;
	
	@EJB
	private ConfiguracionDaoLocal configuracionDaoLocal;
	
	@Override
	public void crearProyecto(String usuario, Integer codigoProyecto) throws Exception {
		ConfiguracionBean configuracionBean = configuracionDaoLocal.obtenerConfiguracion(usuario, codigoProyecto) ;
		ProyectoBean proyectoBean = proyectoDaoLocal.obtenerProyecto(codigoProyecto);
		
		if(validacionConfiguracion(configuracionBean)){
			System.out.println("--->>> "+configuracionBean.getRutaWorkSpace());
			System.out.println("--->>> "+configuracionBean.getRutaSQL());
			System.out.println("---> "+proyectoBean.getNombre());
			System.out.println("---> "+proyectoBean.getJavPaquete());
		}
	}
	
	/**
	 * @param configuracionBean
	 * @return
	 * @throws Exception
	 * 
	 * Se valida que la configuración del usuario sea valida, rutaWorkspace y ruta SQL
	 */
	private boolean validacionConfiguracion(ConfiguracionBean configuracionBean) throws Exception{
		File directorioWorkspace 	= new File(configuracionBean.getRutaWorkSpace());
		File directorioSQL 			= new File(configuracionBean.getRutaSQL());
		
		if(directorioWorkspace.exists()==false || directorioWorkspace.isDirectory()==false){
			throw new Exception("La ruta "+configuracionBean.getRutaWorkSpace()+", no existe o no es un directorio...");
		}
		
		if(directorioSQL.exists()==false || directorioSQL.isDirectory()==false){
			throw new Exception("La ruta "+configuracionBean.getRutaSQL()+", no existe o no es un directorio...");
		}
		
		return true;
	}

}
