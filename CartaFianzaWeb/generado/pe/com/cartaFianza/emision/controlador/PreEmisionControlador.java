package pe.com.cartaFianza.emision.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.emision.servicio.EmisionServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseProcesoController;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class PreEmisionControlador extends BaseProcesoController{

	private static final long serialVersionUID = 1L;

	@EJB
	EmisionServicioLocal emisionServicioLocal;

	@Override
	public ProcesoUtil getProcesoUtil() {
		return new EmisionUtil();
	}

	@Override
	public BaseProcesoServicioLocal getBaseProcesoServicioLocal() {
		return this.emisionServicioLocal;
	}

	@Override
	public Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}

	@Override
	public Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
			Respuesta respuesta = new Respuesta();
			return respuesta;
	}
	public Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCampos(request, response);
		if(mensajeValidacion.isConforme()){
			respuesta.setResultado(true);
			respuesta.setRespuesta(getBaseProcesoServicioLocal().accionCrearInstancia(usuario, getProcesoUtil().poblarObjetos(request, response)));
		}else{
			respuesta.setResultado(false);
			respuesta.setRespuesta(mensajeValidacion);
		}
		return respuesta;
	}
	}