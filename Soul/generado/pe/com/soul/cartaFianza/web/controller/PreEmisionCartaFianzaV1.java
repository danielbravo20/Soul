package pe.com.soul.cartaFianza.web.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.cartaFianza.proceso.EmisionCartaFianzaServiceLocal;
import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.proceso.web.controller.BaseProcesoController;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class PreEmisionCartaFianzaV1 extends BaseProcesoController{

	private static final long serialVersionUID = 1L;
	
	@EJB
	EmisionCartaFianzaServiceLocal emisionCartaFianzaServiceLocal;
	
	@Override
	protected Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception {
		Respuesta respuesta = new Respuesta();
		
		MensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCampos(request, response);
		
		if(mensajeValidacion.isConforme()){
			respuesta.setResultado(true);
			respuesta.setRespuesta(emisionCartaFianzaServiceLocal.crearInstancia(usuario));
		}else{
			respuesta.setResultado(false);
			respuesta.setRespuesta(mensajeValidacion);
		}
		
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
	
	public abstract ProcesoUtil getProcesoUtil();

}
