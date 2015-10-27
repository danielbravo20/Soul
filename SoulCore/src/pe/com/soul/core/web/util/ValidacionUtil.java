package pe.com.soul.core.web.util;

public class ValidacionUtil {

	public static boolean cadenaNoValidaRequestParameter(String cadena){
		return (cadena == null || "".equals(cadena));
	}

	public static boolean longNoValidoRequestParameter(String cadena){
		return (cadena == null || Long.valueOf(cadena).intValue() == 0 );
	}

	public static boolean dateNoValidoRequestParameter(String cadena){
		return (cadena == null);
	}

	public static boolean decimalNoValidoRequestParameter(String cadena){
		return (cadena == null);
	}

	public static boolean timestampNoValidoRequestParameter(String cadena){
		return (cadena == null);
	}
	
}
