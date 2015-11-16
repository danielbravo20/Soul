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
		jpo.tabla("TAREA","TAR").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("TAREA", jpo.tabla("TAREA","TAR").obtener("*"));
		return elementos;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("TAREA","TAR").editar();
		jpo.commitear();
		return true;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("TAREA","TAR").eliminar();
		jpo.commitear();
		return true;
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TAREA","TAR").seleccionar("*");
	}
	
	public Object registrarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		pe.com.mapeo.dao.Tabla 	tarea_insuse = jpo.tabla("tarea_resumen_sub_seccion","TRS");
		tarea_insuse.eliminar();
		
		pe.com.mapeo.dao.Tabla 	tarea_resume = jpo.tabla("tarea_resumen","TAR");
		tarea_resume.eliminar();
		
		tarea_resume.registrarMultiple();
		tarea_insuse.registrarMultiple();
		
		jpo.tabla("tarea","TAE").editar();
		
		String esPlantilla = request.getParameter("esPlantilla");
		if(esPlantilla != null && esPlantilla.equals("true")){
			pe.com.mapeo.dao.Tabla 	tarea_respla = jpo.tabla("tarea_resumen_plantilla","TRP");
			tarea_respla.eliminar();
			tarea_respla.registrar();
		}

		jpo.commitear();
		return true;
	}
	
	public Object listarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			listados.put("SUB_SECCION", jpo.tabla("tarea_resumen_sub_seccion","TRS").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("RESUMEN", jpo.tabla("tarea_resumen","TAR").ordenadoPor("cod_sub_seccion ASC, cod_tarea_resumen ASC").seleccionar("*"));
			listados.put("PLANTILLA", jpo.tabla("tarea_resumen_plantilla","TRP").ordenadoPor("nombre ASC").seleccionar("*"));
		return listados;
	}
	
	public Object eliminarPlantilla(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("tarea_resumen_plantilla","TRP").eliminar();
		jpo.tabla("tarea_resumen_sub_seccion","TRP").eliminar();
		jpo.tabla("tarea_resumen","TAR").eliminar();
		jpo.commitear();
		return true;
	}
	
}