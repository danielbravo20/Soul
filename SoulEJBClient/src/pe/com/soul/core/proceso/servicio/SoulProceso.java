package pe.com.soul.core.proceso.servicio;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;

public interface SoulProceso {
	
	Proceso crearInstancia(Usuario usuario) throws Exception;
	
}
