package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.List;
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
			
		Tabla seccion = jpo.tabla("tarea_accion_seccion","SEC");
		Tabla seccionAdd = jpo.tabla("tarea_accion_seccion","SEA");
		Tabla subseccion = jpo.tabla("tarea_accion_sub_seccion","SUB");
		Tabla accion = jpo.tabla("tarea_accion","ATR");
		
			seccion.eliminar();
			subseccion.eliminar();
			accion.eliminar();
			
			seccion.registrarMultiple();
			seccionAdd.registrarMultiple();
			subseccion.registrarMultiple();
			accion.registrarMultiple();
			
		jpo.commitear();
		
		return true;
		
	}

	public Object listarAccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			
		listados.put("TAREA", jpo.tabla("tarea","TAR").seleccionar("*"));
			listados.put("CANCELAR", jpo.tabla("mae_motivo_cancelar","MMC").ordenadoPor("cod_mae_motivo_cancelar ASC").seleccionar("*"));
			listados.put("RECHAZAR", jpo.tabla("mae_motivo_rechazar","MMR").ordenadoPor("cod_mae_motivo_rechazar ASC").seleccionar("*"));
			listados.put("DOCUMENTO", jpo.tabla("mae_documento_tarea","MDT").ordenadoPor("cod_mae_documento_tarea ASC").seleccionar("*"));
			listados.put("OBSERVACION", jpo.tabla("mae_observacion","MDT").ordenadoPor("cod_mae_observacion ASC").seleccionar("*"));
			listados.put("SUBSANACION", jpo.tabla("mae_subsanacion","MDT").ordenadoPor("cod_mae_subsanacion ASC").seleccionar("*"));

			List<Map<String, Object>> secc = (List<Map<String, Object>>) jpo.tabla("tarea_accion_seccion","SEC").ordenadoPor("cod_seccion ASC").seleccionar("*");
			listados.put("SECCION", secc);
			listados.put("SUB_SECCION", jpo.tabla("tarea_accion_sub_seccion","SUB").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("ATRIBUTO", jpo.tabla("tarea_accion","ATR").ordenadoPor("cod_seccion ASC,cod_sub_seccion ASC, cod_tarea_accion ASC").seleccionar("*"));
			String plantillas = "";
			for(int i = 0; i < secc.size(); i++){
				String codSeccion = (String) secc.get(i).get("cod_seccion_padre");
				if(codSeccion!=null){
					plantillas += "'"+codSeccion+"',";
				}
			}

			if(plantillas.length()>0){
				plantillas = plantillas.substring(0,plantillas.length()-1);
				listados.put("SECCION_PLANTILLA", jpo.tabla("tarea_accion_seccion","SEA").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
				listados.put("SUB_SECCION_PLANTILLA", jpo.tabla("tarea_accion_sub_seccion","SUP").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
				listados.put("ATRIBUTO_PLANTILLA", jpo.tabla("tarea_accion","ATP").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
			}
			
		return listados;
	}
	
	public Object agregarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("tarea_accion_seccion","SEC").registrar();
		jpo.commitear();
		return true;
	}
	
	public Object listarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
		listados.put("SECCION", jpo.tabla("tarea_accion_seccion","SEC").ordenadoPor("cod_seccion ASC").seleccionar("*"));
		listados.put("SUB_SECCION", jpo.tabla("tarea_accion_sub_seccion","SUB").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
		listados.put("ATRIBUTO", jpo.tabla("tarea_accion","ATR").ordenadoPor("cod_seccion ASC,cod_sub_seccion ASC, cod_tarea_accion ASC").seleccionar("*"));
		return listados;
	}
	
	public Object eliminarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("tarea_accion","ATR").eliminar();
		jpo.tabla("tarea_accion_sub_seccion","SUB").eliminar();
		jpo.tabla("tarea_accion_seccion","SEC").eliminar();
		jpo.commitear();
		return true;
	}
	
	// PLANTILLA RESUMEN
	
	public Object registrarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		jpo.tabla("tarea_resumen_plantilla","PLA").registrar();
		jpo.tabla("tarea_resumen_plantilla_sub_seccion","SUB").registrarMultiple();
		jpo.tabla("tarea_resumen_plantilla_atributo","ATR").registrarMultiple();
			
		jpo.commitear();
		
		return true;
	}
	
	public Object editarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Tabla plantilla = jpo.tabla("tarea_resumen_plantilla","PLA");
		Tabla sub_seccion = jpo.tabla("tarea_resumen_plantilla_sub_seccion","SUB");
		Tabla atributo = jpo.tabla("tarea_resumen_plantilla_atributo","ATR");
		
		sub_seccion.eliminar();
		atributo.eliminar();
		
		plantilla.editar();
		sub_seccion.registrarMultiple();
		atributo.registrarMultiple();
		
		jpo.commitear();
		
		return true;
	}
	
	public Object eliminarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Tabla plantilla = jpo.tabla("tarea_resumen_plantilla","PLA");
		Tabla sub_seccion = jpo.tabla("tarea_resumen_plantilla_sub_seccion","SUB");
		Tabla atributo = jpo.tabla("tarea_resumen_plantilla_atributo","ATR");
		
		plantilla.eliminar();
		sub_seccion.eliminar();
		atributo.eliminar();
		
		jpo.commitear();
		
		return true;
	}
	
	public Object listarPlantillaResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
		listados.put("PLANTILLA", jpo.tabla("tarea_resumen_plantilla","PLA").ordenadoPor("nombre ASC").seleccionar("*"));
		listados.put("SUB_SECCION", jpo.tabla("tarea_resumen_plantilla_sub_seccion","SUB").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
		listados.put("ATRIBUTO", jpo.tabla("tarea_resumen_plantilla_atributo","ATR").ordenadoPor("cod_sub_seccion ASC, cod_tarea_resumen_plantilla_atributo ASC").seleccionar("*"));
		return listados;
	}
	
	// RESUMEN
	
	public Object registrarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Tabla sub_seccion = jpo.tabla("tarea_resumen_sub_seccion","SUB");
		Tabla atributo = jpo.tabla("tarea_resumen","ATR");
		
		sub_seccion.eliminar();
		atributo.eliminar();
		
		sub_seccion.registrarMultiple();
		atributo.registrarMultiple();
		
		jpo.tabla("tarea","TAR").editar();
		
		jpo.commitear();
		return true;
	}
	
	public Object listarResumen(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			listados.put("SUB_SECCION", jpo.tabla("tarea_resumen_sub_seccion","SUB").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("ATRIBUTO", jpo.tabla("tarea_resumen","ATR").ordenadoPor("cod_sub_seccion ASC, cod_tarea_resumen ASC").seleccionar("*"));
		return listados;
	}
	
}