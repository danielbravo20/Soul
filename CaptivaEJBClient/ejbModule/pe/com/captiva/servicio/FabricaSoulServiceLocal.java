package pe.com.captiva.servicio;

import javax.ejb.Local;

@Local
public interface FabricaSoulServiceLocal {

	void crearProyecto(String usuario, Integer codigoProyecto) throws Exception;
	
}
