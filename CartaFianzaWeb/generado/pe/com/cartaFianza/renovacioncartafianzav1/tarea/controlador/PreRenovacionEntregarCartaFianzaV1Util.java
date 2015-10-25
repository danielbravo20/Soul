package pe.com.cartaFianza.renovacioncartafianzav1.tarea.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreRenovacionEntregarCartaFianzaV1Util implements TareaUtil{

	@Override
	public MensajeValidacion validacionCompletar(HttpServletRequest request, HttpServletResponse response) {
		return new MensajeValidacion(true);
	}

	@Override
	public MensajeValidacion validacionObservar(HttpServletRequest request, HttpServletResponse response) {
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
		return null;
	}

	@Override
	public Object poblarObservar(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public Object poblarRechazar(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public Object poblarCancelar(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}