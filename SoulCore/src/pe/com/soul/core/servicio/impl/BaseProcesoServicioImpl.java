package pe.com.soul.core.servicio.impl;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.service.portal.TareaServiceLocal;
import pe.com.soul.core.servicio.BaseProcesoServicio;

public abstract class BaseProcesoServicioImpl implements BaseProcesoServicio{

	@EJB
	TareaServiceLocal tareaServiceLocal;
	
	public Proceso accionCrearInstancia(Usuario usuario) throws Exception{
		Proceso proceso = crearInstancia(usuario);
		tareaServiceLocal.crearTarea(definirPrimeraTarea(proceso, usuario), proceso, usuario);
		return proceso;
	}
	
	public abstract Proceso crearInstancia(Usuario usuario) throws Exception;
	
	public abstract TareaPlantilla definirPrimeraTarea(Proceso proceso, Usuario usuario) throws Exception;
	
}
