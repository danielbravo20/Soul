package pe.com.cartaFianza.cambioestadocartafianzav1.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.cambioestadocartafianzav1.servicio.CambioEstadoCartaFianzaV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseProcesoController;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class PreCambioEstadoCartaFianzaV1Controlador extends BaseProcesoController{

	private static final long serialVersionUID = 1L;

	@EJB
	CambioEstadoCartaFianzaV1ServicioLocal cambioestadocartafianzav1ServicioLocal;

	@Override
	public ProcesoUtil getProcesoUtil() {
		return new CambioEstadoCartaFianzaV1Util();
	}

	@Override
	public BaseProcesoServicioLocal getBaseProcesoServicioLocal() {
		return this.cambioestadocartafianzav1ServicioLocal;
	}

	@Override
	protected Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}

	@Override
	protected Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
			Respuesta respuesta = new Respuesta();
			return respuesta;
		}
	}