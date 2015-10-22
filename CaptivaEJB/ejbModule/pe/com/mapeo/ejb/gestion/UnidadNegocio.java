package pe.com.mapeo.ejb.gestion;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

/**
 * Session Bean implementation class Banca
 */
@Stateless
public class UnidadNegocio extends GestionBase implements UnidadNegocioLocal {

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("UnidadNegocio","UNE").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("UnidadNegocio","UNE").eliminar();
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("UnidadNegocio","UNE").editar();
	}

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("UnidadNegocio","UNE").registrar();
	}

}