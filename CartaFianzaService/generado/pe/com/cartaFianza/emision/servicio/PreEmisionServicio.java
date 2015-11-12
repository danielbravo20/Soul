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

public abstract class PreEmisionServicio extends BaseProcesoServicioImpl implements BaseProcesoServicioLocal{

	public static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = 1;
	public static final String PROCESO_NOMBRE = "Emision";
	public static final String PROCESO_ALEAS = "Emision";
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
		return plantilla;
	}

	@Override
	public String definirProximoDueno(Proceso proceso) throws Exception {
		return null;
	}

}