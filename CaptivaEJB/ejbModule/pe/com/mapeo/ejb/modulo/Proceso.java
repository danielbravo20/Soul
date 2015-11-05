package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

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
	
	public Object registrarInicio(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		pe.com.mapeo.dao.Tabla 	proceso_insuse = jpo.tabla("proceso_inicio_sub_seccion","PIS").ordenadoPor("");
		proceso_insuse.eliminar();
		pe.com.mapeo.dao.Tabla 	proceso_inicio = jpo.tabla("proceso_iniciob","PIN");
		proceso_inicio.eliminar();
		
		proceso_inicio.registrarMultiple();
		proceso_insuse.registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object listarInicio(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			listados.put("SUB_SECCION", jpo.tabla("proceso_inicio_sub_seccion","PIS").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("INICIO", jpo.tabla("proceso_iniciob","PIN").ordenadoPor("cod_sub_seccion ASC, cod_proceso_inicio ASC").seleccionar("*"));
		return listados;
	}

}
