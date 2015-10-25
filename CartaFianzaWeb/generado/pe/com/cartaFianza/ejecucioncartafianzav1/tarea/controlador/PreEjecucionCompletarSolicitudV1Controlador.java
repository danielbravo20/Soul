package pe.com.cartaFianza.ejecucioncartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.ejecucioncartafianzav1.tarea.servicio.EjecucionCompletarSolicitudV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreEjecucionCompletarSolicitudV1Controlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	EjecucionCompletarSolicitudV1ServicioLocal ejecucioncompletarsolicitudv1ServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new EjecucionCompletarSolicitudV1Util();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.ejecucioncompletarsolicitudv1ServicioLocal;
	}

}