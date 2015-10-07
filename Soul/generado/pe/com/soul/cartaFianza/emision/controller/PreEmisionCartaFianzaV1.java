package pe.com.soul.cartaFianza.emision.controller;

import javax.ejb.EJB;

import pe.com.soul.cartaFianza.emision.EmisionCartaFianzaServiceLocal;
import pe.com.soul.cartaFianza.emision.controller.util.EmisionCartaFianzaV1Util;
import pe.com.soul.core.servicio.BaseProcesoServicio;
import pe.com.soul.core.web.controller.BaseProcesoController;
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
