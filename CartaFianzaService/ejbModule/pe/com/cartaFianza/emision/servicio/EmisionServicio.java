package pe.com.cartaFianza.emision.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.security.RolesAllowed;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;

import pe.com.cartaFianza.bean.Solicitud;

@Stateless
@LocalBean
public class EmisionServicio extends PreEmisionServicio implements EmisionServicioLocal {

	@RolesAllowed("Analista")
	public Proceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception {
		return super.accionCrearInstancia(usuarioPortal, objeto);
	}

	@Override
	public Solicitud accionVerResumen(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception {
		return null;
	}

	@Override
	public Solicitud accionVerDetalle(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception {
		return null;
	}

}