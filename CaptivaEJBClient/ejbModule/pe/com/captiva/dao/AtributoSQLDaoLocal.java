package pe.com.captiva.dao;

import javax.ejb.Local;

import pe.com.captiva.bean.CampoSQLBean;

@Local
public interface AtributoSQLDaoLocal {

	CampoSQLBean obtenerCampoSQLBean(Integer codigoTabla, Integer codigoAtributo);
}
