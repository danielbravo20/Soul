package pe.com.soul.core.servicio;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;

public interface BaseProcesoServicio {
	
	Proceso accionCrearInstancia(Usuario usuario) throws Exception;
	
}