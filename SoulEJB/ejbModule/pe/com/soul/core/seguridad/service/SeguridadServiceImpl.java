package pe.com.soul.core.seguridad.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.bean.Usuario;
import pe.com.soul.core.dao.UsuarioDaoLocal;
import pe.com.soul.core.seguridad.service.SeguridadServiceLocal;

/**
 * Session Bean implementation class SeguridadServiceImpl
 */
@Stateless
@LocalBean
public class SeguridadServiceImpl implements SeguridadServiceLocal {

	@EJB
	UsuarioDaoLocal usuarioDaoLocal;
	
	@Override
	public List<Usuario> obtenerUsuarios() throws Exception {
		return usuarioDaoLocal.obtenerTodo();
	}

	@Override
	public Usuario obtenerUsuario(String usuario) throws Exception {
		return usuarioDaoLocal.obtenerUsuario(usuario);
	}

}
