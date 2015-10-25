package pe.com.cartaFianza.ejecucioncartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.ejecucioncartafianzav1.tarea.servicio.EjecucionConfirmarEntregaV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreEjecucionConfirmarEntregaV1Controlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	EjecucionConfirmarEntregaV1ServicioLocal ejecucionconfirmarentregav1ServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new EjecucionConfirmarEntregaV1Util();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.ejecucionconfirmarentregav1ServicioLocal;
	}

}