package pe.com.mapeo.ejb.seguridad;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface AccesoLocal extends GestionLocal {
	
	public Object acceder(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;

}