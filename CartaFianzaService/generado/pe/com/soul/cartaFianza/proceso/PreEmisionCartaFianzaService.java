package pe.com.soul.cartaFianza.proceso;

import javax.ejb.EJB;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;

public abstract class PreEmisionCartaFianzaService implements EmisionCartaFianzaServiceLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE 					= "EMISION DE CARTA FIANZA"; 
	public static final String PROCESO_ALEAS 					= "emisionCartaFianzaV1";
	public static final String PROCESO_VERSION 					= "v1.0.0";
	
	@EJB
	ProcesoServiceLocal procesoServiceLocal;
	
	public Proceso crearProceso(Usuario usuario) throws Exception {
		
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setUsuario(usuario);
		
		proceso = procesoServiceLocal.crear(proceso);
		
		return proceso;
	}

	
}
