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
	
	Object registrarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object agregarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object eliminarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	// PLANTILLA RESUMEN
	
	Object registrarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object editarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object eliminarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	// RESUMEN
	
	Object registrarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}