package pe.com.cartaFianza.emision.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

@Stateless
@LocalBean
public class EmisionServicio extends PreEmisionServicio implements EmisionServicioLocal {

	@RolesAllowed("Analista")
	public Proceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception {
		return super.accionCrearInstancia(usuarioPortal, objeto);
	}
	
	@RolesAllowed("Analista")
	public Proceso registrarOperacion(Proceso proceso, UsuarioPortal usuario, Object objeto) throws Exception {
		return super.registrarOperacion(proceso, usuario, objeto);
	}

}