package pe.com.captiva.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.ConfiguracionBean;
import pe.com.captiva.dao.entity.Configuracion;
import pe.com.captiva.dao.entity.ConfiguracionId;

/**
 * Session Bean implementation class ConfiguracionDao
 */
@Stateless
@LocalBean
public class ConfiguracionDao extends BaseDao<Configuracion> implements ConfiguracionDaoLocal {

    public ConfiguracionDao() {
        super(Configuracion.class);
    }

	@Override
	public ConfiguracionBean obtenerConfiguracion(String usuario, Integer codigoProyecto) throws Exception {
		
		ConfiguracionId id = new ConfiguracionId();
		id.setCodProyecto(codigoProyecto);
		id.setUsuario(usuario);
		
		return obtenerConfiguracionBean(obtenerEntity(id));
	}
    
	private ConfiguracionBean obtenerConfiguracionBean(Configuracion configuracion){
		ConfiguracionBean configuracionBean = null;
		if(configuracion!=null){
			configuracionBean = new ConfiguracionBean();
			configuracionBean.setUsuario(configuracion.getId().getUsuario());
			configuracionBean.setCodigoProyecto(configuracion.getId().getCodProyecto());
			configuracionBean.setRutaWorkSpace(configuracion.getRutaWorkspace());
			configuracionBean.setRutaSQL(configuracion.getRutaScriptSql());
		}
		return configuracionBean;
	}
    

}
