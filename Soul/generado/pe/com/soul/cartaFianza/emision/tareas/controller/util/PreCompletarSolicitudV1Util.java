package pe.com.soul.cartaFianza.emision.tareas.controller.util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.Solicitud;
import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreCompletarSolicitudV1Util implements TareaUtil{

	@Override
	public MensajeValidacion validacionCompletar(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		
		String tipoFianza = request.getParameter("tipoFianza");
		String monedaFianza = request.getParameter("monedaFianza");
		String montoFianza = request.getParameter("montoFianza");
		
		if(tipoFianza==null || tipoFianza.trim().length()==0){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("Se debe ingresar el campo tipoFianza");
			return mensajeValidacion;
		}
		
		if(monedaFianza==null || monedaFianza.trim().length()==0){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("Se debe ingresar el campo monedaFianza");
			return mensajeValidacion;
		}
		
		if(montoFianza==null || montoFianza.trim().length()==0){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("Se debe ingresar el campo montoFianza");
			return mensajeValidacion;
		}
		
		mensajeValidacion.setConforme(true);
		
		return mensajeValidacion;
	}

	@Override
	public MensajeValidacion validacionObservar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MensajeValidacion validacionCancelar(HttpServletRequest request, HttpServletResponse response) {
		return new MensajeValidacion(true);
	}

	@Override
	public MensajeValidacion validacionRechazar(HttpServletRequest request, HttpServletResponse response) {
		return new MensajeValidacion(true);
	}

	@Override
	public MensajeValidacion validacionGuardar(HttpServletRequest request, HttpServletResponse response) {
		return new MensajeValidacion(true);
	}

	@Override
	public Object poblarCompletar(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		//solicitud.setTipoFianza(request.getParameter("tipoFianza"));
		//solicitud.setMonedaFianza(request.getParameter("monedaFianza"));
		//solicitud.setMontoFianza(new BigDecimal(request.getParameter("montoFianza")));
		return solicitud;
	}

	@Override
	public Object poblarObservar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object poblarRechazar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object poblarCancelar(HttpServletRequest request, HttpServletResponse response) {
		return new Solicitud();
	}

}
