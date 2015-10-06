package pe.com.soul.cartaFianza.proceso.v1;

import javax.ejb.EJB;

import pe.com.soul.cartaFianza.proceso.EmisionCartaFianzaServiceLocal;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.proceso.service.BaseProcesoServiceImpl;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;

public abstract class PreEmisionCartaFianzaService extends BaseProcesoServiceImpl implements EmisionCartaFianzaServiceLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE 					= "EMISION DE CARTA FIANZA"; 
	public static final String PROCESO_ALEAS 					= "emisionCartaFianzaV1";
	public static final String PROCESO_VERSION 					= "v1.0.0";
	
	@EJB
	ProcesoServiceLocal procesoServiceLocal;

	public Proceso crearInstancia(Usuario usuario) throws Exception {
		
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setCreador(usuario.getUsuario());
		proceso = procesoServiceLocal.crearInstancia(proceso);
		
		return proceso;
	}
	
	public TareaPlantilla definirPrimeraTarea(Proceso proceso, Usuario usuario) throws Exception{
		TareaPlantilla plantilla = new TareaPlantilla();
		plantilla.setCodigoTareaPlantilla(1);
		plantilla.setNombre("Completar Solicitud");
		plantilla.setAleas("completarSolicitudV1");
		plantilla.setEstado(1);
		plantilla.setOrden(1);
		plantilla.setPrioridad(1);
		plantilla.setVersion("v1.0.0");
		return plantilla;
	}
	
}
