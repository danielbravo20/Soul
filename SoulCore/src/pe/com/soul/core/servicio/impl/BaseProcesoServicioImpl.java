package pe.com.soul.core.servicio.impl;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.service.portal.TareaServiceLocal;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;

public abstract class BaseProcesoServicioImpl implements BaseProcesoServicioLocal{

	@EJB
	TareaServiceLocal tareaServiceLocal;
	
	public Proceso accionCrearInstancia(UsuarioPortal usuario, Object objeto) throws Exception{
		Proceso proceso = crearInstancia(usuario);
		proceso = registrarOperacion(proceso, usuario, objeto);
		TareaPlantilla tareaPlantilla = definirProximaTarea(proceso);
		if(tareaPlantilla!=null){
			tareaServiceLocal.crearTarea(tareaPlantilla, proceso, definirProximoDueno(proceso));
		}
		return proceso;
	}
	
	public abstract Proceso crearInstancia(UsuarioPortal usuario) throws Exception;
	
	public abstract Proceso registrarOperacion(Proceso proceso, UsuarioPortal usuario, Object objeto) throws Exception;
	
	public abstract TareaPlantilla definirProximaTarea(Proceso proceso) throws Exception;
	
	public abstract String definirProximoDueno(Proceso proceso) throws Exception;
	
	
}
