package pe.com.soul.cartaFianza.web.controller;

import javax.servlet.annotation.WebServlet;

import pe.com.soul.cartaFianza.web.emisionCartaFianzaV1.util.EmisionCartaFianzaV1Util;
import pe.com.soul.core.web.util.ProcesoUtil;

@WebServlet("/portal/emisionCartaFianzaV1")
public class EmisionCartaFianzaV1 extends PreEmisionCartaFianzaV1 {
	
	private static final long serialVersionUID = 1L;

	@Override
	public ProcesoUtil getProcesoUtil() {
		return new EmisionCartaFianzaV1Util();
	}
	
}
