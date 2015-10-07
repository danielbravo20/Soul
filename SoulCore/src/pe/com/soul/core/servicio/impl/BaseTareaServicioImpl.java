package pe.com.soul.core.servicio.impl;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.service.portal.TareaServiceLocal;
import pe.com.soul.core.servicio.BaseTareaServicio;

public abstract class BaseTareaServicioImpl implements BaseTareaServicio {

	@EJB
	TareaServiceLocal tareaServiceLocal;
	
	public void accionReclamar(Tarea tarea, Usuario usuario) throws Exception {
		
	}
	
	@Override
	public void accionLiberar(Tarea tarea, Usuario usuario) throws Exception {
		tareaServiceLocal.liberar(tarea, usuario);
	}

	@Override
	public Object accionTrabajar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accionCompletar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		
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
	
}
