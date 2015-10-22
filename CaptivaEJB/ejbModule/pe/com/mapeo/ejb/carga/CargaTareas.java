package pe.com.mapeo.ejb.carga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.mapeo.dao.Jpo;

public class CargaTareas {
	
	public static List<Map<String, Object>> tareas ;

	public static boolean cargar(Jpo objJpo,Object objTareas) throws Exception {
		
		Map<String,Object> tarea = null;
		
		tareas = new ArrayList<Map<String,Object>>();
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "CompletarSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "EvaluarSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "SubsanarObservacionesEvaluacionV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "AprobarSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "AutorizarVisadoV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "VisarSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);

		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "AutorizarOperacionV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);

		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "Firma1V1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);

		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "Firma2V1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ImprimirSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "EntregarSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ObservarAprobacionSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ObservarVisadoSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ObservarAutorizacionSolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ObservarFirma1SolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		tarea = new HashMap<String,Object>();
		tarea.put("codigoTarea", "ObservarFirma2SolicitudV1");
		tarea.put("codigoDataSource", "jdbc/cartaFianza");
		tareas.add(tarea);
		
		return true;
		
	}

}