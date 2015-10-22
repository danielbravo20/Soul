package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Consulta extends GestionBase implements ConsultaLocal {

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("CONSULTA","CON").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("CONSULTA_ATRIBUTO","CAT").eliminar();
			jpo.tabla("CONSULTA_TABLA","CTA").eliminar();
			jpo.tabla("CONSULTA","CON").eliminar();
		jpo.commitear();
		return true;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("CONSULTA","CON").editar();
			jpo.tabla("CONSULTA_ATRIBUTO","CAT").eliminar();
			jpo.tabla("CONSULTA_TABLA","CTA").eliminar();
			jpo.tabla("CONSULTA_TABLA","CTA").registrarMultiple();
			jpo.tabla("CONSULTA_ATRIBUTO","CAT").registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("CONSULTA","CON").registrar();
			jpo.tabla("CONSULTA_TABLA","CTA").registrarMultiple();
			jpo.tabla("CONSULTA_ATRIBUTO","CAT").registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("CONSULTA", jpo.tabla("CONSULTA","CON").obtener("*"));
			elementos.put("CONSULTA_TABLA", jpo.tabla("CONSULTA_TABLA","CTA").seleccionar("*"));
			elementos.put("CONSULTA_ATRIBUTO", jpo.tabla("CONSULTA_ATRIBUTO","CAT").seleccionar("*"));
		return elementos;
	}
	
	public Object cargarConsulta(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> elementos = new HashMap<String,Object>();
			elementos.put("CONSULTA", jpo.tabla("CONSULTA","CON").obtener("*"));
			elementos.put("CONSULTA_TABLA", 
				jpo.tablas(
						new String[] {	"CONSULTA_TABLA" ,  "TABLA"	},
						new String[] {	"CTA"			 ,  "TAB"	}
				).dondeUnir(
						new String[] { "CTA", "TAB"},
						new String[] { "COD_TABLA"}
				).seleccionar("CTA.*,TAB.*")
			);
			elementos.put("CONSULTA_ATRIBUTO", 
				jpo.tablas(
						new String[] {	"CONSULTA_ATRIBUTO" ,  "ATRIBUTO", "ATRIBUTO_DO", "ATRIBUTO_SQL"},
						new String[] {	"CAT"				,  "ATR"	 , "ATD"		, "ASQ"			}
				).dondeUnir(
						new String[] { "CAT", "ATR"},
						new String[] { "COD_ATRIBUTO"}
				).dondeUnirIzquierda(
						"ATD", "ATR", new String[] { "COD_ATRIBUTO"}
				).dondeUnirIzquierda(
						"ASQ", "ATR", new String[] { "COD_ATRIBUTO"}
				).seleccionar("CAT.*,ATR.*,ATD.*,ASQ.*,ATR.NOMBRE AS ATR_NOMBRE,ATR.TIPO AS ATR_TIPO,ATD.NOMBRE AS ATD_NOMBRE,ATD.TIPO AS ATD_TIPO,ASQ.TIPO AS ASQ_TIPO")
			);
		return elementos;
	}
	
}