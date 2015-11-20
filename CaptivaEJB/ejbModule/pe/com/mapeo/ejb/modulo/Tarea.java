package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;
import pe.com.mapeo.dao.Tabla;

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
	
	public Object registrarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		jpo.tabla("tarea","TAR").editar();
		
		Tabla cancelar = jpo.tabla("mae_motivo_cancelar","MMC");
		Tabla rechazar = jpo.tabla("mae_motivo_rechazar","MMR");
		Tabla documento = jpo.tabla("mae_documento_tarea","MDT");
		Tabla observacion = jpo.tabla("mae_observacion","MOB");
		Tabla subsanacion = jpo.tabla("mae_subsanacion","MSU");
		
			subsanacion.eliminar();
			observacion.eliminar();
			documento.eliminar();
			rechazar.eliminar();
			cancelar.eliminar();
			
			cancelar.registrarMultiple();
			rechazar.registrarMultiple();
			documento.registrarMultiple();
			observacion.registrarMultiple();
			subsanacion.registrarMultiple();
		
		jpo.commitear();
		
		return true;
		
	}
	/*
	public Object eliminarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("tarea_accion_seccion","TSE").eliminar();
		jpo.tabla("tarea_accion_sub_seccion","TSU").eliminar();
		jpo.tabla("tarea_accion","TAC").eliminar();
		jpo.commitear();
		return true;
	}
	*/
	public Object listarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			listados.put("CANCELAR", jpo.tabla("mae_motivo_cancelar","MMC").ordenadoPor("cod_mae_motivo_cancelar ASC").seleccionar("*"));
			listados.put("RECHAZAR", jpo.tabla("mae_motivo_rechazar","MMR").ordenadoPor("cod_mae_motivo_rechazar ASC").seleccionar("*"));
			listados.put("DOCUMENTO", jpo.tabla("mae_documento_tarea","MDT").ordenadoPor("cod_mae_documento_tarea ASC").seleccionar("*"));
			listados.put("OBSERVACION", jpo.tabla("mae_observacion","MDT").ordenadoPor("cod_mae_observacion ASC").seleccionar("*"));
			listados.put("SUBSANACION", jpo.tabla("mae_subsanacion","MDT").ordenadoPor("cod_mae_subsanacion ASC").seleccionar("*"));
			return listados;
	}
	
}