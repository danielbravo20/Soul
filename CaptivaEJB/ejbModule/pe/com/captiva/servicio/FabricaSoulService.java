package pe.com.captiva.servicio;

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
		System.out.println("--->>> "+configuracionBean.getRutaWorkSpace());
		System.out.println("--->>> "+configuracionBean.getRutaSQL());
		ProyectoBean proyectoBean = proyectoDaoLocal.obtenerProyecto(codigoProyecto);
		System.out.println("---> "+proyectoBean);
	}

}
