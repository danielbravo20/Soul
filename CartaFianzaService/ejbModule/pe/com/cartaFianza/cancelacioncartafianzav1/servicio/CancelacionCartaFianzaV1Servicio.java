package pe.com.cartaFianza.cancelacioncartafianzav1.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class CancelacionCartaFianzaV1Servicio extends PreCancelacionCartaFianzaV1Servicio implements CancelacionCartaFianzaV1ServicioLocal {

	@RolesAllowed("Administrador")
	public Proceso accionCrearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		return super.accionCrearInstancia(usuarioPortal);
	}

}