package pe.com.soul.cartaFianza.emision.tarea;

import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.proceso.service.BaseTareaImpl;
import pe.com.soul.core.proceso.servicio.BaseTarea;

public class PreTareaCompletarSolicitud extends BaseTareaImpl implements BaseTarea{

	private static TareaPlantilla tareaPlantilla = new TareaPlantilla(1, "v1.0.0", '1', "Completar Solicitud", "completarSolicitudV1", 1, 1);
	
	@Override
	public TareaPlantilla obtenerTareaPlantilla() {
		return PreTareaCompletarSolicitud.tareaPlantilla;
	}

}
