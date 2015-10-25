package pe.com.soul.core.servicio;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

public interface BaseProcesoServicioLocal {
	
	Proceso accionCrearInstancia(UsuarioPortal usuario) throws Exception;
	
}
