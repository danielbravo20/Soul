package pe.com.cartaFianza.emisioncartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.emisioncartafianzav1.tarea.servicio.EmisionSubsanarObservacionesAutorizacionV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreEmisionSubsanarObservacionesAutorizacionV1Controlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	EmisionSubsanarObservacionesAutorizacionV1ServicioLocal emisionsubsanarobservacionesautorizacionv1ServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new EmisionSubsanarObservacionesAutorizacionV1Util();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.emisionsubsanarobservacionesautorizacionv1ServicioLocal;
	}

}