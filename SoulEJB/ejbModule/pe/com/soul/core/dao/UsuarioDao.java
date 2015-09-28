package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;

import pe.com.soul.core.dao.jpa.ModuloJPA;
import pe.com.soul.core.dao.jpa.ProcesoPlantillaJPA;
import pe.com.soul.core.dao.jpa.RolJPA;
import pe.com.soul.core.dao.jpa.UsuarioJPA;
import pe.com.soul.core.modelo.Modulo;
import pe.com.soul.core.modelo.ProcesoPlantilla;
import pe.com.soul.core.modelo.Rol;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.seguridad.dao.UsuarioDaoLocal;

/**
 * Session Bean implementation class UsuarioDao
 */
@Stateless
@LocalBean
public class UsuarioDao extends BaseDao<UsuarioJPA> implements UsuarioDaoLocal {

    /**
     * Default constructor. 
     */
    public UsuarioDao() {
    	super(UsuarioJPA.class);
    }
    
    public Usuario obtenerUsuario(String usuarioId){
    	
    	String consulta = "select u from UsuarioJPA u where u.usuario =:parametro";
    	
    	UsuarioJPA usuarioJPA = buscar(consulta, "parametro", usuarioId);
    	Usuario usuario = new Usuario();
    	usuario.setCodigo(usuarioJPA.getCodigoUsuario());
    	usuario.setEstado(usuarioJPA.getEstado());
    	usuario.setCorreo(usuarioJPA.getCorreo());
    	usuario.setNombreCompleto(usuarioJPA.getNombreCompleto());
    	
    	Set<RolJPA> rolJPAs = usuarioJPA.getRols();
    	Iterator<RolJPA> iteratorRol = rolJPAs.iterator();
    	List<Rol> roles = new ArrayList<Rol>(); 
    	List<Modulo> modulos = new ArrayList<Modulo>();
    	List<ProcesoPlantilla> procesoPlantillas = new ArrayList<ProcesoPlantilla>();
    	
    	while(iteratorRol.hasNext()){
    		RolJPA rolJPA = iteratorRol.next();
    		Rol rol = new Rol();
    		rol.setCodRol(rolJPA.getCodigoRol());
    		rol.setNombre(rolJPA.getNombre());
    		roles.add(rol);
    		
    		Set<ModuloJPA> moduloJPAs = rolJPA.getModulos();
    		Iterator<ModuloJPA> iteratorModulo = moduloJPAs.iterator();
    		
    		while (iteratorModulo.hasNext()) {
    			ModuloJPA moduloJPA = (ModuloJPA) iteratorModulo.next();
    			Modulo modulo = new Modulo();
    			modulo.setCodigoModulo(moduloJPA.getCodigoModulo());
    			modulo.setNombre(moduloJPA.getNombre());
    			modulo.setDescripcion(moduloJPA.getDescripcion());
				modulo.setOrden(moduloJPA.getOrden());
				modulo.setUrl(moduloJPA.getUrl());
				modulos.add(modulo);
			}
    		
    		Set<ProcesoPlantillaJPA> procesoPlantillaJPAs = rolJPA.getProcesoPlantillas_1();
    		Iterator<ProcesoPlantillaJPA> iteratorPP = procesoPlantillaJPAs.iterator();
    		
    		while (iteratorPP.hasNext()) {
				ProcesoPlantillaJPA procesoPlantillaJPA = (ProcesoPlantillaJPA) iteratorPP.next();
				ProcesoPlantilla procesoPlantilla = new ProcesoPlantilla();
				procesoPlantilla.setCodigoProcesoPlantilla(procesoPlantillaJPA.getCodigoProcesoPlantilla());
				procesoPlantilla.setNombre(procesoPlantillaJPA.getNombre());
				procesoPlantilla.setVersion(procesoPlantillaJPA.getVersion());
				procesoPlantilla.setAleas(procesoPlantillaJPA.getAleas());
				procesoPlantilla.setEstado(procesoPlantillaJPA.getEstado());
				procesoPlantillas.add(procesoPlantilla);
			}
    		
    	}
    	usuario.setRoles(roles);
    	usuario.setModulos(modulos);
    	usuario.setProcesoPotenciales(procesoPlantillas);
    	
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
