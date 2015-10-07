package pe.com.soul.core.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;

public interface TareaUtil {

	public MensajeValidacion validacionCompletar(HttpServletRequest request, HttpServletResponse response);
	public MensajeValidacion validacionObservar(HttpServletRequest request, HttpServletResponse response);
	public MensajeValidacion validacionCancelar(HttpServletRequest request, HttpServletResponse response);
	public MensajeValidacion validacionRechazar(HttpServletRequest request, HttpServletResponse response);
	public MensajeValidacion validacionGuardar(HttpServletRequest request, HttpServletResponse response);
	public Object poblarCompletar(HttpServletRequest request, HttpServletResponse response);
	public Object poblarObservar(HttpServletRequest request, HttpServletResponse response);
	public Object poblarRechazar(HttpServletRequest request, HttpServletResponse response);
	public Object poblarCancelar(HttpServletRequest request, HttpServletResponse response);
	
}
