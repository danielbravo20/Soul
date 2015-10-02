package pe.com.soul.cartaFianza.emision.tarea;

import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.proceso.servicio.SoulTarea;

public class PreTareaCompletarSolicitud implements SoulTarea{

	private static TareaPlantilla tareaPlantilla = new TareaPlantilla(1, "v1.0.0", '1', "Completar Solicitud", "completarSolicitudV1", 1, 1);
	
	@Override
	public TareaPlantilla obtenerTareaPlantilla() {
		return PreTareaCompletarSolicitud.tareaPlantilla;
	}

}
