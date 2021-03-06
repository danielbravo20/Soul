package pe.com.soul.core.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;

import pe.com.soul.core.dao.entities.ModuloEntity;
import pe.com.soul.core.dao.entities.ProcesoPlantillaEntity;
import pe.com.soul.core.dao.entities.RolEntity;
import pe.com.soul.core.dao.entities.UsuarioEntity;
import pe.com.soul.core.modelo.Modulo;
import pe.com.soul.core.modelo.ProcesoPlantilla;
import pe.com.soul.core.modelo.Rol;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.seguridad.dao.UsuarioDaoLocal;

/**
 * Session Bean implementation class UsuarioDao
 */
@Stateless
@LocalBean
public class UsuarioDao extends BaseDao<UsuarioEntity> implements UsuarioDaoLocal {

    /**
     * Default constructor. 
     */
    public UsuarioDao() {
    	super(UsuarioEntity.class);
    }
    
    public UsuarioPortal obtenerUsuario(String usuarioId){
    	
    	String consulta = "select u from UsuarioEntity u where u.usuario =:parametro";
    	
    	UsuarioEntity usuarioEntity = buscarRegistro(consulta, "parametro", usuarioId);
    	UsuarioPortal usuario = new UsuarioPortal();
    	usuario.setUsuario(usuarioEntity.getUsuario());
    	usuario.setEstado(usuarioEntity.getEstado());
    	usuario.setCorreo(usuarioEntity.getCorreo());
    	usuario.setNombreCompleto(usuarioEntity.getNombreCompleto());
    	
    	Set<RolEntity> rolEntitys = usuarioEntity.getRols();
    	Iterator<RolEntity> iteratorRol = rolEntitys.iterator();
    	List<Rol> roles = new ArrayList<Rol>(); 
    	List<Modulo> modulos = new ArrayList<Modulo>();
    	List<ProcesoPlantilla> procesoPlantillas = new ArrayList<ProcesoPlantilla>();
    	
    	while(iteratorRol.hasNext()){
    		RolEntity rolEntity = iteratorRol.next();
    		Rol rol = new Rol();
    		rol.setCodRol(rolEntity.getCodigoRol());
    		rol.setNombre(rolEntity.getNombreRol());
    		roles.add(rol);
    		
    		Set<ModuloEntity> moduloEntitys = rolEntity.getModulos();
    		Iterator<ModuloEntity> iteratorModulo = moduloEntitys.iterator();
    		
    		while (iteratorModulo.hasNext()) {
    			ModuloEntity moduloEntity = (ModuloEntity) iteratorModulo.next();
    			Modulo modulo = new Modulo();
    			modulo.setCodigoModulo(moduloEntity.getCodigoModulo());
    			modulo.setNombre(moduloEntity.getNombre());
    			modulo.setDescripcion(moduloEntity.getDescripcion());
				modulo.setOrden(moduloEntity.getOrden());
				modulo.setUrl(moduloEntity.getUrl());
				modulos.add(modulo);
			}
    		
    		Set<ProcesoPlantillaEntity> procesoPlantillaEntitys = rolEntity.getProcesoPlantillas_1();
    		Iterator<ProcesoPlantillaEntity> iteratorPP = procesoPlantillaEntitys.iterator();
    		
    		while (iteratorPP.hasNext()) {
				ProcesoPlantillaEntity procesoPlantillaEntity = (ProcesoPlantillaEntity) iteratorPP.next();
				ProcesoPlantilla procesoPlantilla = new ProcesoPlantilla();
				procesoPlantilla.setCodigoProcesoPlantilla(procesoPlantillaEntity.getCodigoProcesoPlantilla());
				procesoPlantilla.setNombre(procesoPlantillaEntity.getNombreProceso());
				procesoPlantilla.setVersion(procesoPlantillaEntity.getVersionProceso());
				procesoPlantilla.setAleas(procesoPlantillaEntity.getAleasProceso());
				procesoPlantilla.setEstado(procesoPlantillaEntity.getEstadoProceso());
				procesoPlantillas.add(procesoPlantilla);
			}
    		
    	}
    	usuario.setRoles(roles);
    	usuario.setModulos(modulos);
    	usuario.setProcesoPotenciales(procesoPlantillas);
    	
    	return usuario;
    }

	@Override
	public UsuarioPortal actualizar(UsuarioPortal usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		actualizarEntity(usuarioEntity);
		return usuario;
	}

	@Override
	public void guardar(UsuarioPortal usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		try {
			guardarEntity(usuarioEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
    public List<UsuarioPortal> obtenerTodo() {
    	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    	cq.select(cq.from(UsuarioEntity.class));
    	return em.createQuery(cq).getResultList();
    }
    
}
