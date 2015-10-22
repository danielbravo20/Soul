package pe.com.mapeo.ejb.carga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.mapeo.dao.Jpo;

public class CargaProcesos {
	
	public static List<Map<String, Object>> procesos ;

	public static boolean cargar(Jpo objJpo,Object objProcesos) throws Exception {
		
		Map<String,Object> proceso = null;
		
		procesos = new ArrayList<Map<String,Object>>();
		
		proceso = new HashMap<String,Object>();
		proceso.put("codigoProceso", "EmisionCartaFianzaV1");
		proceso.put("codigoDataSource", "jdbc/cartaFianza");
		procesos.add(proceso);
		
		return true;
		
	}
	
}
