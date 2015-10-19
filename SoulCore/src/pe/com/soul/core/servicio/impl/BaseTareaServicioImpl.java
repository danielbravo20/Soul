package pe.com.soul.core.servicio.impl;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.service.portal.TareaServiceLocal;
import pe.com.soul.core.servicio.BaseTareaServicio;

public abstract class BaseTareaServicioImpl implements BaseTareaServicio {

	@EJB
	TareaServiceLocal tareaServiceLocal;
	
	@Override
	public Tarea accionReclamar(long tkiid) throws Exception {
		return tareaServiceLocal.reclamar(tkiid);
	}
	
	@Override
	public Tarea accionLiberar(long tkiid) throws Exception {
		return tareaServiceLocal.liberar(tkiid);
	}

	@Override
	public Tarea accionTrabajar(long tkiid) throws Exception {
		Tarea tarea = tareaServiceLocal.trabajar(tkiid);
		tarea.setObjeto(trabajar(tarea));
		return tarea;
	}

	@Override
	public Tarea accionCompletar(long tkiid, Object objeto) throws Exception {
		Tarea tarea = tareaServiceLocal.completar(tkiid);
		tarea.setObjeto(objeto);
		tarea.setObjeto(completar(tarea));
		TareaPlantilla tareaPlantilla = proximaTareaCompletar(tarea);
		if(tareaPlantilla!=null){
			tareaServiceLocal.crearTarea(tareaPlantilla, tarea.getProceso(), proximoDuenoCompletar(tarea));
		}else{
			tareaServiceLocal.finalizar(tkiid);
		}
		return tarea;
	}

	@Override
	public Tarea accionCancelar(long tkiid, Object objeto) throws Exception {
		Tarea tarea = tareaServiceLocal.terminar(tkiid);
		tarea.setObjeto(objeto);
		tarea.setObjeto(cancelar(tarea));
		return tarea;
	}

	@Override
	public Tarea accionRechazar(long tkiid, Object objeto) throws Exception {
		Tarea tarea = tareaServiceLocal.terminar(tkiid);
		tarea.setObjeto(objeto);
		tarea.setObjeto(rechazar(tarea));
		return tarea;
	}

	@Override
	public Tarea accionObservar(long tkiid, Object objeto) throws Exception {
		Tarea tarea = tareaServiceLocal.completar(tkiid);
		tarea.setObjeto(objeto);
		tarea.setObjeto(observar(tarea));
		TareaPlantilla tareaPlantilla = proximaTareaObservar(tarea);
		tareaServiceLocal.crearTarea(tareaPlantilla, tarea.getProceso(), proximoDuenoObservar(tarea));
		return tarea;
	}
	
	@Override
	public Tarea accionTransferir(long tkiid, String nuevoUsuario) throws Exception {
		Tarea tarea = tareaServiceLocal.transferir(tkiid, nuevoUsuario);
		tarea.setObjeto(transferir(tarea, nuevoUsuario));
		return tarea;
	}
	
	public abstract Object trabajar(Tarea tarea) throws Exception;
	
	public abstract Object completar(Tarea tarea) throws Exception;
	
	public abstract TareaPlantilla proximaTareaCompletar(Tarea tarea) throws Exception;
	
	public abstract String proximoDuenoCompletar(Tarea tarea) throws Exception;
	
	public abstract Object cancelar(Tarea tarea) throws Exception;
	
	public abstract Object rechazar(Tarea tarea) throws Exception;
	
	public abstract Object observar(Tarea tarea) throws Exception;
	
	public abstract Object transferir(Tarea tarea, String nuevoUsuario) throws Exception;
	
	public abstract TareaPlantilla proximaTareaObservar(Tarea tarea) throws Exception;
	
	public abstract String proximoDuenoObservar(Tarea tarea) throws Exception;
}
