package pe.com.soul.core.service.seguridad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.seguridad.dao.UsuarioDaoLocal;
import pe.com.soul.core.seguridad.service.SeguridadServiceLocal;

/**
 * Session Bean implementation class SeguridadServiceImpl
 */
@Stateless
@LocalBean
public class SeguridadService implements SeguridadServiceLocal {

	@EJB
	UsuarioDaoLocal usuarioDaoLocal;
	
	private static Map<String, Usuario> sessionIds 	= new HashMap<String, Usuario>();
	private static Map<String, String> sessionUsuario 	= new HashMap<String, String>();
	
	public List<Usuario> obtenerUsuarios() throws Exception {
		return usuarioDaoLocal.obtenerTodo();
	}

	public Usuario registrarUsuario(String usuario, String sesionId) throws Exception {
		return usuarioDaoLocal.obtenerUsuario(usuario);
	}

}
