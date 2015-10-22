package pe.com.captiva.dao;

import javax.ejb.Local;

import pe.com.captiva.bean.ProyectoBean;

@Local
public interface ProyectoDaoLocal {

	ProyectoBean obtenerProyecto(Integer codigo);
	
}
