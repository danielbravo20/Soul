package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Clase extends GestionBase implements ClaseLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("CLASE","CLA").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object buscarClase(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("CLASE").seleccionar("NOMBRE AS id,NOMBRE AS label,NOMBRE AS value");
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("CLASE","CLA").obtener("*");
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("CLASE","CLA").editar();
		jpo.commitear();
		return true; 
	}
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("CLASE","CLA").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(((Boolean) jpo.tabla("ATRIBUTO","ATR").eliminar())){
			jpo.tabla("CLASE","CLA").eliminar();
			jpo.commitear();
			return true; 
		}else {
			return false;
		}
	}
	
}