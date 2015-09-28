package pe.com.soul.cartaFianza.proceso;

import java.util.Date;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;

public abstract class PreEmisionCartaFianzaService{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE 					= "EMISION DE CARTA FIANZA"; 
	public static final String PROCESO_ALEAS 					= "emisionCartaFianzaV1";
	public static final String PROCESO_VERSION 					= "v1.0.0";
	
	public Proceso crearProceso(Usuario usuario) throws Exception {
		
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setFechaCreacion(new Date());
		proceso.setUsuario(usuario);
		
		return null;
	}

	
}
