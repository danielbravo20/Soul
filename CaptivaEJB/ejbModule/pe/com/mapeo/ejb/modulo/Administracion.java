package pe.com.mapeo.ejb.modulo;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.carga.CargaConfiguracion;
import pe.com.mapeo.ejb.carga.CargaMantenimientos;
import pe.com.mapeo.ejb.carga.CargaProcesos;
import pe.com.mapeo.ejb.carga.CargaTareas;
import pe.com.mapeo.ejb.controller.GestionBase;

/**
 * Session Bean implementation class Generar
 */
@Stateless
public class Administracion extends GestionBase implements AdministracionLocal {

	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Tabla Configuracion = jpo.tabla("Configuracion","CON");
		List<Map<String, Object>> buscConfig = (List<Map<String, Object>>) Configuracion.seleccionar("codigoProyecto");
		if(buscConfig.size()>0){
			return Configuracion.editar();
		} else {
			return Configuracion.registrar();
		}
	}
	
	public Object generar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//return jpo.tabla("Generado","GEN").registrar();
		
		Object usuario = jpo.tabla("USUARIO","USU").obtener("*");
		Object proyecto = jpo.tabla("Configuracion","CON").obtener("*");
		Object equipoDeUsuario = jpo.tabla("EQUIPO","EQU").obtener("*");
		
		CargaConfiguracion.cargar(usuario,proyecto,equipoDeUsuario);
		
		CargaProcesos.cargar(jpo, new Object());
		CargaTareas.cargar(jpo, new Object());
		
		if(request.getParameter("GEN_opcMantenimiento") != null){
			CargaMantenimientos.cargar(jpo,jpo.tabla("Mantenimiento","MAN").seleccionar("*"));
		}
		
		CargaConfiguracion.cargarEJBBinding();
		

		/*
		ScriptRunner ejecutarScript = new ScriptRunner(getConnection(), false, false);
		
		ejecutarScript.setDelimiter("@", false);
		Reader reader1 = new FileReader("D:\\IBM\\WRK\\BC\\PLD_WEB\\Scripts\\BDs.sql");
		Reader reader2 = new FileReader("D:\\IBM\\WRK\\BC\\PLD_WEB\\Scripts\\SPs.sql");

		System.out.println("reader1b");
		ejecutarScript.runScript(reader1);*/
		
		
		return true;
		
	}
	
}
