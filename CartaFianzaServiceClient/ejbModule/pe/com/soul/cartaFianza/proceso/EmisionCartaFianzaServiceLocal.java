package pe.com.soul.cartaFianza.proceso;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;

@Local
public interface EmisionCartaFianzaServiceLocal {

	Proceso crearProceso(Usuario usuario) throws Exception;
	
}
