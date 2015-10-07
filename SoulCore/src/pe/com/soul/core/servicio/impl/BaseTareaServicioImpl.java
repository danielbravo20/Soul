package pe.com.soul.core.servicio.impl;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Tarea;
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
		return tarea;
	}

	@Override
	public void accionCancelar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionRechazar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionObservar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public abstract Object trabajar(Tarea tarea) throws Exception;
	
	public abstract Object completar(Tarea tarea) throws Exception;
	
}
