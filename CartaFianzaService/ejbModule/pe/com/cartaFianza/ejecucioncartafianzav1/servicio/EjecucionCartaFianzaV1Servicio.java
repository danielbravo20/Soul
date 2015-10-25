package pe.com.cartaFianza.ejecucioncartafianzav1.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class EjecucionCartaFianzaV1Servicio extends PreEjecucionCartaFianzaV1Servicio implements EjecucionCartaFianzaV1ServicioLocal {

	@RolesAllowed("Administrador")
	public Proceso crearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		return super.crearInstancia(usuarioPortal);
	}

}