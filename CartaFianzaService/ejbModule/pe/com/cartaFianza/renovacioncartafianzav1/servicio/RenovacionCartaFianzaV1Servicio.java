package pe.com.cartaFianza.renovacioncartafianzav1.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class RenovacionCartaFianzaV1Servicio extends PreRenovacionCartaFianzaV1Servicio implements RenovacionCartaFianzaV1ServicioLocal {

	@RolesAllowed("Administrador")
	public Proceso accionCrearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		return super.accionCrearInstancia(usuarioPortal);
	}

}