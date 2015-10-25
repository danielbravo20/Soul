package pe.com.cartaFianza.ejecucioncartafianzav1.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ProcesoUtil;

public class PreEjecucionCartaFianzaV1Util implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}

	@Override
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}