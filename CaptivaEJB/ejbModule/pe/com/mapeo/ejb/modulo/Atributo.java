package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Atributo extends GestionBase implements AtributoLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO","ATR").registrar();
			if(request.getParameter("FLG_dataObject") != null && request.getParameter("FLG_dataObject").equals("true")){
				jpo.tabla("ATRIBUTO_DO","ADO").registrar();
			}
			if(request.getParameter("FLG_sql") != null && request.getParameter("FLG_sql").equals("true")){
				jpo.tabla("ATRIBUTO_SQL","ADB").registrar();
			}
		jpo.commitear();
		return true;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("ATRIBUTO", jpo.tabla("ATRIBUTO","ATR").obtener("*"));
			elementos.put("ATRIBUTO_DO", jpo.tabla("ATRIBUTO_DO","ADO").obtener("*"));
			elementos.put("ATRIBUTO_SQL", jpo.tabla("ATRIBUTO_SQL","ADB").obtener("*"));
		return elementos;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String flg_dataObject = request.getParameter("FLG_dataObject");
		String flg_dataObjectEsEdicion = request.getParameter("FLG_dataObjectEsEdicion");
		String flg_sql = request.getParameter("FLG_sql");
		String flg_sqlEsEdicion = request.getParameter("FLG_sqlEsEdicion");
		
		jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO","ATR").editar();
			Tabla atributoDO = jpo.tabla("ATRIBUTO_DO","ADO");
			if(flg_dataObject != null && flg_dataObject.equals("true")){
				if(flg_dataObjectEsEdicion != null && flg_dataObjectEsEdicion.equals("true")){
					atributoDO.editar();
				} else {
					atributoDO.registrar();
				}
			} else {
				atributoDO.eliminar();
			}
			Tabla atributoSQL = jpo.tabla("ATRIBUTO_SQL","ADB");
			if(flg_sql != null && flg_sql.equals("true")){
				if(flg_sqlEsEdicion != null && flg_sqlEsEdicion.equals("true")){
					atributoSQL.editar();
				} else {
					atributoSQL.registrar();
				}
			} else {
				atributoSQL.eliminar();
			}
		jpo.commitear();
		return true;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO_DO","ADO").eliminar();
			jpo.tabla("ATRIBUTO_SQL","ADB").eliminar();
			jpo.tabla("ATRIBUTO","ATR").eliminar();
		jpo.commitear();
		return true;
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tablas(
				new String[] { "ATRIBUTO"	, "ATRIBUTO_DO"},
				new String[] { "ATR"		, "ATD"		   }
		).dondeUnirIzquierda(
				"ATD",new String[] { "COD_ATRIBUTO"}
		).seleccionar("ATR.*,ATD.NOMBRE AS DO_NOMBRE,ATD.TIPO AS DO_TIPO,ATD.COD_OBJ_BPM AS DO_COD_OBJ_BPM");
	}
	
	public Object listaAtributoxPK(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("ATRIBUTO","ATR").seleccionar("COD_ATRIBUTO,NOMBRE");
	}
	
	public Object listarDependencias(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("OBJ_BPM", jpo.tabla("OBJ_BPM","OBJ").seleccionar("*"));
			elementos.put("TABLA", jpo.tabla("TABLA","TAB").seleccionar("*"));
		return elementos;
	}
	
}