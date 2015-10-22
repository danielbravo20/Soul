package pe.com.mapeo.ejb.gestion;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface UsuarioLocal extends GestionLocal {
	
	public Object cargarUsuario(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object consultarClave(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}