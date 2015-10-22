package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class TareaGestionar extends GestionBase implements TareaGestionarLocal {
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("TAREA","TAR").editar();
			
			jpo.tabla("TAREA_SECCION","TSE").eliminar();
			jpo.tabla("TAREA_SUB_SECCION","TSS").eliminar();
			//jpo.tabla("TAREA_ATRIBUTO","TAR").eliminar();
			
			jpo.tabla("TAREA_SECCION","TSE").registrarMultiple();
			jpo.tabla("TAREA_SUB_SECCION","TSS").registrarMultiple();
			//jpo.tabla("TAREA_ATRIBUTO","TAA").registrarMultiple();
			jpo.commitear();
		return true;	
	}
	
	public Object listarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TAREA_SECCION","TSB").ordenadoPor("TAREA_SECCION_ID ASC").seleccionar("*");
	}
	
	public Object listarSubSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("TAREA_SUB_SECCION","TSS").ordenadoPor("TAREA_SECCION_ID ASC, TAREA_SUB_SECCION_ID ASC").seleccionar("*");
	}
	
	public Object eliminarSeccion(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("TAREA_SECCION","TSB").donde(" OR ID_PADRE = '"+request.getParameter("TSB_W_ID")+"'").eliminar();
			jpo.tabla("TAREA_SUB_SECCION","TSS").eliminar();
		jpo.commitear();
		return true;
	}
	
}