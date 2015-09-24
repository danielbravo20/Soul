package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.bean.Usuario;

@Local
public interface UsuarioDaoLocal {
	
	Usuario actualizar(Usuario usuario);
	void guardar(Usuario usuario);
	List<Usuario> obtenerTodo();
	Usuario obtenerUsuario(String usuario);
}

