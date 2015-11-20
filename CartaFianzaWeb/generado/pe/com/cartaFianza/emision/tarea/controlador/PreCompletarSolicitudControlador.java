package pe.com.cartaFianza.emision.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.emision.tarea.servicio.CompletarSolicitudServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreCompletarSolicitudControlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	CompletarSolicitudServicioLocal completarsolicitudServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new CompletarSolicitudUtil();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.completarsolicitudServicioLocal;
	}

}