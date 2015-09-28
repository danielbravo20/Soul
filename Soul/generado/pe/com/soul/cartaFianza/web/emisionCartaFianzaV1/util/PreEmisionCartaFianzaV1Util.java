package pe.com.soul.cartaFianza.web.emisionCartaFianzaV1.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ProcesoUtil;

public class PreEmisionCartaFianzaV1Util implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
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
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
