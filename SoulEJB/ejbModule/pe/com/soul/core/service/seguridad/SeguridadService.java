package pe.com.soul.core.service.seguridad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.modelo.UsuarioPortal;
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
	
	private static Map<String, UsuarioPortal> sesionIds 		= new HashMap<String, UsuarioPortal>();
	private static Map<String, String> sesionUsuario 	= new HashMap<String, String>();
	
	public List<UsuarioPortal> obtenerUsuarios() throws Exception {
		return usuarioDaoLocal.obtenerTodo();
	}

	public UsuarioPortal registrarUsuario(String usuarioId, String sesionId, String hostRemoto, String ipRemoto) throws Exception {
		
		UsuarioPortal usuario = null;
		
		if(sesionIds.containsKey(sesionId)){
			throw new Exception("La sesion ingresada ya existe, no puede ser registrada...");
		}
		
		if(sesionUsuario.containsKey(usuarioId)){
			usuario = sesionIds.get(sesionUsuario.get(usuarioId));
			
			if(usuario.getHostRemoto().equalsIgnoreCase(hostRemoto)==false ||
					usuario.getIpRemoto().equals(ipRemoto)==false){
				throw new Exception("El usuario ya cuenta con un sesión activa en el equipo: "+usuario.getHostRemoto()+"("+usuario.getIpRemoto()+")");
			}
			
		}
		
		usuario = usuarioDaoLocal.obtenerUsuario(usuarioId);
		usuario.setSesionId(sesionId);
		usuario.setHostRemoto(hostRemoto);
		usuario.setIpRemoto(ipRemoto);
		
		sesionIds.put(sesionId, usuario);
		sesionUsuario.put(usuarioId, sesionId);
		
		return usuario;
	}

}
