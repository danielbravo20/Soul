package pe.com.mapeo.ejb.modulo;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface AdministracionLocal extends GestionLocal {
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception ;
	
	public Object generar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception ;

}