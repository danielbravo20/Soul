package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class ProcesoInicio extends GestionBase implements ProcesoInicioLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO_INICIO","PRI").registrar();
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO_INICIO","PRI").editar();
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("PROCESO_INICIO","PRI").eliminar();
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return jpo.tablas(
				new String[] { "PROCESO_INICIO"	, "ATRIBUTO", "CLASE"},
				new String[] { "PRI"			, "ATR"		, "CLA"}
		).dondeUnir(
				new String[] { "PRI", "ATR"},
				new String[] { "COD_ATRIBUTO"}
		).dondeUnir(
				new String[] { "ATR", "CLA"},
				new String[] { "COD_CLASE"}
		).ordenadoPor("ATR.COD_CLASE, ATR.COD_ATRIBUTO ASC").seleccionar("PRI.*,ATR.NOMBRE AS NOM_ATRIBUTO,CLA.COD_CLASE,CLA.NOMBRE AS NOM_CLASE");
	
	}
	
}