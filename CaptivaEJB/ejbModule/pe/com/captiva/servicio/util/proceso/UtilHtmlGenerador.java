package pe.com.captiva.servicio.util.proceso;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UtilHtmlGenerador {
	
	public static final Map<String, Character> tipoClase;
    static {
        Map<String, Character> clase = new HashMap<String, Character>();
        clase.put("Integer", 'N');
        clase.put("Long", 'N');
        clase.put("long", 'N');
        clase.put("String", 'S');
        clase.put("boolean", 'b');
        clase.put("java.math.BigDecimal", 'B');
        clase.put("java.util.Date",'D');
        clase.put("java.sql.Date", 'D');
        clase.put("java.sql.Timestamp", 'T');
        tipoClase = Collections.unmodifiableMap(clase);
    }
    
}