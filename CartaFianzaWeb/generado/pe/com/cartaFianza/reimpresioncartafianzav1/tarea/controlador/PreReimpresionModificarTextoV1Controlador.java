package pe.com.cartaFianza.reimpresioncartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.reimpresioncartafianzav1.tarea.servicio.ReimpresionModificarTextoV1ServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreReimpresionModificarTextoV1Controlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	ReimpresionModificarTextoV1ServicioLocal reimpresionmodificartextov1ServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new ReimpresionModificarTextoV1Util();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.reimpresionmodificartextov1ServicioLocal;
	}

}