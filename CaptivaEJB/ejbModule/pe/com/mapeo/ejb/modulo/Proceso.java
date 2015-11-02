package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Proceso extends GestionBase implements ProcesoLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("PROCESO","PRO").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object buscarProceso(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO").seleccionar("*");
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO","PRO").obtener("*");
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("PROCESO","PRO").editar();
		jpo.commitear();
		return true;
	}
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO","PRO").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
			//jpo.tabla("Tarea","ATR").eliminar();
			//jpo.tabla("Tarea","ATR").eliminar();
			//jpo.tabla("Tarea","ATR").eliminar();
			jpo.tabla("PROCESO","PRO").eliminar();
		jpo.commitear();
		return true;
	}

}
