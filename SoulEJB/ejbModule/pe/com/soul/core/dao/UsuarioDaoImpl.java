package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.bean.Usuario;

/**
 * Session Bean implementation class UsuarioDao
 */
@Stateless
@LocalBean
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario> implements UsuarioDaoLocal {

    /**
     * Default constructor. 
     */
    public UsuarioDaoImpl() {
    	super(Usuario.class);
    }
    
    public Usuario obtenerUsuario(String usuario){
    	String consulta = "SELECT U FROM SEGURIDAD.USUARIO U where U.USUARIO = :USUARIO1";
    	return buscar(consulta, "USUARIO1", usuario);
    }
    
}
