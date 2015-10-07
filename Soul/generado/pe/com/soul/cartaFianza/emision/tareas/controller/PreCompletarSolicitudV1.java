package pe.com.soul.cartaFianza.emision.tareas.controller;

import javax.ejb.EJB;

import pe.com.soul.cartaFianza.emision.tarea.EmisionCompletarSolicitudV1Local;
import pe.com.soul.cartaFianza.emision.tareas.controller.util.CompletarSolicitudV1Util;
import pe.com.soul.core.servicio.BaseTareaServicio;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreCompletarSolicitudV1 extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	EmisionCompletarSolicitudV1Local emisionCompletarSolicitudV1Local; 
	
	private static CompletarSolicitudV1Util completarSolicitudV1Util = new CompletarSolicitudV1Util();
	
	@Override
	public TareaUtil getTareaUtil() {
		return completarSolicitudV1Util;
	}

	@Override
	public BaseTareaServicio getBaseTareaService() {
		return emisionCompletarSolicitudV1Local;
	}
}
