package pe.com.soul.core.seguridad.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.UsuarioPortal;

@Local
public interface SeguridadServiceLocal {

	List<UsuarioPortal> obtenerUsuarios() throws Exception;
	UsuarioPortal registrarUsuario(String usuarioId, String sesionId, String hostRemoto, String ipRemoto) throws Exception;
	
}
