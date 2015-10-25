package pe.com.cartaFianza.modificacioncartafianzav1.tarea.controlador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.modificacioncartafianzav1.tarea.servicio.ModificacionVisarSolicitudServicioLocal;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreModificacionVisarSolicitudControlador extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	ModificacionVisarSolicitudServicioLocal modificacionvisarsolicitudServicioLocal;

	@Override
	public TareaUtil getTareaUtil() {
		return new ModificacionVisarSolicitudUtil();
	}

	@Override
	public BaseTareaServicioLocal getBaseTareaServicioLocal() {
		return this.modificacionvisarsolicitudServicioLocal;
	}

}