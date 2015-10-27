package pe.com.cartaFianza.cambioestadocartafianzav1.servicio;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;
import pe.com.soul.core.servicio.impl.BaseProcesoServicioImpl;

public abstract class PreCambioEstadoCartaFianzaV1Servicio extends BaseProcesoServicioImpl implements BaseProcesoServicioLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 4;
	public static final String PROCESO_NOMBRE = "BE - CAMBIO DE ESTADO DE CARTA FIANZA";
	public static final String PROCESO_ALEAS = "CambioEstadoCartaFianzaV1";
	public static final String PROCESO_VERSION = "v1.0.0";

	@EJB
	ProcesoServiceLocal procesoServiceLocal;

	@Resource
	private SessionContext sessionContext;

	public Proceso crearInstancia(UsuarioPortal usuarioPortal) throws Exception {
		Proceso proceso = new Proceso();
		proceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);
		proceso.setNombre(PROCESO_NOMBRE);
		proceso.setAleas(PROCESO_ALEAS);
		proceso.setVersion(PROCESO_VERSION);
		proceso.setCreador(usuarioPortal.getUsuario());
		proceso = procesoServiceLocal.crearInstancia(proceso);
		return proceso;
	}

	public TareaPlantilla definirProximaTarea(Proceso proceso) throws Exception{
		TareaPlantilla plantilla = new TareaPlantilla();
		plantilla.setCodigoTareaPlantilla(401);
		plantilla.setNombre("COMPLETAR SOLICITUD");
		plantilla.setAleas("CambioEstadoCompletarSolicitudV1");
		plantilla.setEstado(1);
		plantilla.setOrden(1);
		plantilla.setPrioridad(1);
		plantilla.setVersion("v1.0.0");
		return plantilla;
	}

	@Override
	public String definirProximoDueno(Proceso proceso) throws Exception {
		return null;
	}

}