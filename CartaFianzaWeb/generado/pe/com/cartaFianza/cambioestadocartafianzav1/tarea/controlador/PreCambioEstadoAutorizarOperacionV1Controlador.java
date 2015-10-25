package pe.com.cartaFianza.cambioestadocartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.cambioestadocartafianzav1.tarea.servicio.CambioEstadoAutorizarOperacionV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreCambioEstadoAutorizarOperacionV1Controlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	CambioEstadoAutorizarOperacionV1ServicioLocal cambioestadoautorizaroperacionv1ServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new CambioEstadoAutorizarOperacionV1Util();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.cambioestadoautorizaroperacionv1ServicioLocal;
	}

}