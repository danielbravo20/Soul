package pe.com.soul.core.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;

public interface ProcesoUtil {

	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response);
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response);
	
}
