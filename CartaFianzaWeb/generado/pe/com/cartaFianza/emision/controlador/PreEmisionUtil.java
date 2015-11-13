package pe.com.cartaFianza.emision.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ValidacionUtil;
import pe.com.soul.core.web.util.ProcesoUtil;

import pe.com.cartaFianza.bean.*;

public class PreEmisionUtil implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.decimalNoValidoRequestParameter(request.getParameter("solicitud.monto"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("monto es invalido");
			return mensajeValidacion;
		}

		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}

	@Override
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		if(request.getParameter("solicitud.monto")!=null && request.getParameter("solicitud.monto").trim().length()>0){
			solicitud.setMonto(new java.math.BigDecimal(request.getParameter("solicitud.monto").trim()));
		}
		return solicitud;
	}
	@Override
	public MensajeValidacion validacionCamposVerResumen(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("codigoProceso"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("ingrese el codigo de proceso");
			return mensajeValidacion;
		}
		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}
	@Override
	public Object poblarObjetosVerResumen(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigoProceso(new Long(request.getParameter("codigoProceso").trim()));
		return solicitud;
	}
	@Override
	public MensajeValidacion validacionCamposVerDetalle(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("codigoProceso"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("ingrese el codigo de proceso");
			return mensajeValidacion;
		}
		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}
	@Override
	public Object poblarObjetosVerDetalle(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigoProceso(new Long(request.getParameter("codigoProceso").trim()));
		return solicitud;
	}
}