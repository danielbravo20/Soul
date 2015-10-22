package pe.com.captiva.dao;

import javax.ejb.Local;

import pe.com.captiva.bean.ConfiguracionBean;

@Local
public interface ConfiguracionDaoLocal {

	ConfiguracionBean obtenerConfiguracion(String usuario, Integer codigoProyecto) throws Exception;
	
}
