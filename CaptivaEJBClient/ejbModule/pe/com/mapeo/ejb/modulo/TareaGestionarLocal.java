package pe.com.mapeo.ejb.modulo;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface TareaGestionarLocal extends GestionLocal {
	
	public Object registrar(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	public Object listarSubSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object eliminarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}