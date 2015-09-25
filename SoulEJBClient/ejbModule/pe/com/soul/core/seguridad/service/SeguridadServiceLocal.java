package pe.com.soul.core.seguridad.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Usuario;

@Local
public interface SeguridadServiceLocal {

	List<Usuario> obtenerUsuarios() throws Exception;
	Object obtenerUsuario(String usuario) throws Exception;
	
}
