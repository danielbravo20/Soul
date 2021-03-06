package pe.com.soul.core.service.portal;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;

@Local
public interface ProcesoServiceLocal {

	Proceso crearInstancia(Proceso proceso) throws Exception;
	
	Proceso terminar(Proceso proceso) throws Exception;
	Proceso finalizar(Proceso proceso) throws Exception;
	
}
