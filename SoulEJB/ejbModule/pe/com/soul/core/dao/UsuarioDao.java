package pe.com.soul.core.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.core.dao.jpa.Usuario;

/**
 * Session Bean implementation class UsuarioDao
 */
@Stateless
@LocalBean
public class UsuarioDao extends BaseDaoImpl<Usuario> implements UsuarioDaoLocal {

    /**
     * Default constructor. 
     */
    public UsuarioDao() {
    	super(Usuario.class);
    }
    
}
