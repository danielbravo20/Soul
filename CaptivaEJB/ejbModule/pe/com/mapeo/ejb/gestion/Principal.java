package pe.com.mapeo.ejb.gestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Principal extends GestionBase implements PrincipalLocal {

	public Object listarProyectos(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map<String,Object> listados = new HashMap<String,Object>();
		
		List<Map<String, Object>> listaProyectosxEquipo = (List<Map<String, Object>>) jpo.tabla("EQUIPO","EQU").seleccionar("cod_proyecto");
		
		if(listaProyectosxEquipo.size()>0){
			
			StringBuilder wheresIn = new StringBuilder();
			for(int i=0;i<listaProyectosxEquipo.size();i++){
				Map<String, Object> objProyecto = listaProyectosxEquipo.get(i);
				wheresIn.append("'"+objProyecto.get("cod_proyecto")+"',");
			}
			String whereIn = wheresIn.toString().substring(0,wheresIn.toString().length()-1);
			
			listados.put("PROYECTOS", jpo.tabla("PROYECTO").donde("cod_proyecto IN ("+whereIn+")").seleccionar("*"));
			//listados.put("USUARIOS_PROYECTOS", jpo.tabla("EQUIPO").donde("COD_PROYECTO IN ("+whereIn+")").seleccionar("*"));
			//listados.put("VERSIONES", jpo.tabla("VERSION").donde("COD_PROYECTO IN ("+whereIn+")").seleccionar("*"));
			
		}
		
		listados.put("USUARIOS", jpo.tabla("USUARIO").seleccionar("*"));
		
		return listados;
	}
	
	public Object listarVersiones(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("VERSION","VER").seleccionar("COD_VERSION,TIEMPO_ESTIMADO,FECHA_CREACION");
	}
	
	public Object registrarVersiones(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("VERSION","VER").registrar();
	}
	
	public Object listarRegistros(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map<String,Object> listados = new HashMap<String,Object>();
		
		// COD_PROYECTO
		listados.put("ROL", jpo.tabla("ROL","LIB").seleccionar("*"));
		listados.put("ESQUEMA", jpo.tabla("ESQUEMA","LIB").seleccionar("*"));
		listados.put("DATASOURCE", jpo.tabla("DATASOURCE","LIB").seleccionar("*"));
		listados.put("CONFIGURACION", jpo.tabla("CONFIGURACION","LIB").obtener("*"));
		listados.put("CLASE", jpo.tabla("CLASE","LIB").seleccionar("*"));
		listados.put("PROCESO", jpo.tabla("PROCESO","LIB").seleccionar("*"));
		listados.put("TABLA", jpo.tabla("TABLA","LIB").seleccionar("*"));
		//listados.put("OBJ_BPM", jpo.tabla("OBJ_BPM","LIB").seleccionar("*"));
		listados.put("CONSULTA", jpo.tabla("CONSULTA","LIB").seleccionar("*"));
		listados.put("MANTENIMIENTO", jpo.tabla("MANTENIMIENTO","LIB").seleccionar("*"));
		// COD_PROYECTO y COD_VERSION
		// COD_PROYECTO y COD_USUARIO
		listados.put("EQUIPO", jpo.tabla("EQUIPO","LIC").obtener("*"));
		
		// SIN CONDICIONES
		//listados.put("MAE_UNI_NEGOCIO", jpo.tabla("MAE_UNI_NEGOCIO").seleccionar("*"));
		//listados.put("MAE_PRODUCTO", jpo.tabla("MAE_PRODUCTO").seleccionar("*"));
		
		return listados;
	
	}
	
}