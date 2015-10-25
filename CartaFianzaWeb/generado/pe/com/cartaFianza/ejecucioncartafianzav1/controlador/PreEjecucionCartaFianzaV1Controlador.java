package pe.com.cartaFianza.ejecucioncartafianzav1.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.ejecucioncartafianzav1.servicio.EjecucionCartaFianzaV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseProcesoController;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class PreEjecucionCartaFianzaV1Controlador extends BaseProcesoController{

	private static final long serialVersionUID = 1L;

	@EJB
	EjecucionCartaFianzaV1ServicioLocal ejecucioncartafianzav1ServicioLocal;

	@Override
	public ProcesoUtil getProcesoUtil() {
		return new EjecucionCartaFianzaV1Util();
	}

	@Override
	public BaseProcesoServicioLocal getBaseProcesoServicioLocal() {
		return this.ejecucioncartafianzav1ServicioLocal;
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