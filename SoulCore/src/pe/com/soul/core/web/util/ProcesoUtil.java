package pe.com.soul.core.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;

public interface ProcesoUtil {

	MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response);
	Object poblarObjetos(HttpServletRequest request, HttpServletResponse response);
	MensajeValidacion validacionCamposVerResumen(HttpServletRequest request, HttpServletResponse response);
	Object poblarObjetosVerResumen(HttpServletRequest request, HttpServletResponse response);
	MensajeValidacion validacionCamposVerDetalle(HttpServletRequest request, HttpServletResponse response);
	Object poblarObjetosVerDetalle(HttpServletRequest request, HttpServletResponse response);
}
