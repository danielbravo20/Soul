package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
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
		pe.com.mapeo.dao.Tabla 	proceso_insuse = jpo.tabla("proceso_inicio_sub_seccion","PIS");
		proceso_insuse.eliminar();
		pe.com.mapeo.dao.Tabla 	proceso_inicio = jpo.tabla("proceso_inicio","PIN");
		proceso_inicio.eliminar();
		
		proceso_inicio.registrarMultiple();
		proceso_insuse.registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object listarInicio(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			listados.put("SUB_SECCION", jpo.tabla("proceso_inicio_sub_seccion","PIS").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("INICIO", jpo.tabla("proceso_inicio","PIN").ordenadoPor("cod_sub_seccion ASC, cod_proceso_inicio ASC").seleccionar("*"));
		return listados;
	}
	
	// PROCESO DETALLE
	
	public Object registrarDetalle(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("proceso","PRO").editar();
		
		Tabla prodesec = jpo.tabla("proceso_detalle_seccion","SEC");
		Tabla seccionAdd = jpo.tabla("proceso_detalle_seccion","SEA");
		Tabla prodesub = jpo.tabla("proceso_detalle_sub_seccion","SUB");
		Tabla prodetal = jpo.tabla("proceso_detalle","ATR");
		
		prodesec.eliminar();
		prodesub.eliminar();
		prodetal.eliminar();
		
		prodesec.registrarMultiple();
		seccionAdd.registrarMultiple();
		prodesub.registrarMultiple();
		prodetal.registrarMultiple();
		
		jpo.commitear();
		return true;
	}
	
	public Object eliminarDetalle(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("proceso_detalle_seccion","SEC").eliminar();
		jpo.tabla("proceso_detalle_sub_seccion","SUB").eliminar();
		jpo.tabla("proceso_detalle","ATR").eliminar();
		jpo.commitear();
		return true;
	}
	
	public Object listarDetalle(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
			
		listados.put("PROCESO", jpo.tabla("proceso","PRO").seleccionar("*"));
		
		List<Map<String, Object>> secc = (List<Map<String, Object>>) jpo.tabla("proceso_detalle_seccion","SEC").ordenadoPor("cod_seccion ASC").seleccionar("*");
			listados.put("SECCION", secc);
			listados.put("SUB_SECCION", jpo.tabla("proceso_detalle_sub_seccion","SUB").ordenadoPor("cod_sub_seccion ASC").seleccionar("*"));
			listados.put("ATRIBUTO", jpo.tabla("proceso_detalle","ATR").ordenadoPor("cod_seccion ASC,cod_sub_seccion ASC, cod_proceso_detalle ASC").seleccionar("*"));
		
		String plantillas = "";
		for(int i = 0; i < secc.size(); i++){
			String codSeccion = (String) secc.get(i).get("cod_seccion_padre");
			if(codSeccion!=null){
				plantillas += "'"+codSeccion+"',";
			}
		}

		if(plantillas.length()>0){
			plantillas = plantillas.substring(0,plantillas.length()-1);
			listados.put("SECCION_PLANTILLA", jpo.tabla("proceso_detalle_seccion","SEA").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
			listados.put("SUB_SECCION_PLANTILLA", jpo.tabla("proceso_detalle_sub_seccion","SUP").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
			listados.put("ATRIBUTO_PLANTILLA", jpo.tabla("proceso_detalle","ATP").donde("cod_seccion IN ("+plantillas+")").seleccionar("*"));
		}
		
		return listados;
	}
	
	public Object agregarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("proceso_detalle_seccion","SEC").registrar();
		jpo.commitear();
		return true;
	}

}
