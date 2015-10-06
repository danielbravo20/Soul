package pe.com.soul.cartaFianza.web.controller;

import javax.ejb.EJB;

import pe.com.soul.cartaFianza.proceso.EmisionCartaFianzaServiceLocal;
import pe.com.soul.cartaFianza.web.emisionCartaFianzaV1.util.EmisionCartaFianzaV1Util;
import pe.com.soul.core.proceso.servicio.BaseProcesoServicio;
import pe.com.soul.core.proceso.web.controller.BaseProcesoController;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class PreEmisionCartaFianzaV1 extends BaseProcesoController{

	private static final long serialVersionUID = 1L;
	
	@EJB
	EmisionCartaFianzaServiceLocal emisionCartaFianzaServiceLocal;
	
	@Override
	public ProcesoUtil getProcesoUtil() {
		return new EmisionCartaFianzaV1Util();
	}
	
	@Override
	public BaseProcesoServicio getBaseProcesoService() {
		return this.emisionCartaFianzaServiceLocal;
	}
	

}
