package pe.com.soul.core.proceso.servicio.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.ProcesoDaoLocal;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;

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

	@Override
	public Proceso terminar(Proceso proceso) throws Exception {
		Date fecha = new Date();
		proceso.setFechaTermino(fecha);
		proceso.setEstado(Proceso.ESTADO_TERMINADO);
		proceso = procesoDaoLocal.actualizar(proceso);
		return proceso;
	}
	
	

}
