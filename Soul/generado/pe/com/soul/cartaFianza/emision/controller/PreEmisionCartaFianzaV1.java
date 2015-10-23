package pe.com.soul.cartaFianza.emision.controller;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.cartaFianza.bean.Cliente;
import pe.com.cartaFianza.emision.EmisionCartaFianzaServiceLocal;
import pe.com.cartaFianza.bean.Solicitud;
import pe.com.soul.cartaFianza.emision.controller.util.EmisionCartaFianzaV1Util;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.servicio.BaseProcesoServicio;
import pe.com.soul.core.web.bean.Respuesta;
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
	
	@Override
	protected Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception {
		
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigo(new Long(10001));
		//solicitud.setMonedaFianza("USD");
		//solicitud.setMontoFianza(new BigDecimal(100000));
		//solicitud.setTipoFianza("LIC");
		
		Respuesta respuesta = new Respuesta();
		respuesta.setResultado(true);
		respuesta.setRespuesta(solicitud);
		
		return respuesta;
	}
	
	@Override
	protected Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setTipoDocumento("RUC");
		cliente.setNumeroDocumento("1000115452");
		cliente.setRazonSocial("LA RAZON SOCIAL");
		
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigo(new Long(10001));
		//solicitud.setMonedaFianza("USD");
		//solicitud.setMontoFianza(new BigDecimal(100000));
		//solicitud.setTipoFianza("LIC");
		//solicitud.setCliente(cliente);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setResultado(true);
		respuesta.setRespuesta(solicitud);
		
		return respuesta;
	}
	
}
