package pe.com.mapeo.ejb.modulo;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface TareaLocal extends GestionLocal {
	
	public Object registrar(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object registrarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object eliminarPlantilla(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object registrarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	//Object eliminarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object agregarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object eliminarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}