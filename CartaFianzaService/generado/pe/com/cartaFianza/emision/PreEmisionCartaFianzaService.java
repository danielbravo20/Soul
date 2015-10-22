package pe.com.cartaFianza.emision;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;
import pe.com.soul.core.servicio.BaseProcesoServicio;
import pe.com.soul.core.servicio.impl.BaseProcesoServicioImpl;

@DeclareRoles("Administrador")
public abstract class PreEmisionCartaFianzaService extends BaseProcesoServicioImpl implements BaseProcesoServicio{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE 					= "EMISION DE CARTA FIANZA"; 
	public static final String PROCESO_ALEAS 					= "emisionCartaFianzaV1";
	public static final String PROCESO_VERSION 					= "v1.0.0";
	
	@EJB
	ProcesoServiceLocal procesoServiceLocal;
	
	@Resource
    private SessionContext sessionContext;

	public Proceso crearInstancia(Usuario usuario) throws Exception {
		System.out.println("-->>> "+sessionContext.isCallerInRole("Administrador"));
		System.out.println(sessionContext.getCallerPrincipal());
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setCreador(usuario.getUsuario());
		proceso = procesoServiceLocal.crearInstancia(proceso);
		
		return proceso;
	}
	
	@RolesAllowed("Administrador")
	public TareaPlantilla definirProximaTarea(Proceso proceso) throws Exception{
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

	@Override
	public String definirProximoDueno(Proceso proceso) throws Exception {
		return sessionContext.getCallerPrincipal().getName();
	}
	
}
