package pe.com.soul.cartaFianza.web.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.cartaFianza.proceso.EmisionCartaFianzaServiceLocal;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.proceso.web.controller.BaseProcesoController;
import pe.com.soul.core.web.bean.Respuesta;

public abstract class PreEmisionCartaFianzaV1 extends BaseProcesoController{

	private static final long serialVersionUID = 1L;
	
	@EJB
	EmisionCartaFianzaServiceLocal emisionCartaFianzaServiceLocal;
	
	@Override
	protected Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
		Respuesta respuesta = new Respuesta();
		
		respuesta.setResultado(true);
		respuesta.setRespuesta("NADA NUEVO");
		
		return respuesta;
	}

	@Override
	protected Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
		return null;
	}

	@Override
	protected Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
		return null;
	}

}
