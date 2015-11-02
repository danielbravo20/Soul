package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Tabla extends GestionBase implements TablaLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("TABLA","TAB").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object buscarClase(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TABLA").seleccionar("NOMBRE AS id,NOMBRE AS label,NOMBRE AS value");
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TABLA","TAB").obtener("*");
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("TABLA","TAB").editar();
		jpo.commitear();
		return true; 
	}
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TABLA","TAB").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(((Boolean) jpo.tabla("ATRIBUTO_SQL","ATR").eliminar())){
			jpo.tabla("TABLA","TAB").eliminar();
			jpo.commitear();
			return true; 
		}else {
			return false;
		}
	}
	
}