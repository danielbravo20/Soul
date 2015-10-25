package pe.com.cartaFianza.cambioestadocartafianzav1.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class CambioEstadoCartaFianzaV1Servicio extends PreCambioEstadoCartaFianzaV1Servicio implements CambioEstadoCartaFianzaV1ServicioLocal {

	@RolesAllowed("Administrador")
	public Proceso crearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		return super.crearInstancia(usuarioPortal);
	}

}