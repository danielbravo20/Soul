package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;

import pe.com.soul.core.dao.jpa.RolJPA;
import pe.com.soul.core.dao.jpa.UsuarioJPA;
import pe.com.soul.core.modelo.Rol;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.seguridad.dao.UsuarioDaoLocal;

/**
 * Session Bean implementation class UsuarioDao
 */
@Stateless
@LocalBean
public class UsuarioDaoImpl extends BaseDaoImpl<UsuarioJPA> implements UsuarioDaoLocal {

    /**
     * Default constructor. 
     */
    public UsuarioDaoImpl() {
    	super(UsuarioJPA.class);
    }
    
    public Usuario obtenerUsuario(String usuarioId){
    	
    	String consulta = "select u from UsuarioJPA u where u.usuario =:parametro";
    	
    	UsuarioJPA usuarioJPA = buscar(consulta, "parametro", usuarioId);
    	Usuario usuario = new Usuario();
    	usuario.setNombreCompleto(usuarioJPA.getNombreCompleto());
    	
    	Set<RolJPA> rolJPAs = usuarioJPA.getRols();
    	Iterator<RolJPA> iterator = rolJPAs.iterator();
    	List<Rol> roles = new ArrayList<Rol>(); 
    	while(iterator.hasNext()){
    		RolJPA rolJPA = iterator.next();
    		Rol rol = new Rol();
    		rol.setCodRol(rolJPA.getCodigoRol());
    		rol.setNombre(rolJPA.getNombre());
    		roles.add(rol);
    	}
    	usuario.setRoles(roles);
    	return usuario;
    }

	@Override
	public Usuario actualizar(Usuario usuario) {
		UsuarioJPA usuarioJPA = new UsuarioJPA();
		actualizar(usuarioJPA);
		return usuario;
	}

	@Override
	public void guardar(Usuario usuario) {
		UsuarioJPA usuarioJPA = new UsuarioJPA();
		
		guardar(usuarioJPA);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
    public List<Usuario> obtenerTodo() {
    	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    	cq.select(cq.from(UsuarioJPA.class));
    	return em.createQuery(cq).getResultList();
    }
    
}
