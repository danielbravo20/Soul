package pe.com.captiva.dao;

import javax.ejb.Local;

import pe.com.captiva.bean.EquipoBean;

@Local
public interface EquipoDaoLocal {

	EquipoBean obtenerEquipoBean(String usuario, Integer codigoProyecto);
	
}
