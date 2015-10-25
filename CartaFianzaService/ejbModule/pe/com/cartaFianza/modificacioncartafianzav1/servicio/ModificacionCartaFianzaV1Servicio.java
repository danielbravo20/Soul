package pe.com.cartaFianza.modificacioncartafianzav1.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class ModificacionCartaFianzaV1Servicio extends PreModificacionCartaFianzaV1Servicio implements ModificacionCartaFianzaV1ServicioLocal {

	@RolesAllowed("Administrador")
	public Proceso crearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		return super.crearInstancia(usuarioPortal);
	}

}