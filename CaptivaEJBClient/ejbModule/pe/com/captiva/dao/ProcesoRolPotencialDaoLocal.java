package pe.com.captiva.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.captiva.bean.RolBean;

@Local
public interface ProcesoRolPotencialDaoLocal {

	List<RolBean> obtenerRolesPotenciales(Integer codigo);
	
}
