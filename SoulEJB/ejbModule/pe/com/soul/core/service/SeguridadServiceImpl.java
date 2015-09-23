package pe.com.soul.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.UsuarioDaoLocal;
import pe.com.soul.core.dao.jpa.Usuario;

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

}
