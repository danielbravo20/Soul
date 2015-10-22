package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class TareaAtributo extends GestionBase implements TareaAtributoLocal {
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String tipoAccion = request.getParameter("TIPO_ACCION");
		if(tipoAccion != null){
			jpo.autoCommit(false);
				jpo.tabla("TAREA_ATR_"+tipoAccion,"ATR").eliminar();
				jpo.tabla("TAREA_ATR_"+tipoAccion,"ATR").registrarMultiple();
			jpo.commitear();
			return true;
		} else {
			return false;
		}		
	}

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String tipoAccion = request.getParameter("TIPO_ACCION");
		return jpo.tablas(
				new String[] { "TAREA_ATR_"+tipoAccion	, "ATRIBUTO"},
				new String[] { "ATR"					, "ARB"		}
		).dondeUnir(
				new String[] { "ATR", "ARB"},
				new String[] { "COD_ATRIBUTO"}
		).ordenadoPor("ARB.COD_CLASE, ARB.COD_ATRIBUTO ASC").seleccionar("ATR.*,ARB.COD_CLASE");
	}
	
}