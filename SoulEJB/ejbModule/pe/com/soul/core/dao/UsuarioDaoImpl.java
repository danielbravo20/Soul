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
    	String consulta = "select u from Usuario u where u.usuario =:parametro";
    	return buscar(consulta, "parametro", usuario);
    }
    
}
