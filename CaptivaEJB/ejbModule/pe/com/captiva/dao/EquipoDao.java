package pe.com.captiva.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.EquipoBean;
import pe.com.captiva.dao.entity.Equipo;
import pe.com.captiva.dao.entity.EquipoId;

/**
 * Session Bean implementation class EquipoDao
 */
@Stateless
@LocalBean
public class EquipoDao extends BaseDao<Equipo> implements EquipoDaoLocal {

    /**
     * Default constructor. 
     */
    public EquipoDao() {
        super(Equipo.class);
    }

    public EquipoBean obtenerEquipoBean(String usuario, Integer codigoProyecto){
    	
    	return parseEquipoBean(obtenerEntity(new EquipoId(codigoProyecto, usuario)));
    	
    }
    
    private EquipoBean parseEquipoBean(Equipo equipo){
    	EquipoBean equipoBean = null;
    	if(equipo!=null){
    		equipoBean = new EquipoBean();
    		equipoBean.setCodigoProyecto(equipo.getId().getCodProyecto());
    		equipoBean.setUsuario(equipo.getId().getCodUsuario());
    		equipoBean.setDirectorioWorkspace(equipo.getCarpetaDestinoWorkspace());
    		equipoBean.setDirectorioParcial(equipo.getCarpetaDestinoParcial());
    	}
    	return equipoBean;
    }
    
}
