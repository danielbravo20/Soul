package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.dao.jpa.Usuario;

@Local
public interface UsuarioDaoLocal {
	
	Usuario update(Usuario usuario);
	void save(Usuario usuario);
	List<Usuario> obtenerTodo();
	
}

