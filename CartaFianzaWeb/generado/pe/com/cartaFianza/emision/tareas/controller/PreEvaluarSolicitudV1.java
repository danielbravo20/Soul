package pe.com.cartaFianza.emision.tareas.controller;

import javax.ejb.EJB;

import pe.com.cartaFianza.emision.tarea.EmisionEvaluarSolicitudV1Local;
import pe.com.cartaFianza.emision.tareas.controller.util.EvaluarSolicitudV1Util;
import pe.com.soul.core.servicio.BaseTareaServicio;
import pe.com.soul.core.web.controller.BaseTareaController;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class PreEvaluarSolicitudV1 extends BaseTareaController{

	private static final long serialVersionUID = 1L;

	@EJB
	EmisionEvaluarSolicitudV1Local emisionEvaluarSolicitudV1Local; 
	
	private static EvaluarSolicitudV1Util evaluarSolicitudV1 = new EvaluarSolicitudV1Util(); 
	
	@Override
	public TareaUtil getTareaUtil() {
		return evaluarSolicitudV1;
	}

	@Override
	public BaseTareaServicio getBaseTareaService() {
		return emisionEvaluarSolicitudV1Local;
	}
}
