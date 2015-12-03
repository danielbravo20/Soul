package pe.com.captiva.servicio;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface FabricaSoulServiceLocal {

	void crearProyecto(String usuario, Integer codigoProyecto,HttpServletRequest request) throws Exception;
	
}
