package pe.com.soul.core.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.dao.jpa.Usuario;

@Local
public interface SeguridadServiceLocal {

	List<Usuario> obtenerUsuarios() throws Exception;
	
}
