package pe.com.soul.core.service.portal;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.ProcesoDaoLocal;
import pe.com.soul.core.modelo.Proceso;

/**
 * Session Bean implementation class ProcesoService
 */
@Stateless
@LocalBean
public class ProcesoService implements ProcesoServiceLocal {

	@EJB
	ProcesoDaoLocal procesoDaoLocal;
	
	@Override
	public Proceso crearInstancia(Proceso proceso) throws Exception {
		
		Date fecha = new Date();
		proceso.setFechaCreacion(fecha);
		proceso.setEstado(Proceso.ESTADO_EJECUTANDO);
		proceso = procesoDaoLocal.guardar(proceso);
		
		return proceso;
	}
	
	

}
