package pe.com.soul.core.seguridad.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.UsuarioPortal;

@Local
public interface UsuarioDaoLocal {
	
	UsuarioPortal actualizar(UsuarioPortal usuario);
	void guardar(UsuarioPortal usuario);
	List<UsuarioPortal> obtenerTodo();
	UsuarioPortal obtenerUsuario(String usuario);
}

