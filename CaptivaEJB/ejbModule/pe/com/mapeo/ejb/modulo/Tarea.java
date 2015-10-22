package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Tarea extends GestionBase implements TareaLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("TAREA","TAR").registrar();
			jpo.tabla("TAREA_COM_BPM","TCB").registrarMultiple();
			jpo.tabla("TAREA_TRA_BPM","TTB").registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("TAREA", jpo.tabla("TAREA","TAR").obtener("*"));
			elementos.put("TAREA_COM_BPM", jpo.tabla("TAREA_COM_BPM","TCB").seleccionar("*"));
			elementos.put("TAREA_TRA_BPM", jpo.tabla("TAREA_TRA_BPM","TTB").seleccionar("*"));
		return elementos;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("TAREA","TAR").editar();
			jpo.tabla("TAREA_COM_BPM","TCB").eliminar();
			jpo.tabla("TAREA_TRA_BPM","TTB").eliminar();
			jpo.tabla("TAREA_COM_BPM","TCB").registrarMultiple();
			jpo.tabla("TAREA_TRA_BPM","TTB").registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TAREA","TAR").eliminar();
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TAREA","TAR").seleccionar("*");
	}
	
}