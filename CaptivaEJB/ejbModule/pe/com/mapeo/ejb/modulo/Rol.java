package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Rol extends GestionBase implements RolLocal {

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("Rol","ROL").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("Rol","ROL").eliminar();
		jpo.commitear();
		return true;
	}

	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("Rol","ROL").editar();
		jpo.commitear();
		return true;
	}
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("Rol","ROL").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object listarRolxEntidad(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String tipoAccion = request.getParameter("tabla");
		return jpo.tabla(tipoAccion,"ROL").seleccionar("*");
	}
	
	
}