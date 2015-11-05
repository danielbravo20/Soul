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
		//jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO","ATR").registrar();
			if(request.getParameter("FLG_sql") != null && request.getParameter("FLG_sql").equals("true")){
				jpo.tabla("ATRIBUTO_SQL","ADB").registrar();
			}
		jpo.commitear();
		return true;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("ATRIBUTO", jpo.tabla("ATRIBUTO","ATR").obtener("*"));
			elementos.put("ATRIBUTO_SQL", jpo.tabla("ATRIBUTO_SQL","ADB").obtener("*"));
		return elementos;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String flg_sql = request.getParameter("FLG_sql");
		String flg_sqlEsEdicion = request.getParameter("FLG_sqlEsEdicion");
		
		//jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO","ATR").editar();
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
		//jpo.autoCommit(false);
			jpo.tabla("ATRIBUTO_SQL","ADB").eliminar();
			jpo.tabla("ATRIBUTO","ATR").eliminar();
		jpo.commitear();
		return true;
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("ATRIBUTO","ATR").seleccionar("*");
	}
	
	public Object listarDetalle(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tablas(
				new String[] { "CLASE"	, "ATRIBUTO" 	, "ATRIBUTO_SQL"},
				new String[] { "CLA"	, "ATR"			, "ASQ"		 	}
		).dondeUnir(
				new String[] { "ATR", "CLA"},
				new String[] { "cod_clase"}
		).dondeUnirIzquierda(
				"ASQ", "ATR", new String[] { "COD_ATRIBUTO"}
		).seleccionar("ATR.*,CLA.nombre AS cla_nombre,ASQ.longitud AS sql_longitud,ASQ.precision AS sql_precision,ASQ.obligatorio AS sql_obligatorio");
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
	
	public Object listarSQL(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tablas(
				new String[] {	"ATRIBUTO_SQL" ,  "ATRIBUTO"},
				new String[] {	"ATR"		   ,  "ABT"		}
		).dondeUnir(
				new String[] { "ATR", "ABT"},
				new String[] { "COD_ATRIBUTO"}
		).seleccionar("ATR.*,ABT.cod_clase AS atr_cod_clase,ABT.NOMBRE AS ATR_NOMBRE,ABT.WEB_NOMBRE AS ATR_WEB_NOMBRE");
	}
	
}