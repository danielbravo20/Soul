package pe.com.soul.cartaFianza.emision.tarea;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.servicio.BaseTareaServicio;

/**
 * Session Bean implementation class EmisionCompletarSolicitudV1
 */
@Stateless
@LocalBean
public class EmisionCompletarSolicitudV1 extends PreEmisionCompletarSolicitudV1 implements EmisionCompletarSolicitudV1Local {

	@Override
	public TareaPlantilla obtenerTareaPlantilla() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
