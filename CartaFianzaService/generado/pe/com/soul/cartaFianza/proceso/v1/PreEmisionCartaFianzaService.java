package pe.com.soul.cartaFianza.proceso.v1;

import javax.ejb.EJB;

import pe.com.soul.cartaFianza.emision.tarea.v1.TareaCompletarSolicitud;
import pe.com.soul.cartaFianza.proceso.EmisionCartaFianzaServiceLocal;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.proceso.servicio.BaseTarea;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;
import pe.com.soul.core.service.portal.TareaServiceLocal;

public abstract class PreEmisionCartaFianzaService implements EmisionCartaFianzaServiceLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE 					= "EMISION DE CARTA FIANZA"; 
	public static final String PROCESO_ALEAS 					= "emisionCartaFianzaV1";
	public static final String PROCESO_VERSION 					= "v1.0.0";
	
	@EJB
	ProcesoServiceLocal procesoServiceLocal;
	
	@EJB
	TareaServiceLocal tareaServiceLocal;
	
	public Proceso crearInstancia(Usuario usuario) throws Exception {
		
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setCreador(usuario.getUsuario());
		proceso = procesoServiceLocal.crearInstancia(proceso);
		
		crearPrimeraActividad(proceso, usuario);
		
		return proceso;
	}
	
	protected void crearPrimeraActividad(Proceso proceso, Usuario usuario) throws Exception{
		
		BaseTarea soulTarea = new TareaCompletarSolicitud();
		tareaServiceLocal.crearTarea(soulTarea.obtenerTareaPlantilla(), proceso, usuario);
		
	}
	
}
