package pe.com.cartaFianza.emision.servicio;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.service.portal.ProcesoServiceLocal;
import pe.com.soul.core.servicio.BaseProcesoServicioLocal;
import pe.com.soul.core.servicio.impl.BaseProcesoServicioImpl;

import pe.com.cartaFianza.bean.*;

import pe.com.cartaFianza.emision.servicio.dao.EmisionDaoLocal;

public abstract class PreEmisionServicio extends BaseProcesoServicioImpl implements BaseProcesoServicioLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE = "Emision";
	public static final String PROCESO_ALEAS = "Emision";
	public static final String PROCESO_VERSION = "v1.0.0";

	@EJB
	ProcesoServiceLocal procesoServiceLocal;

	@EJB
	EmisionDaoLocal emisionDaoLocal;

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

	public Proceso registrarOperacion(Proceso proceso, UsuarioPortal usuario, Object objeto) throws Exception {
		Solicitud solicitud = (Solicitud)objeto;
		solicitud.setCodigoProceso(proceso.getCodigoProceso());
		emisionDaoLocal.registrar(solicitud);
		return proceso;
	}
	public TareaPlantilla definirProximaTarea(Proceso proceso) throws Exception{
		TareaPlantilla plantilla = new TareaPlantilla();
		plantilla.setCodigoTareaPlantilla(1);
		plantilla.setNombre("Completar Solicitud");
		plantilla.setAleas("CompletarSolicitud");
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

	public Solicitud accionVerResumen(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception {
		return emisionDaoLocal.verResumen(solicitud);
	}

	public Solicitud accionVerDetalle(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception {
		return emisionDaoLocal.verDetalle(solicitud);
	}

}