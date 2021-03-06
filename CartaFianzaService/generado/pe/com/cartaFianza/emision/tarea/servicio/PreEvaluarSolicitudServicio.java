package pe.com.cartaFianza.emision.tarea.servicio;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.servicio.impl.BaseTareaServicioImpl;

public abstract class PreEvaluarSolicitudServicio extends BaseTareaServicioImpl implements BaseTareaServicioLocal{

	@Override
	public Object trabajar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public Object completar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public TareaPlantilla proximaTareaCompletar(Tarea tarea) throws Exception {
		TareaPlantilla tareaPlantilla = new TareaPlantilla();
		tareaPlantilla.setCodigoTareaPlantilla(1);
		tareaPlantilla.setAleas("asd");
		tareaPlantilla.setEstado(1);
		tareaPlantilla.setNombre("asd");
		tareaPlantilla.setOrden(2);
		tareaPlantilla.setPrioridad(1);
		tareaPlantilla.setVersion("v1.0.0");
		return tareaPlantilla;
	}

	@Override
	public String proximoDuenoCompletar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public Object cancelar(Tarea tarea) throws Exception {
	return null;
	}

	@Override
	public Object rechazar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public Object observar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public TareaPlantilla proximaTareaObservar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public String proximoDuenoObservar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public Object transferir(Tarea tarea, String nuevoUsuario) throws Exception {
		return null;
	}

}

