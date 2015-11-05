package pe.com.mapeo.ejb.modulo;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface AtributoLocal extends GestionLocal {
	
	public Object registrar(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	Object listarDetalle(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listaAtributoxPK(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listarDependencias(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listarSQL(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}