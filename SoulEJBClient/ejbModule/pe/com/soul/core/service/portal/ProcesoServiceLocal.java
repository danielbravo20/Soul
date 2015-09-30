package pe.com.soul.core.service.portal;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;

@Local
public interface ProcesoServiceLocal {

	Proceso crear(Proceso proceso) throws Exception;
}
